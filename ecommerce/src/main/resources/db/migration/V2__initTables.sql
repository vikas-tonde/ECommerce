create table users(u_id int primary key auto_increment, 
first_name varchar(20),
last_name varchar(20),
email varchar(35) unique not null, 
password varchar(150), 
role varchar(20));

insert into users(first_name,last_name,email,password, role) values
("Vikas","Tonde","tondev23@gmail.com","$2a$12$G39k7Xwpcy.3F11LULOSVuDYSfA.E3hxmzJ9YjmHpv/JpkLYQvZiu","ADMIN");

insert into users(first_name,last_name,email,password, role) values
("Shreya","Ramtirth","shreyaramtirth4@gmail.com","$2a$10$K/S7Z.ejr1OEi4AMNz.FI.ZwnKMzIjevRe6W1srxqrp65jRQCFBIm","CUSTOMER");

create table product(p_id int primary key auto_increment,
product_name varchar(30),
description text(200),
color varchar(20),
quantity int,
price float(10,2),
image varchar(200)  
);

insert into product(product_name,description,color,quantity,price,image)
values("I Phone","This is iphone 12 with dynamic island","Green",200,150000,"E:\Java Full Stack\uploads\photo-5.jpg");

create table address(
a_id int primary key auto_increment,
receiver_name varchar(50),
street_name varchar(30),
apartment_number varchar(20),
city varchar(30),
state varchar(30),
pin_code char(6),
contact_number char(10)
);

create table cart(
c_id int primary key auto_increment,
u_id int references users(u_id),
status boolean default true,
purchasable boolean default true,
subtotal float(20,3) default 0,
approval varchar(20),  
a_id int references address(a_id)
);

create table purchase_product(
pp_id int primary key auto_increment,
quantity int,
p_id int references product(p_id),
c_id int references cart(c_id),
purchasable boolean default true
);