
USE Movies
go

CREATE TABLE Director(
    DirectorId INT PRIMARY KEY,
    Name VARCHAR(150),
    Age INT
)

CREATE TABLE Content_Rating(
    CRatingId INT PRIMARY KEY,
    Name VARCHAR(100),
    Description VARCHAR(150),
    
)

CREATE TABLE Production_House(
    PHouseId INT PRIMARY KEY,
    Name VARCHAR(200),
)

--1:m relation
CREATE TABLE Movie(
    MovieId INT PRIMARY KEY,
    Name VARCHAR(150),
    Time TIME,
    Year INT,
    CRatingId INT FOREIGN KEY REFERENCES Content_Raiting(CRaitingId),
    Genre VARCHAR(100),
    Description VARCHAR(300),
    DirectorId INT FOREIGN KEY REFERENCES Director(DirectorId),
    PHouseId INT FOREIGN KEY REFERENCES Production_House(PHouseId)
)

CREATE TABLE Actor(
    ActorId INT PRIMARY KEY,
    Name VARCHAR(100),
    Surname VARCHAR(100),
    Age INT,

)

CREATE TABLE Genre(
    GenreId INT PRIMARY KEY,
    Name VARCHAR(100),

)

--m:n relation
CREATE TABLE MovieActor(
    MovieActorId INT PRIMARY KEY,
    MovieId INT FOREIGN KEY REFERENCES Movie(MovieId), 
    ActorId INT FOREIGN KEY REFERENCES Actor(ActorId)
)

CREATE TABLE MovieGenre(
    MovieGenreId INT PRIMARY KEY,
    MovieId INT FOREIGN KEY REFERENCES Movie(MovieId),
    GenreId INT FOREIGN KEY REFERENCES Genre(GenreId)
)

ALTER TABLE Movie
DROP COLUMN Time

ALTER TABLE Movie
DROP COLUMN Genre

ALTER TABLE Director
ADD Email VARCHAR(100)





