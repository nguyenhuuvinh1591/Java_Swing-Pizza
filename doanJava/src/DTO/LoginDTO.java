
package DTO;

import java.sql.*;
import javax.swing.*;
public class LoginDTO {
    public int type;
    public String username;
    public String password;
    public String id;

    public LoginDTO() {
    }

    public LoginDTO(int type, String username, String password, String id) {
        this.type = type;
        this.username = username;
        this.password = password;
        this.id = id;
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


    
    
}
