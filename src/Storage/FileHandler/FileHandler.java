//Author: Huma Karim
//This file includes the implementation of saving and retrieving data from txt files. here files can also be deleted 
//and new files can be created. if a file is created, detail is stored in txt file and its name is stored in state.txt.
//similarly, if a file is deleted then its name is also deletd from states.txt.

package src.Storage.FileHandler;

import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONObject;
import src.BL.Grid.*;
import src.Interfaces.JSONInterface.JSONInterface;
import src.Interfaces.StorageInterface.*;

import java.io.*;

public class FileHandler implements StorageInterface {

    private String pathAllStates = "src/Storage/FileHandler/States.txt";
    private String pathSavedStaes = "src/Storage/FileHandler/SavedStates/";
    JSONInterface FHParser;

    public FileHandler(JSONInterface Parser) {
        FHParser = Parser;
    }

    public JSONObject viewStates() // show all file names
    {
        ArrayList<String> StateNames = new ArrayList<String>();

        File myFile = new File(pathAllStates);

        try {
            Scanner sc = new Scanner(myFile);

            while (sc.hasNextLine()) {
                String State = sc.nextLine();
                StateNames.add(State);
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return FHParser.ArraylistTOjson(StateNames);
    }

    public JSONObject loadState(JSONObject GridName) // ReadFile
    {
        String row;
        String column;
        
        String StateName = (String) GridName.get("StateName");
        String FileName = pathSavedStaes + StateName + ".txt";

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

        return FHParser.GridTOJSON(State);

    }

    public void saveState(JSONObject state) // WriteFile
    {

        JSONObject JStateName = (JSONObject) state.get("StateName");

        String StateName = (String) JStateName.get("StateName");

        String FileName = pathSavedStaes + StateName + ".txt";

        File myfile = new File(FileName);

        try {
            myfile.createNewFile();
        }

        catch (IOException e) {
            System.out.println("Unable to create new File");
            e.printStackTrace();
        }
        try {

            JSONObject JGrid = (JSONObject) state.get("Grid");

            int rows = (int) JGrid.get("MaxRows");

            int columns = (int) JGrid.get("MaxCols");

            FileWriter fileWriter = new FileWriter(FileName);

            fileWriter.write(rows + " " + columns); // write gide row and column size

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {

                    String Location = i + " " + j;

                    if (JGrid.get(Location) != null) {
                        fileWriter.write("\n" + i + " " + j);
                    }
                    
                }
            }

            fileWriter.close();

            try {
                FileWriter fw = new FileWriter(pathAllStates, true); // the true will append the new data
                fw.write(StateName + "\n");// appends the string to the file
                fw.close();
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteState(JSONObject Name) {

        String StateName = (String) Name.get("StateName");

        String FileName = pathSavedStaes + StateName + ".txt";

        File myfile = new File(FileName);

        // delete from saved states folder
        if (myfile.delete()) {
            System.out.println("I have deleted: " + myfile.getName());

            // remove this state from states file
            try {
                File file = new File(pathAllStates);

                File temp = File.createTempFile("file", ".txt", file.getParentFile());

                String charset = "UTF-8";

                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));

                PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(temp), charset));

                for (String line; (line = reader.readLine()) != null;) {

                    if (!line.equals(StateName)) {
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
