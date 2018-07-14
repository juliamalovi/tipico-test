import NotesAdd from './NotesAdd';

it('renders without crashing', () => {
  const wrapper = mount(<NotesAdd />);
  const input = wrapper.find('#form-text1');
  expect(input.prop('value')).toBe('aaa');
  
  wrapper.instance().author.value = "abc";
   console.log(wrapper.instance().author.value); 
   console.log(input.prop('value')); 

  //const inputAfter = wrapper.find('#form-text1');
  //expect(input.prop('value')).toBe('after');


  /*inputFiels.instance().value = "correctUsername"
   inputFiels.simulate('change');
   const text =  inputFiels.props().value;
 
   expect(text).toEqual("asd");*/
});
