create table user
(
    id     bigint default not null auto_increment,
    csid varchar(50) not null,
    e_name varchar(50) not null,
    c_name varchar(50) not null,
    involved bit(1) not null default 0,
    score TINYINT default -1

);

insert into user(csid,e_name,c_name,involved) values('test','test1','test2',false);