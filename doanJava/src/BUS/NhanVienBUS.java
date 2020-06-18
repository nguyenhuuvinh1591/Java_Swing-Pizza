/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class NhanVienBUS {
    public  static  ArrayList<NhanVienDTO> Arr_Nhanvien = new ArrayList();
    public  void docaccount() throws Exception
    {
        NhanVienDAO data = new NhanVienDAO();
        if(Arr_Nhanvien == null)
            Arr_Nhanvien = new ArrayList<>();
        Arr_Nhanvien = data.docNhanVien();
        //ddd
    }
}
