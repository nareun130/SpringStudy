-- MySQL에서는 CURRENT_DATE() 대신, NOW()를 사용
insert into todo(id, description, done, target_date, username)
    values(10001,'Learn AWS',false,now(),'nareun130');

insert into todo(id, description, done, target_date, username)
    values(10002,'Get AWS Certified',false,now(),'nareun130');

insert into todo(id, description, done, target_date, username)
    values(10003,'Learn DevOps',false,now(),'nareun130');