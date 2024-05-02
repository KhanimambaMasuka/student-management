DROP SCHEMA IF EXISTS app CASCADE;

CREATE SCHEMA IF NOT EXISTS app;

SET SCHEMA 'app';

CREATE TABLE roles
(
    id          TEXT NOT NULL PRIMARY KEY,
    description TEXT NOT NULL
);

insert into roles(id, description)
VALUES ('EDUCATOR', 'Educator'),
       ('STUDENT', 'Student');

CREATE TABLE users
(
    id        SERIAL PRIMARY KEY,
    full_name TEXT UNIQUE NOT NULL,
    password  TEXT        NOT NULL,
    email     TEXT UNIQUE NOT NULL,
    role_id   TEXT REFERENCES roles (id)
);

insert into users(full_name, password, email, role_id)
VALUES ('Khanimamba Masuka', '$2a$10$PYQtSBAM82uKK3gBIG.2BeJOFpQvObUqaSQWKWxqhYk3auc.1vB8C', 'khanimambahm@gmail.com',
        'EDUCATOR');

CREATE TABLE user_roles
(
    id      SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users (id),
    role_id TEXT REFERENCES roles (id)
);

CREATE TABLE student
(
    id               SERIAL PRIMARY KEY,
    student_no       text not null,
    full_name        text NOT NULL,
    cell_no    text NOT NULL,
    email text not null,
    current_score    text,
    average_score text,
    CONSTRAINT unique_student_email UNIQUE (email),
    CONSTRAINT unique_student_no UNIQUE (student_no)
);

CREATE TABLE score
(
    id          SERIAL PRIMARY KEY,
    student_id  INT REFERENCES student (id),
    score_value INT NOT NULL CHECK (score_value >= 0 AND score_value <= 100),
    timestamp   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_score_student FOREIGN KEY (student_id) REFERENCES student (id)
);

