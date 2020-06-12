CREATE TABLE user_type (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    parentid INTEGER,
    name VARCHAR(128) NOT NULL,
    color varchar(128),
    PRIMARY KEY (id)
);