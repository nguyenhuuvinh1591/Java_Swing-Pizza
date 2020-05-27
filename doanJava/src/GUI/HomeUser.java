/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author nguyen thanh sang
 */
public class HomeUser extends JFrame implements MouseListener, ActionListener {
    JPanel menuPanel,headerPanel,contentPanel ;
    //JLabel lbl1;
    JButton menuButton[];
    HomeUser()
    {
        init();
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    public void init()
    {
        setSize(1280,768);
        setLayout(new BorderLayout(3,3));
        
        menuPanel=CreateJPanel_Menu();
        menuPanel.setPreferredSize(new Dimension(200, 1110));
        JPanel menuPanelIcon = CreateJPanel_Icon();
        menuPanel.add(menuPanelIcon);
        
        headerPanel= CreateJPanel_Header();
        headerPanel.setPreferredSize(new Dimension(0,100));
        
        contentPanel=CreateJPanel_Content();
        contentPanel.setBackground(Color.green);
        
        add(menuPanel,BorderLayout.WEST);
        add(headerPanel,BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    
    public JPanel CreateJPanel_Menu (){
        JPanel pMenu = new JPanel();
        pMenu.setLayout(null);
        Border menuTitleBoder = BorderFactory.createTitledBorder("Menu");
        pMenu.setBorder(menuTitleBoder);
        menuButton=new JButton[4];
        String[] arrMenu = {"Sản Phẩm", "Tài Khoản", "Đăng Xuất"} ;     

        int toaDoXMenuButton=5,toaDoYMenuButton=200;
        for(int i=0;i<arrMenu.length; i++)
        {
            menuButton [i] = new JButton(arrMenu[i]);
            menuButton[i].setBounds(toaDoXMenuButton, toaDoYMenuButton, 190, 50);
            menuButton[i].setFont(new Font("Arial", Font.PLAIN, 20));
            menuButton[i].setActionCommand("button"+i);
            menuButton[i].addActionListener(this);
            pMenu.add(menuButton[i]);
            toaDoYMenuButton += 50;
        }
        return pMenu;
    }
    
    public JPanel CreateJPanel_Icon(){
        JPanel pIconMenu = new JPanel();
        pIconMenu.setLayout(null);
        return pIconMenu;
    }
    
    public JPanel CreateJPanel_Header (){
        JPanel pHeader = new JPanel();
        pHeader.setLayout(null);

        JLabel nameHeaderLable = new JLabel("Pizza Hups");
        nameHeaderLable.setBounds(520, 25, 400, 50);
        nameHeaderLable.setFont(new Font("Arial", Font.BOLD, 28));
        pHeader.add(nameHeaderLable);
        return pHeader;
    }
    
    public JPanel CreateJPanel_Content (){
        JPanel sanPhamPanel = new JPanel();
        return sanPhamPanel;
    }
    
    public JPanel CreateJPanel_SanPham () throws Exception{
        JPanel sanPhamPanel = new JPanel();
        sanPhamPanel.setBackground(Color.red);
        sanPhamPanel.setLayout(null);
        final String url="jdbc:mysql://localhost:3306/test";
        final String user="root";
        final String password="";
        Connection Conn = null;
        PreparedStatement ps;   
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conn = DriverManager.getConnection(url, user, password);
        }
        catch (Exception ex) {
           System.out.println(ex.getMessage());
        }
        
        JButton add = new JButton("Add New Product");
	JButton change = new JButton("Edit Selected Product");
	JButton delete = new JButton("Delete a product");     
	JTextField search = new JTextField();
	JButton find = new JButton("Search");
        
	add.setBounds(250, 550, 180, 50);
	change.setBounds(450, 550, 180, 50);
	delete.setBounds(650, 550, 180, 50);
	search.setBounds(550, 20, 400, 30);
	find.setBounds(950,20,100,30);
	add.setBackground(Color.red);
	add.setForeground(Color.white);
	change.setBackground(Color.BLUE);
	change.setForeground(Color.white);
	delete.setBackground(Color.green);
	delete.setForeground(Color.white);
	sanPhamPanel.setBackground(Color.CYAN);
	sanPhamPanel.add(add);
	sanPhamPanel.add(change);
	sanPhamPanel.add(delete);
	sanPhamPanel.add(search);
	sanPhamPanel.add(find);
        
	String[] header = {"id","name","quantity","type"};
	String[][] data= {{"1","tomato","100","italy"},{"2","beef","150","paris"}};
        
	JTable table=new JTable(data,header);
        this.add(sanPhamPanel);
        JScrollPane scroll = new JScrollPane();
        sanPhamPanel.add(scroll);
        table.setRowHeight(30);
        scroll.setBounds(10, 80, 1040, 450);
   
        
        this.setVisible(true);
        return sanPhamPanel;
    }
    
    public JPanel CreateJPanel_GioHang (){
        JPanel gioHanPanel = new JPanel();
        gioHanPanel.setBackground(Color.pink);
        this.add(gioHanPanel);
        this.setVisible(true);
        return gioHanPanel;
    }
    
    public JPanel CreateJPanel_TaiKhoan (){
        JPanel taiKhoanPanel = new JPanel();
        taiKhoanPanel.setBackground(Color.yellow);
        this.add(taiKhoanPanel);
        this.setVisible(true);
        return taiKhoanPanel;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("mousePressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //hrow new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("mouseReleased");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("mouseEntered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("mouseExited");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
            if("button0".equals(e.getActionCommand()))
            {
                try {     
                    JPanel sanPhamPanel =CreateJPanel_SanPham();
                } catch (Exception ex) {
                    Logger.getLogger(HomeUser.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
            else if("button1".equals(e.getActionCommand()))
            {
                JPanel gioHangPanel =CreateJPanel_GioHang();
               
            }
           else if("button2".equals(e.getActionCommand()))
            {
                JPanel taiKhoanPanel =CreateJPanel_TaiKhoan();
               
            }
            /*else if("button3".equals(e.getActionCommand()))
            {
                System.exit(0);
            }*/
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
        
    public static void main(String[] args) {
        HomeUser bd=new HomeUser();
    }
}
