create table user
(
    id          bigint    default not null auto_increment,
    csid        varchar(50) not null,
    e_name      varchar(50) not null,
    c_name      varchar(50) not null,
    submit_time timestamp default null,
    score       TINYINT   default -1

);

create table quiz
(
    id      bigint default not null auto_increment,
    index   TINYINT not null,
    context varchar(500)

);

create table quiz_option
(
    id             bigint default not null auto_increment,
    question_index TINYINT      not null,
    index          TINYINT      not null,
    context        varchar(500) not null,
    is_answer      bit(1)

);