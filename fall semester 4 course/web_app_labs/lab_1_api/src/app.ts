import 'reflect-metadata';

import dotenv from 'dotenv';
import express from 'express';
import bodyParser from 'body-parser';
import cors from 'cors';
import { createConnection } from 'typeorm';

import { Routes } from './routes';
import Logger from './utils/Logger';

createConnection().then(async connection => {
    dotenv.config();

    // await connection.runMigrations();

    const server = express();
    const port = process.env.PORT || 3080;

    server.use(bodyParser.urlencoded({
        extended: true
    }));

    server.use(bodyParser.json());
    server.use(express.json());
    server.use(cors());

    server.use('/api', Routes);

    const http = require('http');
    const app = http.Server(server);

    Logger.Info('Database connected');

    app.listen(port, () => {
        Logger.Info(`Server listening on the port: ${port}`);
    });
}).catch(error => {
    Logger.Error(`Cant connect database ${error.toString()}`)
    process.exit(2);
});
