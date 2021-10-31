import React, { useState } from 'react';

import { Dialog, DialogTitle, DialogContent, DialogActions, TextField, Button, DialogContentText } from '@mui/material';

export default function CreateRecordModal({open, onClose, onCreate}) {
    const [firstName, setFirstName] = useState("");
    const [secondName, setSecondName] = useState("");
    const [eMail, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const isEmailRegex = /\S+@\S+\.\S+/;

    return (
        <Dialog open = {open} onClose = {onClose}>
            <DialogTitle>Create</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    Create user
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
                    label = 'email'
                    type = 'text'
                    fullWidth
                    variant = 'standard'
                    value={eMail}
                    error={!isEmailRegex.test(eMail)}
                    placeholder={"Enter your email..."}
                    onChange={event => setEmail(event.target.value)}
                />
                <TextField
                    autoFocus
                    margin = 'dense'
                    id = 'name'
                    label = 'password'
                    type = 'password'
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
                    onCreate(firstName, secondName, eMail, password);
                    onClose();
                }}>Save</Button>
            </DialogActions>
        </Dialog>
    );
}

