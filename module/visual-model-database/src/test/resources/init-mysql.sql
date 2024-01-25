-- Create the test table
CREATE TABLE IF NOT EXISTS test
(
    id
        INT
        PRIMARY
            KEY,
    name
        VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS test2
(
    id
    INT
    PRIMARY
    KEY,
    name
    VARCHAR(255)
    );

-- Insert 10 rows of sample data
INSERT INTO test (id, name)
VALUES (1, 'Name1');
INSERT INTO test (id, name)
VALUES (2, 'Name2');
INSERT INTO test (id, name)
VALUES (3, 'Name3');
INSERT INTO test (id, name)
VALUES (4, 'Name4');
INSERT INTO test (id, name)
VALUES (5, 'Name5');
INSERT INTO test (id, name)
VALUES (6, 'Name6');
INSERT INTO test (id, name)
VALUES (7, 'Name7');
INSERT INTO test (id, name)
VALUES (8, 'Name8');
INSERT INTO test (id, name)
VALUES (9, 'Name9');
INSERT INTO test (id, name)
VALUES (10, 'Name10');
