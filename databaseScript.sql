create database db_jd2_EE_eCommerce;
use db_jd2_EE_eCommerce;

create table t_user(
  f_id bigint not null auto_increment primary key,
  username varchar(25) not null,
  f_password varchar(100) not null,
  f_user_information_id bigint not null,
  f_role_id bigint not null default 1,
  f_verification_id bigint not null,
  f_account_id bigint not null);

create table t_account(
  f_account_id bigint not null auto_increment primary key,
  f_is_activated boolean not null default 0,
  f_is_blocked boolean not null default 0);

insert into t_account (f_is_activated, f_is_blocked) values(1,0);
insert into t_account (f_is_activated, f_is_blocked) values(1,0);
insert into t_account (f_is_activated, f_is_blocked) values(0,0);

create table t_verification(
  f_verification_id bigint not null auto_increment primary key,
  f_verification_token varchar(70));

insert into t_verification (f_verification_token) values('token1');
insert into t_verification (f_verification_token) values('token2');
insert into t_verification (f_verification_token) values('token3');

create table t_user_information(
  f_user_info_id bigint not null auto_increment primary key,
  f_name_surname varchar(25) not null,
  f_phone varchar(20) not null,
  f_address varchar(35) not null);

create table t_role(
  f_role_id bigint not null auto_increment primary key,
  f_role varchar(20) not null);

alter table t_user add constraint fk_role_id
foreign key (f_role_id) references t_role(f_role_id);

insert into t_role (f_role) values('ROLE_USER');
insert into t_role (f_role) values('ROLE_ADMIN');
insert into t_role (f_role) values('ROLE_SUPERADMIN');

insert into t_user (username, f_password, f_user_information_id,
                    f_role_id, f_verification_id, f_account_id) values(
  'maksim-a@tut.by', '$2a$10$n6Nzb2i/ygXBZOVjCT1QVOwKSDz9fyHl2XVjKwMU0cz3TQKuCaqV2', 1, 1, 1, 1);

insert into t_user_information (f_name_surname, f_phone, f_address) values(
  'Maksim Aristov','+37529 3594012','Sucharevskaya 27-145 St. Minsk');

insert into t_user(username, f_password, f_user_information_id,
                   f_role_id, f_verification_id, f_account_id) values(
  'admin@tut.by', '$2a$10$n6Nzb2i/ygXBZOVjCT1QVOwKSDz9fyHl2XVjKwMU0cz3TQKuCaqV2', 2, 2, 2, 2);

insert into t_user_information (f_name_surname, f_phone, f_address) values(
  'Admin admin','admin phone','admin address');

insert into t_user(username, f_password, f_user_information_id,
                   f_role_id, f_verification_id, f_account_id) values(
  'superadmin@tut.by', '$2a$10$n6Nzb2i/ygXBZOVjCT1QVOwKSDz9fyHl2XVjKwMU0cz3TQKuCaqV2', 3, 3, 3, 3);

insert into t_user_information (f_name_surname, f_phone, f_address) values(
  'Superadmin admin','superadmin phone','superadmin address');


create table t_order(
  f_order_id bigint not null auto_increment primary key,
  f_order_number int not null,
  f_user_id bigint not null,
  f_order_status_id bigint not null);

alter table t_order add constraint fk_user_id
foreign key (f_user_id) references t_user(f_id);

create table t_order_status(
  f_order_status_id bigint not null auto_increment primary key,
  f_order_status varchar(20) not null);

alter table t_order add constraint fk_order_status_id
foreign key (f_order_status_id) references t_order_status(f_order_status_id);

insert into t_order_status (f_order_status) values('NEW');
insert into t_order_status (f_order_status) values('REVIEWING');
insert into t_order_status (f_order_status) values('IN_PROGRESS');
insert into t_order_status (f_order_status) values('DELIVERED');

create table t_middle_order_order_item(
  f_middle_id bigint not null auto_increment primary key,
  f_order_id bigint not null,
  f_order_item_id bigint not null);

alter table t_middle_order_order_item add constraint fk_order_id
foreign key (f_order_id) references t_order(f_order_id);

alter table t_middle_order_order_item add constraint fk_order_item_id
foreign key (f_order_item_id) references t_order_item(f_order_item_id);



create table t_order_item(
  f_order_item_id bigint not null auto_increment primary key,
  f_order_name varchar(40) not null,
  f_order_description varchar(150) not null,
  f_order_price int not null,
  f_order_item_picture_id bigint not null);

create table t_order_item_picture(
  f_picture_id bigint not null auto_increment primary key,
  f_picture_name varchar(25));


insert into t_order_item_picture (f_picture_name)
values('pepperoni');

insert into t_order_item (f_order_name, f_order_description,
                          f_order_price, f_order_item_picture_id)
values('Pepperoni','Pepperoni is characteristically soft, slightly smoky,
and bright red in color.', 25, 1);

insert into t_order_item_picture (f_picture_name)
values('margherita');

insert into t_order_item (f_order_name, f_order_description,
                          f_order_price, f_order_item_picture_id)
values('Margherita','Margherita is a pizza of a Queen, delicious and very big', 30, 2);

insert into t_order_item_picture (f_picture_name)
values('hawaiian');

insert into t_order_item (f_order_name, f_order_description,
                          f_order_price, f_order_item_picture_id)
values('Hawaiian','Hawaiian pizza is a pizza topped with tomato sauce,
cheese, pineapple, and Canadian bacon or ham.', 20, 3);

insert into t_order_item_picture (f_picture_name)
values('neapolitan');

insert into t_order_item (f_order_name, f_order_description,
                          f_order_price, f_order_item_picture_id)
values('Neapolitan','It is a kind of pizza made with
tomatoes and mozzarella cheese.', 35, 4);

insert into t_order_item_picture (f_picture_name)
values('marinara');

insert into t_order_item (f_order_name, f_order_description,
                          f_order_price, f_order_item_picture_id)
values('Marinara','Marinara is a style of Neapolitan pizza in
Italian cuisine prepared with plain
marinara sauce and seasoned with oregano and garlic.', 28, 5);

insert into t_order_item_picture (f_picture_name)
values('greek');

insert into t_order_item (f_order_name, f_order_description,
                          f_order_price, f_order_item_picture_id)
values('Greek','Greek is a style of amazing pizza in Greek
cuisine prepared with loveand seasoned with vegetables,
chicken and slices of ham.', 41, 6);

create table t_message_from_user(
  f_message_id bigint not null auto_increment primary key,
  f_user_id bigint not null,
  f_message_theme varchar(50),
  f_message_body varchar(250) not null,
  f_receiving_date varchar(35) not null);

alter table t_order add constraint fk_f_user_id
foreign key (f_user_id) references t_user(f_id);

create table t_news(
  f_news_id bigint not null auto_increment primary key,
  f_news_head varchar(100) not null,
  f_news_body varchar(250) not null);

insert into t_news (f_news_head, f_news_body) values(
  'Hot news!', 'Discounts are here!!!');

create table t_comment(
  f_comment_id bigint not null auto_increment primary key,
  f_comment varchar(250) not null,
  f_user_id bigint not null,
  f_news_id bigint not null);

alter table t_comment add constraint ffk_user_id
foreign key(f_user_id) references t_user(f_id);

alter table t_comment add constraint fk_news_id
foreign key(f_news_id) references t_news(f_news_id);

create table t_user_message_reply(
  f_reply_id bigint not null auto_increment primary key,
  f_reply_body varchar(250) not null,
  f_user_id bigint not null,
  f_user_message_id bigint not null);
