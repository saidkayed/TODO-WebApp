import React from 'react'


const TodoMarkup = (props) => {
    return (
        <div style={{ display: "flex" }}>


            <div style={{ paddingLeft: '20px', paddingRight: '20px' }} >
                <h2>{props.isEdit ? "Edit Todo" : "New Todo"}</h2>

                <input type="text" placeholder="Title" onChange={props.changeTitle} value={props.isEdit ? props.title : null}></input>
                <br/>
                <br/>
                <input type='date' onChange={props.changeDate}></input>
                <br/>
                <br/>
                <textarea style={{ resize: 'none', }} placeholder="Write your todo" maxLength="500" required cols="50" rows="20" onChange={props.changeBio} value={props.isEdit ? props.bio : null}>
                </textarea>
                <br></br>
                <input
                    onClick={props.handleClick}
                    type="submit"
                    value="Submit"
                    disabled={props.title.length === 0 || props.bio.length === 0 ? true : false} >
                </input>
            </div>

            <div>
                <h2>TODO LIST</h2>

                <div style={{ overflow: 'scroll', height: '400px', width: '270px' }}>
                    {props.data.map((data) =>
                        <div style={{ borderWidth: "1px", borderStyle: "solid", borderColor: "black", width: "250px", textAlign: "center", marginBottom: "20px" }}>
                            <h3>{data.task}</h3>
                            <p>{data.bio}</p>
                            <p>{data.date}</p>
                            <input onClick={() => props.deleteTodo(data.id)} type="submit" value="Delete"></input>
                            <input onClick={() => props.handleEditClick(data.id, data.task, data.bio)} type="submit" value="Edit"></input>
                        </div>

                    )}
                </div>
            </div>


        </div>


    )
}



export default TodoMarkup