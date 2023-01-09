--���� ����
create user musthave identified by 1234;

--���� ����
grant connect, resource to musthave;

-- ����
conn musthave/1234;

-- ��ȸ
select * from tab;

/*
���̺� ����
*/
drop table member;
drop table board;
drop sequence seq_board_num;

--���̺� ����
create table member (
    id varchar2(10) not null,
    pass varchar2(10) not null,
    name varchar2(30) not null,
    regidate date default sysdate not null,
    primary key (id)
);

--���̺� ����
create table board (
    num number primary key,
    title varchar2(200) not null,
    content varchar2(2000) not null,
    id varchar2(10) not null,
    postdate date default sysdate not null,
    visitcount number(6)
);

--���� ����
alter table board
    add constraint board_mem_fk foreign key (id)
    references member (id);
    
--������ ����
create sequence seq_board_num 
    increment by 1
    start with 1
    minvalue 1
    nomaxvalue
    nocycle
    nocache;
    
--����Ȯ��
insert into member (id, pass, name) values ('musthave', '1234', '�ӽ�Ʈ�غ�');
insert into board  (num, title, content, id, postdate, visitcount) 
	values (seq_board_num.nextval, '����', '������', 'musthave', sysdate, 0);
commit;