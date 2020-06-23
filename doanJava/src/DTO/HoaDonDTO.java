/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import com.toedter.calendar.JDateChooser;
import java.sql.Date;

/**
 *
 * @author nguye
 */
public class HoaDonDTO {
    public String ID_Hoadon;
    public String ID_NhanVien;
    public String ID_Khachhang;
    public String ID_Giamgia;
    public double ThanhTien;
    public String Ngaylap;
    public int TrangThai;
    

    public HoaDonDTO() {
    }

    public HoaDonDTO(String ID_Hoadon, String ID_NhanVien, String ID_Khachhang, String ID_Giamgia, double ThanhTien, String Ngaylap, int TrangThai) {
        this.ID_Hoadon = ID_Hoadon;
        this.ID_NhanVien = ID_NhanVien;
        this.ID_Khachhang = ID_Khachhang;
        this.ID_Giamgia = ID_Giamgia;
        this.ThanhTien = ThanhTien;
        this.Ngaylap = Ngaylap;
        this.TrangThai = TrangThai;
    }

    public String getID_Hoadon() {
        return ID_Hoadon;
    }

    public void setID_Hoadon(String ID_Hoadon) {
        this.ID_Hoadon = ID_Hoadon;
    }

    public String getID_NhanVien() {
        return ID_NhanVien;
    }

    public void setID_NhanVien(String ID_NhanVien) {
        this.ID_NhanVien = ID_NhanVien;
    }

    public String getID_Khachhang() {
        return ID_Khachhang;
    }

    public void setID_Khachhang(String ID_Khachhang) {
        this.ID_Khachhang = ID_Khachhang;
    }

    public String getID_Giamgia() {
        return ID_Giamgia;
    }

    public void setID_Giamgia(String ID_Giamgia) {
        this.ID_Giamgia = ID_Giamgia;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(double ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public String getNgaylap() {
        return Ngaylap;
    }

    public void setNgaylap(String Ngaylap) {
        this.Ngaylap = Ngaylap;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

       
}
