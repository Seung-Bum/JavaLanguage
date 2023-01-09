--계정 생성
create user musthave identified by 1234;

--권한 관리
grant connect, resource to musthave;

-- 접속
conn musthave/1234;

-- 조회
select * from tab;

/*
테이블 삭제
*/
drop table member;
drop table board;
drop sequence seq_board_num;

--테이블 생성
create table member (
    id varchar2(10) not null,
    pass varchar2(10) not null,
    name varchar2(30) not null,
    regidate date default sysdate not null,
    primary key (id)
);

--테이블 생성
create table board (
    num number primary key,
    title varchar2(200) not null,
    content varchar2(2000) not null,
    id varchar2(10) not null,
    postdate date default sysdate not null,
    visitcount number(6)
);

--정보 변경
alter table board
    add constraint board_mem_fk foreign key (id)
    references member (id);
    
--시퀀스 생성
create sequence seq_board_num 
    increment by 1
    start with 1
    minvalue 1
    nomaxvalue
    nocycle
    nocache;
    
--실행확인
insert into member (id, pass, name) values ('musthave', '1234', '머스트해브');
insert into board  (num, title, content, id, postdate, visitcount) 
	values (seq_board_num.nextval, '제목', '콘텐츠', 'musthave', sysdate, 0);
commit;