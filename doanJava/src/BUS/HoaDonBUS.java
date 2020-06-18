/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
import GUI.HoaDonGUI;
import GUI.HomeUser;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class HoaDonBUS {
    public  static ArrayList<HoaDonDTO> Arr_HoaDonBUS = new ArrayList<>();
    
    public  void docHoaDon() throws Exception
    {
        //trung chuyển data qua cái GUI
        HoaDonDAO data = new HoaDonDAO();
        if(Arr_HoaDonBUS == null)
            Arr_HoaDonBUS = new ArrayList<>();
        Arr_HoaDonBUS = data.docHoaDon();
        //ddd
        
    }
    public void themHoaDon(HoaDonDTO hoadon)
    {
        HoaDonDAO data = new HoaDonDAO();
        data.them(hoadon);//gọi hàm thêm bên DAO để thêm sách vào database
        Arr_HoaDonBUS.add(hoadon);//
    }
//    public  void themHoaDon(){
//        HoaDonGUI data = new HoaDonGUI();
//        if(Arr_HoaDonBUS == null)
//            Arr_HoaDonBUS = new ArrayList<>();
//        Arr_HoaDonBUS = data.Arr_HoaDon;
//    }
    public static boolean kt_trung_ma (String maHD, ArrayList<HoaDonDTO> a)
    {
        for (HoaDonDTO hoadon: a)
        {
            if(hoadon.getID_Hoadon().equals(maHD))
                return true;
        }
        return false;
    }
}
