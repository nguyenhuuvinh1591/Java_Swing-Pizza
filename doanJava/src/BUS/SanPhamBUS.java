/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ProductsDAO;
import DTO.ProductsDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nguye
 */
public class SanPhamBUS {
    public  static  ArrayList<ProductsDTO> Arr_products = new ArrayList();
    public  void docSanPham() throws Exception
    {
        //trung chuyển data qua cái GUI
        ProductsDAO data = new ProductsDAO();
        if(Arr_products == null)
            Arr_products = new ArrayList<>();
        Arr_products = data.docSanPham();
        //ddd
        
    }
    public void themSanPham(ProductsDTO products)
    {
        ProductsDAO data = new ProductsDAO();
        data.them(products);//gọi hàm thêm bên DAO để thêm sách vào database
        Arr_products.add(products);//
    }
    public void suaSanPham(int i,ProductsDTO products)
    {
        ProductsDAO data=new ProductsDAO();
        data.sua(products);
        Arr_products.set(i, products);
    }
    public void xoaSanPham(ProductsDTO products,int i)
    {
        ProductsDAO productDAO =new ProductsDAO();
        String ID=Arr_products.get(i).getID_Product();
        productDAO.xoa(ID);
        Arr_products.remove(i);
    }
    
    public  ArrayList<ProductsDTO> timkiem_ID_sanPham(String ID_Product)
    {
        ArrayList<ProductsDTO> kq = new ArrayList<ProductsDTO>();
        for(ProductsDTO products: Arr_products)
        {
            if(products.getID_Product().equals(ID_Product))
                kq.add(products);
        }
        return kq;
    }
    
    
    public static ArrayList<ProductsDTO> timkiemTheoGia(Double giaBanDau , Double giaKetThuc)
    {
        ArrayList<ProductsDTO> kq = new ArrayList<ProductsDTO>();
        for (int i =0; i < SanPhamBUS.Arr_products.size();i++)
        {
            if(giaBanDau <= Arr_products.get(i).getPice() && Arr_products.get(i).getPice() <= giaKetThuc){
              kq.add(SanPhamBUS.Arr_products.get(i));
            }
        }
        return kq;
    }
    
    public static ArrayList<ProductsDTO> timkiemTheoLoai(String loai)
    {
         ArrayList<ProductsDTO> Arr_temp = new ArrayList<>();
         for (int i =0; i < SanPhamBUS.Arr_products.size();i++) {
            if(Arr_products.get(i).getCategory().toLowerCase().contains(loai.toLowerCase())){
                Arr_temp.add(SanPhamBUS.Arr_products.get(i));
            }
        }
         return Arr_temp;
    }
    
    //Vinh moi add
    public static ArrayList<ProductsDTO> timkiemALL(String tuKhoa){
         ArrayList<ProductsDTO> Arr_temp = new ArrayList<>();
         for (int i =0; i < SanPhamBUS.Arr_products.size();i++) {
            if(Arr_products.get(i).getID_Product().toLowerCase().contains(tuKhoa.toLowerCase())||
            Arr_products.get(i).getCategory().toLowerCase().contains(tuKhoa.toLowerCase())||
            Arr_products.get(i).getName().toLowerCase().contains(tuKhoa.toLowerCase())||
            Arr_products.get(i).getPice().toString().contains(tuKhoa)){
                Arr_temp.add(SanPhamBUS.Arr_products.get(i));
            }
        }
         return Arr_temp;
    }
    
    public int kt_trung_ma (String ID_SanPham)
    {
        for (ProductsDTO products: Arr_products)
        {
            if(products.getID_Product().equals(ID_SanPham))
                return 1;
        }
        return 0;
    }
    public static boolean kiemtraTenSanPham(String s) throws Exception
    {
        try
        {
          if(s.matches("^[a-z A-Z]{5,40}$"))
          {
              return true;
          }
          else
          {
              return false;
          }
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
}
