/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BUS.HoaDonBUS;
import static DAO.Connect.conn;
import DTO.HoaDonDTO;
import GUI.HoaDonGUI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class HoaDonDAO {
    MySQLConnect connect = new MySQLConnect("localhost", "root", "", "pizza");
    ArrayList<HoaDonDTO> Arr_HoaDonDetail = new ArrayList();
    public static int shd;
    public ArrayList<HoaDonDTO> docHoaDon() throws Exception{
        //connect    
        String query = "SELECT * From hoadon ";
        Statement st = connect.getStatement();
        ResultSet rs = st.executeQuery(query);
        shd=connect.CountRow(rs);
        rs.first();
        try {
                while (rs.next()) {
                HoaDonDTO hoadon = new HoaDonDTO();
                hoadon.setID_Hoadon(rs.getString("ID_Hoadon"));
                hoadon.setID_NhanVien(rs.getString("ID_NhanVien"));
                hoadon.setID_Khachhang(rs.getString("ID_Khachhang"));
                hoadon.setID_Giamgia(rs.getString("ID_Giamgia"));
                hoadon.setNgaylap(rs.getString("Ngaylap"));
                hoadon.setThanhTien(rs.getDouble("ThanhTien"));
            
                Arr_HoaDonDetail.add(hoadon );
          
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Lỗi đọc danh sách");
        }

        rs.close();
       
        return Arr_HoaDonDetail;
    }    
    public void them(HoaDonDTO hoadon){
         try{
            String qry ="insert into hoadon values (";
            qry = qry+"'"+hoadon.getID_Hoadon()+"'";
            qry = qry+","+"'"+hoadon.getID_NhanVien()+"'";
            qry = qry+","+"'"+hoadon.getID_Khachhang()+"'";
            qry = qry+","+"'"+hoadon.getID_Giamgia()+"'";
            qry = qry+","+"'"+hoadon.getNgaylap()+"'"; 
            qry = qry+","+"'"+hoadon.getThanhTien()+"'";
            qry = qry+")";
            Statement st = connect.getStatement();     
            st.executeUpdate(qry);
            System.out.println(qry);
            JOptionPane.showMessageDialog(null, "Thêm Thành Công!!");
            st.close();
       } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, "Lỗi: Không Thể Cập Nhật data !!!");
       }       
    }
}


