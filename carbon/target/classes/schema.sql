drop table if exists USERS;
drop table if exists country;
drop table if exists BOOK;

create table USERS(
  ID int not null AUTO_INCREMENT,
  NAME varchar(100) not null,
  STATUS int,
  PRIMARY KEY ( ID )
);

CREATE TABLE country (
  id   INTEGER      NOT NULL AUTO_INCREMENT,
  name VARCHAR(128) NOT NULL,
  PRIMARY KEY (id)
);

create table BOOK(
  ID int not null AUTO_INCREMENT,
  NAME varchar(128) not null,
  PRIMARY KEY ( ID )
);

insert into Users values (1, 'Alex', 1);
insert into Users values (2, 'Bob', 1);
insert into Users values (3, 'John', 0);
insert into Users values (4, 'Harry', 0);
insert into Users values (5, 'Smith', 1);

INSERT INTO country  VALUES (1,'India');
INSERT INTO country  VALUES (2,'Brazil');
INSERT INTO country VALUES (3,'USA');


