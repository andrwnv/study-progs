import express from 'express';

import { RecordsCtrl } from '../controllers/records_controller';

const RecordsRouter = express.Router();

RecordsRouter.get('/all_records', RecordsCtrl.index);
RecordsRouter.post('/create', RecordsCtrl.create);
RecordsRouter.delete('/delete', RecordsCtrl.delete);
RecordsRouter.put('/update/:id', RecordsCtrl.update);
RecordsRouter.get('/:id', RecordsCtrl.info);

export default RecordsRouter;
