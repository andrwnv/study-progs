import express from 'express';
import { getRepository } from 'typeorm';

import { UserEntity } from '../entity/user.entity';

class UserController {
    async index(req: express.Request, res: express.Response): Promise<void> {
        try {
            const repo = getRepository(UserEntity);
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
            const repo = getRepository(UserEntity);
            const promise = repo.save(repo.manager.create(UserEntity, {
                firstName: req.body.firstName,
                lastName: req.body.lastName,
                age: req.body.age,
                eMail: req.body.eMail
            }));

            promise.then(data => {
                res.status(200).json({
                    data: data,
                    error: true
                });
            }).catch(_ => {
                res.status(409).json({
                    data: 'Duplicate or insufficient data',
                    error: true
                });
            });
        } catch(err) {
            res.status(500).json({ data: err, error: true });
        }
    }

    async update(req: express.Request, res: express.Response): Promise<void> {
        const repo = getRepository(UserEntity);

        const properties = await repo.find({
            id: parseInt(req.params.id)
        });

        if ( !properties.length ) {
            res.status(404).json({ data: 'User data not found', error: true })
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
                data: 'User data not acceptable',
                error: true
            });
        });
    }

    async delete(req: express.Request, res: express.Response): Promise<void> {
        try {
            const repo = getRepository(UserEntity);

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
            res.status(500).json({ data: err, error: true });
        }
    }

    async info(req: express.Request, res: express.Response): Promise<void> {
        try {
            const repo = getRepository(UserEntity);
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
                    data: 'User not found',
                    error: true
                });
            });
        } catch(err) {
            res.status(500).json({ data: err, error: true });
        }
    }
}

export const UserCtrl = new UserController();
