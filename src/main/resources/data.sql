insert into user_details(id,name,dob) values (101,'dilip',current_date());
insert into user_details(id,name,dob) values (102,'Kaisem',current_date()+1);
insert into post(id,USER_ID ,description) values (1002,102,'Java Full stack');
insert into post(id,USER_ID ,description) values (1003,101,'MicroServices');