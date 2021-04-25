INSERT INTO notice(id, writer, title, content, created_date, modified_date)
VALUES (null, '작성자1', '제목1', '내용1', now(), now()),
       (null, '작성자2', '제목2', '내용2', now(), now()),
       (null, '작성자3', '제목3', '내용3', now(), now()),
       (null, '작성자4', '제목4', '내용4', now(), now()),
       (null, '작성자5', '제목5', '내용5', now(), now()),
       (null, '작성자6', '제목6', '내용6', now(), now()),
       (null, '작성자7', '제목7', '내용7', now(), now()),
       (null, '작성자8', '제목8', '내용8', now(), now()),
       (null, '작성자9', '제목9', '내용9', now(), now()),
       (null, '작성자10', '제목10', '내용10', now(), now()),
       (null, '작성자11', '제목11', '내용11', now(), now()),
       (null, '작성자12', '제목12', '내용12', now(), now()),
       (null, '작성자13', '제목13', '내용13', now(), now());

INSERT INTO notice(id, writer, title, content, created_date, modified_date)
SELECT null, writer, title, content, created_date, modified_date
  FROM notice;
INSERT INTO notice(id, writer, title, content, created_date, modified_date)
SELECT null, writer, title, content, created_date, modified_date
  FROM notice;
INSERT INTO notice(id, writer, title, content, created_date, modified_date)
SELECT null, writer, title, content, created_date, modified_date
  FROM notice;
INSERT INTO notice(id, writer, title, content, created_date, modified_date)
SELECT null, writer, title, content, created_date, modified_date
  FROM notice;