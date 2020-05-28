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
        MySQLConnect connect = new MySQLConnect("localhost", "root", "", "test");
        String query = "SELECT * From dangnhap ";
        Statement st = connect.getStatement();
        ResultSet rs = st.executeQuery(query);
        try {
                    while (rs.next()) {
                LoginDTO Login = new LoginDTO();
                Login.setUsername(rs.getString("username"));
                Login.setPassword(rs.getString("password"));
            
                Arr_login.add(Login);
          
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Lỗi đọc danh sách");
        }

        rs.close();
       
        return Arr_login;
    }
}
