/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonReader;
import org.json.simple.JSONValue;

/**
 *
 * @author rahul
 */
public class Helper extends DbConnection{
    public String getData()
    {
        String result=null;
        try {
            Statement statement=conn.createStatement();
            System.out.println("connection status is establish");
            
            ResultSet rs=statement.executeQuery("select * from Login");
//            System.out.println(" data is "+getJSONFromResultSet(rs, "data"));
//            rs.first();
            return getJSONFromResultSet(rs, "data");
//            while (rs.next()) {
//                result+=rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(2);
//                System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
//            }
//            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return "Failed to retrive ";
    }
   public String getJSONFromResultSet(ResultSet rs,String keyName) {
    Map json = new HashMap(); 
    List list = new ArrayList();
    if(rs!=null)
    {
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            while(rs.next())
            {
                Map<String,Object> columnMap = new HashMap<String, Object>();
                for(int columnIndex=1;columnIndex<=metaData.getColumnCount();columnIndex++)
                {
                    if(rs.getString(metaData.getColumnName(columnIndex))!=null)
                        columnMap.put(metaData.getColumnLabel(columnIndex),     rs.getString(metaData.getColumnName(columnIndex)));
                    else
                        columnMap.put(metaData.getColumnLabel(columnIndex), "");
                }
                list.add(columnMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        json.put(keyName, list);
     }
     return JSONValue.toJSONString(json);
}
}
