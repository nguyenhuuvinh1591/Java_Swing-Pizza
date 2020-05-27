/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.*;
import DAO.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author dangh
 */
public class LoginBUS {
    
    public static String host;
    public static String username;
    public static String password;
    public static String database;
    public static String taikhoan;
    public static String matkhau;
    public static int type;
    public static Connection conn = null;
    public static ResultSet rs = null;
    public static PreparedStatement ps= null;
    public static String testConnect(){
        Connect.host= "localhost";
        Connect.username="root";
        Connect.password="";
        Connect.database="test";
        try {
            
            conn = Connect.getConnect();
            return "Ket noi thanh cong";
        } catch (Exception e) {
            return "Ket noi that bai";
        }
    }
    
    public static ResultSet cLog(String user, String pass){
        String sql = "SELECT * FROM dangnhap WHERE `username`= ? AND `password`= ?";
        try{
            ps= conn.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            return rs=ps.executeQuery();
        }
        catch(Exception e){
            return rs=null;
        }
    }
}
