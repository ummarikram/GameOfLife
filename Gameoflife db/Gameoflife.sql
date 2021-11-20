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
if exists
(
	select *
	from Grid
	where Grid.GridName=GridNamein and Grid.ColumnNO=ColumnNoin and Grid.RowNO=RowNoin
)
then 		
	set @print=concat("value already exists with name ",GridNamein, " row no ", RowNoin);
	set @print2=concat(" ColumnNo ",ColumnNoin," Alive state ",cast(Alivein as signed) );
	select @print,@print2 ;
else
	insert into Grid(GridName,RowNo,ColumnNo,Alive) values(GridNamein,RowNoin,ColumnNoin,Alivein);
	if exists
	(
	select *
	from Grid
	where Grid.GridName=GridNamein and Grid.ColumnNO=ColumnNoin and Grid.RowNO=RowNoin
	) then
			set @print3=" value inserted successfully ";
			select @print3;
	  else 
				set @print4=" error while insertion ";
				select @print4;
	  end if;
end if;
END $$

call saveState ('New',1,1,1);
call saveState ('New',2,1,1);
call saveState ('Newtest',2,2,1);

-- drop procedure viewState

DELIMITER $$
use gameoflife $$
create procedure viewState
(
IN GridNamein varchar(50)
)
begin
	if exists
	(
	select *
	from Grid
	where Grid.GridName=GridNamein 
	)
		then
			select *
			from Grid
			where Grid.GridName=GridNamein ;
	else
			set @print=("value does not exists with name ", GridName) ;
			select @print;
		end if;
end;

call viewState('New');

DELIMITER $$
use gameoflife $$
create procedure loadState
(
IN GridNamein varchar(50)
)
begin
	if exists
	(
	select *
	from Grid
	where Grid.GridName=GridNamein 
	)
		then
			select *
			from Grid
			where Grid.GridName=GridNamein;
	else
		set @print=('value does not exists with name ',GridNamein );
	end if;
end;

call loadState ('New');




DELIMITER $$
use gameoflife $$
create procedure deleteState
(
IN GridNamein varchar(50)
)
begin

	if exists
	(
	select *
	from Grid
	where Grid.GridName=GridNamein 
	)
		then
			delete from Grid where Grid.GridName=GridNamein  ;
			if exists
				(
				select *
				from Grid
				where Grid.GridName=GridNamein 
				)
				then
					set @print=('state deletion failed');
                    select @print;
			else
					set @print=('state deleted successfully');
                    select @print;
			end if;
	else
		set @print=('value does not exists with name '+ GridName );
		
end if;
end;

call deleteState ('New');

select * from grid
