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
	stuname varchar(50),
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
insert into tb_gradeinfo(gradeID,gradeName) values ("01","初一");
insert into tb_gradeinfo(gradeID,gradeName) values ("02","初二");
insert into tb_gradeinfo(gradeID,gradeName) values ("03","初三");
insert into tb_gradeinfo(gradeID,gradeName) values ("04","高一");
insert into tb_gradeinfo(gradeID,gradeName) values ("05","高二");
insert into tb_gradeinfo(gradeID,gradeName) values ("06","高三");
insert into tb_examkinds(kindID,kindName) values ("01","第一次月考");
insert into tb_examkinds(kindID,kindName) values ("02","期中考试");
insert into tb_examkinds(kindID,kindName) values ("03","第二次月考");
insert into tb_examkinds(kindID,kindName) values ("04","期末考试");
insert into tb_subject(code,subject) values ("01","数学");
insert into tb_subject(code,subject) values ("02","语文");
insert into tb_subject(code,subject) values ("03","英文");
insert into tb_subject(code,subject) values ("04","物理");
insert into tb_subject(code,subject) values ("05","化学");
insert into tb_subject(code,subject) values ("06","生物");
insert into tb_subject(code,subject) values ("07","政治");
insert into tb_subject(code,subject) values ("08","地理");
insert into tb_subject(code,subject) values ("09","历史");
insert into tb_subject(code,subject) values ("10","计算机");
