/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseFoundation;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Machajewski
 */
public class DBConnection {
    
    private static String USERNAME = "root";
    private static String PASSWORD = "martin";
    private static String URL = "jdbc:mysql://localhost:3306?autoReconnect=true&useSSL=false";
    
    private static Connection _con = null;
    
    /**
     * Constructor that sets up a connection your local database
     */
    public DBConnection() throws SQLException
    {
        setUpConnection();
    }
    
    /**
     * Constructor that sets up a connection to a given database
     * 
     * 
     * (If you can not connect ask your database administrator
     * to open the firewall for your IP address)
     * @param url to your sql database
     * @param username gain access to your database
     * @param password gain access to your database
     */
    public DBConnection(String url, String password, String username)
    {
        setUpConnection(url, password, username);
    }
    
    /**
     * connect to your local mysql database with the given
     * credentials 
     */
    private void setUpConnection()
    {
        try {
            _con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch(SQLException se){
           //Handle errors for JDBC
           se.printStackTrace();
        }catch(Exception e){
           //Handle errors for Class.forName
           e.printStackTrace();
        }
    }
    
    /**
     * 
     * Tries to connect to a given mysql database
     * 

     * 
     * @param url to your sql database
     * @param username gain access to your database
     * @param password gain access to your database
     */
    private void setUpConnection(String url, String username, String password)
    {
        try {
            _con = DriverManager.getConnection(url, username, password);
        }catch(SQLException se){
           //Handle errors for JDBC
           se.printStackTrace();
        }catch(Exception e){
           //Handle errors for Class.forName
           e.printStackTrace();
        }
    }
    
    public void createDatabase(String dbName) throws SQLException
    {
        Statement stmt = null;
        String query = "CREATE DATABASE" + " " + dbName;
        
        try{
            stmt = _con.createStatement();
            stmt.execute(query);
          
        }catch(SQLException se){
           //Handle errors for JDBC
           se.printStackTrace();
        }finally {
            if (stmt != null) { stmt.close(); }
        }
        
    }
    
    /** 
     * Get all databases under the given connection
     *
     * >>Does not show databases 
     * that you have no privileges for<<
     * https://dev.mysql.com/doc/refman/8.0/en/database-use.html
     *
     * @return a list that contains the names of the databases
     * @throws SQLException 
     */
    public List<String> getDatabaseNames() throws SQLException
    {
        
        Statement stmt = null;
        String query = "SHOW DATABASES";
        List<String> dbList = new ArrayList<>();
        
        try {
            stmt = _con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next())
            {
                String curEntry = rs.getString(1);
                dbList.add(curEntry);
                System.out.println(curEntry);
            }
            
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (stmt != null) { stmt.close(); }
        }
        return dbList;
    }
    
    /**
     * Specifies on which database the opertions will be execute.
     * Whitout a selected database an operation cannot be executed!
     * 
     * @param dbName the name of the database
     * @throws SQLException 
     */
    public void useDatabase(String dbName) throws SQLException
    {
        Statement stmt = null;
        String query = "USE" +" "+ dbName;

        try
        {
            stmt = _con.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException e ) {
            e.printStackTrace();
        }finally{
            if (stmt != null) { stmt.close(); }            
        }
    }

    
    /**
     * Must be used after all work on the database is done
     * @throws SQLException 
     */
    public void closeConnection() throws SQLException
    {
        _con.close();
    }
    
    /**
     * returns the current connection
     * @return 
     */
    public Connection getConnection()
    {
        return _con;
    }

}
