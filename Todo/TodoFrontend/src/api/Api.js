const axios = require('axios');

export const getTODO = () => {

    return axios.get('http://localhost:8080/TodoBackend/api/todo')
        .then((res) => {
            // console.log(res.data)
            return res.data
        })
        .catch((err) => {
            console.log(err)
        })
}

export const postTODO = (title, bio) => {

    return axios.post('http://localhost:8080/TodoBackend/api/create', {
        task: title,
        bio, bio,
    })
        .then((res) => {
            // console.log(res.data)
            return res.data
        })
        .catch((err) => {
            console.log(err)
        })
}

export const deleteTODO = (id) => {

    return axios.delete("http://localhost:8080/TodoBackend/api/delete?id=" + id)
        .then((res) => {
            // console.log(res.data)
            return res.data
        })
        .catch((err) => {
            console.log(err)
        })
}

export const editTODO = (id, title, bio) => {

    return axios.put("http://localhost:8080/TodoBackend/api/edit/" + id, {
        task: title,
        bio, bio,
    })
        .then((res) => {
            // console.log(res.data)
            return res.data
        })
        .catch((err) => {
            console.log(err)
        })
}

