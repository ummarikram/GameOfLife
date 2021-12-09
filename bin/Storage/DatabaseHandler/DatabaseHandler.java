//implement storageinterface
//saves state that is all grid in db
//loads grid from db and returns a grid
//view saved states in db
//delete grids  in db
//.................
package bin.Storage.DatabaseHandler;

import bin.BL.Grid.*;
import bin.Interfaces.StorageInterface.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringJoiner;
import javax.sql.StatementEvent;
import java.sql.*;
import java.sql.Types;

public class DatabaseHandler implements StorageInterface {

    private String user = "root";
    private String password = "rf9qedae";

    public ArrayList<String> viewStates() {

        ArrayList<String> output = new ArrayList<String>();

        try {

            String url = "jdbc:mysql://localhost/gameoflife"; // connection string here test is the name of the database
            Connection con = DriverManager.getConnection(url, user, password); // pass the connection string,
                                                                               // username and password
            System.out.println(con);
            System.out.println("connected");
            Statement one = con.createStatement();
            String callViewStates = "call viewState;";
            ResultSet rs = one.executeQuery(callViewStates);

            // store all statenames inside a string;
            while (rs.next()) {
                output.add(rs.getString(1));
            }
            
            con.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

        return output;
    }

    public Grid loadState(String GridName) {

        Grid grid = null;

        try {

            String url = "jdbc:mysql://localhost/gameoflife"; // connection string here test is the name of the database
            Connection con = DriverManager.getConnection(url, user, password); // pass the connection string,
                                                                               // username and password
            System.out.println(con);
            System.out.println("connected");
            Statement one = con.createStatement();
            String query = "call loadState " + "('" + GridName + "');";
            ResultSet getMaxRowCol = one.executeQuery(query);

            int row = 0, col = 0;

            getMaxRowCol.next();

                    row = getMaxRowCol.getInt(4);
               

               
                    col = getMaxRowCol.getInt(5);


            grid = new Grid(row, col);

            ResultSet callLoadState = one.executeQuery(query);

            while (callLoadState.next()) {
                grid.setCellState(callLoadState.getInt(1), callLoadState.getInt(2), callLoadState.getBoolean(3));
            }

            con.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

        return grid;

    }

    public void saveState(Grid grid, String GridName) {

        try {

            String url = "jdbc:mysql://localhost/gameoflife"; // connection string here test is the name of the database
            Connection con = DriverManager.getConnection(url, user, password); // pass the connection string,
                                                                               // username and password
            System.out.println(con);
            System.out.println("connected");
            Statement one = con.createStatement();
            String query;
            int alive;

            for (int RowNo = 0; RowNo < grid.getRows(); RowNo++) {
                for (int ColNo = 0; ColNo < grid.getColumns(); ColNo++) {
                    if (grid.getCellState(RowNo, ColNo)) {
                        alive = 1;
                        query = "call saveState " + "('" + GridName + "'," + RowNo + "," + ColNo + "," + alive+"," + grid.getRows()+"," + grid.getColumns()+");";
                      one.executeQuery(query);
                    } 
                    
                }
            }

            con.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteState(String Gridname) {
        try {

            String url = "jdbc:mysql://localhost/gameoflife"; // connection string here test is the name of the database
            Connection con = DriverManager.getConnection(url, user, password); // pass the connection string,
                                                                               // username and password
            System.out.println(con);
            System.out.println("connected");
            Statement one = con.createStatement();
            String query = "call deleteState " + "('" + Gridname + "');";
            one.executeQuery(query);
            con.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

}
