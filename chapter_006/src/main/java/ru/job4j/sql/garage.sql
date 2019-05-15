--создание таблиц
create table engine(
	id serial primary key,
	type varchar (30)
);
create table body(
	id serial primary key,
	type varchar (30)
);
create table transmission(
	id serial primary key,
	type varchar (30)
);
create table car(
	id serial primary key,
	logo varchar (10),
	engine_type int references engine (id),
	transmission_type int references transmission (id),
	body_type int references body (id)
);

--заполнение таблиц данными

insert into engine (type) values ('дизель');
insert into engine (type) values ('бензин');
insert into engine (type) values ('элетро');
insert into engine (type) values ('гибрид');


insert into transmission (type) values ('автомат');
insert into transmission (type) values ('механика');
insert into transmission (type) values ('полуавтомат');

insert into body (type) values ('грузовой');
insert into body (type) values ('хэчбэк');
insert into body (type) values ('кабриолет');
insert into body (type) values ('седан');

insert into car (logo, engine_type, transmission_type, body_type) values ('renault', 1, 2, 1);
insert into car (logo, engine_type, transmission_type, body_type) values ('volvo', 2, 2, 2);
insert into car (logo, engine_type, transmission_type, body_type) values ('mercedes', 2, 1, 2);
insert into car (logo, engine_type, transmission_type, body_type) values ('ranault', 2, 1, 2);
insert into car (logo, engine_type, transmission_type, body_type) values ('toyota', 3, 1, 3);

--запрос на вывод всех автомобилей с входящими в них запчастями

select c.logo, b.type as body_type, e.type as engine_type, t.type as transmission_type from car as c 
left outer join body as b on c.body_type = b.id
left outer join engine as e on c.engine_type = e.id
left outer join transmission as t on c.transmission_type = t.id; 

--запрос на неиспользуемые типы кузовов
select b.type as body_type
from car as c
right join body as b on c.body_type = b.id where c.id is null; 

--запрос на неиспользуемые типы двигателей
select e.type as engine_type
from car as c
right join engine as e on c.engine_type = e.id where c.id is null; 

--запрос на неиспользуемые типы трансмиссий
select t.type as transmission_type
from car as c
right join transmission as t on c.transmission_type = t.id where c.id is null; 