
package DTO;

import java.sql.*;
import javax.swing.*;
public class LoginDTO {
    public int type;
    public String username;
    public String password;
    public String id;
    public int TrangThai;

    public LoginDTO() {
    }

    public LoginDTO(int type, String username, String password, String id, int TrangThai) {
        this.type = type;
        this.username = username;
        this.password = password;
        this.id = id;
        this.TrangThai = TrangThai;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    
    
}
