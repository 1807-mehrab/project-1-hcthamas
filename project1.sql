

create table ACCOUNTS(
    ACCOUNT_ID integer primary key,
    USERNAME VARCHAR2(20) unique, 
    EMAIL VARCHAR2(50) unique,
    PASSWORD varchar2(32) not null check(length(PASSWORD) > 7),
    IS_ADMIN integer 
);

create table THREADS(
     T_ID integer primary key,
     T_NAME varchar2(16) not null,
     USER_ID integer,
     CREATED timestamp
);

create table POSTS(
    P_ID integer primary key,
    MESSAGE varchar2(1000),
    T_ID integer,
    USER_ID integer, 
    FLAG integer,
    created timestamp
);

create sequence acc_seq start with 4;
create sequence thr_seq start with 4;
create sequence pst_seq start with 4;

CREATE OR REPLACE TRIGGER TR_INSERT_ACCOUNT
BEFORE INSERT ON ACCOUNTS
FOR EACH ROW
BEGIN
    SELECT ACC_SEQ.NEXTVAL INTO :NEW.ACCOUNT_ID FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER TR_INSERT_THREAD
BEFORE INSERT ON THREADS
FOR EACH ROW
BEGIN
    SELECT THR_SEQ.NEXTVAL INTO :NEW.T_ID FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER TR_INSERT_POST
BEFORE INSERT ON POSTS
FOR EACH ROW
BEGIN
    SELECT PST_SEQ.NEXTVAL INTO :NEW.P_ID FROM DUAL;
END;
/

insert into ACCOUNTS values ('Admin', 'admin@blot.com', 'admin@here', 1);
insert into ACCOUNTS values (1, 'Pleb', 'pleb@blot.com', 'password1234', 0);
insert into THREADS values(1, 'Rules', 0, current_timestamp(0));
insert into THREADS values(2, 'Applesauce', 1, current_timestamp(0));
insert into POSTS values(0, 'Welcome to Blott!', 0,0,0,current_timestamp(0));
insert into POSTS values(1, '1. Be nice.', 1,0,0,current_timestamp(0));
insert into POSTS values(2, 'i hate you LUL!!!!1!!11!', 1,1,1,current_timestamp(0));
--select * from ACCOUNTS where (username = 'Admin' or email= 'bob@blot.com') and password = 'admin@here';
--select * from THREADS inner join POSTs on THREADS.T_ID = POSTS.T_ID where THREADS.T_ID = 0;
--select * from THREADS where T_ID = 2;
insert into ACCOUNTS values (1, 'Henry', 'henry@blot.com', 'wordpass', 1);
insert into THREADS values(0, 'test', 0, current_timestamp(0));
--insert into THREADS values(10, 'test', 0, current_timestamp(0));
--select * from ACCOUNTS;
--select * from THREADS;
--select * from POSTS where T_ID = 0;
update ACCOUNTS set USERNAME = 'nothenry' where ACCOUNT_ID=3;

--drop sequence thr_seq;
--drop sequence acc_seq;
--drop sequence pst_seq;
commit;
