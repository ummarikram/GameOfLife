create database GameOfLife;
use  Gameoflife;
CREATE TABLE Grid
(
GridName varchar(50),
RowNO int,
ColumnNO int,
Alive BIT,
primary key(GridName,RowNO,ColumnNO)
);
DROP PROCEDURE savestate

DELIMITER $$
use GameofLife $$
create procedure saveState
(
IN GridNamein varchar(50),
IN RowNoin int,
IN ColumnNoin int,
IN Alivein bit
)
BEGIN
	REPLACE into Grid(GridName,RowNo,ColumnNo,Alive) values(GridNamein,RowNoin,ColumnNoin,Alivein);
END $$

call saveState ('New',1,1,1);
call saveState ('New',9,1,1);
call saveState ('Newtest',2,2,1);

--  drop procedure viewState

DELIMITER $$
use gameoflife $$
create procedure viewState
(
OUT NoOfStates INT
)
begin

	set NoOfStates= select count(Grid.GridName) from Grid order by Grid.GridName;

	select Grid.GridName
	from Grid
	order by Grid.GridName;
    
end;

call viewState;

DELIMITER $$
use gameoflife $$
create procedure loadState
(
IN GridNamein varchar(50),
OUT Result bit
)
begin
	if exists
	(
	select Grid.RowNO, Grid.ColumnNO, Grid.Alive
	from Grid
	where Grid.GridName=GridNamein 
	)
	
	then
		set Result=1;
		select Grid.RowNO, Grid.ColumnNO, Grid.Alive
		from Grid
		where Grid.GridName=GridNamein;
	else
		set Result=0;
	end if;
end;

call loadState ('New');

DELIMITER $$
use gameoflife $$
create procedure deleteState
(
IN GridNamein varchar(50),
OUT Result bit
)
begin

	if exists
	(
	select *
	from Grid
	where Grid.GridName=GridNamein 
	)
	then
		set Result=1;
		delete from Grid where Grid.GridName=GridNamein;
	else
		set Result=0;
		
end if;
end;

call deleteState ('New');

select * from grid
