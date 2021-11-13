use master
create database GameOfLife
use GameOfLife

create table Grid
(
GridName varchar(50),
RowNO int,
ColumnNO int,
Alive BIT,
primary key(GridName,RowNO,ColumnNO)
)


go
create procedure saveState
@GridName varchar(50),
@RowNo int,
@ColumnNo int,
@Alive bit
as 
begin

	if exists
	(
	select *
	from Grid
	where [Grid].GridName=@GridName and [Grid].ColumnNO=@ColumnNo and [Grid].RowNO=@RowNo
	)
		begin
			print('value already exists with name '+ @GridName + ' row no ' + cast (@RowNo as varchar) + ' ColumnNo ' + cast(@ColumnNo as varchar) + ' Alive state  '+ cast(@Alive as varchar) )
		end;
	else
		begin
		  insert into [Grid] values (@GridName,@RowNo,@ColumnNo,@Alive)
			if exists
			(
			select *
			from Grid
			where [Grid].GridName=@GridName and [Grid].ColumnNO=@ColumnNo and [Grid].RowNO=@RowNo
			)
			    begin
			       print('value inserted successfully')
			    end;
			 else
				begin
					print('Value insertion failed')
				end;
		
		end;
end;

exec saveState @GridName='New',
@RowNo=1,
@ColumnNo=1,
@Alive=1

exec saveState @GridName='New',
@RowNo=2,
@ColumnNo=2,
@Alive=1





go
create procedure viewState
@GridName varchar(50)
as 
begin
	if exists
	(
	select *
	from Grid
	where [Grid].GridName=@GridName 
	)
		begin
			select *
			from Grid
			where [Grid].GridName=@GridName 
		end;
	else
		begin
			print('value does not exists with name '+ @GridName )
		end;
end;

exec viewState @GridName='New'




go
create procedure loadState
@GridName varchar(50)
as 
begin
	if exists
	(
	select *
	from Grid
	where [Grid].GridName=@GridName 
	)
		begin
			select *
			from Grid
			where [Grid].GridName=@GridName 
		end;
	else
		begin
			print('value does not exists with name '+ @GridName )
		end;
end;

exec loadState @GridName='New'




go
create procedure deleteState
@GridName varchar(50),
@RowNo int,
@ColumnNo int
as 
begin

	if exists
	(
	select *
	from Grid
	where [Grid].GridName=@GridName and [Grid].ColumnNO=@ColumnNo and [Grid].RowNO=@RowNo
	)
		begin
			delete from [Grid] where [Grid].GridName=@GridName and [Grid].ColumnNO=@ColumnNo and [Grid].RowNO=@RowNo 
			if exists
				(
				select *
				from Grid
				where [Grid].GridName=@GridName and [Grid].ColumnNO=@ColumnNo and [Grid].RowNO=@RowNo
				)
				begin
					print('state deletion failed')
				end;
			else
				begin 
					print('state deleted successfully')
				end;
		end;
	else
		begin
			print('value does not exists with name '+ @GridName + ' row no ' + cast (@RowNo as varchar) + ' ColumnNo ' + cast(@ColumnNo as varchar)  )
		end;
end;

exec deleteState @GridName='New',
@RowNo=1,
@ColumnNo=1



select * from [Grid]