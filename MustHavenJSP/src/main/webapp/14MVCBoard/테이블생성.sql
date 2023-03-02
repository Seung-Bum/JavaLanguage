-- 한글이름의 파일을 저장할때 생기는 한글 깨짐 문제를 방지하기 위해 ofile 원본파일이름과 저장된 파일이름을 저장한다.
create table mvcboard (
	idx number primary key, 
	name varchar2(50) not null, 
	title varchar2(200) not null, 
	content varchar2(2000) not null, 
	postdate date default sysdate not null, 
	ofile varchar2(200), 
	sfile varchar2(30), 
	downcount number(5) default 0 not null, 
	pass varchar2(50) not null, 
	visitcount number default 0 not null
);