package DAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Connect {
    public static String host;
    public static String username;
    public static String password;
    public static String database;
    public static Connection conn = null;
    
    public Connect (String host,String username,String password,String database ){
        this.database = database;
        this.host = host;
        this.password = password;
        this.username = username;
    }
    public static Connection getConnect() {
    String url = "jdbc:mysql://" + host +":3306/"+ database;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                return DriverManager.getConnection(url,username,password);
                
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,"Kết nối thất bại","Thông báo",1); 
            }
        
        return conn;
    }

    public PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}