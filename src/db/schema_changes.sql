CREATE DATABASE smartshop;
USE smartshop;

create table products(
product_id int auto_increment primary key,
product_name varchar(100) not null,
description varchar(255) not null,
price decimal(10,2) not null,
quantity int not null
);

create table users ( 
id int auto_increment primary key,
first_name varchar(50) not null,
last_name varchar(50) not null,
username varchar(50) not null unique,
password varchar(50) not null ,
city varchar(50),
email varchar(50) not null unique,
mobile varchar(15) not null unique,
role ENUM('user','admin') DEFAULT 'user',
createdAt timestamp default current_timestamp 
);

select * from products;
