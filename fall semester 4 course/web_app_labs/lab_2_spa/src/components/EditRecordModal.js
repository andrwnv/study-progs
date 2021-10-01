import React, { useState } from 'react';

import { Dialog, DialogTitle, DialogContent, DialogActions, TextField, Button, DialogContentText } from '@mui/material';

export default function EditRecordModal({title, description, open, onClose, onSave}) {
    const [editableTitle, editTitle] = useState(title);
    const [editableDesc, editDesc] = useState(description);

    return (
        <Dialog open = {open} onClose = {onClose}>
            <DialogTitle>Editing</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    Edit your record
                </DialogContentText>
                <TextField
                    autoFocus
                    margin = 'dense'
                    id = 'name'
                    label = 'Title'
                    type = 'text'
                    fullWidth
                    variant = 'standard'
                    value={editableTitle}
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
                    value={editableDesc}
                    onChange={event => editDesc(event.target.value)}
                />
            </DialogContent>
            <DialogActions>
                <Button onClick = {onClose}>Cancel</Button>
                <Button onClick = {() => {
                    onSave(editableTitle, editableDesc);
                    onClose();
                }}>Save</Button>
            </DialogActions>
        </Dialog>
    );
}
