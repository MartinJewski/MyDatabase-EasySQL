/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydatabaseeasysql;

import Basic.DataTypes;
import DatabaseAssistant.TableAssistant;
import DatabaseFoundation.DBConnection;
import static java.sql.DriverManager.println;
import java.sql.JDBCType;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Martin Machajewski
 */
public class MyDatabaseEasySQL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here

        DBConnection myDBC = new DBConnection();
        myDBC.createDatabase("MyDatabase");
        List<String> curDBs = myDBC.getDatabaseNames();
        curDBs.forEach((curIter) -> {
            println(curIter);
        });
        
        myDBC.useDatabase(("MyDatabase"));
        TableAssistant ta = new TableAssistant(myDBC);
        DataTypes a = new DataTypes(DataTypes.Typ.Int, 0);
        
        
        ta.createTable("myTable");
        ta.addColumn("myTable", "myFirstColumn", a);
    }

}
