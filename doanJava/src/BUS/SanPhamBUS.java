/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ProductsDAO;
import DTO.ProductsDTO;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class SanPhamBUS {
    public  static  ArrayList<ProductsDTO> Arr_products = new ArrayList();
    public  void docSanPham() throws Exception
    {
        ProductsDAO data = new ProductsDAO();
        if(Arr_products == null)
            Arr_products = new ArrayList<>();
        Arr_products = data.docSanPham();
        //ddd
    }
}
