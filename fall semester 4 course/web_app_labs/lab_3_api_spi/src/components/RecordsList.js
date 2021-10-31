import React, { useEffect, useState } from 'react';

import { Button } from '@mui/material';

import Record from './Record';
import CreateRecordModal from './CreateRecordModal';

const RecordsList = (props) => {

    const [newRecordDialogIsOpen, openNewRecordDialog] = useState(false);

    useEffect(() => {
        console.log('record_list_rerender', props.records);
    })

    return (
        <div style={{width: '100%', display: 'flex', justifyContent: 'center', alignItems: 'center', flexDirection: 'column'}}>
            <CreateRecordModal
                open={newRecordDialogIsOpen}
                onClose={() => openNewRecordDialog(false)}
                onCreate={props.onCreate}
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
                {props.records.map((value) => {
                    console.log(value);
                    return(
                        <div style={{margin: '1%', width: '40%'}}>
                            <Record
                                key = {value.id}
                                record={value}
                                onDelete={props.onDelete}
                            />
                        </div>
                    );
                })}
            </div>
        </div>
    );
}

export default RecordsList;
