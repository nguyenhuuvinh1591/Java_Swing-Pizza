/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author nguye
 */
public class NhanVienDTO {
    public String ID_NhanVien;
    public String TenNhanVien;
    public String NgaySinh;
    public String GioiTinh;
    public String DiaChi;
    public String SDT;

    public NhanVienDTO() {
    }

    public NhanVienDTO(String ID_NhanVien, String TenNhanVien, String NgaySinh, String GioiTinh, String DiaChi, String SDT) {
        this.ID_NhanVien = ID_NhanVien;
        this.TenNhanVien = TenNhanVien;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
    }

    public String getID_NhanVien() {
        return ID_NhanVien;
    }

    public void setID_NhanVien(String ID_NhanVien) {
        this.ID_NhanVien = ID_NhanVien;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
    
    
}
