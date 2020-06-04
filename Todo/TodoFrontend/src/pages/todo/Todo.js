import React, { Component } from 'react'
import TodoMarkup from './TodoMarkup'
import { getTODO, postTODO, deleteTODO, editTODO } from '../../api/Api'

class Todo extends Component {
    constructor(props) {
        super();

        this.state = {
            title: "q",
            bio: "a",
            data: [],
            isEdit: false,
            todo_id:""
        }

    }

    componentDidMount = () => {

        this.fetchTodoData()

        console.log(this.state.data)
    }


    fetchTodoData = async () => {
        await getTODO().then((res) => {
            this.setState({ data: res })
            console.log(this.state.data)
        })

    }


    deleteTodo = async (id) => {

        await deleteTODO(id)

        this.fetchTodoData()

    }

    
    handleEditClick = (id, etitle, ebio) => {
        this.setState({ isEdit: true, todo_id: id, title: etitle, bio: ebio})
        console.log(etitle)
        console.log(this.state.title)
        
    }



    handleClick = async () => {
        if (this.state.isEdit === false) {
            console.log("i was clicked")
            await postTODO(this.state.title, this.state.bio).then((res) => {
                console.log(res);
            })
        } else {
            await editTODO(this.state.todo_id, this.state.title, this.state.bio)
            this.setState({isEdit: false})
        }

        this.fetchTodoData()
    }

    changeTitle = (event) => {
        this.setState({ title: event.target.value })
        console.log(this.state.title)
    }

    changeBio = (event) => {
        this.setState({ bio: event.target.value })
        console.log(this.state.bio)
    }



    render() {
        return (
            <TodoMarkup
                title={this.state.title}
                bio={this.state.bio}
                isEdit={this.state.isEdit}
                handleClick={this.handleClick}
                changeBio={this.changeBio}
                changeTitle={this.changeTitle}
                data={this.state.data}
                deleteTodo={this.deleteTodo}
                handleEditClick={this.handleEditClick} />
        );
    }
}


export default Todo