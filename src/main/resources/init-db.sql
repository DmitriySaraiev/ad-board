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
