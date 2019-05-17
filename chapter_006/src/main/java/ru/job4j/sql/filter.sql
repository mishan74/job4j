create table type (
	id serial primary key,
	type_name varchar(20)
);
create table product (
	id serial primary key,
	product_name varchar(20),
	type_id int references type (id),
	expreid_date date,
	price decimal
);

--1. Написать запрос получение всех продуктов с типом "СЫР"

select * from product where type_id = (select id from type where type_name = 'сыр');

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"

select * from product where product_name like '%мороженное%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.

select * from product where expreid_date <= '2019-06-30';

--4. Написать запрос, который выводит самый дорогой продукт.

select * from product order by price desc limit 1;

--5. Написать запрос, который выводит количество всех продуктов определенного типа.

select count (id) from product where type_id = 1;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"

select * from product where 
type_id = (select id from type where type_name = 'сыр') or 
type_id = (select id from type where type_name = 'молоко');

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  

SELECT product_name, type_id  FROM product  
GROUP BY product_name, type_id 
having COUNT(type_id) < 10 
order by type_id;

--8. Вывести все продукты и их тип.

select product_name, type_name 
from product as p join type as t 
on p.type_id=t.id

