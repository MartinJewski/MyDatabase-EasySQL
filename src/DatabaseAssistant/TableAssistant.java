/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseAssistant;

import Basic.DataTypes;
import Basic.DataTypes.Typ;
import DatabaseFoundation.DBConnection;
import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Martin Machajewski
 */
public class TableAssistant {
    
    //TODO: UNIQUE ID CREATEOR => 32! Permutations 
    
    private static Connection _con = null;
    private static final String UKID = "UKID";//unique key identifier
    private static final String UKID_SIZE = "INT(32)";
    
    public TableAssistant(DBConnection dbc)
    {
        _con = dbc.getConnection();
    }
    
    /**
     * creates a table with one column named temp that
     * has no entries.(its necessary to have at least one column
     * to be able to create a table).
     * @param tbName
     * @throws SQLException 
     */
    public void createTable(String tbName) throws SQLException
    {
        Statement stmt = null;
        String query = "CREATE TABLE" + " " + tbName 
                  + "("+ UKID + " INT NOT NULL AUTO_INCREMENT PRIMARY KEY;"; //column name
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
     * Adds a column with given namen and 
     * type to an existing table
     * @param tlName name of the table in which to add the column
     * @param colName new columns name
     * @param t an enum type defined in the DataType class
     * @throws SQLException 
     */
    public void addColumn(String tlName, String colName, DataTypes t) throws SQLException
    {
        Statement stmt = null;
        String query = "ALTER TABLE" + " " + tlName + "\n"
                        + " " + "ADD COLUMN" + " " + colName 
                        + " " + t.getType();

        try{
            stmt = _con.createStatement();
            stmt.execute(query);

        }catch(SQLException se)
        {
          se.printStackTrace();
        }finally{
           if (stmt != null) { stmt.close(); }
        }
    }
    
    public void deleteColumn(String tlName, String colName) throws SQLException
    {
        Statement stmt = null;
        String query = "ALTER TABLE "+ tlName +"\n" +
                        "DROP COLUMN "+ colName ;
        
        try
        {
            stmt = _con.createStatement();
            stmt.execute(query);
            
        }catch(SQLException se)
        {
          se.printStackTrace();
        }finally{
           if (stmt != null) { stmt.close(); }
        }
    }
    
    public void deleteRow(String tlName, Integer pUKID) throws SQLException
    {
        Statement stmt = null;
        String query = "DELETE FROM TABLE" + " " + tlName + " "+ "WHERE"
                        + " " + UKID + "=" +  pUKID;
        
        try
        {
            stmt = _con.createStatement();
            stmt.execute(query);
            
        }catch(SQLException se)
        {
          se.printStackTrace();
        }finally{
           if (stmt != null) { stmt.close(); }
        }
    }
}
