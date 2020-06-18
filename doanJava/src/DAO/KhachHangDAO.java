/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.KhachHangDTO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class KhachHangDAO {
    ArrayList<KhachHangDTO> Arr_khachhang = new ArrayList();
    MySQLConnect connect = new MySQLConnect("localhost", "root", "", "pizza");
    public ArrayList<KhachHangDTO> docKhachHang() throws Exception{
        //connect
        

        String query = "SELECT * From khachhang ";
        Statement st = connect.getStatement();
        ResultSet rs = st.executeQuery(query);
        try {
                    while (rs.next()) {
                KhachHangDTO khachhang = new KhachHangDTO();
                khachhang.setID_Khachhang(rs.getString("ID_Khachhang"));
                khachhang.setTenKhachHang(rs.getString("TenKhachHang"));
                khachhang.setSDT(rs.getString("SDT"));
                khachhang.setGmail(rs.getString("Gmail"));    
                Arr_khachhang.add(khachhang);     
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Lỗi đọc danh sách");
        }

        rs.close();
       
        return Arr_khachhang;
    }
//    public void them(KhachHangDTO khachhang){
//         try{
//            String qry ="INSERT INTO product values (";
//            qry = qry+"'"+products.getID_Product()+"'";
//            qry = qry+","+"'"+products.getName()+"'";
//            qry = qry+","+"'"+products.getPice()+"'";
//            qry = qry+","+"'"+products.getCategory()+"'";
//            qry = qry+","+"'"+products.getImg_path()+"'";
//             qry = qry+")";
//            connect.getStatement();
//            connect.executeQuery(qry);
//            System.out.println(qry);
//            connect.Close();
//       } catch (Exception ex) {
//       }       
//    }
//    public void xoa(String ID_Product){
//      String ma =" ";
//        try{
//            String qry="delete from products where ID_Product='"+ID_Product+"'";
//           connect.getStatement();
//           connect.executeUpdate(qry);
//            System.out.println(qry);
//           connect.Close();
//        }catch(Exception ex){
//            
//        }
//    }
//    public void sua(ProductsDTO products){
//        try{
//            String qry="Update sach Set ";
//            qry = qry+"ID_Product="+"'"+products.getID_Product()+"',";
//            qry = qry+"Name="+"'"+products.getName()+"'";
//            qry = qry+"Price="+"'"+products.getPice()+"'";
//            qry = qry+"Category="+"'"+products.getCategory()+"'";
//            qry = qry+"Img_path="+"'"+products.getImg_path()+"'";
//            connect.getStatement();
//            connect.executeUpdate(qry);
//            System.out.println(qry);
//            connect.Close();
//                    
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
}
