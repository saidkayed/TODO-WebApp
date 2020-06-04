import React from 'react'


const TodoMarkup = (props) => {
    return (
        <div style={{ display: "flex", justifyContent: "center", alignItems: "center" }}>


            <div style={{ width: "33.33vw" }}>
                <h2>{props.isEdit ? "Edit Todo" : "New Todo"}</h2>

                <input type="text" placeholder="Title" onChange={props.changeTitle} value={props.isEdit ? props.title : null}></input>
                <br></br>
                <br></br>
                <textarea placeholder="Write your todo" maxLength="500" required cols="50" rows="20" onChange={props.changeBio} value={props.isEdit ? props.bio : null}>
                </textarea>
                <br></br>
                <input onClick={props.handleClick} type="submit" value="Submit"></input>
            </div>

            <div style={{ width: "33.33vw" }}>
                <h1>TODO LIST</h1>

                {props.data.map((data) =>
                    <div style={{ borderWidth: "1px", borderStyle: "solid", borderColor: "black", width: "250px", textAlign: "center", marginBottom: "20px" }}>
                        <h3>{data.task}</h3>
                        <p>{data.bio}</p>
                        <input onClick={() => props.deleteTodo(data.id)} type="submit" value="Delete"></input>
                        <input onClick={() => props.handleEditClick(data.id, data.task, data.bio)} type="submit" value="Edit"></input>
                    </div>

                )}

            </div>


        </div>


    )
}



export default TodoMarkup