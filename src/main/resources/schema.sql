create table if not exists User (
id identity,
telegramId varchar(50) not null,
token varchar(20) not null,
merchantId varchar(20) not null,
secretKey varchar(20) not null
);