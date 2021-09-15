import express from 'express';

import UserRouter from './user.router';
import RecordsRouter from './records.router';

export const Routes = express.Router();

Routes.get('/', (_, res: express.Response) => {
    res.status(200).json('Welcome to LAB_1 API!');
});

Routes.use('/user', UserRouter);
Routes.use('/record', RecordsRouter);
