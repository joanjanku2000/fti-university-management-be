-- MYSQL DIALECT
create table location
(
    ID     bigint auto_increment
        primary key,
    name   varchar(64) not null,
    active tinyint(1)  null
);

create table course
(
    ID          bigint auto_increment
        primary key,
    name        varchar(64)  not null,
    description varchar(255) null,
    start_time  timestamp    null,
    end_time    timestamp    null,
    active      tinyint(1)   null,
    location_id bigint       null,
    constraint loc_fk
        foreign key (location_id) references location (ID)
);

create table university_user
(
    ID                bigint auto_increment
        primary key,
    name              varchar(64)  not null,
    last_name         varchar(64)  not null,
    birthday          timestamp    not null ,
    ID_NUMBER         varchar(12)  not null,
    gender            int          not null,
    picture           text         null,
    role              int          null,
    registration_date timestamp    null ,
    active            varchar(255) null,
    email             varchar(255) null,
    password          varchar(255) null,
    constraint email_UNIQUE
        unique (email)
);

create table course_student
(
    ID         serial
        primary key,
    joined_on  timestamp  not null,
    left_on    timestamp  null,
    course_id  bigint     not null,
    student_id bigint     not null,
    active     tinyint(1) null,
    constraint course_fk
        foreign key (course_id) references course (ID),
    constraint user_fk
        foreign key (student_id) references university_user (ID)
);

create table friendship
(
    ID          serial
        primary key,
    sender_id   bigint     not null,
    receiver_id bigint     not null,
    status      int        not null,
    active      tinyint(1) null,
    constraint receiver
        foreign key (receiver_id) references university_user (ID),
    constraint sender
        foreign key (sender_id) references university_user (ID)
);

create table notification_user
(
    ID       serial
        primary key,
    message  varchar(255) not null,
    user_id  bigint       not null,
    active   tinyint(1)   null,
    type     int          null,
    notifier bigint       null,
    constraint notification_user_university_user_ID_fk
        foreign key (notifier) references university_user (ID),
    constraint user_fkkk
        foreign key (user_id) references university_user (ID)
);

create table university_comment
(
    ID             serial
        primary key,
    comment_string text       not null,
    created_at     timestamp  not null,
    parent_id      bigint     null,
    type           int        not null,
    user_id        bigint     not null,
    course_id      bigint     null,
    active         tinyint(1) null,
    constraint course_FKk
        foreign key (course_id) references course (ID),
    constraint replying_to
        foreign key (parent_id) references university_comment (ID),
    constraint user_fkk
        foreign key (user_id) references university_user (ID)
);

create table likes
(
    ID         serial
        primary key,
    user_id    bigint     not null,
    comment_id bigint     not null,
    active     tinyint(1) null,
    constraint comment_FKdk
        foreign key (comment_id) references university_comment (ID),
    constraint user_fkdk
        foreign key (user_id) references university_user (ID)
);