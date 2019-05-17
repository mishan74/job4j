create database postgres;

create table users (
	id serial primary key,
	first_name varchar(10),
	second_name varchar(10)
	);
create table role (
	id serial primary key,
	role_name varchar(10),
	id_users int references users (id)
);
create table rule (
	id serial primary key,
	rule_name varchar(10)
);
create table rule_role (
	id serial primary key,
	id_role int references role (id),
	id_rule int references rule (id)
);
create table comment (
	id serial primary key,
	text_comment text
);
create table attach (
	id serial primary key,
	bin_file bytea
);
create table item (
	id_users int primary key references users (id),
	item_name varchar(20),
	id_comment int references comment (id),
	id_attach int references attach (id)
);
create table category (
	id serial primary key,
	type_category varchar(40),
	id_item int references item (id_users)
);
create table state (
	id serial primary key,
	state_desc varchar(200),
	id_item int references item (id_users)
);