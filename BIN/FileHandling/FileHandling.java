package FileHandling;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import Grid.Grid;
public class FileHandling {
    
    public void WriteFile(String FileName,Grid grid)
    {
        File myfile =new File(FileName);
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

        FileWriter fileWriter =new FileWriter(FileName);
        for(int i=0;i<grid.getRows();i++)
        {
            for(int j=0;j<grid.getColumns();j++)
            {
               if(grid.getCellState(i, j)==true)
               {
                fileWriter.write(i+" "+j+" "+1+"\n");
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
    public Grid ReadFile(String FileName)
    {


    }


}
