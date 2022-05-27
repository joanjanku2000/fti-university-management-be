
CREATE TABLE public.location
(
    id integer NOT NULL DEFAULT nextval('location_id_seq'::regclass),
    name character varying(64) COLLATE pg_catalog."default" NOT NULL,
    active boolean,
    CONSTRAINT location_pkey PRIMARY KEY (id)
);

CREATE TABLE public.course
(
    id integer NOT NULL DEFAULT nextval('course_id_seq'::regclass),
    name character varying(64) COLLATE pg_catalog."default" NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    start_time timestamp without time zone,
    end_time timestamp without time zone,
    location_id bigint,
    active boolean,
    CONSTRAINT course_pkey PRIMARY KEY (id),
    CONSTRAINT loc_fk FOREIGN KEY (location_id)
        REFERENCES public.location (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.university_user
(
    id integer NOT NULL DEFAULT nextval('university_user_id_seq'::regclass),
    name character varying(64) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(64) COLLATE pg_catalog."default" NOT NULL,
    birthday timestamp without time zone NOT NULL,
    id_number character varying(12) COLLATE pg_catalog."default" NOT NULL,
    gender integer NOT NULL,
    picture text COLLATE pg_catalog."default",
    role integer,
    registration_date timestamp without time zone,
    email character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    active boolean,
    CONSTRAINT university_user_pkey PRIMARY KEY (id),
    CONSTRAINT email_unique UNIQUE (email)
);

CREATE TABLE public.course_student
(
    id integer NOT NULL DEFAULT nextval('course_student_id_seq'::regclass),
    joined_on timestamp without time zone NOT NULL,
    left_on timestamp without time zone,
    course_id bigint NOT NULL,
    student_id bigint NOT NULL,
    active boolean,
    CONSTRAINT course_student_pkey PRIMARY KEY (id),
    CONSTRAINT course_fk FOREIGN KEY (course_id)
        REFERENCES public.course (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_fk FOREIGN KEY (student_id)
        REFERENCES public.university_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
;


CREATE TABLE public.friendship
(
    id integer NOT NULL DEFAULT nextval('friendship_id_seq'::regclass),
    sender_id bigint NOT NULL,
    receiver_id bigint NOT NULL,
    status integer NOT NULL,
    active boolean,
    CONSTRAINT friendship_pkey PRIMARY KEY (id),
    CONSTRAINT receiver FOREIGN KEY (receiver_id)
        REFERENCES public.university_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT sender FOREIGN KEY (sender_id)
        REFERENCES public.university_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE TABLE public.university_comment
(
    id integer NOT NULL DEFAULT nextval('university_comment_id_seq'::regclass),
    comment_string text COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp without time zone NOT NULL,
    parent_id bigint,
    type integer NOT NULL,
    user_id bigint NOT NULL,
    course_id bigint,
    active boolean,
    CONSTRAINT university_comment_pkey PRIMARY KEY (id),
    CONSTRAINT course_fkk FOREIGN KEY (course_id)
        REFERENCES public.course (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT replying_to FOREIGN KEY (parent_id)
        REFERENCES public.university_comment (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_fkk FOREIGN KEY (user_id)
        REFERENCES public.university_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE TABLE public.likes
(
    id integer NOT NULL DEFAULT nextval('likes_id_seq'::regclass),
    user_id bigint NOT NULL,
    comment_id bigint NOT NULL,
    active boolean,
    CONSTRAINT likes_pkey PRIMARY KEY (id),
    CONSTRAINT comment_fkdk FOREIGN KEY (comment_id)
        REFERENCES public.university_comment (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_fkdk FOREIGN KEY (user_id)
        REFERENCES public.university_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);




CREATE TABLE public.notification_user
(
    id integer NOT NULL DEFAULT nextval('notification_user_id_seq'::regclass),
    message character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_id bigint NOT NULL,
    type integer,
    notifier bigint,
    active boolean,
    CONSTRAINT notification_user_pkey PRIMARY KEY (id),
    CONSTRAINT notification_user_university_user_id_fk FOREIGN KEY (notifier)
        REFERENCES public.university_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_fkkk FOREIGN KEY (user_id)
        REFERENCES public.university_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
;




