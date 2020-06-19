/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class KhachHangBUS {
    public  static  ArrayList<KhachHangDTO> Arr_khachhang = new ArrayList();
     public  void docKhachHang() throws Exception
    {
        //trung chuyển data qua cái GUI
        KhachHangDAO data = new KhachHangDAO();
        if(Arr_khachhang == null)
            Arr_khachhang = new ArrayList<>();
        Arr_khachhang = data.docKhachHang();
        //ddd
        
    }
     
     public static ArrayList<KhachHangDTO> timkiemALL(String tuKhoa){
         ArrayList<KhachHangDTO> Arr_temp = new ArrayList<>();
         for (int i =0; i < SanPhamBUS.Arr_products.size();i++) {
            if(Arr_khachhang.get(i).getID_Khachhang().toLowerCase().contains(tuKhoa.toLowerCase())||
            Arr_khachhang.get(i).getTenKhachHang().toLowerCase().contains(tuKhoa.toLowerCase())||
            Arr_khachhang.get(i).getSDT().toLowerCase().contains(tuKhoa.toLowerCase())){  
                Arr_temp.add(KhachHangBUS.Arr_khachhang.get(i));
            }
        }
       
         return Arr_temp;
    }
}
