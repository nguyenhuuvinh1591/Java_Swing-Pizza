/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.LoginDTO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author dangh
 */
public class LoginDAO {
    
    public ArrayList<LoginDTO> docdangnhap() throws Exception{
        //connect
        ArrayList<LoginDTO> Arr_login = new ArrayList();
        MySQLConnect connect = new MySQLConnect("localhost", "root", "", "pizza");
        String query = "SELECT * From `dangnhap`";
        Statement st = connect.getStatement();
        ResultSet rs = st.executeQuery(query);
        try {
                    while (rs.next()) {
                LoginDTO Login = new LoginDTO();
                Login.setUsername(rs.getString("username"));
                Login.setPassword(rs.getString("password"));
                Login.setId(rs.getString("ID_Nhanvien"));
                Login.setType(rs.getInt("Type"));
                Arr_login.add(Login);     
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Lỗi đọc danh sách");
        }

        rs.close();
       
        return Arr_login;
    }

    public void updateTaikhoan(String taikhoan, String matkhau, String id) {
        try{           
                MySQLConnect connect = new MySQLConnect("localhost", "root", "", "pizza");
                LoginDTO acc = new LoginDTO();
                Statement st = connect.getStatement();

                String sql = "UPDATE `dangnhap` "
                        + "SET `username`= \"" + taikhoan + "\", "
                        + "`password` = \"" + matkhau + "\" "
                        + "WHERE `ID_Nhanvien`= \"" + id + "\" ";
                st.executeUpdate(sql);
            }
            catch (Exception e){ 
            }
    }

    public void addTaikhoan(String taikhoan, String matkhau, String id, String type) {
        try {
                MySQLConnect connect = new MySQLConnect("localhost", "root", "", "pizza");
                LoginDTO acc = new LoginDTO();
                Statement st = connect.getStatement();
                
                String sql = "INSERT INTO `dangnhap` (`username`, `password`, `ID_Nhanvien`, `Type`) VALUES ("
                        + "\"" + taikhoan + "\""
                        + ",\"" + matkhau + "\""
                        + ",\"" + id + "\""
                        + ",'" + type + "')";
                st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTaikhoan(String taikhoan, String matkhau, String id, String type) {
        try {
                MySQLConnect connect = new MySQLConnect("localhost", "root", "", "pizza");
                LoginDTO acc = new LoginDTO();
                Statement st = connect.getStatement();
                
                String sql = "DELETE FROM `dangnhap` WHERE "
                        + "`username` = \"" + taikhoan + "\"";
                st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

