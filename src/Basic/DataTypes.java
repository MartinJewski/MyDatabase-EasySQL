/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Basic;

import java.sql.JDBCType;

/**
 *
 * @author Martin Machajewski
 */
public class DataTypes {
    
    public static enum Typ{Boolean, Byte, FixedByte, Short, Int, Long, Float, Double, Decimal,
                    FixedString, String }
    
    private String usedType;
    
    public DataTypes(Typ t, Integer size)
    {
        usedType = this.toDatabaseTyp(t, size);
    }
    /**
     * Simple function to map java types to mysql types. The returned String
     * can be used if a function asks for a type. 
     * 
     * 
     * Mapping
     *  Boolean = BIT;
     *  Byte = VARBINARY(size)
     *  FixedByte = BINARY(size)
     *  Short = SMALLINT
     *  Int = Integer
     *  Long = BIGINT
     *  Float = FLOAT
     *  Double = DOUBLE
     *  Decimal = DECIMAL
     *  String = VARCHAR(size)
     *  FixedString = CHAR(size)
     *  
     *  Note:
     *  Use Decimal for finance calculation!!
     * 
     * Special Types:
     *  FixedString = All Strings have the same size
     *          e.g FixedString size 30 => All Strings in the column have size 30
     *          (see mysql CHAR(M))
     * 
     *  FixedByte = all Bytes have the same size
     *          e.g Fixedbyte size 10 => all byte string have size 10. 
     *              !!Inserted byte strings with size less than 10 get a padding!!!
     *              (see mysql BYINARY and VARBINARY(M));
     * 
     * @param typ which type the mysql data has to be
     * @param size 
     * @return String name for the mysql type
     */
    public String toDatabaseTyp(Typ typ, Integer size)
    {
        String temp = null;
        switch(typ){
            case Boolean:
                temp = JDBCType.BIT.getName();
                break;
            case Byte:
                temp = "VARBINARY"+ "("+ size +")";
                break;
            case FixedByte:
                temp = "BINARY"+ "("+ size +")";
                break;
            case Short:
                temp = JDBCType.SMALLINT.getName();
                break;
            case Int:
                temp = JDBCType.INTEGER.getName();
                break; 
            case Long:
                temp = JDBCType.BIGINT.getName();
                break;
            case Float:
                temp = JDBCType.FLOAT.getName();
                break;
            case Double:
                temp = JDBCType.DOUBLE.getName();
                break;
            case Decimal:
                temp = JDBCType.DECIMAL.getName();
                break;
            case String:
                temp = "VARCHAR" + "(" + size + ")";
                break;
            case FixedString:
                temp = "CHAR" + "(" + size + ")";
                break;
        }
        return temp;
    }
    
    public String getType()
    {
        return usedType;
    }

}
