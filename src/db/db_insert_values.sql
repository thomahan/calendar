INSERT INTO room (name, seat_count) VALUES
	("R1", 1),
	("R2", 2),
	("R3", 3),
	("R4", 4),
	("R5", 5),
	("R6", 6),
	("R7", 7),
	("R8", 8),
	("R9", 9);

INSERT INTO user_group (name) VALUES
	("All employees"),
	("Sales"),
	("Accounting"),
	("Management");

INSERT INTO group_in_group (sub_group_id, user_group_id) VALUES
	(2, 1),
	(3, 1),
	(4, 1);

INSERT INTO user_in_group (username, user_group_id) VALUES
	("alice", 2),
	("bob", 2),
	("bob", 4),
	("carol", 4),
	("dave", 3),
	("erin", 3),
	("erin", 4),
	("frank", 1);
