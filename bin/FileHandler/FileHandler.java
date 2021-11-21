package FileHandler;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import Grid.Grid;
import STORAGE_INTERFACE.STORAGE_INTERFACE;

public class FileHandler implements STORAGE_INTERFACE{
    
    public String viewStates() // show all file names
    {
        String FileName = "States.txt";
        String StateNames = "";

        File myFile = new File(FileName);
        
        try 
        {
            Scanner sc = new Scanner(myFile);

            while(sc.hasNextLine())
            {
                String State = sc.nextLine();
                StateNames = StateNames + State + "\n";
            }

            sc.close();
        } 
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return StateNames;
    }
    public Grid loadState (String StateName) // ReadFile
    {
        String row ;
        String column ;
        String FileName = StateName + ".txt";
      
        Grid State = null;
        File myFile = new File(FileName);
       
        try 
        {
            Scanner sc = new Scanner(myFile);
         
            row  = sc.next();     
            column  = sc.next();

            State = new Grid();
            State.ChangeDimensions(Integer.parseInt(row), Integer.parseInt(column));

            System.out.println(row + "  " + column + "\n");
            
            while(sc.hasNextLine())
            {
                row  = sc.next();
         
                column  = sc.next();
                
                State.setCellState(Integer.parseInt(row),  Integer.parseInt(column), true); // store in grid only alive cells
  
                System.out.println(row + "  " + column + "\n");
            }

            sc.close();

        } 
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }


        return State;

    }
    public void saveState(Grid grid, String StateName)   // WriteFile
    {
        String FileName = StateName + ".txt";

        File myfile = new File(FileName);
        
        try
        {
            myfile.createNewFile();
        }
        
        catch(IOException e)
        {
           System.out.println("Unable to create new File");
           e.printStackTrace();
        }
        try
        {
            FileWriter fileWriter = new FileWriter(FileName);

            fileWriter.write(grid.getRows() + " " + grid.getColumns() + "\n"); // write gide row and column size

            for ( int i = 0 ; i < grid.getRows() ; i++)
            {
                for ( int j = 0 ; j < grid.getColumns() ; j++ )
                {
                    if ( grid.getCellState(i, j) == true)
                    {
                        fileWriter.write(i + " " + j + "\n" );
                    }
                }
            }
      
            fileWriter.close();
        }
        catch(IOException e)
        {
           e.printStackTrace();
        }

    }
    public void deleteState(String StateName)
    {
        String FileName = StateName + ".txt";

        File myFile = new File(FileName);

        if ( myFile.delete() )
        {
            System.out.println("I have deleted: " + myFile.getName());
        }
        else
        {
            System.out.println("Some problem occurred while deleting the file..!");
        }

    }

}
