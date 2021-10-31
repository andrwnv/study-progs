import React, { useState } from 'react';

import { Dialog, DialogTitle, DialogContent, DialogActions, TextField, Button, DialogContentText } from '@mui/material';

export default function EditRecordModal({record, onSave, onClose, open}) {
    const [firstName, setFirstName] = useState(record.firstName);
    const [secondName, setSecondName] = useState(record.secondName);
    const [password, setPassword] = useState(record.password);

    return (
        <Dialog open = {open} onClose = {onClose}>
            <DialogTitle>Edit</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    Edit your record
                </DialogContentText>
                <TextField
                    autoFocus
                    margin = 'dense'
                    id = 'name'
                    label = 'First name'
                    type = 'text'
                    fullWidth
                    variant = 'standard'
                    value={firstName}
                    placeholder={"Enter your first name..."}
                    onChange={event => setFirstName(event.target.value)}
                />
                <TextField
                    autoFocus
                    margin = 'dense'
                    id = 'name'
                    label = 'Second name'
                    type = 'text'
                    fullWidth
                    variant = 'standard'
                    value={secondName}
                    placeholder={"Enter your second name..."}
                    onChange={event => setSecondName(event.target.value)}
                />
                <TextField
                    autoFocus
                    margin = 'dense'
                    id = 'name'
                    label = 'password'
                    type = 'text'
                    fullWidth
                    variant = 'standard'
                    value={password}
                    placeholder={"Enter your password..."}
                    onChange={event => setPassword(event.target.value)}
                />
            </DialogContent>
            <DialogActions>
                <Button onClick = {onClose}>Cancel</Button>
                <Button onClick = {() => {
                    onSave(firstName, secondName, password);
                    onClose();
                }}>Save</Button>
            </DialogActions>
        </Dialog>
    );
}

