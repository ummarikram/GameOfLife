package DatabaseHandler;
import Grid.Grid;
import STORAGE_INTERFACE.STORAGE_INTERFACE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.StringJoiner;

import javax.sql.StatementEvent;

import java.sql.*;

public class DatabaseHandler implements STORAGE_INTERFACE {

    public String viewStates()
    {

        String output=null;
        try
        {
          
            String url = "jdbc:mysql://localhost/gameoflife"; //connection string here test is the name of the database
            Connection con = DriverManager.getConnection(url, "root", "rf9qedae"); // pass the connection string, username and password
            System.out.println(con);
            System.out.println("connected");
            Statement one=con.createStatement();
            String query="call viewState;";
            ResultSet rs= one.executeQuery(query);

            int i=0;

            while(rs.next())  
            {
                if(i==0)
                {
                    output=rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"\n";  
                }
                else
                {
                    output=output+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"\n";
                }
          
                i++;
            }

            con.close();
            
            
        } 
        catch (SQLException e )
        {
            System.out.println(e);
        }  
        return output;
    }

    public void loadState(String GridName)
    {
     try
        {
          
            String url = "jdbc:mysql://localhost/gameoflife"; //connection string here test is the name of the database
            Connection con = DriverManager.getConnection(url, "root", "rf9qedae"); // pass the connection string, username and password
            System.out.println(con);
            System.out.println("connected");
            Statement one=con.createStatement();
            String query="call loadState " + "('"+ GridName +"');";
            ResultSet rs= one.executeQuery(query);

            while(rs.next())  
            {
            System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));  
            
            }
            con.close();
 
            
        } 
        catch (SQLException e )
        {
            System.out.println(e);
        }  
        
    }


    public void saveState(Grid grid,String GridName)
    {
        int alive=0;
        if(bit==true)
        {
             alive=1;
        }
        else
        {
             alive=0;
        }
         try
            {
              
                String url = "jdbc:mysql://localhost/gameoflife"; //connection string here test is the name of the database
                Connection con = DriverManager.getConnection(url, "root", "rf9qedae"); // pass the connection string, username and password
                System.out.println(con);
                System.out.println("connected");
                Statement one=con.createStatement();
                String query="call saveState " + "('"+ GridName +"',"+RowNo+","+ColNo+","+alive+ ");";
                one.executeQuery(query);
    
                con.close();
     
                
            } 
            catch (SQLException e )
            {
                System.out.println(e);
            } 
    }

    public void deleteState(String Gridname)
    {
        try
        {
          
            String url = "jdbc:mysql://localhost/gameoflife"; //connection string here test is the name of the database
            Connection con = DriverManager.getConnection(url, "root", "rf9qedae"); // pass the connection string, username and password
            System.out.println(con);
            System.out.println("connected");
            Statement one=con.createStatement();
            String query="call deleteState " + "('"+ Gridname +"');";
            one.executeQuery(query);
            con.close();
 
            
        } 
        catch (SQLException e )
        {
            System.out.println(e);
        }  
        
    }
    
}
