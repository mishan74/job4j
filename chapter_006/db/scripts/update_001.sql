create table if not exists item (
  id serial primary key,
  item_name varchar (50),
  item_desc varchar (300),
  item_comment varchar(300),
  item_time timestamp
);
