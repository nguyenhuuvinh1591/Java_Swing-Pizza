/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.AccountDTO;
import GUI.HomeUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author dangh
 */
public class AccountDAO {
        MySQLConnect connect = new MySQLConnect("localhost", "root", "", "pizza");
        public ArrayList<AccountDTO> docaccount() throws Exception{
        //connect
        ArrayList<AccountDTO> Arr_account = new ArrayList();
        String query = "SELECT * From `nhanvien`";
        Statement st = connect.getStatement();
        ResultSet rs = st.executeQuery(query);
        try {
                    while (rs.next()) {
                AccountDTO Account = new AccountDTO();
                Account.setId_nhanvien(rs.getString("ID_NhanVien"));
                Account.setHoten(rs.getString("TenNhanVien"));
                Account.setNgaysinh(rs.getString("NgaySinh"));
                Account.setGioitinh(rs.getString("GioiTinh"));
                Account.setDiachi(rs.getString("DiaChi"));
                Account.setSodienthoai(rs.getString("SDT"));
                Arr_account.add(Account);
          
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Lỗi đọc danh sách");
        }
      
        rs.close();
       
        return Arr_account;
        }
        public void updateThongtin(String hoten, String ngaysinh, String gioitinh ,String sodienthoai, String id){
            try{           
                MySQLConnect connect = new MySQLConnect("localhost", "root", "", "pizza");
                AccountDTO acc = new AccountDTO();
                Statement st = connect.getStatement();

                String sql = "UPDATE `nhanvien` "
                        + "SET `TenNhanVien`= \"" + hoten + "\", "
                        + "`NgaySinh` = \"" + ngaysinh + "\", "
                        + "`Gioitinh` = \"" + gioitinh + "\", "
                        + "`SDT` = \"" + sodienthoai + "\" "
                        + "WHERE `ID_Nhanvien`= \"" + id + "\" ";
                st.executeUpdate(sql);
            }
            catch (Exception e){
                
            }
        }

    public void updateNhanvien(String hoten, String ngaysinh, String gioitinh, String diachi, String sdt, String id) {
        try{           
                MySQLConnect connect = new MySQLConnect("localhost", "root", "", "pizza");
                AccountDTO acc = new AccountDTO();
                Statement st = connect.getStatement();

                String sql = "UPDATE `nhanvien` "
                        + "SET `TenNhanVien`= \"" + hoten + "\", "
                        + "`NgaySinh` = \"" + ngaysinh + "\", "
                        + "`GioiTinh` = \"" + gioitinh + "\", "
                        + "`DiaChi` = \"" + diachi + "\", "
                        + "`SDT` = \"" + sdt + "\" "
                        + "WHERE `ID_Nhanvien`= \"" + id + "\" ";
                st.executeUpdate(sql);
            }
            catch (Exception e){
                
            }
    }

    public void deleteNhanvien(String hoten, String ngaysinh, String gioitinh, String diachi, String sdt, String id) {
        try {
                MySQLConnect connect = new MySQLConnect("localhost", "root", "", "pizza");
                AccountDTO acc = new AccountDTO();
                Statement st = connect.getStatement();
                
                String sql = "DELETE FROM `nhanvien` WHERE "
                        + "`ID_Nhanvien` = \"" + id + "\"";
                st.executeUpdate(sql);
        } catch (Exception e) {
        }
    }

    public void addNhanvien(String hoten, String ngaysinh, String gioitinh, String diachi, String sdt, String id) {
        try {
                MySQLConnect connect = new MySQLConnect("localhost", "root", "", "pizza");
                AccountDTO acc = new AccountDTO();
                Statement st = connect.getStatement();
                
                String sql = "INSERT INTO `nhanvien` (`TenNhanvien`, `NgaySinh`, `GioiTinh`, `DiaChi`,`SDT`, `ID_Nhanvien`) VALUES ("
                        + "\"" + hoten + "\""
                        + ",\"" + ngaysinh + "\""
                        + ",\"" + gioitinh + "\""
                        + ",\"" + diachi + "\""
                        + ",\"" + sdt + "\""
                        + ",\"" + id + "\")";
                st.executeUpdate(sql);
        } catch (Exception e) {
        }
    }
}
