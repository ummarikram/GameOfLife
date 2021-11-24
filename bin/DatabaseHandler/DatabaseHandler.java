package DatabaseHandler;

import Grid.Grid;
import Interfaces.StorageHandler.*; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.StringJoiner;
import javax.sql.StatementEvent;
import java.sql.*;

public class DatabaseHandler implements StorageHandler {

    private String user = "root";
    private String password = "rf9qedae";

    public String viewStates() {

        String output = "";

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
                output = output + rs.getString(1) + "\n";
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

            while (getMaxRowCol.next()) {

                if (getMaxRowCol.getInt(2) > row)
                {
                    row  = getMaxRowCol.getInt(2);
                }

                if (getMaxRowCol.getInt(3) > col)
                {
                    col = getMaxRowCol.getInt(3);
                }
               
            }

            grid = new Grid(row, col);

            ResultSet callLoadState = one.executeQuery(query);

            while (callLoadState.next()) {
                grid.setCellState(callLoadState.getInt(2), callLoadState.getInt(3), callLoadState.getBoolean(4));
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

            for (int RowNo =0 ; RowNo < grid.getRows(); RowNo++)
            {
                for (int ColNo = 0; ColNo < grid.getColumns(); ColNo++)
                {
                    if (grid.getCellState(RowNo, ColNo))
                    {
                        alive = 1;
                    }
                    else
                    {
                        alive = 0;
                    }

                    query = "call saveState " + "('" + GridName + "'," + RowNo + "," + ColNo + "," + alive + ");";
                    one.executeQuery(query);
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
