create table if not exists User (
id identity,
telegramId varchar(50) not null,
merchantId varchar(20) not null,
secretKey varchar(100) not null,
registrationDate timestamp not null
);
create table if not exists Project (
id identity,
name varchar(50) not null,
token varchar(20) not null,
user_id long not null,
foreign key (user_id) references User(id)
);
create table if not exists Payment (
id identity,
username varchar(50) not null,
description varchar(200) not null,
moneyAmount DOUBLE not null,
purchaseDate timestamp not null,
project_id long not null,
foreign key (project_id) references Project(id)
);