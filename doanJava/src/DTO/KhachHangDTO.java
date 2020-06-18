/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author nguye
 */
public class KhachHangDTO {
    public String ID_Khachhang;
    public String TenKhachHang;
    public String SDT;
    public String Gmail;

    public KhachHangDTO() {
    }

    public KhachHangDTO(String ID_Khachhang, String TenKhachHang, String SDT, String Gmail) {
        this.ID_Khachhang = ID_Khachhang;
        this.TenKhachHang = TenKhachHang;
        this.SDT = SDT;
        this.Gmail = Gmail;
    }

    public String getID_Khachhang() {
        return ID_Khachhang;
    }

    public void setID_Khachhang(String ID_Khachhang) {
        this.ID_Khachhang = ID_Khachhang;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String TenKhachHang) {
        this.TenKhachHang = TenKhachHang;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String Gmail) {
        this.Gmail = Gmail;
    }
    
}
