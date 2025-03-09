USE Movies
GO

--check a varchar
CREATE FUNCTION checkVarchar(@v VARCHAR(100))
RETURNS BIT
AS
BEGIN
    DECLARE @b BIT
    IF @v LIKE'[a-z]%[a-z]'
        SET @b=1
    ELSE
        SET @b=0
    RETURN @b
END
GO

--check email
CREATE FUNCTION checkEmail(@email VARCHAR(100))
RETURNS BIT
AS
BEGIN
    DECLARE @b BIT
    IF @email LIKE'%@gmail.com'
        SET @b=1
    ELSE
        SET @b=0
    RETURN @b
END
GO

--check age
CREATE FUNCTION checkAge(@n INT)
RETURNS INT
AS
BEGIN
    DECLARE @no INT
    IF @n>17 AND @n<=90
        SET @no = 1
    ELSE
        SET @no = 0
    RETURN @no
END
GO

--check year
CREATE FUNCTION checkYear(@year INT)
RETURNS INT
AS
BEGIN
    DECLARE @no INT
     IF @year>=1940 AND @year<=2025
        SET @no = 1
    ELSE
        SET @no = 0
    RETURN @no
END
GO

--insert Director details
CREATE OR ALTER PROCEDURE InsertDirectorMovieDetails @DirectorId INT, @Name VARCHAR(100), @Age INT, @Email VARCHAR(100),@MovieId INT, @Movie_Name VARCHAR(100),@Year INT ,@Description VARCHAR(300)
AS
BEGIN
    IF(dbo.checkAge(@Age) = 1 AND dbo.checkEmail (@Email) = 1)
    BEGIN
        INSERT INTO Director(DirectorId, Name, Age, Email) VALUES (@DirectorId,@Name,@Age,@Email)
        PRINT 'value added'
        SELECT * FROM Director
    END
    ELSE
    BEGIN
        PRINT 'the parameters are not correct'
        SELECT * FROM Director
    END
        IF(dbo.checkVarchar(@Name) = 1 AND dbo.checkYear (@Year) = 1)
    BEGIN
        DECLARE @CRId INT
        SELECT @CRId = MAX(CRaitingId) FROM Content_Raiting
        DECLARE @DId INT
        SET @DId = (SELECT DirectorId FROM Director WHERE Age < 30)
        DECLARE @PHId INT
        SELECT @PHId = MIN(PHouseId) FROM Production_House
        INSERT INTO Movie(movieid, name, year, craitingid, description, directorid, phouseid) VALUES (@MovieId,@Movie_Name,@Year,@CRId,@Description,@DirectorId,@PHId)
        PRINT 'values added'
        SELECT * FROM Movie
    END
    ELSE
    BEGIN
        PRINT 'the parameters are not correct'
        SELECT * FROM Movie
    END
END
GO

exec InsertDirectorMovieDetails 78,'Mihaiull',22,'mihaiullchiarel@gmail.com',16,'Thor',2022,'Marvel movie about Thor'
exec InsertDirectorMovieDetails 6,'Ana',5,'ana@gmail.com',16,'Thor',2022,'Marvel movie about Thor' --won't work because the age should not be validated
exec InsertDirectorMovieDetails 6,'Ana',30,'ana@yahoo.ro',16,'Thor',2022,'Marvel movie about Thor' --won't work because the email should not be validated
exec InsertDirectorMovieDetails 6,'Ana',30,'ana@yahoo.ro', 9,'Thor',100,'Marvel movie about Thor' --won't work because the year should not be validated
exec InsertDirectorMovieDetails 6,'Ana',30,'ana@yahoo.ro', 8,'1234',2011,'Marvel movie about Thor'--won't work because the name should not be validated


--view the movies with their directors and publishing house that have PG CR
CREATE OR ALTER VIEW ViewAllMoviesPG
AS
SELECT Movie_Name = M.Name,Movie_Year = M.Year,Director_Name = D.Name,PHouse_Name = PH.Name
FROM Movie M
INNER JOIN Director D ON M.DirectorId = D.DirectorId
INNER JOIN Production_House PH ON M.PHouseId = PH.PHouseId
WHERE M.CRaitingId = 1

SELECT * FROM ViewAllMoviesPG

--LogTable
CREATE TABLE LogTable(TriggerDateTime DATE, TriggerType VARCHAR(100), TableName VARCHAR(100),AffectedRecords int)

--trigger
CREATE OR ALTER TRIGGER DirectorTrigger ON Director
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    DECLARE @OperationType VARCHAR(20)
    IF EXISTS(SELECT * FROM inserted)
    BEGIN
        IF EXISTS(SELECT * FROM deleted)
            SET @OperationType = 'UPDATE'
        ELSE
            SET @OperationType = 'INSERT'
    END
    ELSE
        SET @OperationType = 'DELETE'
    IF(@OperationType = 'INSERT')
    BEGIN
        INSERT INTO LogTable(TriggerDateTime, TriggerType,TableName, AffectedRecords) VALUES (GETDATE(),@OperationType,'Director', (SELECT COUNT(*) FROM inserted))
    END
    ELSE IF(@OperationType = 'UPDATE')
    BEGIN
        INSERT INTO LogTable(TRIGGERDATETIME, TRIGGERTYPE, TABLENAME, AFFECTEDRECORDS) VALUES (GETDATE(),@OperationType,'Director',(SELECT COUNT(*) FROM inserted))
    END
    ELSE IF(@OperationType = 'DELETE')
    BEGIN
        INSERT INTO LogTable(TRIGGERDATETIME, TRIGGERTYPE, TABLENAME, AFFECTEDRECORDS) VALUES (GETDATE(),@OperationType,'Director',(SELECT COUNT(*) FROM deleted))
    END
END

INSERT INTO Director(DIRECTORID, NAME, AGE, EMAIL) VALUES (99,'Ana',45,'anaaremere@gmail.com')
SELECT * FROM Director
UPDATE Director SET Email = 'sth@gmail.com' WHERE Email IS NULL
SELECT * FROM Director
DELETE FROM Director WHERE DirectorId = 99
SELECT * FROM Director
SELECT * FROM LogTable
INSERT INTO Director(DIRECTORID, NAME, AGE, EMAIL) VALUES (98,'Ana',26,'anaarepere@gmail.com')
INSERT INTO Director(DIRECTORID, NAME, AGE, EMAIL) VALUES (45,'Ana',26,'anaarepere@gmail.com')
DELETE FROM Director WHERE DirectorId = 45


--create non-clustered index
CREATE NONCLUSTERED INDEX N_idx_Age ON Director(Age)

IF EXISTS(SELECT name FROM sys.indexes WHERE name = N'N_idx_Age')
    DROP INDEX N_idx_Age ON Director
GO


SELECT * FROM Director ORDER BY DirectorId
SELECT * FROM Director ORDER BY Name
--clustered index scan
SELECT *  FROM Director WHERE Age > 25
--clustered index seek
SELECT * FROM Director D INNER JOIN Movie M ON D.DirectorId = M.DirectorId WHERE D.Name = 'Alex Domnit'







