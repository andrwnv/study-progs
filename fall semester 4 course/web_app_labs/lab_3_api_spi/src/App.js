import RecordsList from './components/RecordsList';
import { Typography } from '@mui/material';
import { useEffect, useRef, useState } from 'react';
import api from './api/UserAPI';

let id = 4;

export default function App() {
    let [users, updateUserData] = useState([]);

    const handleRemove = (rec_id) => {
        api.deleteUserRequest(rec_id).then(res => {
            if (res.status === 200)
                updateUserData(users.filter((item) => item.id !== rec_id));
        }).catch(err => {
            alert(`Cant remove user sry ${err.toString()}`)
        })
    }

    function handleCreate(firstName, secondName, eMail, password) {
        updateUserData([...users, {
            id: id,
            firstName: firstName,
            secondName: secondName,
            eMail: eMail,
            password: password
        }]);

        id++;
    }

    useEffect(() => {
        api.getAllUsersRequest().then(res => {
            const resUsers = res.data.data;
            let users = []

            resUsers.forEach(user => {
                users.push({
                    id: user.id,
                    firstName: user.firstName,
                    secondName: user.lastName,
                    eMail: user.eMail,
                    password: user.password
                })
            })

            updateUserData(users);
        });
    }, []);

    return (
        <div
            style = {{
                width: '100%',
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                flexDirection: 'column'
            }}
        >
            <Typography sx = {{fontSize: 30, fontWeight: 700, mb: 1.5}} style = {{marginTop: '1.5%'}}>
                Users
            </Typography>

            <RecordsList
                records = {users}
                onCreate = {handleCreate}
                onDelete = {handleRemove}
            />
      </div>
    );
}
