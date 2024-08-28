create database kosmo;

use kosmo;
-- page 8 의 구조로 테이블 book,orders,customer 생성
-- primary key,foreign key,스키마구조 정의
-- 값을 입력하기 

create table book(
bookid int not null primary key ,
bookname varchar(20) not null,
publisher varchar(20) not null,
price int not null
);

insert into book values(1,'축구의 역사','굿 스포츠',7000);
insert into book values(2,'축구 아는 여자','나무수',13000);
insert into book values(3,'축구의 이해','대한미디어',22000);
insert into book values(4,'골프 바이블','대한미디어',35000);
insert into book values(5,'피겨 교본','굿 스포츠',8000);
insert into book values(6,'역도 단계별 기술','굿 스포츠',6000);
insert into book values(7,'야구의 추억','이상미디어',20000);
insert into book values(8,'야구를 부탁해','이상미디어',13000);
insert into book values(9,'올림픽 이야기','삼성당',7500);
insert into book values(10,'Olympic Champions','Pearson',13000);

create table customer(
cusid int not null primary key,
name varchar(40) not null,
address varchar(40) not null,
phone varchar (30) 
);
insert into customer values(1,'박지성','영국 맨체스터','000-5000-0001');
insert into customer values(2,'김연아','대한민국 서울','000-6000-0001');
insert into customer values(3,'장미란','대한민국 강원도','000-7000-0001');
insert into customer values(4,'추신수','미국 클리블랜드','000-8000-0001');
insert into customer (cusid,name,address)values(5,'박세리','대한민국 대전');

create table orders(
orderid int not null primary key,
cusid int not null,
bookid int not null,
saleprice int not null,
orderate date not null,
foreign key(cusid) references customer(cusid)
on delete cascade,
foreign key(bookid) references book(bookid)
on delete cascade
);

insert into orders values(1,1,1,6000,20200701);
insert into orders values(2,1,3,21000,20200702);
insert into orders values(3,2,5,8000,20200703);
insert into orders values(4,3,6,6000,20200704);
insert into orders values(5,4,7,20000,20200705);
insert into orders values(6,1,2,12000,20200706);
insert into orders values(7,4,8,13000,20200707);
insert into orders values(8,3,10,12000,20200708);
insert into orders values(9,2,10,7000,20200709);
insert into orders values(10,3,8,13000,20200710);

