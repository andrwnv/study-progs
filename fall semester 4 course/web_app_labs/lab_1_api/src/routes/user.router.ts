import express from 'express';

import { UserCtrl } from '../controllers/user_controller';

const UserRouter = express.Router();

UserRouter.get('/all_users', UserCtrl.index);
UserRouter.post('/create', UserCtrl.create);
UserRouter.delete('/delete', UserCtrl.delete);
UserRouter.put('/update/:id', UserCtrl.update);
UserRouter.get('/:id', UserCtrl.info);

export default UserRouter;
