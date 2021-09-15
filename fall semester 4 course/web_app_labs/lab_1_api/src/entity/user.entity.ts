import { Entity, PrimaryGeneratedColumn, Column, OneToMany, Index } from 'typeorm';
import { RecordEntity } from './record.entity';

@Entity()
export class UserEntity {
    @PrimaryGeneratedColumn()
    id!: number;

    @Column({ type: 'text', nullable: false })
    firstName!: string;

    @Column({ type: 'text', nullable: false })
    lastName!: string;

    @Column({ type: 'integer', nullable: false })
    age!: number;

    @Column({ type: 'text', nullable: false })
    @Index({ unique: true })
    eMail!: string

    @OneToMany(() => RecordEntity, record => record.user)
    records?: RecordEntity[]
}
