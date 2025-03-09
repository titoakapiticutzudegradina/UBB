create table R (
    R1 INT,
    R2 INT,
    R3 INT ,
    A VARCHAR(200),
    B INT,
    C INT,
    D INT
    PRIMARY KEY (R1,R2,R3)
)

INSERT INTO R VALUES (1,1,2,'Turning and turning in the widening gyre', 3, null, 1),
                     (1,2,5,'The falcon cannot hear the falconer;', 2,0,1),
                     (1,3,2,'Things fall apart; the center cannot hold;',3,4,5),
                     (2,1,2,'Mere anarchy is loosed upon the world,',0,5,null),
                     (2,4,6,'The blood-dimmed tide is loosed, and everywhere', 3,2,null),
                     (3,1,1,'The ceremony of innocence is drowned;', 1,3,1),
                     (3,3,3,'The best lack of conviction, while the worst',2,0,8),
                     (4,4,1,'Are full of passionate intensity.', 4,1,4 ),
                     (5,1,8,'Because I could not stop for Death,',3,3,null),
                     (5,2,3,'He kindly stopped for me;',0,5,5),
                     (5,5,2,'The carrige held just ourselves and immorality.',1,null,1)
select * from R
select R2, count(D) as num
from R
group by R2, C
order by num

select * from R
select R3, max(distinct B)
from R
where A like'%rs%' or C is not null or D between 0 and 5
GROUP BY R3
Having sum(b)<=3

create or alter trigger tronupdate on R
    for update as
    declare @no int = 0
    select @no = avg(d.B + i.B)
    from deleted d inner join inserted i on d.R1 = i.R1 and d.R2 = i.R2 and d.R3 = i.R3 where d.B > i.B
    print @no

update R
    set B = 10
    where R1 > R2 or R1 < R3

create table Sth(
    A1 int,
    A2 int,
    x varchar(10),
    y varchar(10),
    z varchar(10),
    v int,
    w int
)
insert into Sth values (11,11,'x1','y1','z1',10,4),
                       (11,12,'x','y1','z2',8,4),
                       (11,13,'x2','y','z',null,4),
                       (12,11,'x3','y3','z',null,20),
                       (12,13,'x2','y','z3',null,20)

select * from Sth r1 left join Sth r2 on r1.A1 = r2.A2

select distinct * from Sth r1 inner join Sth r2 on r1.A1 = r2.A2

select * from Sth r1 right outer join Sth r2 on r1.A1 = r2.A1 where r1.z like '%1*'