INSERT INTO fti_uniman.location (ID, name, active) VALUES (1, 'UPT HALL', 1);
INSERT INTO fti_uniman.location (ID, name, active) VALUES (2, 'UPT HALL', 0);
INSERT INTO fti_uniman.location (ID, name, active) VALUES (3, 'UPT HALL', 0);
INSERT INTO fti_uniman.location (ID, name, active) VALUES (5, 'UPT HALL', 0);
INSERT INTO fti_uniman.location (ID, name, active) VALUES (6, 'FSHN HALL', 1);
INSERT INTO fti_uniman.course (ID, name, description, start_time, end_time, active, location_id) VALUES (1, 'Algebra', 'Course', '2022-07-08 15:02:06', '2022-07-08 16:02:06', 0, 3);
INSERT INTO fti_uniman.course (ID, name, description, start_time, end_time, active, location_id) VALUES (2, 'Algebra', 'Algebra Coursee', '2022-07-08 15:02:06', '2022-07-08 16:02:06', 0, 5);
INSERT INTO fti_uniman.course (ID, name, description, start_time, end_time, active, location_id) VALUES (6, 'Algebra', 'Algebra Coursee', '2022-07-08 15:02:06', '2022-07-08 16:02:06', 1, 6);
INSERT INTO fti_uniman.university_user (ID, name, last_name, birthday, ID_NUMBER, gender, picture, role, registration_date, active, email, password) VALUES (1, 'Joan', 'Janku', '2000-02-04 00:00:00', '123456789', 0, null, 0, null, '1', 'jo@fti.edu.al', '$2a$10$do/3/rlrnQcZs/qHXeMdbOVDXrq.sv8aoCEjYdQTiU1LPqhCZpYdS');
INSERT INTO fti_uniman.university_user (ID, name, last_name, birthday, ID_NUMBER, gender, picture, role, registration_date, active, email, password) VALUES (13, 'Stevi', 'Ziu', '2000-02-04 00:00:00', '123456789', 0, null, 0, null, '1', 'Stevi.Ziu@fti.edu.al', '$2a$10$AqyQI6pQoHSWRknMtVLBI.rrgnSbHmwmYzzA.ptxZRFPV9B7pLtJG');

-- PostgreSQL
INSERT INTO public.location (ID, name, active) VALUES (1, 'UPT HALL', true);
INSERT INTO public.location (ID, name, active) VALUES (6, 'FSHN HALL', true);
INSERT INTO public.course (ID, name, description, start_time, end_time, active, location_id) VALUES (1, 'Algebra', 'Course', '2022-07-08 15:02:06', '2022-07-08 16:02:06', false, 1);
INSERT INTO public.course (ID, name, description, start_time, end_time, active, location_id) VALUES (2, 'Algebra', 'Algebra Coursee', '2022-07-08 15:02:06', '2022-07-08 16:02:06', false, 1);
INSERT INTO public.course (ID, name, description, start_time, end_time, active, location_id) VALUES (6, 'Algebra', 'Algebra Coursee', '2022-07-08 15:02:06', '2022-07-08 16:02:06', true, 1);
INSERT INTO public.university_user (ID, name, last_name, birthday, ID_NUMBER, gender, picture, role, registration_date, active, email, password) VALUES (1, 'Joan', 'Janku', '2000-02-04 00:00:00', '123456789', 0, null, 0, null, true, 'jo@fti.edu.al', '$2a$10$do/3/rlrnQcZs/qHXeMdbOVDXrq.sv8aoCEjYdQTiU1LPqhCZpYdS');
INSERT INTO university_user (ID, name, last_name, birthday, ID_NUMBER, gender, picture, role, registration_date, active, email, password) VALUES (13, 'Stevi', 'Ziu', '2000-02-04 00:00:00', '123456789', 0, null, 0, null, true, 'Stevi.Ziu@fti.edu.al', '$2a$10$AqyQI6pQoHSWRknMtVLBI.rrgnSbHmwmYzzA.ptxZRFPV9B7pLtJG');
