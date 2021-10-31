import React, { useState } from 'react';

import { CardContent, Typography, CardActions, Box, Checkbox, IconButton, Card } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import EditRecordModal from './EditRecordModal';
import api from '../api/UserAPI';

const Record = ({record, onDelete}) => {
    const [editDialogIsOpen, openEditDialog] = useState(false);

    const id = record.id;

    const [firstName, setFirstName] = useState(record.firstName);
    const [secondName, setSecondName] = useState(record.secondName);
    const [eMail, _] = useState(record.eMail);
    const [password, setPassword] = useState(record.password);

    return (
        <Box>
            <EditRecordModal
                record={record}
                open={editDialogIsOpen}
                onClose={() => openEditDialog(false)}
                onSave={(firstName, secondName, password) => {
                    api.updateUserRequest(id, firstName, secondName, password).then(res => {
                        if (res.status === 200) {
                            setFirstName(firstName)
                            setSecondName(secondName)
                            setPassword(password)
                        }
                    }).catch(err => {
                        alert(`Cant update user sry ${err.toString()}`)
                    });
                }}
            />
            <Card variant="outlined">
                <CardContent>
                    <Typography sx={{ fontSize: 12, fontWeight: 600, mb: 1.5 }} color="text.secondary">
                        {id}
                    </Typography>
                    <Typography sx={{ fontSize: 20, fontWeight: 600, mb: 1.5 }} color="text.secondary">
                        {`${firstName}   ${secondName}`}
                    </Typography>
                    <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
                        {`${eMail}   ${password}`}
                    </Typography>
                </CardContent>

                <CardActions>
                    <IconButton size="small" onClick={() => { openEditDialog(!editDialogIsOpen); }}>
                        <EditIcon />
                    </IconButton>
                    <IconButton size="small" onClick={() => {
                        onDelete(record.id)
                    }}>
                        <DeleteIcon />
                    </IconButton>
                </CardActions>
            </Card>
        </Box>
    );
}

export default Record;
