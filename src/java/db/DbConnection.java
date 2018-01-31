/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.Constant;

/**
 *
 * @author rahul
 */
public class DbConnection {
    Connection conn;
    public DbConnection()
    {
        try {
            Class.forName(Constant.DRIVER_NAME);
            conn=(Connection) DriverManager.getConnection(Constant.CONNECTION_NAME,Constant.USERNAME,Constant.PASSWORD);
         
        } catch (Exception ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Connection getConnection()
    {
        return conn;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    conn.close();
    }
    
    
}
