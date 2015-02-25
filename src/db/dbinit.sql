CREATE TABLE user (
	username VARCHAR(64) NOT NULL,
	salt VARCHAR(128) NOT NULL,
	password_hash VARCHAR(128) NOT NULL,
	PRIMARY KEY (username));
