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

    public ArrayList<ProductsDTO> docSanPham() throws Exception{
        //connect
        
        MySQLConnect connect = new MySQLConnect("localhost", "root", "", "test");
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
                Products.setImg_path(rs.getString("img_path"));
            
                Arr_products.add(Products);
          
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Lỗi đọc danh sách");
        }

        rs.close();
       
        return Arr_products;
    }
 
}
