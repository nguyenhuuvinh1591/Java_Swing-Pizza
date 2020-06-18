/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhanVienDTO;
import DTO.ProductsDTO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class NhanVienDAO {
    ArrayList<NhanVienDTO> Arr_Nhanvien = new ArrayList();
    MySQLConnect connect = new MySQLConnect("localhost", "root", "", "pizza");
    public ArrayList<NhanVienDTO> docNhanVien() throws Exception{
        //connect      
        String query = "SELECT * From nhanvien ";
        Statement st = connect.getStatement();
        ResultSet rs = st.executeQuery(query);
        try {
                    while (rs.next()) {
                NhanVienDTO nhanvien = new NhanVienDTO();
                nhanvien.setID_NhanVien(rs.getString("ID_NhanVien"));
                nhanvien.setTenNhanVien(rs.getString("TenNhanVien"));
                nhanvien.setNgaySinh(rs.getString("NgaySinh"));
                nhanvien.setGioiTinh(rs.getString("GioiTinh"));
                nhanvien.setDiaChi(rs.getString("DiaChi"));
                nhanvien.setSDT(rs.getString("SDT"));
            
                Arr_Nhanvien.add(nhanvien);
          
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Lỗi đọc danh sách");
        }

        rs.close();
       
        return Arr_Nhanvien;
    }
}
