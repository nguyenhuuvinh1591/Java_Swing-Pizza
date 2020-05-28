/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.*;
import DAO.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author dangh
 */
public class LoginBUS {
    public  static  ArrayList<LoginDTO> Arr_login = new ArrayList();
    public  void docdangnhap() throws Exception
    {
        LoginDAO data = new LoginDAO();
        if(Arr_login == null)
            Arr_login = new ArrayList<>();
        Arr_login = data.docdangnhap();
        //ddd
    }
}
