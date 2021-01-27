insert into user (login,password,role,status) values ('admin',sha1('admin'),'ADMINISTRATOR','ACTIVE');
insert into user (login,password,role,status) values ('guest',sha1('guest'),'GUEST','ACTIVE');
insert into user (login,password,role,status) values ('test',sha1('test'),'GUEST','ACTIVE');
insert into user (login,password,role,status) values ('blocked',sha1('blocked'),'GUEST','BLOCKED');
insert into user (login,password,role,status) values ('deleted',sha1('deleted'),'GUEST','DELETED');


insert into room (type,price,status,capacity,number) values ('STANDARD',100,'AVAILABLE',2,11);
insert into room (type,price,status,capacity,number) values ('STANDARD',150,'AVAILABLE',3,12);
insert into room (type,price,status,capacity,number) values ('STANDARD',200,'AVAILABLE',4,13);
insert into room (type,price,status,capacity,number) values ('STANDARD',250,'AVAILABLE',1,14);
insert into room (type,price,status,capacity,number) values ('COMFORT',200,'AVAILABLE',2,21);
insert into room (type,price,status,capacity,number) values ('COMFORT',300,'AVAILABLE',3,22);
insert into room (type,price,status,capacity,number) values ('COMFORT',400,'AVAILABLE',1,23);
insert into room (type,price,status,capacity,number) values ('COMFORT',500,'AVAILABLE',2,24);
insert into room (type,price,status,capacity,number) values ('LUX',300,'AVAILABLE',1,31);
insert into room (type,price,status,capacity,number) values ('LUX',450,'AVAILABLE',2,32);
insert into room (type,price,status,capacity,number) values ('LUX',600,'AVAILABLE',3,33);
insert into room (type,price,status,capacity,number) values ('LUX',750,'AVAILABLE',4,34);
insert into room (type,price,status,capacity,number) values ('PRESIDENT',500,'AVAILABLE',1,41);
insert into room (type,price,status,capacity,number) values ('PRESIDENT',800,'AVAILABLE',2,42);
insert into room (type,price,status,capacity,number) values ('PRESIDENT',1100,'AVAILABLE',3,43);
insert into room (type,price,status,capacity,number) values ('PRESIDENT',1300,'AVAILABLE',4,44);

insert into application (check_in,check_out,room_type,capacity,status,user_id,room_id,invoice) values ('2021-01-15','2021-01-18','STANDARD',2,'IN_PROGRESS',2,0,0);
insert into application (check_in,check_out,room_type,capacity,status,user_id,room_id,invoice) values ('2021-01-20','2021-01-21','STANDARD',2,'IN_PROGRESS',2,0,0);
insert into application (check_in,check_out,room_type,capacity,status,user_id,room_id,invoice) values ('2021-01-24','2021-01-26','COMFORT',1,'IN_PROGRESS',2,0,0);
insert into application (check_in,check_out,room_type,capacity,status,user_id,room_id,invoice) values ('2021-01-27','2021-01-28','PRESIDENT',1,'IN_PROGRESS',2,0,0);
insert into application (check_in,check_out,room_type,capacity,status,user_id,room_id,invoice) values ('2021-01-20','2021-01-21','STANDARD',2,'IN_PROGRESS',3,0,0);
insert into application (check_in,check_out,room_type,capacity,status,user_id,room_id,invoice) values ('2021-01-24','2021-01-26','COMFORT',1,'IN_PROGRESS',3,0,0);
insert into application (check_in,check_out,room_type,capacity,status,user_id,room_id,invoice) values ('2021-01-27','2021-01-28','PRESIDENT',1,'IN_PROGRESS',3,0,0);
insert into application (check_in,check_out,room_type,capacity,status,user_id,room_id,invoice) values ('2021-02-20','2021-02-21','STANDARD',2,'IN_PROGRESS',2,0,0);
insert into application (check_in,check_out,room_type,capacity,status,user_id,room_id,invoice) values ('2021-01-21','2021-01-26','COMFORT',1,'IN_PROGRESS',2,0,0);
insert into application (check_in,check_out,room_type,capacity,status,user_id,room_id,invoice) values ('2021-01-01','2021-01-01','PRESIDENT',1,'IN_PROGRESS',2,0,0);


