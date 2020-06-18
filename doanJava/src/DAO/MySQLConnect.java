/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class MySQLConnect {
    String Host="";
    String UserName="";
    String Password="";
    String DataBase="";
    
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    
    public MySQLConnect (){
  
    }
    
    public MySQLConnect (String Host,String UserName,String Password,String DataBase ){
        this.DataBase = DataBase;
        this.Host = Host;
        this.Password = Password;
        this.UserName = UserName;
//        conn = new Connection();
        
    }
    //ham kiem tra xem driver connect MYsqp da san sang hay ch
    public void driverTest() throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            throw new Exception("MySQL JBDC Driver not found ");
        }
    }
    //ham lay connection
    public Connection getConnect() throws Exception{
        //neu connection null thi KHOI TAO MOI
        if(this.conn == null){
            //kiem tra driver
            driverTest();
             
            String url = "jdbc:mysql://" + this.Host + ":3306/"+ this.DataBase;
            try {
                this.conn = DriverManager.getConnection(url,this.UserName,this.Password);
            } catch (java.sql.SQLException e) {
                throw new Exception("Khong the ket noi den database" + url + e.getMessage());
            }
        }
        return this.conn;
    } 
    
    //tao statement de thuc thi cau Querry
    public Statement getStatement() throws Exception{
        if(this.st == null? true: this.st.isClosed()){
        //khoi tao 1 statemen moi
            this.st = this.getConnect().createStatement();
        }
        return this.st;
    }
    //thuc thi cau lenh Inser , Updata , Delete
    public ResultSet executeQuery(String Query) throws Exception{
        int rs = Integer.MIN_VALUE;
        try {
            rs = this.getStatement().executeUpdate(Query);
        } catch (Exception e) {
            throw new Exception ("Error:" +e.getMessage()+"-"+ Query);
        }finally{
            this.Close();
        }
        return this.rs;
    }
    //updata
    public int executeUpdate(String qry) throws Exception{
        int res =0;
        try{
            res = this.getStatement().executeUpdate(qry);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return res;
    }
    //Ham dong ket noi
    public void Close() throws SQLException{
        //neu result ch dong, dong result
        if(this.rs!=null && ! this.rs.isClosed()){
            this.rs.close();
            this.rs= null;
        }
        
        if(this.st!=null && ! this.st.isClosed()){
            this.st.close();
            this.st= null;
        }
        
        if(this.conn!=null && ! this.conn.isClosed()){
            this.conn.close();
            this.conn= null;
        }
    }
    public int CountRow(ResultSet rs) throws SQLException{
        int count=0;
        while(rs.next()){
            count++;
        }
        return count;
    }
}
