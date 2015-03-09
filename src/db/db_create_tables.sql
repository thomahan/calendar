CREATE TABLE IF NOT EXISTS user (
	username VARCHAR(64) NOT NULL,
	salt VARCHAR(128) NOT NULL,
	password_hash VARCHAR(128) NOT NULL,
	first_name VARCHAR(256),
	last_name VARCHAR(256),
	PRIMARY KEY (username)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS user_group (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(256),
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS room (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(256),
	seat_count INT NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS appointment (
	id INT NOT NULL AUTO_INCREMENT,
	start_time DATETIME NOT NULL,
	end_time DATETIME NOT NULL,
	alarm_time DATETIME,
	title VARCHAR(256) NOT NULL,
	description VARCHAR(256),
	location VARCHAR(256),
	creator VARCHAR(64) NOT NULL,
	room_id INT,
	PRIMARY KEY (id),
	CONSTRAINT FOREIGN KEY (creator) REFERENCES user(username) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY (room_id) REFERENCES room(id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS appointment_invitation (
	appointment_id INT NOT NULL,
	username VARCHAR(64) NOT NULL,
	status VARCHAR(32),
	is_visible BOOL NOT NULL DEFAULT true,
	PRIMARY KEY (appointment_id, username),
	CONSTRAINT FOREIGN KEY (appointment_id) REFERENCES appointment(id) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY (username) REFERENCES user(username) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS appointment_change (
	appointment_id INT NOT NULL,
	change_time DATETIME NOT NULL,
	start_time DATETIME,
	end_time DATETIME,
	alarm_time DATETIME,
	title VARCHAR(256),
	description VARCHAR(256),
	location VARCHAR(256),
	creator VARCHAR(64),
	room_id INT,
	PRIMARY KEY (appointment_id, change_time),
	CONSTRAINT FOREIGN KEY (appointment_id) REFERENCES appointment(id) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY (room_id) REFERENCES room(id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;
