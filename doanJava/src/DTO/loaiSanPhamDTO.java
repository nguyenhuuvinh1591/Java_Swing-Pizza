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
public class loaiSanPhamDTO {
    public String IDloaiSanPham;
    public String tenloaiSanPham;
    public String dactinh;

    public loaiSanPhamDTO(String IDloaiSanPham, String tenloaiSanPham, String dactinh) {
        this.IDloaiSanPham = IDloaiSanPham;
        this.tenloaiSanPham = tenloaiSanPham;
        this.dactinh = dactinh;
    }

    public String getIDloaiSanPham() {
        return IDloaiSanPham;
    }

    public void setIDloaiSanPham(String IDloaiSanPham) {
        this.IDloaiSanPham = IDloaiSanPham;
    }

    public String getTenloaiSanPham() {
        return tenloaiSanPham;
    }

    public void setTenloaiSanPham(String tenloaiSanPham) {
        this.tenloaiSanPham = tenloaiSanPham;
    }

    public String getDactinh() {
        return dactinh;
    }

    public void setDactinh(String dactinh) {
        this.dactinh = dactinh;
    }
}
