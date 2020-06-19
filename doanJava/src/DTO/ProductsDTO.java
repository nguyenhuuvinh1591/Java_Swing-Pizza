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
public class ProductsDTO {
    public String ID_Product;
    public String Name;
    public Double Pice;
    public int amount;
    public String Category;
    public String img_path;
    public int TrangThai;

    public ProductsDTO() {
    }

    public ProductsDTO(String ID_Product, String Name, Double Pice, int amount, String Category, String img_path, int TrangThai) {
        this.ID_Product = ID_Product;
        this.Name = Name;
        this.Pice = Pice;
        this.amount = amount;
        this.Category = Category;
        this.img_path = img_path;
        this.TrangThai = TrangThai;
    }

    public String getID_Product() {
        return ID_Product;
    }

    public void setID_Product(String ID_Product) {
        this.ID_Product = ID_Product;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Double getPice() {
        return Pice;
    }

    public void setPice(Double Pice) {
        this.Pice = Pice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    
    
}

