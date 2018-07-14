import React, { Component } from 'react';
import {NotificationContainer, NotificationManager} from 'react-notifications';

class NotesAdd extends Component {
	constructor(props) {
	  super(props);
	  this.handleSubmit = this.handleSubmit.bind(this);
	}
	componentDidMount(){
	}

	handleSubmit(e) {
	    e.preventDefault();
        const data ={"text": this.content.value,"title": this.title.value,"author": this.author.value};
        console.log(JSON.stringify(data));
        fetch('http://localhost:8090/api/add-note', {
            method: 'POST',
            headers: {'Content-Type':'application/json'},
            body: JSON.stringify(data)
        }).then((response)=>{
            NotificationManager.success('Success message', 'Note has been added succesfully!');
            this.props.refreshList(0);
            this.content.value = '';
            this.title.value = '';
            this.author.value = '';
        }).catch((error) =>{
            console.error(error);
            NotificationManager.error('Error message', 'Impossible to add a note!');
        });
    }

  render() {
    return (
    	<div className="row">
     	    <div className="col-md-4 col-sm-5 sidebar">
                <h4>Add a new note:</h4>
                <form className="form-class" id="con_form" onSubmit={this.handleSubmit}>
                    <div className="row">
                        <div className="col-sm-6">
                            <input type="text" id="form-text1" ref={(ref) => {this.author = ref}} name="author" placeholder="Author" value="aaa"/>
                        </div>
                        <div className="col-sm-12">
                            <input type="text" ref={(ref) => {this.title = ref}}  name="title" placeholder="Title"/>
                            <textarea  maxLength="100" name="content"  ref={(ref) => {this.content = ref}} placeholder="Message"></textarea>
                            <button className="site-btn">send</button>
                        </div>
                    </div>
                </form>
          	</div>
          	<NotificationContainer/>
        </div>
    );
  }
}

export default NotesAdd;
