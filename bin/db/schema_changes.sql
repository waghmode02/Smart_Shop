CREATE DATABASE smartshop;
USE smartshop;

create table products(
product_id int auto_increment primary key,
product_name varchar(100) not null,
description varchar(255) not null,
price decimal(10,2) not null,
quantity int not null
);

select * from products;