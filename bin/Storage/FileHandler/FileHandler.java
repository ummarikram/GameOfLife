package bin.Storage.FileHandler;

import java.util.Scanner;
import java.io.*;

import bin.BL.Grid.*;
import bin.Interfaces.StorageHandler.*; 

public class FileHandler implements StorageHandler {

    public String viewStates() // show all file names
    {
        String FileName = "bin/FileHandler/States.txt";
        String StateNames = "";

        File myFile = new File(FileName);

        try {
            Scanner sc = new Scanner(myFile);

            while (sc.hasNextLine()) {
                String State = sc.nextLine();
                StateNames = StateNames + State + "\n";
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return StateNames;
    }

    public Grid loadState(String StateName) // ReadFile
    {
        String row;
        String column;
        String FileName = "bin/FileHandler/SavedStates/" + StateName + ".txt";

        Grid State = null;
        File myFile = new File(FileName);

        try {
            Scanner sc = new Scanner(myFile);

            row = sc.next();
            column = sc.next();

            State = new Grid(Integer.parseInt(row), Integer.parseInt(column));
           
            while (sc.hasNextLine()) {
                row = sc.next();

                column = sc.next();

                State.setCellState(Integer.parseInt(row), Integer.parseInt(column), true); // store in grid only alive
                                                                                           // cells
            }

            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return State;

    }

    public void saveState(Grid grid, String StateName) // WriteFile
    {
        String FileName = "bin/FileHandler/SavedStates/" + StateName + ".txt";

        File myfile = new File(FileName);

        try {
            myfile.createNewFile();
        }

        catch (IOException e) {
            System.out.println("Unable to create new File");
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter(FileName);

            fileWriter.write(grid.getRows() + " " + grid.getColumns()); // write gide row and column size

            for (int i = 0; i < grid.getRows(); i++) {
                for (int j = 0; j < grid.getColumns(); j++) {
                    if (grid.getCellState(i, j) == true) { 
                        fileWriter.write("\n" + i + " " + j);    
                    }
                }
            }

            fileWriter.close();

            try {
                String filename = "bin/FileHandler/States.txt";
                FileWriter fw = new FileWriter(filename, true); // the true will append the new data
                fw.write(StateName + "\n");// appends the string to the file
                fw.close();
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteState(String StateName) {
        String FileName = "bin/FileHandler/SavedStates/" + StateName + ".txt";

        File myfile = new File(FileName);


        // delete from saved states folder
        if (myfile.delete()) {
            System.out.println("I have deleted: " + myfile.getName());

            // remove this state from states file
            try {
                File file = new File("bin/FileHandler/States.txt");

                File temp = File.createTempFile("file", ".txt", file.getParentFile());

                String charset = "UTF-8";

                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));

                PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(temp), charset));

                for (String line; (line = reader.readLine()) != null;) {
                    
                    if (!line.equals(StateName))
                    {
                        writer.println(line);
                    }
                }

                reader.close();
                writer.close();

                file.delete();
                temp.renameTo(file);

            } catch (IOException e) {
               
                e.printStackTrace();
            }

        } else {
            System.out.println("Some problem occurred while deleting the file..!");
        }

    }

}
