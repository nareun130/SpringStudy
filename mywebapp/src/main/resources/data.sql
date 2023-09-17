-- ! data.sql은 엔티티 처리 전에 실행 됨. ->  오류 발생 -> 프로퍼티 설정
insert into todo (ID, USERNAME, DESCRIPTION, TARGET_DATE, DONE)
    values(10001, 'nareun', 'Get AWS Certified', CURRENT_DATE(), false);

insert into todo (ID, USERNAME, DESCRIPTION, TARGET_DATE, DONE)
    values(10002, 'nareun', 'Get Azure Certified', CURRENT_DATE(), false);

insert into todo (ID, USERNAME, DESCRIPTION, TARGET_DATE, DONE)
    values(10003, 'nareun', 'Get GCP Certified', CURRENT_DATE(), false);

insert into todo (ID, USERNAME, DESCRIPTION, TARGET_DATE, DONE)
    values(10004, 'nareun', 'Learn DevOps', CURRENT_DATE(), false);        
