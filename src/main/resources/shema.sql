drop table if exists task;
create table task
(
    due_date    datetime(6) null,
    task_id     bigint auto_increment
        primary key,
    description varchar(255) null,
    priority    enum ('HIGH', 'LOW', 'MEDIUM') null,
    status      varchar(255) null,
    title       varchar(255) null
);

drop table if exists comment;
create table comment
(
    cmt_id  bigint auto_increment
        primary key,
    date    datetime(6) null,
    task_id bigint null,
    comment tinytext null,
    constraint FKfknte4fhjhet3l1802m1yqa50
        foreign key (task_id) references task (task_id)
);

