SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS community_reply;
DROP TABLE IF EXISTS community;
DROP TABLE IF EXISTS community_category;
DROP TABLE IF EXISTS exercise_file_accuracy;
DROP TABLE IF EXISTS excirse_file;
DROP TABLE IF EXISTS exercise_reply;
DROP TABLE IF EXISTS like_exercise;
DROP TABLE IF EXISTS exercise;
DROP TABLE IF EXISTS excirse_category;
DROP TABLE IF EXISTS exercise_mate;
DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS users_profile;
DROP TABLE IF EXISTS users;




/* Create Tables */

CREATE TABLE community
(
	community_id int NOT NULL AUTO_INCREMENT,
	user_id varchar(12) NOT NULL,
	exercise_category_id int DEFAULT 0 NOT NULL,
	community_title varchar(30) NOT NULL,
	community_content varchar(4096) NOT NULL,
	community_regTime datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	community_viewCount int DEFAULT 0 NOT NULL,
	community_replyCount int DEFAULT 0 NOT NULL,
	PRIMARY KEY (community_id)
);


CREATE TABLE community_category
(
	exercise_category_id int NOT NULL AUTO_INCREMENT,
	category_name varchar(10) NOT NULL,
	PRIMARY KEY (exercise_category_id)
);


CREATE TABLE community_reply
(
	reply_id int NOT NULL AUTO_INCREMENT,
	community_id int NOT NULL,
	user_id varchar(12) NOT NULL,
	reply_content varchar(300) NOT NULL,
	reply_regTime datetime DEFAULT (CURRENT_TIMESTAMP) NOT NULL,
	reply_origin_number int DEFAULT 0 NOT NULL,
	reply_order int DEFAULT 0 NOT NULL,
	reply_isMine int DEFAULT 0 NOT NULL,
	PRIMARY KEY (reply_id)
);


CREATE TABLE excirse_category
(
	exercise_category_id int NOT NULL AUTO_INCREMENT,
	category_name varchar(10) NOT NULL,
	PRIMARY KEY (exercise_category_id)
);


CREATE TABLE excirse_file
(
	file_number int NOT NULL AUTO_INCREMENT,
	exercise_id int NOT NULL,
	orgin_file_name varchar(300) NOT NULL,
	sotred_file_name varchar(300) NOT NULL,
	file_size int NOT NULL,
	create_date datetime DEFAULT (CURRENT_TIMESTAMP) NOT NULL,
	file_isDeleted int DEFAULT 0 NOT NULL,
	PRIMARY KEY (file_number)
);


CREATE TABLE exercise
(
	exercise_id int NOT NULL AUTO_INCREMENT,
	user_id varchar(12) NOT NULL,
	exercise_category_id int NOT NULL,
	exercise_title varchar(30) NOT NULL,
	exercise_content varchar(4096) NOT NULL,
	exercise_regTime datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	exercise_viewCount int DEFAULT 0 NOT NULL,
	exercise_replyCount int DEFAULT 0 NOT NULL,
	PRIMARY KEY (exercise_id)
);


CREATE TABLE exercise_file_accuracy
(
	file_number int NOT NULL,
	exercise_accuracy varchar(1000) NOT NULL,
	exercise_file_image varchar(300) NOT NULL
);


CREATE TABLE exercise_mate
(
	user_id varchar(12) NOT NULL,
	profile_id int NOT NULL,
	mate_content varchar(200) NOT NULL
);


CREATE TABLE exercise_reply
(
	reply_id int NOT NULL AUTO_INCREMENT,
	exercise_id int NOT NULL,
	user_id varchar(12) NOT NULL,
	reply_content varchar(300) NOT NULL,
	reply_regTime datetime DEFAULT (CURRENT_TIMESTAMP) NOT NULL,
	reply_origin_number int DEFAULT 0 NOT NULL,
	reply_order int DEFAULT 0 NOT NULL,
	reply_isMine int DEFAULT 0 NOT NULL,
	PRIMARY KEY (reply_id)
);


CREATE TABLE like_exercise
(
	user_id varchar(12) NOT NULL,
	exercise_id int NOT NULL,
	like_exercise_num int DEFAULT 0 NOT NULL
);


CREATE TABLE message
(
	send_user_id varchar(12) NOT NULL,
	recieve_user_id varchar(12) NOT NULL,
	message_title varchar(30) NOT NULL,
	message_content varchar(500) NOT NULL,
	message_regTime datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	message_confirm int DEFAULT 0 NOT NULL
);


CREATE TABLE users
(
	user_id varchar(12) NOT NULL,
	user_pwd char(60) NOT NULL,
	user_name varchar(10) NOT NULL,
	user_phone varchar(20) NOT NULL,
	user_email varchar(40) NOT NULL,
	user_nickname varchar(10) NOT NULL,
	email_check int DEFAULT 0 NOT NULL,
	user_role int DEFAULT 0 NOT NULL,
	user_regDate date DEFAULT (CURRENT_DATE) NOT NULL,
	PRIMARY KEY (user_id)
);


CREATE TABLE users_profile
(
	profile_id int NOT NULL AUTO_INCREMENT,
	user_id varchar(12) NOT NULL,
	user_image varchar(300),
	user_addr varchar(50),
	user_grade varchar(10) DEFAULT 'BRONZE' NOT NULL,
	PRIMARY KEY (profile_id)
);



/* Create Foreign Keys */

ALTER TABLE community_reply
	ADD FOREIGN KEY (community_id)
	REFERENCES community (community_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE community
	ADD FOREIGN KEY (exercise_category_id)
	REFERENCES community_category (exercise_category_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE exercise
	ADD FOREIGN KEY (exercise_category_id)
	REFERENCES excirse_category (exercise_category_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE exercise_file_accuracy
	ADD FOREIGN KEY (file_number)
	REFERENCES excirse_file (file_number)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE excirse_file
	ADD FOREIGN KEY (exercise_id)
	REFERENCES exercise (exercise_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE exercise_reply
	ADD FOREIGN KEY (exercise_id)
	REFERENCES exercise (exercise_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE like_exercise
	ADD FOREIGN KEY (exercise_id)
	REFERENCES exercise (exercise_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE community
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE community_reply
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE exercise
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE exercise_mate
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE exercise_reply
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE like_exercise
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE message
	ADD FOREIGN KEY (send_user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE message
	ADD FOREIGN KEY (recieve_user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE users_profile
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE exercise_mate
	ADD FOREIGN KEY (profile_id)
	REFERENCES users_profile (profile_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



