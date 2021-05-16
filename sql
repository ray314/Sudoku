create table sudokutable (
userID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
username varchar(30),
board varchar(5),
difficulty varchar (10),
time varchar(10))

