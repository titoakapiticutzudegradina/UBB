USE Movies
GO


CREATE PROCEDURE AddColumn
AS
BEGIN
    IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'Actor' AND COLUMN_NAME = 'Address')
    BEGIN
        ALTER TABLE Actor
        ADD Address VARCHAR(100) NULL;
    END
END
GO

CREATE PROCEDURE RemoveColumn
AS
BEGIN
    IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'Actor' AND COLUMN_NAME = 'Address')
    BEGIN
        ALTER TABLE Actor
        DROP COLUMN Address;
    END
END
GO


CREATE PROCEDURE AddDefault_constraints
AS
BEGIN
    ALTER TABLE Director
    ADD CONSTRAINT DF_Director_Email DEFAULT 'example@example.com' FOR EMAIL;
END
GO

CREATE PROCEDURE RemoveDefault_constraints
AS
BEGIN
    ALTER TABLE Director
    DROP CONSTRAINT DF_Director_Email;
END
GO


CREATE PROCEDURE CreateTable
AS
BEGIN
    CREATE TABLE Clients(
        ClientId INT PRIMARY KEY,
        Name VARCHAR(100),
        Age INT
    );
END
GO

CREATE PROCEDURE DropTable
AS
BEGIN
    DROP TABLE IF EXISTS Clients;
END
GO

CREATE PROCEDURE AddForeignKey
AS
BEGIN
    ALTER TABLE Movie
    ADD CONSTRAINT FK_Movie_CR FOREIGN KEY (CRaitingId) REFERENCES Content_Raiting(CRaitingId);
END
GO

CREATE PROCEDURE RemoveForeignKey
AS
BEGIN
    ALTER TABLE Movie
    DROP CONSTRAINT FK_Movie_CR;
END
GO

CREATE TABLE DataBaseVersion(
    VersionNumber INT PRIMARY KEY
);
INSERT INTO DataBaseVersion (VersionNumber) VALUES (1);
GO

CREATE TABLE Version(versionFrom INT, versionTo INT, doProc nvarchar(100), undoProc nvarchar(100))
INSERT INTO Version VALUES
                        (0,1, 'AddColumn','RemoveColumn' ),
                        (1,2,'AddDefault_constraints','RemoveDefault_constraints'),
                        (2,3,'CreateTable','DropTable'),
                        (3,4,'AddForeignKey','RemoveForeignKey')
GO

CREATE OR ALTER PROCEDURE ChangeDataBaseVersion
    @TargetVersion INT
AS
BEGIN
    IF NOT EXISTS(SELECT * FROM DataBaseVersion WHERE VersionNumber = @TargetVersion)
    BEGIN
        RAISERROR ('Version does not exist',16,1)
    END
   DECLARE @CurrentVersion INT;
   SELECT @CurrentVersion = VersionNumber FROM DataBaseVersion;

   --Handling forward transitions
    IF @TargetVersion > @CurrentVersion
    BEGIN
       WHILE @TargetVersion <> @CurrentVersion
        BEGIN
           DECLARE @DoProc NVARCHAR(100);

           SELECT @DoProc = doProc FROM Version
            WHERE versionFrom = @CurrentVersion AND versionTo = @CurrentVersion + 1;

           IF @DoProc IS NOT NULL
            BEGIN
                EXEC @DoProc
                PRINT 'Executed procedures: ' + @DoProc;
            END

            --Increment version
            SET @CurrentVersion = @CurrentVersion + 1;
                UPDATE DataBaseVersion SET VersionNumber = @TargetVersion;
        END
    END
    --Handling backward transitions
    ELSE IF @TargetVersion < @CurrentVersion
    BEGIN
        WHILE @CurrentVersion <> @TargetVersion
        BEGIN
            DECLARE @UndoProc NVARCHAR(100);

            SELECT @UndoProc = undoProc FROM Version
            WHERE versionFrom = @CurrentVersion - 1 AND versionTo = @CurrentVersion

            IF @UndoProc IS NOT NULL
            BEGIN
                EXEC @UndoProc
                PRINT 'Executed procedures: ' + @UndoProc;
            END

            --Decrement version
            SET @CurrentVersion = @CurrentVersion - 1;
                UPDATE DataBaseVersion SET VersionNumber = @TargetVersion;
        END
    END

    PRINT 'DataBase version reached the target version: ' + CAST(@TargetVersion AS VARCHAR(10));
END
GO


    EXEC ChangeDataBaseVersion @TargetVersion = -1;


SELECT * FROM Version
DROP PROCEDURE ChangeDataBaseVersion