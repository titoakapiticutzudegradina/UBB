CREATE DATABASE MockTest
GO

USE MockTest
GO

CREATE TABLE Groups(
    GroupId INT PRIMARY KEY,
    NrStudents INT,
    Tutor VARCHAR(100),
    Leader VARCHAR(100)
)

CREATE TABLE Student(
    StudentId INT PRIMARY KEY identity (1,1),
    Name VARCHAR(100),
    Surname VARCHAR(100),
    DateBirth DATE,
    GroupId INT FOREIGN KEY REFERENCES Groups(GroupId)
)

CREATE TABLE Courses(
    CourseId INT PRIMARY KEY identity (1,1),
    Name VARCHAR(100)
)

CREATE TABLE Professors(
    ProfessorId int primary key,
    Name varchar(100),
    Surname varchar(100),
    Func varchar(100)
)


CREATE TABLE Exams(
    --ExamId int primary key,
    StudentId int foreign key references Student(StudentId),
    CourseId int foreign key references Courses(CourseId),
    Date date,
    Mark int,
    constraint pk_Exams primary key (StudentId,CourseId)
)

CREATE TABLE Specialization(
    --SpecializationId int primary key,
    ProfessorId int foreign key references Professors(ProfessorId),
    CourseId int foreign key references Courses(CourseId),
    spec varchar(50),
    NrCredits int,
    constraint pk_Specialization primary key(ProfessorId,CourseId)
)




--insert
INSERT INTO Groups VALUES (811,30,'Anca Grad', 'Marcus Coman')
INSERT INTO Groups VALUES (812,28,'Alina Todea', 'Mihai Ghilencea')
INSERT INTO Groups VALUES (821,31,'Edit Salomon', 'Teodora Catanas')
INSERT INTO Groups VALUES (823,29,'Sonia Kerekes', 'Teodora Toda-Pop')

INSERT INTO Student VALUES (1,'Marcus','Coman','2004/12/12', 811)
INSERT INTO Student VALUES (2,'Mihai','Ghilencea','2003/06/28', 811)
INSERT INTO Student VALUES (3,'Tito','Catanas','2004/08/18', 812)
INSERT INTO Student VALUES (4,'Ana','Cocis','2004/12/24', 821)

INSERT INTO Courses VALUES (1,'English')
INSERT INTO Courses VALUES (2,'Analysis')
INSERT INTO Courses VALUES (3,'Phisics')
INSERT INTO Courses VALUES (4,'Math')

INSERT INTO Professors VALUES (1,'Anca','Grad','asd')
INSERT INTO Professors VALUES (2,'Alina','Todea','asd')
INSERT INTO Professors VALUES (3,'Edit','Salomon','asd')
INSERT INTO Professors VALUES (4,'Sonia','Kerekes','asd')


INSERT INTO Exams VALUES (1,1,1,'2025/01/13',7),
                         (2,4,1,'2025/01/13',10),
                         (3,3,4,'2024/12/14',10)

INSERT INTO Specialization VALUES (1,2,3,6),
                                  (2,2,4,6)


CREATE OR ALTER PROCEDURE P1
    @studentId int,
    @courseId int,
    @date date,
    @mark int
AS
    BEGIN
        IF NOT EXISTS(SELECT * FROM Exams WHERE CourseId = @courseId AND StudentId = @studentId)
        BEGIN
            INSERT INTO Exams VALUES (10,@studentId,@courseId,@date,@mark)
        end
        ELSE
        BEGIN
            UPDATE Exams SET Date = @date WHERE CourseId = @courseId AND StudentId = @studentId
            UPDATE Exams SET Mark = @mark WHERE CourseId = @courseId AND StudentId = @studentId
        end
    end
GO

SELECT * FROM Exams
exec P1 @studentId = 1, @courseId = 1,@date = '2025/01/13',@mark = 8
SELECT * FROM Exams
exec P1 @studentId = 2, @courseId = 3,@date = '2025/01/24',@mark = 8
SELECT * FROM Exams


create or alter proc Add_Exams @Sid int, @Cid int, @d datetime, @m float
    as
    declare @nr int
    set @nr = 0
    select @nr = count(*) from Exams where StudentId= @Sid and CourseId=@Cid
    if(@nr <> 0) begin
        update Exams
        set Date=@d , Mark= @m
        where StudentId = @Sid and CourseId = @Cid
    end
    else begin
        insert into Exams values (@Sid,@Cid,@d,@m)
    end
go

CREATE VIEW V1
AS
    SELECT Group_Code = G.GroupId , Max_Mark = E.Mark
    FROM Groups G INNER JOIN Student S on G.GroupId = S.GroupId INNER JOIN dbo.Exams E on S.StudentId = E.StudentId
    where E.Mark = 10

SELECT * FROM V1

create view vGroupMax
as
    select GroupId, Max(Mark) as maxGrade
    from Student s inner join Exams E on s.StudentId = E.StudentId
    group by GroupId
having MAX(Mark)= (select MAx(Mark) from Exams)

create or alter function F1(@M int)
returns table
as
    return
select distinct p.ProfessorId,p.Name,count(p.Name) as ProfName
from Professors p inner join Specialization S on p.ProfessorId = S.ProfessorId
group by p.ProfessorId, p.Name
having count(p.Name)>=@m
go



