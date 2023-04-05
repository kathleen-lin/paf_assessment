create database bgg;

use bgg;

create table user(
	user_id char(8) not null,
    username varchar(50) not null,
    name varchar(50),
    constraint user_pk primary key (user_id)
);

create table task(
	task_id smallint auto_increment not null,
    description varchar(255) not null,
    priority int not null,
    due_date varchar(50) not null,
    user_id char(8) not null,
    constraint task_pk primary key (task_id),
	constraint task_user_fk foreign key (user_id) references user (user_id),
    check (priority in (1,2,3))
);