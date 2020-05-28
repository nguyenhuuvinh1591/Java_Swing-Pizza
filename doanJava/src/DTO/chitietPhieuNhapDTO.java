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
public class chitietPhieuNhapDTO {
    public String IDPhieuNhap;
    public String IDSanPham;
    public int soluong;
    public int gianhap;

    public String getIDPhieuNhap() {
        return IDPhieuNhap;
    }

    public void setIDPhieuNhap(String IDPhieuNhap) {
        this.IDPhieuNhap = IDPhieuNhap;
    }

    public String getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(String IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGianhap() {
        return gianhap;
    }

    public void setGianhap(int gianhap) {
        this.gianhap = gianhap;
    }

    public chitietPhieuNhapDTO(String IDPhieuNhap, String IDSanPham, int soluong, int gianhap) {
        this.IDPhieuNhap = IDPhieuNhap;
        this.IDSanPham = IDSanPham;
        this.soluong = soluong;
        this.gianhap = gianhap;
    }

}
