CREATE TABLE user (
  user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(128) NOT NULL UNIQUE,
  password VARCHAR(256) NOT NULL,
  firstname VARCHAR(128) NOT NULL,
  lastname VARCHAR(128) NOT NULL,
  email VARCHAR(128) NOT NULL UNIQUE,
  date_registration DATE NOT NULL,
  details VARCHAR(1024),
  imgurl VARCHAR(1024) NOT NULL
);

CREATE TABLE auth_user_group (
  auth_user_group_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(128) NOT NULL,
  auth_group VARCHAR(128) NOT NULL,
  CONSTRAINT user_auth_user_group_fk FOREIGN KEY(username) REFERENCES user(username),
  UNIQUE (username, auth_group)
);

CREATE TABLE teacher (
    teacher_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(128) NOT NULL,
    lastname VARCHAR(128) NOT NULL,
    email VARCHAR(128) NOT NULL,
    description VARCHAR(256) NOT NULL,
    details VARCHAR(1024),
    imgurl VARCHAR(1024) NOT NULL
);

CREATE TABLE course (
  course_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(128) NOT NULL UNIQUE,
  description VARCHAR(256) NOT NULL,
  details VARCHAR(1024) NOT NULL ,
  difficulty VARCHAR(128) NOT NULL,
  teacher_id BIGINT NOT NULL,
  url VARCHAR(1024) NOT NULL ,
  imgurl VARCHAR(1024) NOT NULL ,
  CONSTRAINT course_fk FOREIGN KEY(teacher_id) REFERENCES teacher(teacher_id)
);

CREATE TABLE tuition (
    tuition_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    date DATE NOT NULL,
    CONSTRAINT tuition_user_fk FOREIGN KEY(user_id) REFERENCES user(user_id),
    CONSTRAINT tuition_course_fk FOREIGN KEY(course_id) REFERENCES course(course_id)
);