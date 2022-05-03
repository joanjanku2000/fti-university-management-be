CREATE TABLE `university_user` (
	`ID` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(64) NOT NULL,
	`last_name` VARCHAR(64) NOT NULL,
	`birthday` TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
	`ID_NUMBER` VARCHAR(12) NOT NULL,
	`gender` INT NOT NULL,
	`picture` TEXT,
	`role` INT,
	`registration_date` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`active` BOOLEAN,
	PRIMARY KEY (`ID`)
);
CREATE TABLE `location` (
	`ID` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(64) NOT NULL,
	`active` BOOLEAN,
	PRIMARY KEY (`ID`)
);
CREATE TABLE `course` (
	`ID` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(64) NOT NULL,
	`description` VARCHAR(255) ,
	`start_time` timestamp ,
	`end_time` timestamp ,
	`active` BOOLEAN,
	`location_id` BIGINT ,
	PRIMARY KEY (`ID`),
	constraint loc_fk 
	foreign key (location_id)
	REFERENCES location(id)
);
CREATE TABLE `course_student` (
	`ID` BIGINT NOT NULL AUTO_INCREMENT,
	`joined_on`TIMESTAMP NOT NULL,
	`left_on`TIMESTAMP NOT NULL,
	`course_id` BIGINT not null ,
	`student_id` BIGINT not null , 
	`active` BOOLEAN,
	PRIMARY KEY (`ID`),
	constraint course_fk 
	foreign key (course_id)
	REFERENCES course(id) ,
	constraint user_fk 
	foreign key (student_id)
	REFERENCES university_user(id)
);

CREATE TABLE `notification` (
	`ID` BIGINT NOT NULL AUTO_INCREMENT,
	`notification_type` INTEGER NOT NULL,
	`active` BOOLEAN,
	PRIMARY KEY (`ID`)
	);
	

CREATE TABLE `notification_user` (
	`ID` BIGINT NOT NULL AUTO_INCREMENT,
	`message`varchar(255) NOT NULL,
	`type_id`BIGINT NOT NULL,
	`user_id` BIGINT not null ,
	`active` BOOLEAN,
	PRIMARY KEY (`ID`),
	constraint notif_fk 
	foreign key (type_id)
	REFERENCES notification(id) ,
	constraint user_fkkk 
	foreign key (user_id)
	REFERENCES university_user(id)
);

CREATE TABLE `university_comment` (
	`ID` BIGINT NOT NULL AUTO_INCREMENT,
	`comment_string` text NOT NULL,
	`created_at` TIMESTAMP NOT NULL,
	`parent_id` BIGINT not null ,
	`type` INTEGER not null ,
	`user_id` BIGINT not null ,
	`course_id` BIGINT ,
	`active` BOOLEAN,
	PRIMARY KEY (`ID`),
	constraint replying_to 
	foreign key (parent_id)
	REFERENCES university_comment(id) ,
	constraint user_fkk 
	foreign key (user_id)
	REFERENCES university_user(id),
	constraint course_FKk 
	foreign key (course_id)
	REFERENCES course(id)
);


CREATE TABLE `likes` (
	`ID` BIGINT NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT not null ,
	`comment_id` BIGINT not null  ,
	`active` BOOLEAN,
	PRIMARY KEY (`ID`),

	constraint user_fkdk 
	foreign key (user_id)
	REFERENCES university_user(id),
	constraint comment_FKdk 
	foreign key (comment_id)
	REFERENCES university_comment(id)
);


CREATE TABLE `friendship` (
	`ID` BIGINT NOT NULL AUTO_INCREMENT,
	`sender_id` BIGINT not null ,
	`receiver_id` BIGINT not null ,
	`status` INTEGER not null  ,
	`active` BOOLEAN,
	PRIMARY KEY (`ID`),
	constraint sender 
	foreign key (sender_id)
	REFERENCES university_user(id),
	constraint receiver 
	foreign key (receiver_id)
	REFERENCES university_user(id)
);

ALTER TABLE `fti_uniman`.`university_user`
    CHANGE COLUMN `active` `active` VARCHAR(255) NULL DEFAULT NULL ,
    ADD UNIQUE INDEX `active_UNIQUE` (`active` ASC) VISIBLE;
;