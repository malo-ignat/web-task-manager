DROP TABLE IF EXISTS task;

CREATE TABLE task (
    id   SERIAL PRIMARY KEY,
    title VARCHAR(128) NOT NULL,
    isCompleted BOOLEAN NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    priority VARCHAR(128) NOT NULL
);