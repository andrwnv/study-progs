import axios from 'axios';

const ip = '127.0.0.1';

const createUserRequest = (firstName, secondName, email, username, password) => {
    return axios({
        method: 'POST',
        url: `http://${ip}/user/create`,
        data: {
            firstName: firstName,
            lastName: secondName,
            password: password,
            eMail: email,
            username: username
        }
    })
}

const getAllUsersRequest = () => {
    return axios({
        method: 'GET',
        url: `http://${ip}:3010/user`
    })
}

const deleteUserRequest = (id) => {
    return axios({
        method: 'DELETE',
        url: `http://${ip}:3010/user/${id}`
    })
}

const updateUserRequest = (id, firstName, secondName, password) => {
    return axios({
        method: 'PATCH',
        url: `http://${ip}:3010/user/update`,
        data: {
            id: id,
            firstName: firstName,
            lastName: secondName,
            password: password
        }
    })
}

export default {getAllUsersRequest, createUserRequest, deleteUserRequest, updateUserRequest};
