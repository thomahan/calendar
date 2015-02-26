CREATE TABLE user (
	id INT NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(256),
	last_name VARCHAR(256),
	username VARCHAR(64) NOT NULL,
	salt VARCHAR(128) NOT NULL,
	password_hash VARCHAR(128) NOT NULL,
	PRIMARY KEY (id));

CREATE TABLE group (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(256),
	PRIMARY KEY (id));

CREATE TABLE room (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(256),
	capacity INT NOT NULL,
	PRIMARY KEY (id));

CREATE TABLE appointment (
	id INT NOT NULL AUTO_INCREMENT,
	start_time DATETIME NOT NULL,
	stop_time DATETIME NOT NULL,
	alarm_time DATETIME,
	description VARCHAR(256) NOT NULL,
	location VARCHAR(256),
	owner_id INT NOT NULL,
	room_id INT,
	PRIMARY KEY (id),
	FOREIGN KEY (owner_id) REFERENCES user(id));
	FOREIGN KEY (room_id) REFERENCES room(id));

CREATE TABLE appointment_invitation (
	appointment_id INT NOT NULL,
	person_id INT NOT NULL,
	status VARCHAR(256),
	is_visible BOOL DEFAULT 'true',
	PRIMARY KEY (appointment_id, person_id),
	FOREIGN KEY (appointment_id) REFERENCES appointment(id) ON UPDATE CASCADE ON DELETE CASCADE),
	FOREIGN KEY (person_id) REFERENCES person(id) ON UPDATE CASCADE ON DELETE CASCADE);
