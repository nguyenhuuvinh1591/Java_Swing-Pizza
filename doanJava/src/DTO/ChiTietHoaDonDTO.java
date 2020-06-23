/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author dangh
 */
public class ChiTietHoaDonDTO {
    public String ID_Product;
    public String ID_Hoadon;
    public String ID_Khachhang;
    public int soLuong;
    public Double thanhTien;
    public Date ngayLap;

    public ChiTietHoaDonDTO() {
    }

    public ChiTietHoaDonDTO(String ID_Product, String ID_Hoadon, String ID_Khachhang, int soLuong, Double thanhTien, Date ngayLap) {
        this.ID_Product = ID_Product;
        this.ID_Hoadon = ID_Hoadon;
        this.ID_Khachhang = ID_Khachhang;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.ngayLap = ngayLap;
    }

    public String getID_Product() {
        return ID_Product;
    }

    public void setID_Product(String ID_Product) {
        this.ID_Product = ID_Product;
    }

    public String getID_Hoadon() {
        return ID_Hoadon;
    }

    public void setID_Hoadon(String ID_Hoadon) {
        this.ID_Hoadon = ID_Hoadon;
    }

    public String getID_Khachhang() {
        return ID_Khachhang;
    }

    public void setID_Khachhang(String ID_Khachhang) {
        this.ID_Khachhang = ID_Khachhang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    

}
