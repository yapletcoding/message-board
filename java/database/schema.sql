BEGIN TRANSACTION;

DROP TABLE IF EXISTS user_forums;
DROP TABLE IF EXISTS user_comments;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS user_posts;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS forums;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);


CREATE TABLE forums (
id serial,
forum_title VARCHAR(80),
forum_description VARCHAR(200),
forum_members INT,
forum_posts INT,

CONSTRAINT pk_forums PRIMARY KEY (id)
);


CREATE TABLE posts (
id serial,
forum_id INT NOT NULL,
user_id INT NOT NULL,
post_title VARCHAR(80) NOT NULL,
post_text VARCHAR(400) NOT NULL,
post_likes INT,
post_dislikes INT,
post_comments INT NULL,
post_datetime DATE NULL,
post_img VARCHAR NULL,

CONSTRAINT pk_posts PRIMARY KEY (id),
CONSTRAINT fk_posts FOREIGN KEY (forum_id) REFERENCES forums (id),
CONSTRAINT fk_posts_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE user_posts (
id serial,
user_id INT NOT NULL,
post_id INT NOT NULL,
post_interaction_like BOOLEAN DEFAULT FALSE,
post_interaction_dislike BOOLEAN DEFAULT FALSE,

CONSTRAINT pk_user_posts PRIMARY KEY (id),
CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id),
CONSTRAINT fk_post FOREIGN KEY (post_id) REFERENCES posts (id)
);

CREATE TABLE user_forums (
id serial,
user_id INT NOT NULL,
forum_id INT NOT NULL,
favorite BOOLEAN DEFAULT FALSE,
moderator BOOLEAN DEFAULT FALSE,

CONSTRAINT pk_user_forums PRIMARY KEY (id),
CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id),
CONSTRAINT fk_forums_id FOREIGN KEY (forum_id) REFERENCES forums (id)
);

CREATE TABLE comments (
id serial,
post_id INT NOT NULL,
replying_to_id INT NULL,
user_id INT,
comment_likes INT,
comment_dislikes INT,
comment_datetime DATE,
comment_text VARCHAR(200),

CONSTRAINT pk_comments PRIMARY KEY (id),
CONSTRAINT fk_comments FOREIGN KEY (user_id) REFERENCES users (user_id),
CONSTRAINT fk_posts FOREIGN KEY (post_id) REFERENCES posts (id)
);

CREATE TABLE user_comments (
id serial,
user_id INT NOT NULL,
post_id INT NOT NULL,
comment_id INT NOT NULL,
comment_interaction_like BOOLEAN DEFAULT FALSE,
comment_interaction_dislike BOOLEAN DEFAULT FALSE,

CONSTRAINT pk_user_comments PRIMARY KEY (id),
CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id),
CONSTRAINT fk_post FOREIGN KEY (post_id) REFERENCES posts (id),
CONSTRAINT fk_comment FOREIGN KEY (comment_id) REFERENCES comments (id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO forums (forum_title, forum_description) VALUES ('tech', 'A forum for all things technology related');
INSERT INTO forums (forum_title, forum_description) VALUES ('sports', 'A forum for discussing sports');
INSERT INTO forums (forum_title, forum_description) VALUES ('cuteAnimals', 'A forum for adorable creatures');
INSERT INTO forums (forum_title, forum_description) VALUES ('coding', 'A forum for programming');
INSERT INTO forums (forum_title, forum_description) VALUES ('food', 'A forum for delicious edible things');
INSERT INTO forums (forum_title, forum_description) VALUES ('Video Games', 'A forum for gamers');
INSERT INTO forums (forum_title, forum_description) VALUES ('movies', 'A forum for discussing films old and new');
INSERT INTO forums (forum_title, forum_description) VALUES ('funny', 'A forum for hilarity');
INSERT INTO forums (forum_title, forum_description) VALUES ('dogs', 'A forum for discussing all things dogs');
INSERT INTO forums (forum_title, forum_description) VALUES ('cats', 'A forum for discussing all things cats');
INSERT INTO forums (forum_title, forum_description) VALUES ('music', 'A forum for wiggly air');
INSERT INTO forums (forum_title, forum_description) VALUES ('DIY', 'A forum for getting things done');
INSERT INTO posts (forum_id, post_title, user_id, post_text, post_likes, post_dislikes, post_comments, post_datetime)
VALUES ('1', 'How do I code?', '1', 'someone please teach me', 8, 2, 2, CURRENT_TIMESTAMP);
INSERT INTO posts (forum_id, post_title, user_id, post_text, post_likes, post_dislikes, post_comments, post_datetime)
VALUES ('1', 'Java or C#?', '1', 'Java all day right?', 7, 54, 0, CURRENT_TIMESTAMP);

INSERT INTO posts(forum_id, post_title, user_id, post_text, post_likes, post_dislikes, post_comments, post_datetime)
VALUES ('1', 'How do I center a div?', '1', 'It cant be that hard right?', '21', '3', '2', '20210818 10:34:09 AM');
INSERT INTO posts(forum_id, post_title, user_id, post_text, post_likes, post_dislikes, post_comments, post_datetime)
VALUES ('1', 'Learn Python the hard way', '1', 'has anyone read this book?', '40', '9', '1', '20210810 10:34:09 AM');
INSERT INTO posts(forum_id, post_title, user_id, post_text, post_likes, post_dislikes, post_comments, post_datetime)
VALUES ('1', 'I love coding', '2', 'Anyone else?', '18', '8', '0', '20210820 10:34:09 AM');

INSERT INTO comments(post_id, replying_to_id, user_id, comment_likes, comment_dislikes, comment_datetime, comment_text)
VALUES ('1', null,'1', '3', '21', '20210818 10:34:09 PM', 'you dont');
INSERT INTO comments(post_id, replying_to_id, user_id, comment_likes, comment_dislikes, comment_datetime, comment_text)
VALUES ('1', null,'1', '21', '3', '20210818 10:35:09 PM', 'with a computer');
INSERT INTO comments(post_id, replying_to_id, user_id, comment_likes, comment_dislikes, comment_datetime, comment_text)
VALUES ('2', null,'1', '40', '6', '20210810 10:35:09 PM', 'baseball');
INSERT INTO comments(post_id, replying_to_id, user_id, comment_likes, comment_dislikes, comment_datetime, comment_text)
VALUES ('2', null,'1', '40', '6', '20210810 10:35:09 PM', 'also curling. We should make a curling forum.');

INSERT INTO posts(forum_id, post_title, user_id, post_text, post_likes, post_dislikes, post_comments, post_datetime)
VALUES ('2', 'What is your favorite sport?', '1', 'Mine is curling', '4', '1', '2', '20211210 7:30:29 PM');
INSERT INTO user_forums (user_id, forum_id, favorite, moderator) VALUES ('1', '1', 'true', 'true');
INSERT INTO user_forums (user_id, forum_id, favorite, moderator) VALUES ('2', '1', 'true', 'false');





COMMIT TRANSACTION;
