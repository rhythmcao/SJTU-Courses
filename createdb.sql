create database db_student;
use db_student;

create table tb_user(
	userid varchar(50) primary key,
	username varchar(50),
	pass varchar(50)
);

create table tb_classinfo(
	classID varchar(10) primary key,
	gradeID varchar(10) 
	references tb_gradeinfo(gradeID)
	on delete set null
	on update cascade,	
	className varchar(20)
);

create table tb_examkinds(
	kindID varchar(20) primary key,
	kindName varchar(20) 
);

create table tb_gradeinfo(
	gradeID varchar(10) primary key,
	gradeName varchar(20)
);

create table tb_studentinfo(
	stuid varchar(10) primary key,
	classID varchar(10) 
	references tb_classinfo(classID)
	on delete set null
	on update cascade,
	stuname varchar(50) 
	references tb_studentinfo(stuname)
	on delete set null
	on update cascade,
	sex varchar(10) check(sex='男' or sex='女'),
	age int(4) check(age>0 and age<100),
	addr varchar(50),
	phone varchar(20) check(REGEXP_like(phone,'[0-9]+')),
	email varchar(20) check(email like '%_@%_._%')
);

create table tb_subject(
	code varchar(10) primary key,
	subject varchar(10)
);

create table tb_teacher(
	teaid varchar(10) primary key,
	classID varchar(10) 
	references tb_classinfo(classID)
	on delete set null
	on update cascade,
	teaname varchar(20),
	sex varchar(10) check(sex='男' or sex='女'),
	knowledge varchar(20),
	knowlevel varchar(20)
);

create table tb_gradeinfo_sub(
	stuid varchar(10) 
	references tb_studentinfo(stuid)
	on delete set null
	on update cascade,
	stuname varchar(50) 
	references tb_studentinfo(stuname)
	on delete set null
	on update cascade,
	kindID varchar(10) 
	references tb_examkinds(kindID)
	on delete set null
	on update cascade,
	code varchar(10) 
	references tb_subject(code)
	on delete set null
	on update cascade,
	grade float(8),
	examdate datetime(6),
	primary key(stuid,kindID,code)
);

insert into tb_user(userid,username,pass) values ("admin","adminstrator","");
insert into tb_gradeinfo(gradeID,gradeName) values ("01","大一");
insert into tb_gradeinfo(gradeID,gradeName) values ("02","大二");
insert into tb_gradeinfo(gradeID,gradeName) values ("03","大三");
insert into tb_gradeinfo(gradeID,gradeName) values ("04","大四");
insert into tb_gradeinfo(gradeID,gradeName) values ("05","研一");
insert into tb_gradeinfo(gradeID,gradeName) values ("06","研二");
insert into tb_gradeinfo(gradeID,gradeName) values ("07","博一");
insert into tb_gradeinfo(gradeID,gradeName) values ("08","博二");
insert into tb_gradeinfo(gradeID,gradeName) values ("09","博三");
insert into tb_gradeinfo(gradeID,gradeName) values ("10","博四");
insert into tb_gradeinfo(gradeID,gradeName) values ("11","博五");
insert into tb_classinfo(classID,gradeID,className) values ("010001","01","大一计算机系一班");
insert into tb_classinfo(classID,gradeID,className) values ("020001","02","大二计算机系一班");


