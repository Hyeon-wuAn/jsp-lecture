drop table member;
-- drop�� commit ���� �ϸ� ��. ����.
drop table board;
create table member(
    id              varchar2(100) unique not null, --���� ����
    --id              varchar2(100) primary key,  �� �ٲ㾵 �� ����(�ߺ��Ǹ� �ȵǰ� �ݵ�� �ʿ�)
    name            varchar2(100) not null,
    password        varchar2(100) not null,
    zonecode        number(5) not null,
    address         varchar2(300) not null,
    detailaddress   varchar2(100), 
    extraaddress    varchar2(100)
);

alter table member add profile varchar2(100);
alter table member add realprofile varchar2(100);

insert into member values ('aaa','������','1234','����','001','��');
insert into member values ('bbb','�ϴϴ�','1234','����','002','��');
delete from member where password = '1203';
delete from member where name = '1203';
delete from member where id = '(null)';
delete from member where password = '1234';
delete from member;

select * from member;
select * from member where id = 'zero';

select id,name,address,lpad(zonecode,5,'0') as zonecode, detailAddress from member;
--������ 5�ڸ�. ���ڶ�� ���ʺ��� 0�� ä���.(lpad)
delete from member where id = 'hong' and password = '1234';

update member set name = '������' where id = 'zero' and password = '1203';
update member set name = '�������' where id = 'zero';

commit;
rollback;

-- �ڵ����� auto increament my sql
create table board (
    id              number primary key,         -- ���� ���� ��ȣ
    userId          varchar2(100) not null,     -- member id �� ���� ��ȸ
    name            varchar2(100) not null,
    title           varchar2(100) not null,
    contents        clob not null,
    regdate         date default sysdate,
    hit             number, 
    constraint      fk_userid foreign key(userId) references member (id)
    -- constraint   [���� ���ϴ� ����Ű �̸�] foreign key([���� ���̺��� �÷���]) references [�ٸ� ���̺� ��] ([�ٸ� ���̺��� �÷���])
);



insert into board values (seq_board.nextval, 'qwerty', '��Ƽ', '������ ���ϴ�.', 
'������ ���ϴ�.', sysdate, 0);

select * from board where id > 239 and id <= 249 order by id desc;

select * from board;
select * from member;
delete from board where name = '���̾�';
delete from board where userId = 'qwerty';
rollback;
commit;
delete from board where id = 16;
select * from board where id = 14;
select count(*) from board;

update board set hit = hit + 1 where id = 30;


select * from board where id < 262 and id > 239 order by id desc;


-- �������� 
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
    id          number primary key,  -- ���� ���� ��ȣ
    userId      varchar2(100) not null, -- member id�� ���� ��ȸ
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
    -- constraint [�������ϴ� ����Ű �̸�] foreign key([���� ���̺��� �÷���]) references [�ٸ� ���̺� ��] ([�ٸ� ���̺��� �÷���])
);

drop table replyboard;

select   nvl( max(regroup),0 ) as regroupmax from replyboard;

select * from replyboard;

-- ���ۿ� ���� ����� ���� ���� ���� regroup���� relevel�� 1 ������Ų��......


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
select * from board where contents like '%��%';
select * from board where name like '%��%';
select * from board where name like '��%';
select * from board where name like '%��';
select * from board where contents like '%��%';

select * from board where (name like '%��%') and (title like '%��%');
select * from board where (title like '%��%') or (title like '%��%') or (title like '%��%');

select * from board where no between 1 and 10;

select * from 
    (select rownum as no,b.* from (
		select * from board order by id desc, name asc
	) b) where no >= 1 and no <= 10;

select * from 
    (select rownum as no,b.* from (
		select * from board where name like '%��%' order by id desc, name asc
	) b) 
where no >= 1 and no <= 10;