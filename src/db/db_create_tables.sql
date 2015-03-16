CREATE TABLE IF NOT EXISTS user (
	username VARCHAR(64) NOT NULL,
	salt VARCHAR(128) NOT NULL,
	password_hash VARCHAR(128) NOT NULL,
	first_name VARCHAR(20),
	last_name VARCHAR(30),
	PRIMARY KEY (username)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS user_group (
	user_group_id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(20),
	PRIMARY KEY (user_group_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS room (
	room_id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(20),
	seat_count INT NOT NULL,
	PRIMARY KEY (room_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS appointment (
	appointment_id INT NOT NULL AUTO_INCREMENT,
	start_time DATETIME NOT NULL,
	end_time DATETIME NOT NULL,
	description VARCHAR(50) NOT NULL,
	location VARCHAR(50),
	room_id INT,
	creator VARCHAR(64) NOT NULL,
	PRIMARY KEY (appointment_id),
	CONSTRAINT FOREIGN KEY (creator) REFERENCES user(username) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY (room_id) REFERENCES room(room_id) ON UPDATE CASCADE ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS invitation (
	appointment_id INT NOT NULL,
	username VARCHAR(64) NOT NULL,
	status VARCHAR(20) DEFAULT "Not replied",
	unseen_change BOOL DEFAULT 0,
	alarm_time DATETIME,
	PRIMARY KEY (appointment_id, username),
	CONSTRAINT FOREIGN KEY (appointment_id) REFERENCES appointment(appointment_id) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY (username) REFERENCES user(username) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS cancel_notification (
	appointment_id INT NOT NULL,
	username VARCHAR(64) NOT NULL,
	canceller VARCHAR(64) NOT NULL,
	PRIMARY KEY (appointment_id, username, canceller),
	CONSTRAINT FOREIGN KEY (appointment_id) REFERENCES appointment(appointment_id) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY (username) REFERENCES user(username) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY (canceller) REFERENCES user(username) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS group_invitation (
	appointment_id INT NOT NULL,
	user_group_id INT NOT NULL,
	PRIMARY KEY (appointment_id, user_group_id),
	CONSTRAINT FOREIGN KEY (appointment_id) REFERENCES appointment(appointment_id) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY (user_group_id) REFERENCES user_group(user_group_id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS user_in_group (
	username VARCHAR(64) NOT NULL,
	user_group_id INT NOT NULL,
	PRIMARY KEY (username, user_group_id),
	CONSTRAINT FOREIGN KEY (username) REFERENCES user(username) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY (user_group_id) REFERENCES user_group(user_group_id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS group_in_group (
	sub_group_id INT NOT NULL,
	user_group_id INT NOT NULL,
	PRIMARY KEY (sub_group_id, user_group_id),
	CONSTRAINT FOREIGN KEY (sub_group_id) REFERENCES user_group(user_group_id) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY (user_group_id) REFERENCES user_group(user_group_id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;
