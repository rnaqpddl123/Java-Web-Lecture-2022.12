SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS like_exercise;
DROP TABLE IF EXISTS reply;
DROP TABLE IF EXISTS exercise_board;
DROP TABLE IF EXISTS shopping_board;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS users;




/* Create Tables */

CREATE TABLE exercise_board
(
	bid int NOT NULL AUTO_INCREMENT,
	btitle varchar(100) NOT NULL,
	bcontent varchar(4096),
	uid varchar(12) NOT NULL,
	modeTime datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	viewCount int DEFAULT 0 NOT NULL,
	replyCount int DEFAULT 0 NOT NULL,
	content varchar(4096),
	PRIMARY KEY (bid)
);


CREATE TABLE like_exercise
(
	uid varchar(12) NOT NULL,
	bid int NOT NULL
);


CREATE TABLE product
(
	pid int NOT NULL AUTO_INCREMENT,
	pname varchar(40) NOT NULL,
	PRIMARY KEY (pid)
);


CREATE TABLE reply
(
	rid int NOT NULL AUTO_INCREMENT,
	rcontent varchar(256) NOT NULL,
	regTime datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	isMine int DEFAULT 0 NOT NULL,
	uid varchar(12) NOT NULL,
	bid int NOT NULL,
	PRIMARY KEY (rid)
);


CREATE TABLE shopping_board
(
	sid int NOT NULL AUTO_INCREMENT,
	pid int NOT NULL,
	state varchar(5) NOT NULL,
	content varchar(4096) NOT NULL,
	PRIMARY KEY (sid)
);


CREATE TABLE users
(
	uid varchar(12) NOT NULL,
	pwd char(60) NOT NULL,
	uname varchar(10) NOT NULL,
	email varchar(20),
	regDate date DEFAULT (CURRENT_DATE) NOT NULL,
	PRIMARY KEY (uid)
);



/* Create Foreign Keys */

ALTER TABLE like_exercise
	ADD FOREIGN KEY (bid)
	REFERENCES exercise_board (bid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE reply
	ADD FOREIGN KEY (bid)
	REFERENCES exercise_board (bid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE shopping_board
	ADD FOREIGN KEY (pid)
	REFERENCES product (pid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE exercise_board
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE like_exercise
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE reply
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



