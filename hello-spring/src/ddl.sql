drop table if exists member CASCADE;
create table member
(
    id   bitint generated by default as identity,
    name varchar(225),
    primary key (id)
);