# This is a database project implementing 

# Student Management System
=====================================================================
## Project Structure
---------------------------------------------------------------------
> images: Images used in this project
>
> src: Source code
>> data: package containing classes to auto-generate sql data
>>
>> model: package containing classes providing data structures to store objects for various tables
>>
>> util: package containing classes used to manipulate sql operations and provide interfaces
>>
>> view: package containing classes used to construct GUI Jframes
>>
>> AppStu.java: entrance class

> createdb.sql: sql script file, create database db_student and some initializations
----------------------------------------------------------------------
## To run this project
1. Download and install MySQL, run the createdb.sql script to create database db_student
2. Run /src/data/ConnectToMySQL.java to autogenerate data
3. Run /src/AppStu.java

__Attention__: 
In ConnectToMySQL.java and AppStu.java, we access MySQL with USER='root' and PASSWORD='123456'. Maybe you need to change it.
-----------------------------------------------------------------------
## Initial login account
>userid: admin
>
>password: 
* no pass word
-----------------------------------------------------------------------
## Other stuff
* In our database instance, gradeID is numbered '01','02','03'......
* In our database instance, classID is numbered gradeID+'0001', gradeID+'0002', ......
* In our database instance, stuid is numbered classID+'01', classID+'02', ......
* In our database instance, teaid is numbered '1'+classID, '2'+classID, ......
* If you want to change this method, then you might change the JF_view_query_grade_mx.java and JF_view_query_grade_hz.java files in package view and ProduceMaxBh.java in package util