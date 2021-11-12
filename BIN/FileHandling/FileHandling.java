package FileHandling;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import Grid.Grid;
public class FileHandling {
    
    public void viewStates (String FileName) // show all file names
    {
        File myFile = new File(FileName);
        try 
        {
            Scanner sc = new Scanner(myFile);

            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                System.out.println(line);
            }
            sc.close();
        } 
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public Grid loadstate (String FileName) // ReadFile
    {
        int row = 0;
        int column = 0;
      
        Scanner input = new Scanner(FileName); 
    
        row  = Integer.parseInt(input.next());     
        column  = Integer.parseInt(input.next());
       
        Grid temp = new Grid(row, column);

        while (input.hasNext()) 
        {
            row  = Integer.parseInt(input.next());
         
            column  = Integer.parseInt(input.next());
            
            temp.setCellState(row, column, true); // store in grid only alive cells
          
        }
        input.close();

        return temp;

    }
    public void saveState(String FileName,Grid grid)   // WriteFile
    {
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
    public void deletefile(String Filename)
    {

        File myFile = new File(Filename);

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
