/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author dangh
 */
public class SanPhamDTO {
    public String IDSanPham;
    public String IDloaiSanPham;
    public String tenSanPham;
    public int soluong;
    public int dongia;
    public String loaiSanPham;

    public SanPhamDTO(String IDSanPham, String IDloaiSanPham, String tenSanPham, int soluong, int dongia, String loaiSanPham) {
        this.IDSanPham = IDSanPham;
        this.IDloaiSanPham = IDloaiSanPham;
        this.tenSanPham = tenSanPham;
        this.soluong = soluong;
        this.dongia = dongia;
        this.loaiSanPham = loaiSanPham;
    }

    public String getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(String IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public String getIDloaiSanPham() {
        return IDloaiSanPham;
    }

    public void setIDloaiSanPham(String IDloaiSanPham) {
        this.IDloaiSanPham = IDloaiSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }
}
