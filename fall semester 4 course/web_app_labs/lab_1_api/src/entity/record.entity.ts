import { Entity, PrimaryGeneratedColumn, ManyToOne, Column } from 'typeorm';
import { UserEntity } from './user.entity';

@Entity()
export class RecordEntity {
    @PrimaryGeneratedColumn()
    id!: number;

    @Column({ type: 'boolean', nullable: false, select: false })
    done!: boolean

    @Column({ type: 'text', nullable: false })
    title!: string

    @Column({ type: 'text', nullable: true })
    desc?: string

    @ManyToOne(() => UserEntity, user => user.records, {
        onDelete: 'CASCADE',
    })
    user!: UserEntity;
}
