create database db_student;
use db_student;

create table tb_user(
	userid varchar(50) primary key,
	username varchar(50),
	pass varchar(50)
);

create table tb_classinfo(
	classID varchar(10) primary key,
	gradeID varchar(10),
	className varchar(20)
);

create table tb_examkinds(
	kindID varchar(20) primary key,
	kindName varchar(20) 
);

create table tb_gradeinfo(
	gradeID varchar(10) primary key references tb_classinfo(gradeID),
	gradeName varchar(20)
);

create table tb_gradeinfo_sub(
	stuid varchar(10) references tb_studentinfo(stuid),
	stuname varchar(50) references tb_studentinfo(stuname),
	kindID varchar(10) references tb_examkinds(kindID),
	code varchar(10) references tb_subject(code),
	grade float(8),
	examdate datetime(6),
	primary key(stuid,kindID,code)
);

create table tb_studentinfo(
	stuid varchar(10) primary key,
	classID varchar(10) references tb_classinfo(classID),
	stuname varchar(50) references tb_studentinfo(stuname),
	sex varchar(10),
	age int(4),
	addr varchar(50),
	phone varchar(20)
);

create table tb_subject(
	code varchar(10) primary key,
	subject varchar(10)
);

create table tb_teacher(
	teaid varchar(10) primary key,
	classID varchar(10) references tb_classinfo(classID),
	teaname varchar(20),
	sex varchar(10),
	knowledge varchar(20),
	knowlevel varchar(20)
);

