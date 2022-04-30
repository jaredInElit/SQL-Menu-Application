create database if not exists books;

use books;

drop table if exists books;

create table books (
	id int(10) not null auto_increment,
	name varchar(50) not null,
	author_lastname varchar(50) not null,
	publish_date year,
	primary key(id)
);