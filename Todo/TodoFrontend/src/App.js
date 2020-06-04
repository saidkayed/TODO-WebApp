import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';
import Todo from './pages/todo/Todo'

class App extends Component {
  constructor(props){
    super();
  }


render(){
  return (
<Todo/>
  );
}
}

export default App;
