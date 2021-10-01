import React, { useState } from 'react';

import { Dialog, DialogTitle, DialogContent, DialogActions, TextField, Button, DialogContentText } from '@mui/material';

export default function CreateRecordModal({open, onClose, onCreate}) {
    const [newTitle, editTitle] = useState("");
    const [newDesc, editDesc] = useState("");

    return (
        <Dialog open = {open} onClose = {onClose}>
            <DialogTitle>Editing</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    Create your record
                </DialogContentText>
                <TextField
                    autoFocus
                    margin = 'dense'
                    id = 'name'
                    label = 'Title'
                    type = 'text'
                    fullWidth
                    variant = 'standard'
                    value={newTitle}
                    placeholder={"Enter your title..."}
                    onChange={event => editTitle(event.target.value)}
                />
                <TextField
                    autoFocus
                    margin = 'dense'
                    id = 'name'
                    label = 'Description'
                    type = 'text'
                    fullWidth
                    variant = 'standard'
                    value={newDesc}
                    placeholder={"Enter your description..."}
                    onChange={event => editDesc(event.target.value)}
                />
            </DialogContent>
            <DialogActions>
                <Button onClick = {onClose}>Cancel</Button>
                <Button onClick = {() => {
                    onCreate(newTitle, newDesc, false);
                    onClose();
                }}>Save</Button>
            </DialogActions>
        </Dialog>
    );
}
