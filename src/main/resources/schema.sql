DROP TABLE IF EXISTS task;

CREATE TABLE task (
    id   SERIAL PRIMARY KEY,
    title VARCHAR(128) NOT NULL,
    is_completed BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    priority INT NOT NULL
);