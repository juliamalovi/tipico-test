import React, { Component } from 'react';
import NotesAdd from './NotesAdd';
import {NotificationContainer, NotificationManager} from 'react-notifications';

class Content extends Component {
    constructor(props) {
	  super(props);
	  this.state = { notes: [], currentPage: 0, totalPages:0, pageSize: 10 };
	  this.orderDir = 'DESC';
	  this.orderByField = 'createDate';
	}
	componentDidMount(){
	   this.fetchNotes(this.state.currentPage);
	}
	fetchNotes(currentPage){
	    const url = (page,size, orderDirection, orderBy) =>
        `http://localhost:8090/api/notes?p=${page}&s=${size}&d=${orderDirection}&o=${orderBy}`;
	    fetch(url(currentPage,this.state.pageSize, this.orderDir,this.orderByField))
	   .then(response=>response.json())
	   .then(data=>this.setState({notes:data.notes,currentPage:data.currentPage, totalPages: data.totalPages,isLoading:false}))
	    .catch((err)=>{
              console.error('err', err);
              NotificationManager.error('Error message', 'Impossible to view notes!');
        });
	}

    deleteNote = (e, id) => {
        e.preventDefault();
        if (e.target) {
          fetch('http://localhost:8090/api/notes/'+id, {
                        method: 'DELETE'
                    }).then((response)=>{
                        NotificationManager.success('Success message', 'Note has been deleted succesfully!');
                        this.fetchNotes(this.state.currentPage);
                    }).catch((error) =>{
                        console.error(error);
                        NotificationManager.error('Error message', 'Impossible to delete a note!');
                    });
        }
    }

	handleChangeOrderField = (e) => {
        if (e.target) {
            this.orderByField = e.target.value;
            console.log(`Selected: ${e.target.value}`);
            this.fetchNotes(0);
        }
    }

    handleChangeOrderDirection = (e) => {
        if (e.target) {
            this.orderDir = e.target.value;
            console.log(`Selected: ${e.target.value}`);
            this.fetchNotes(0);
        }
    }

	changePage = (e, i) =>{
       e.preventDefault();
       this.fetchNotes(i);
	}

	pagination(){
	    const paginationArr = [];
	    new Array(this.state.totalPages).fill(0).map(( zero,i ) =>{
	        const index = i+1;
	        if(this.state.currentPage === i)
    		    paginationArr.push(<a className="active" href="" key={index} onClick={(e) => this.changePage(e,i)}>0{index}.</a>);
    		else
    			paginationArr.push(<a href="" key={index} onClick={(e) => this.changePage(e,i)}>0{index}.</a>);
        });
	    return (
	        <div className="page-pagination">
	            {paginationArr}
			</div>
	    );
	}

    render() {
        const{notes,currentPage,totalPages,orderDir,orderByField} = this.state;
        return (
            <div>
                <div className="page-top-section">
                    <div className="overlay"></div>
                    <div className="container text-right">
                        <div className="page-info">
                            <h2>Notes list</h2>
                        </div>
                    </div>
                </div>

                <div className="page-section spad">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-8 col-sm-7 blog-posts">
                                <div className="element">
                                    {/*Filters section*/}
                                    <h3>Sort by</h3>
                                    <select onChange={this.handleChangeOrderField}>
                                        <option value="createDate">Date</option>
                                        <option value="title">Title</option>
                                        <option value="text">Content</option>
                                        <option value="author">Author</option>
                                    </select>

                                    <select onChange={this.handleChangeOrderDirection}>
                                        <option value="DESC">DESC</option>
                                        <option value="ASC">ASC</option>
                                    </select>

                                    {/*Notes section*/}
                                   <h1>Notes:</h1>

                                   {notes.map((note, i)=>
                                    <div className="post-item" key={i} data-id={note.id}>
                                        <div className="post-date">
                                            <h2>{note.day}</h2>
                                            <h3>{note.month}</h3>
                                        </div>
                                        <div className="post-content">
                                            <h2 className="post-title">{note.title}</h2>
                                            <div className="post-meta">
                                                <a>{note.author}</a>
                                            </div>
                                            <p>{note.text}</p>
                                            <div className="element">
                                                <div className="buttons">
                                                    <button className="site-btn btn-2 mr20" onClick={(e) => this.deleteNote(e,note.id)}>Delete</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    )}
                                </div>

                                 {/*Pagination block*/}
                                {this.pagination()}
                            </div>

                            {/*Sidebar with form to add a new note*/}
                            <NotesAdd refreshList={this.fetchNotes.bind(this)}/>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Content;
