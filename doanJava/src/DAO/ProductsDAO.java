/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ProductsDTO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class ProductsDAO {
    ArrayList<ProductsDTO> Arr_products = new ArrayList();
        MySQLConnect connect = new MySQLConnect("localhost", "root", "", "pizza");
    public ArrayList<ProductsDTO> docSanPham() throws Exception{
        //connect
        

        String query = "SELECT * From product ";
        Statement st = connect.getStatement();
        ResultSet rs = st.executeQuery(query);
        try {
                    while (rs.next()) {
                ProductsDTO Products = new ProductsDTO();
                Products.setID_Product(rs.getString("ID_Product"));
                Products.setName(rs.getString("Name"));
                Products.setPice(rs.getDouble("Price"));
                Products.setCategory(rs.getString("Category"));
                Products.setAmount(rs.getInt("amount"));
                Products.setImg_path(rs.getString("img_path"));
            
                Arr_products.add(Products);
          
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Lỗi đọc danh sách");
        }

        rs.close();
       
        return Arr_products;
    }
    public void them(ProductsDTO products){
         try{
            String qry ="INSERT into product values (";
            qry = qry+"'"+products.getID_Product()+"'";
            qry = qry+","+"'"+products.getName()+"'";
            qry = qry+","+"'"+products.getPice()+"'";
            qry = qry+","+"'"+products.getCategory()+"'";
            qry = qry+","+"'"+products.getImg_path()+"'";
             qry = qry+")";
            connect.getStatement();
            connect.executeQuery(qry);
            System.out.println(qry);
            connect.Close();
       } catch (Exception ex) {
       }       
    }
    public void xoa(String ID_Product){
      String ma =" ";
        try{
            String qry="delete from products where ID_Product='"+ID_Product+"'";
           connect.getStatement();
           connect.executeUpdate(qry);
            System.out.println(qry);
           connect.Close();
        }catch(Exception ex){
            
        }
    }
    public void sua(ProductsDTO products){
        try{
            String qry="Update sach Set ";
            qry = qry+"ID_Product="+"'"+products.getID_Product()+"',";
            qry = qry+"Name="+"'"+products.getName()+"'";
            qry = qry+"Price="+"'"+products.getPice()+"'";
            qry = qry+"Category="+"'"+products.getCategory()+"'";
            qry = qry+"Img_path="+"'"+products.getImg_path()+"'";
            connect.getStatement();
            connect.executeUpdate(qry);
            System.out.println(qry);
            connect.Close();
                    
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
 
}
