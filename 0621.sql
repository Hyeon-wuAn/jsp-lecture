drop table member;
-- drop은 commit 없이 하면 끝. 조심.
drop table board;
create table member(
    id              varchar2(100) unique not null, --제약 조건
    --id              varchar2(100) primary key,  로 바꿔쓸 수 있음(중복되면 안되고 반드시 필요)
    name            varchar2(100) not null,
    password        varchar2(100) not null,
    zonecode        number(5) not null,
    address         varchar2(300) not null,
    detailaddress   varchar2(100), 
    extraaddress    varchar2(100)
);

alter table member add profile varchar2(100);
alter table member add realprofile varchar2(100);

insert into member values ('aaa','가나다','1234','지구','001','남');
insert into member values ('bbb','니니니','1234','지구','002','여');
delete from member where password = '1203';
delete from member where name = '1203';
delete from member where id = '(null)';
delete from member where password = '1234';
delete from member;

select * from member;
select * from member where id = 'zero';

select id,name,address,lpad(zonecode,5,'0') as zonecode, detailAddress from member;
--무조건 5자리. 모자라면 왼쪽부터 0을 채운다.(lpad)
delete from member where id = 'hong' and password = '1234';

update member set name = '안현우' where id = 'zero' and password = '1203';
update member set name = '안현우우' where id = 'zero';

commit;
rollback;

-- 자동증가 auto increament my sql
create table board (
    id              number primary key,         -- 글의 고유 번호
    userId          varchar2(100) not null,     -- member id 를 통한 조회
    name            varchar2(100) not null,
    title           varchar2(100) not null,
    contents        clob not null,
    regdate         date default sysdate,
    hit             number, 
    constraint      fk_userid foreign key(userId) references member (id)
    -- constraint   [내가 정하는 포린키 이름] foreign key([현재 테이블의 컬럼명]) references [다른 테이블 명] ([다른 테이블의 컬럼명])
);



insert into board values (seq_board.nextval, 'qwerty', '쿼티', '제목을 씁니다.', 
'내용이 들어갑니다.', sysdate, 0);

select * from board where id > 239 and id <= 249 order by id desc;

select * from board;
select * from member;
delete from board where name = '아이아';
delete from board where userId = 'qwerty';
rollback;
commit;
delete from board where id = 16;
select * from board where id = 14;
select count(*) from board;

update board set hit = hit + 1 where id = 30;


select * from board where id < 262 and id > 239 order by id desc;


-- 서브쿼리 
--(select rownum as no,b.* from 
--    (select * from board order by id desc) b) where no >=1 and no <10;
--select * from board order by id desc;

select rownum, board.* from board;

select * from 
    (select rownum as no, b.* from 
    (select * from board order by id desc) b) 
    where no >=1 and no<=10 ;
    
    
select count(*) from board;

--alter table board modify contents CLOB;

-------------------------------------------------------------------------------

create table replyboard (
    id          number primary key,  -- 글의 고유 번호
    userId      varchar2(100) not null, -- member id를 통한 조회
    name        varchar2(100) not null,
    title       varchar2(300) not null,
    contents    clob not null,
    regdate     date default sysdate,
    hit         number,
    regroup     number not null,
    relevel     number not null,
    restep      number not null,
    available   number(1) default 1,
    constraint fk02_userid foreign key(userId) references member (id)
    -- constraint [내가정하는 포린키 이름] foreign key([현재 테이블의 컬럼명]) references [다른 테이블 면] ([다른 테이블의 컬럼명])
);

drop table replyboard;

select   nvl( max(regroup),0 ) as regroupmax from replyboard;

select * from replyboard;

-- 원글에 대한 댓글을 쓸때 내가 가진 regroup안의 relevel은 1 증가시킨다......


update replyboard set relevel=relevel+1 where regroup = 3 and relevel > 0;

rollback;

select rownum as no, b.* from (
    select * from replyboard order by regroup desc, relevel asc
) b;

delete from replyboard;
commit;


update replyboard set relevel = relevel + 1 where regroup = 4 and relevel > 1;

rollback;

select * from replyboard order by regroup desc, relevel asc;

select * from board;
select * from board order by id desc;
select * from board where name = '1';
select * from board where contents like '%밤%';
select * from board where name like '%현%';
select * from board where name like '현%';
select * from board where name like '%우';
select * from board where contents like '%이%';

select * from board where (name like '%현%') and (title like '%현%');
select * from board where (title like '%현%') or (title like '%밤%') or (title like '%밤%');

select * from board where no between 1 and 10;

select * from 
    (select rownum as no,b.* from (
		select * from board order by id desc, name asc
	) b) where no >= 1 and no <= 10;

select * from 
    (select rownum as no,b.* from (
		select * from board where name like '%현%' order by id desc, name asc
	) b) 
where no >= 1 and no <= 10;