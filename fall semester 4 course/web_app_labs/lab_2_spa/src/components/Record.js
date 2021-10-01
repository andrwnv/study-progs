import React, { useState } from 'react';

import { CardContent, Typography, CardActions, Box, Checkbox, IconButton, Card } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import EditRecordModal from './EditRecordModal';

export default function Record({id, title, description, done, onDelete}) {
    const [editDialogIsOpen, openEditDialog] = useState(false);

    const [recordTitle, updateTitle] = useState(title);
    const [recordDesc, updateDesc] = useState(description);
    const [recordDone, updateDone] = useState(done);

    return (
        <Box id={id}>
            <EditRecordModal
                title={recordTitle}
                description={recordDesc}
                open={editDialogIsOpen}
                onClose={() => openEditDialog(false)}
                onSave={(newTitle, newDesc) => {
                    updateTitle(newTitle);
                    updateDesc(newDesc);
                }}
            />
            <Card variant="outlined">
                <React.Fragment>
                    <CardContent>
                        <Typography sx={{ fontSize: 20, fontWeight: 600, mb: 1.5 }} color="text.secondary">
                            {recordDone ? <s>{recordTitle}</s> : recordTitle}
                        </Typography>
                        <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
                            {recordDesc}
                        </Typography>
                    </CardContent>

                    <CardActions>
                        <IconButton size="small" onClick={() => { openEditDialog(!editDialogIsOpen); }}>
                            <EditIcon />
                        </IconButton>
                        <IconButton size="small" onClick={onDelete}>
                            <DeleteIcon />
                        </IconButton>
                        <Checkbox style={{marginLeft: 'auto'}} onClick={() => updateDone(!recordDone)}/>
                    </CardActions>
                </React.Fragment>
            </Card>
        </Box>
    );
}
