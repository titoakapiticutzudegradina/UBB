USE Movies
go

--insert
INSERT INTO Director (DirectorId, Name, Age, Email) VALUES
    (1, 'Tim Burton', 67, 'ana@gmail.com'),
    (2, 'Christopher Nolan', 56, 'tito@gmail.com'),
    (3,'Alex Domnit', 21,'mihai@gmail.com'),
    (4, 'Alex Domnit', 80,'alex@gmail.com')

INSERT INTO Content_Raiting (CRaitingId, Name, Description) VALUES
    (1, 'PG', 'General public, no violent content'),
    (2,'N17','Not allowed under 17 without parents consent'),
    (3,'R', 'Restricted for under 18')

INSERT INTO Production_House (PHouseId, Name) VALUES
    (1,'A24'),
    (2,'BlumHouse Production'),
    (3, 'Warner Brothers')

INSERT INTO Actor (ActorId, Name, Surname, Age) VALUES
    (1,'Johnny','Depp', 63),
    (2,'Daniel', 'Radcliffe', 35 ),
    (3,'Jenna', 'Ortega',22)

INSERT INTO Movie (MovieId, Name, Year, CRaitingId, Description, DirectorId, PHouseId) VALUES
    (4, 'Charlie and The Chocolate Factory',  2005, 1, 'Willy Wonka gives five golden tickets for five lucky children', 1,3 ),
    (5,'Harry Potter and The Order of The Phoenix', 2007,1,'Voldemort is alive and nobody believes it', 3,3),
    (6,'Tenet',2020,2,'Objects can be manipulated as weapons in the future', 2,1)

INSERT INTO MovieActor (MovieActorId, MovieId, ActorId) VALUES
    (1,4,1),
    (2,4,3),
    (3,5,2),
    (4,6,2)

--update
--update the name where the age is less than 30 and name is not null
SELECT * FROM Actor
UPDATE Actor
SET Name = 'Sabrina'
WHERE Age <= 30 OR Name IS NULL
SELECT * FROM Actor

--update name where the age is in the list (35,63) and the surname is Radcliffe
SELECT * FROM Actor
UPDATE Actor
SET Name = 'Mihai'
WHERE Age IN (35, 63) AND Surname = 'Radcliffe'
SELECT * FROM Actor

UPDATE Actor
SET Name = 'Johnny' WHERE Age = 63
UPDATE Actor
SET Name = 'Daniel' WHERE Age = 35
UPDATE Actor
SET Name = 'Jenna' WHERE Age = 22

--update the age where the name ends in t or the age is between 60 and 70
SELECT * FROM Director
UPDATE Director
SET Age = 30
WHERE Name  LIKE '%_t' OR Age BETWEEN 60 AND 70
SELECT * FROM Director


--delete DO NEW TABLES FROM DELETE
--delete the record where the name starts with A OR THE AGE IS GRATER THAN 60
CREATE TABLE DirectorForDelete(
    DirectorId INT PRIMARY KEY,
    Name VARCHAR(150),
    Age INT
)

INSERT INTO DirectorForDelete (DirectorId, Name, Age) VALUES
    (1, 'Tim Burton', 67),
    --(2, 'Christopher Nolan', 56),
    (3,'Alex Domnit', 21),
    (4, 'Alex Domnit', 80)

SELECT * FROM DirectorForDelete
DELETE FROM DirectorForDelete
WHERE Name LIKE 'A_%' OR Age >= 60
SELECT * FROM DirectorForDelete

--delete the record where the name is null or the age is between 20 and 40
CREATE TABLE ActorForDelete(
    ActorId INT NOT NULL PRIMARY KEY,
    Name VARCHAR(100),
    Surname VARCHAR(100),
    Age INT,

)

INSERT INTO ActorForDelete (ActorId, Name, Surname, Age) VALUES
    --(1,'Johnny','Depp', 63),
    (2,'Daniel', 'Radcliffe', 35 ),
    (3,'Jenna', 'Ortega',22)

SELECT * FROM ActorForDelete
DELETE ActorForDelete
WHERE Age BETWEEN 20 AND 40 OR Name IS NULL
SELECT * FROM ActorForDelete


--select
--union between the records where the name starts with J and the records that have the age >= 30
SELECT * FROM Actor
WHERE Name LIKE 'J_%'
UNION
SELECT * FROM Actor
WHERE Age >= 40

--intersection where the record has the name "Alex Domnit" and the age less than or equal to 70
SELECT * FROM Director
WHERE Name = 'Alex Domnit'
INTERSECT
SELECT * FROM Director
WHERE Age <= 70

--show the records where the age is grater than 30 but the name doesn't start with J
SELECT * FROM Actor
WHERE Age >= 30
EXCEPT
SELECT * FROM Actor
WHERE Name LIKE 'J_%'

--INNER JOIN
SELECT * FROM Movie m INNER JOIN Content_Raiting cr ON m.CRaitingId = cr.CRaitingId

--LEFT OUTER JOIN
SELECT * FROM Production_House ph LEFT OUTER JOIN Movie m ON ph.PHouseId = m.PHouseId

--RIGHT OUTER JOIN
SELECT * FROM  Director D RIGHT OUTER JOIN Movie m ON D.DirectorId = m.DirectorId ORDER BY D.Name

--FULL OUTER JOIN
SELECT * FROM Content_Raiting cr FULL OUTER JOIN Movie m ON cr.CRaitingId = m.CRaitingId FULL OUTER JOIN Production_House ph ON m.PHouseId = ph.PHouseId

--IN
SELECT * FROM Director

SELECT * FROM Director d
WHERE d.Age > 30 AND d.DirectorId IN (SELECT m.DirectorId FROM Movie m)

--EXIST
SELECT * FROM Actor
SELECT * FROM Actor a
WHERE a.ActorId > 1 AND EXISTS (SELECT * FROM MovieActor m WHERE m.ActorId = a.ActorId )

--FROM
SELECT R.Name FROM
(SELECT m.Name, m.Year FROM Movie m WHERE m.PHouseId = 3 )R

--GROUP BY
SELECT D.DirectorId, D.Name FROM Director D RIGHT OUTER JOIN Movie m ON D.DirectorId = m.DirectorId
GROUP BY D.DirectorId, D.Name

--GROUP BY WITH HAVING
SELECT A.Name, A.Surname, AVG(Age) FROM Actor A
GROUP BY A.Name, A.Surname
HAVING AVG(Age)>(SELECT MIN(Age) FROM Actor)

SELECT * FROM Director
SELECT d.Name, d.Age FROM Director d
GROUP BY d.Name, d.Age
HAVING MIN(d.Age) > 30

--DISTINCT
SELECT DISTINCT d.Name FROM Director d

--ORDER BY
SELECT a.Name, a.Age FROM Actor a
ORDER BY a.Name DESC

--TOP
SELECT TOP 2 m.Name, m.Year FROM Movie m ORDER BY m.CRaitingId

