create database projectDB
use  projectdb
create table Users(userNo int primary key identity , Name varchar(51), UserId varchar(51), Password varchar(51) )
select * from Users

create table Categories(CategoryID int primary key,CategoryName varchar(20))

insert into Categories values (1,'Travel Trivia')
insert into Categories values(2,'Gadgets geeks')
insert into Categories values(3,'Know your Law')
insert into Categories values (4,'Fictional fun')

select * from Categories


drop table Questions

create table Questions(QuestionID int primary key,QuestionText varchar(100),CategoryID int references Categories(CategoryID))
insert into Questions values(1,'Which country,other than India, has hindi as its official language?',1)
insert into Questions values(2,'Which country has most number of lakes?',1)
insert into Questions values(3,'Which is the most diverse country of the world?',1)
insert into Questions values(4,'Which city is known as Diamond City Of India?',1)
insert into Questions values(5,'The wettest inhabited place in world is',1)
insert into Questions values(6,'Where is the highest cricket ground of the world located?',1)
insert into Questions values(7,'The Electronic city of India is',1)
insert into Questions values(8,'Which is the land of no rivers?',1)
insert into Questions values(9,'Which city of india is known as Paris Of East?',1 )
insert into Questions values(10,'The Royal city of India is',1)
insert into Questions values
insert into Questions values


select * from Questions






create table Options(OptionID int primary key,OptionText varchar(20),IsAnswer int,QusetionID int references Questions(QuestionID))
insert into Options values(1,'London',0,1)
insert into Options values(2,'Fiji',1,1)
insert into Options values(3,'Canada',0,1)
insert into Options values(4,'Dubai',0,1)
insert into Options values(5,'Australia',0,2)
insert into Options values(6,'Asia',0,2) 
insert into Options values(7,'Canada',1,2)
insert into Options values(8,'America',0,2)
insert into Options values(9,'India',1,3)
insert into Options values(10,'China',0,3)
insert into Options values(3,'Japan',0,3)
insert into Options values(4,'Russia',0,3)
insert into Options values(1,'Nasik',0,4)
insert into Options values(2,'Visakhapatnam',0,4)
insert into Options values(3,'Vadodra',0,4)
insert into Options values(4,'Surat',1,4)
insert into Options values(1,'Shimla',0,5)
insert into Options values(2,'Meghalaya',1,5)
insert into Options values(3,'Tripura',0,5)
insert into Options values(4,'Dehradun',0,5)
insert into Options values(1,'Dharamshala',0,6)
insert into Options values(2,'Jammu',0,6)
insert into Options values(3,'Manali',0,6)
insert into Options values(4,'Chail',1,6)
insert into Options values(1,'Madhya Pradesh',0,7)
insert into Options values(2,'West Bengal',0,7)
insert into Options values(3,'Karnatka',1,7)
insert into Options values(4,'Nagpur',0,7)
insert into Options values(1,'Saudi Arabia',1,8)
insert into Options values(2,'Canada',0,8)
insert into Options values(3,'Switzerland',0,8)
insert into Options values(4,'America',0,8)
insert into Options values(1,'Pndicherry',1,9)
insert into Options values(2,'Ooty',0,9)
insert into Options values(3,'Madras',0,9)
insert into Options values(4,'Karnatka',0,9)
insert into Options values(1,'Guwhati',0,10)
insert into Options values(2,'Shimla',0,10)
insert into Options values(3,'Patiala',1,10)
insert into Options values(4,'Amritsar',0,10)

select * from Options
drop table Options


create table Challenges(ChallengeID int primary key identity, Date date, FromUserNo int references Users(UserNo), ToUserNo int references Users(UserNo), CategoriID int references Categories(CategoryID), Status int)
select * from Challenges