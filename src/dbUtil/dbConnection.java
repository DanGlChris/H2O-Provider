/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class dbConnection {
    private static final String USERNAME = "DanGlChris";
    private static final String PASSWORD = "daglox455";
    private static final String CONN = "jdbc:mysql://localhost/login";
    private static final String SQLCONN = "jdbc:sqlite:H20_Provider.sqlite";
    
    public static Connection getConnection() throws SQLException{
        try{
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQLCONN);
            
        }catch (ClassNotFoundException h){
            h.printStackTrace();
        }
        return null;
    }
    
}
