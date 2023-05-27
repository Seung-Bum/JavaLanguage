--user2에 테이블 생성 권한은 있으나 데이터 insert에 대한 권한이 없을 경우 아래 쿼리문 실행
--ALTER USER user2 DEFAULT TABLESPACE USERS QUOTA UNLIMITED ON USERS;

CREATE TABLE MEMBER
(
	NO NUMBER(4) NOT NULL,
	name VARCHAR2(10),
	email VARCHAR2(100),
	password VARCHAR2(15),
	registDate DATE
);

CREATE TABLE BOARD
(
	NO NUMBER(4) NOT NULL,
	title VARCHAR2(50),
	content VARCHAR2(4000),
	viewCount NUMBER(4),
	createDate DATE
);

CREATE TABLE review (
	NO NUMBER NOT NULL PRIMARY KEY,
	board_no NUMBER NOT NULL,
	MEMBER_no NUMBER NOT NULL,
	review_content varchar(100)
);

ALTER TABLE BOARD ADD writer varchar2(100);
ALTER TABLE MEMBER ADD CONSTRAINT NO PRIMARY KEY (NO);
ALTER TABLE BOARD ADD PRIMARY KEY (NO);
ALTER TABLE review ADD CONSTRAINT board_no FOREIGN key(board_no) REFERENCES board (NO);
ALTER TABLE review ADD CONSTRAINT member_no FOREIGN key(member_no) REFERENCES member (NO);

-- 자동증가
--ALTER TABLE auto_test MODIFY id INT NOT NULL AUTO_INCREMENT;

--member 입력
INSERT INTO MEMBER (NO, name, email, password, registDate)
VALUES(1, 'user1', 'user1@naver.com', '1234', to_date('2023-05-01 00:30:30', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO MEMBER (NO, name, email, password, registDate)
VALUES(2, 'user2', 'user2@daum.net', '1234', sysdate);

-- board 입력
INSERT INTO BOARD (NO, title, content, viewCount, createDate, writer)
VALUES(1, '게시글 처음올림', '안녕하세요 글 처음 올려보네요', 0, sysdate);
INSERT INTO BOARD (NO, title, content, viewCount, createDate)
VALUES(2, '게시글 올립니다.', '호호호 반갑습니다.', 0, sysdate);

-- review 입력
INSERT INTO review (NO, BOARD_NO, MEMBER_NO, REVIEW_CONTENT)
VALUES(1, 1, 1, '댓글을 남겨요');
INSERT INTO review (NO, BOARD_NO, MEMBER_NO, REVIEW_CONTENT)
VALUES(2, 1, 1, '댓글을 또 남겨요');
INSERT INTO review (NO, BOARD_NO, MEMBER_NO, REVIEW_CONTENT)
VALUES(3, 1, 2, '저는 user2 입니다. 반갑습니다.');

UPDATE BOARD SET writer = 'user1@naver.com' WHERE NO = 1;
UPDATE BOARD SET writer = 'user2@daum.net' WHERE NO = 2;

ALTER TABLE	BOARD ADD photo_image blob;

COMMIT;