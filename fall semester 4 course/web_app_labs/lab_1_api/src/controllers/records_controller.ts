import express from 'express';
import { getRepository } from 'typeorm';

import { RecordEntity } from '../entity/record.entity';
import { UserEntity } from '../entity/user.entity';

class RecordsController {
    async index(req: express.Request, res: express.Response): Promise<void> {
        try {
            const repo = getRepository(RecordEntity);
            res.status(200).json({
                data: await repo.find({}),
                error: false
            });
        } catch(err) {
            res.status(500).json({ data: null, error: true });
        }
    }

    async create(req: express.Request, res: express.Response): Promise<void> {
        try {
            const user = await getRepository(UserEntity).find({
                id: parseInt(req.body.userId)
            });

            if ( !user.length ) {
                res.status(404).json({
                    data: 'User not found',
                    error: true
                });

                return;
            }

            const repo = getRepository(RecordEntity);
            const promise = repo.save(repo.manager.create(RecordEntity, {
                user: user[0],
                done: false,
                ...req.body,
            }));

            promise.then(data => {
                res.status(200).json({
                    data: data,
                    error: false
                });
            }).catch(_ => {
                console.log(_);
                res.status(400).json({
                    data: 'Bad request',
                    error: true
                });
            });
        } catch(err) {
            res.status(500).json({ data: null, error: true });
        }
    }

    async update(req: express.Request, res: express.Response): Promise<void> {
        try {
            const repo = getRepository(RecordEntity);

            const properties = await repo.find({
                id: parseInt(req.params.id)
            });

            if ( !properties.length ) {
                res.status(404).json({ data: 'Record data not found', error: true })
                return;
            }

            const promise = repo.save({
                ...properties[0],
                ...req.body
            });

            promise.then(data => {
                res.status(200).json({
                    data: data,
                    error: false
                });
            }).catch(_ => {
                res.status(406).json({
                    data: 'Record not acceptable',
                    error: true
                });
            });
        } catch(err) {
            res.status(500).json({ data: null, error: true });
        }
    }

    async delete(req: express.Request, res: express.Response): Promise<void> {
        try {
            const repo = getRepository(RecordEntity);

            if ( req.query.id === undefined ) {
                res.status(400).json({ data: null, error: true });
                return;
            }

            if ( typeof req.query.id === 'string' ) {
                repo.delete({
                    id: parseInt(req.query.id)
                }).then(() => {
                    res.status(200).json({ error: false });
                }).catch((err) => {
                    res.status(400).json({ data: err, error: true });
                });
            }
        } catch(err) {
            res.status(500).json({ data: null, error: true });
        }
    }

    async info(req: express.Request, res: express.Response): Promise<void> {
        try {
            const repo = getRepository(RecordEntity);

            const promise = repo.find({
                id: parseInt(req.params.id)
            });

            promise.then(data => {
                res.status(200).json({
                    data: data[0],
                    error: true
                });
            }).catch(_ => {
                res.status(404).json({
                    data: 'Record not found',
                    error: true
                });
            });
        } catch(err) {
            res.status(500).json({ data: null, error: true });
        }
    }
}

export const RecordsCtrl = new RecordsController();
