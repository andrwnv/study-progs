import React, { useState } from 'react';

import { Button } from '@mui/material';

import Record from './Record';
import CreateRecordModal from './CreateRecordModal';

export default function RecordsList(_) {
    const [newRecordDialogIsOpen, openNewRecordDialog] = useState(false);

    const [records, updateRecords] = useState([
        {id: 1, title: 'test title', done: false, description: 'some desc text'},
        {id: 2, title: 'test title', done: false, description: 'some desc text'},
        {id: 3, title: 'test title', done: false, description: 'some desc text'},
        {id: 4, title: 'test title', done: false, description: 'some desc text'},
    ]);

    let id = 5;

    const onCreate = (title, desc, done) => {
        records.push({id: id, title: title, description: desc, done: done});
        updateRecords(records);

        id++;
    }

    return (
        <div style={{width: '100%', display: 'flex', justifyContent: 'center', alignItems: 'center', flexDirection: 'column'}}>
            <CreateRecordModal
                open={newRecordDialogIsOpen}
                onClose={() => openNewRecordDialog(false)}
                onCreate={onCreate}
            />
            <Button
                variant="outlined"
                style={{marginTop: '3%', marginBottom: '2%'}}
                onClick={() => openNewRecordDialog(true)}
            >
                Create record
            </Button>

            <div
                style={{
                    display: 'flex',
                    width: '80%',
                    flexWrap: 'wrap',
                    justifyContent: 'center',
                    alignItems: 'center',
                    paddingBottom: '3%',
                    borderBottom: '1mm ridge #ffffff'
                }}>
                {records.map(value => {
                    return(
                        <div style={{margin: '1%', width: '40%'}}>
                            <Record
                                title={value.title}
                                done={value.done}
                                description={value.description}
                                onDelete={() => { updateRecords(records.filter(rec => rec.id !== value.id)); }}
                            />
                        </div>
                    );
                })}
            </div>
        </div>
    );
}
