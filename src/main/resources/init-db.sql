create table role
(
    id   bigint auto_increment
        primary key,
    name varchar(100) not null
);

create table user
(
    id       bigint auto_increment
        primary key,
    name     varchar(200) not null,
    login    varchar(200) not null,
    password varchar(200) not null
);

create table user_role
(
    id      bigint auto_increment
        primary key,
    user_id bigint not null,
    role_id bigint null,
    constraint user_role_role_id_fk
        foreign key (role_id) references role (id),
    constraint user_role_user_id_fk
        foreign key (user_id) references user (id)
);

create table ad
(
    id          bigint auto_increment
        primary key,
    title       varchar(500) not null,
    description text         not null,
    user_id     bigint       not null,
    constraint ad_user_id_fk
        foreign key (user_id) references user (id)
);

insert into user(username, password) values ('admin', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhc2QxMjMiLCJleHAiOjE2MDM0MDA0MDB9.IX6XKzNcV1CVBrgBI5lsUWtogJJmbN20H3Ssz9ECfg3uiNLcRk-PohxOhaXrmhzj9JkOHZPvRzCjR4Pv7Z9XCQ');

insert into role(name) values ('ADMIN');
insert into role(name) values ('USER');

insert into user_role(user _id, role_id) values (1, 1);