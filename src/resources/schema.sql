CREATE TABLE users(
	id int PRIMARY KEY AUTO_INCREMENT,
	name varchar(64) NOT NULL,
	login_id varchar(64) UNIQUE NOT NULL,
	password binary(32) NOT NULL,
	created_at datetime NOT NULL DEFAULT NOW(),
	is_deleted tinyint NOT NULL DEFAULT 0,
	
	INDEX(login_id)
);

CREATE TABLE questions(
	id int PRIMARY KEY AUTO_INCREMENT,
	user_id int NOT NULL,
	title varchar(256) NOT NULL,
	content varchar(1024) NOT NULL,
	created_at datetime NOT NULL DEFAULT NOW(),
	is_deleted tinyint NOT NULL DEFAULT 0,
	
	FULLTEXT (title, content) WITH PARSER ngram,
	FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE answers(
	id int PRIMARY KEY AUTO_INCREMENT,
	question_id int NOT NULL,
	user_id int NOT NULL,
	title varchar(256) NOT NULL,
	content varchar(1024) NOT NULL,
	created_at datetime NOT NULL DEFAULT NOW(),
	is_deleted tinyint NOT NULL DEFAULT 0,
	
	FULLTEXT (title, content) WITH PARSER ngram,
	FOREIGN KEY (user_id) REFERENCES users(id),
	FOREIGN KEY (question_id) REFERENCES questions(id)
);

CREATE VIEW questions_info AS 
	SELECT
		questions.id AS id,
		users.id AS author_id,
		name AS author_name,
		questions.title AS title,
		questions.content AS content,
		questions.created_at AS created_at
	FROM questions
	JOIN users ON questions.user_id = users.id
	WHERE questions.is_deleted = 0;

CREATE VIEW answers_info AS 
	SELECT
		answers.id AS id,
		answers.question_id AS question_id,
		users.id AS author_id,
		name AS author_name,
		answers.title AS title,
		answers.content AS content,
		answers.created_at AS created_at
	FROM answers
	JOIN users ON answers.user_id = users.id
	WHERE answers.is_deleted = 0;
