/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.*;
import DTO.*;
import DAO.*;
import java.awt.*;
import static java.awt.Font.BOLD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyen thanh sang
 */
public class HomeUser extends JFrame implements MouseListener, ActionListener {
    JPanel menuPanel,headerPanel,contentPanel;
    JTable productTable; 
    JButton menuButton[];
    DefaultTableModel model = new DefaultTableModel();
    Vector header;
    JScrollPane scrollPanel;  
    ProductsDTO Products = new ProductsDTO();
    public HomeUser()
    {
        init();
        SanPhamBUS bus = new SanPhamBUS();
        if (SanPhamBUS.Arr_products.size() == 0) {
            try {
                bus.docSanPham();
            } catch (Exception ex) {
                Logger.getLogger(HomeUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Add_header();
        for (ProductsDTO products : SanPhamBUS.Arr_products) {
            Add_row(products);
        }
        productTable.setModel(model);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    public void init()
    {
        setSize(1280,768);
        setLayout(new BorderLayout(3,3));
        
        menuPanel=CreateJPanel_Menu();
        menuPanel.setPreferredSize(new Dimension(200, 1110));
        
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
        pMenu.setBackground(Color.BLACK);
        JLabel iconHeader = CreateJLable_Icon(10, 20, 180, 200, "/Image/sv.jpg");
        pMenu.add(iconHeader);
        
        Border menuTitleBoder = BorderFactory.createTitledBorder("Menu");
        pMenu.setBorder(menuTitleBoder);
        menuButton=new JButton[4];
        String[] arrMenu = {"Trang Chủ","Sản Phẩm", "Tài Khoản", "Đăng Xuất"} ;     

        int toaDoXMenuButton=5,toaDoYMenuButton=230;
        for(int i=0;i<arrMenu.length; i++)
        {
            menuButton [i] = new JButton(arrMenu[i]);
            menuButton[i].setBounds(toaDoXMenuButton, toaDoYMenuButton, 190, 50);
            menuButton[i].setFont(new Font("Arial", Font.PLAIN, 20));
            menuButton[i].setActionCommand("button"+i);
            menuButton[i].addActionListener(this);
            menuButton[i].setBackground(Color.GREEN);
            pMenu.add(menuButton[i]);
            toaDoYMenuButton += 50;
        }
        return pMenu;
    }
    //create ICON 
    public JLabel CreateJLable_Icon(int x , int y , int w , int h , String imgpath){
        Icon icon  = new ImageIcon( getClass().getResource(imgpath) );
        JLabel iconlable = new JLabel(icon);
        iconlable.setBounds(x, y, w,h);
        return iconlable;
    }
    
    public JPanel CreateJPanel_Header (){
        JPanel pHeader = new JPanel();
        pHeader.setLayout(null);
        JLabel iconHeader = CreateJLable_Icon(400, -50, 150, 200,  "/Image/PizzaLoGoHome.PNG");
        pHeader.add(iconHeader);
        
        JLabel nameHeaderLable = new JLabel("Pizza Hups");
        nameHeaderLable.setBounds(520, 25, 400, 50);
        nameHeaderLable.setFont(new Font("Arial", Font.BOLD, 28));
        pHeader.add(nameHeaderLable);
        return pHeader;
    }
    
    public JPanel CreateJPanel_Content (){
        JPanel trangChuPanel = new JPanel();
        trangChuPanel.setBackground(Color.DARK_GRAY);
        this.add(trangChuPanel);
        this.setVisible(true);
        return trangChuPanel;
    }
    
   
    
    public JPanel CreateJPanel_SanphamTable() {
        JPanel sanPhamPanel = new JPanel();
        sanPhamPanel.setLayout(null);
        sanPhamPanel.setBackground(Color.orange);
        header = new Vector();
        header.add("Mã sản phẩm");
        header.add("Tên sản phẩm");
        header.add("Đơn giá");
        header.add("Loại Sản Phẩm");

        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(header, 0);
        }
        productTable = new JTable(null, header);

        productTable.setFont(new Font("Arial", 0, 15));
        productTable.setModel(model);//add model len table
        productTable.getTableHeader().setFont(new Font("Arial", BOLD, 18)); //set font cho vector header
        productTable.getTableHeader().setForeground(Color.black); //set màu chữ cho header
        productTable.getTableHeader().setPreferredSize(new Dimension(30, 50));//set độ dài độ rộng của header
        productTable.getTableHeader().setBackground(Color.pink);//set background cho header

        scrollPanel = new JScrollPane(productTable);
        scrollPanel.setBounds(5, 100, 700, 515);
        // tblQLS.getTableHeader().setBorder(BorderFactory.createLineBorder(null, 0, true));
        productTable.setPreferredSize(new Dimension(500,500));
         scrollPanel.setPreferredSize(new Dimension(500,500));

        sanPhamPanel.add(scrollPanel); // add table vào scrollPanel
        productTable.setFillsViewportHeight(true);//hiển thị table
        
        //button
        JButton add = new JButton("Add");
	JButton change = new JButton("Edit");
	JButton delete = new JButton("Delete");     
	JTextField searchtxt = new JTextField();
	JButton find = new JButton("seach");
        
	add.setBounds(720, 550, 100, 50);
	change.setBounds(830, 550, 100, 50);
	delete.setBounds(940, 550, 100, 50);
	searchtxt.setBounds(300, 60, 300, 30);
	find.setBounds(600,60,100,30);
        
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
	sanPhamPanel.add(searchtxt);
	sanPhamPanel.add(find);
        
        add.setActionCommand("add");
        change.setActionCommand("change");
        delete.setActionCommand("delete");
        find.setActionCommand("find");
        
        add.addActionListener(this);
        change.addActionListener(this);
        delete.addActionListener(this);
        find.addActionListener(this);
        
        this.add(sanPhamPanel);
        this.setVisible(true);
        return sanPhamPanel;

    }

    private void Add_header() {
        Vector header = new Vector();
        header.add("Mã sản phẩm");
        header.add("Tên sản phẩm");
        header.add("Đơn giá");
        header.add("Loại Sản Phẩm");
        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(header, 0);
        }
    }

    private void Add_row(ProductsDTO products) {
        Vector row = new Vector();
        row.add(products.getID_Product());
        row.add(products.getName());
        row.add(products.getPice());
        row.add(products.getCategory());     
        model.addRow(row);
//        productTable.setModel(model);
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
                 JPanel trangChuPanel = CreateJPanel_Content();
            }
            else if("button1".equals(e.getActionCommand()))
            {   
                    JPanel sanPhamPanel =CreateJPanel_SanphamTable();
               
            }
           else if("button2".equals(e.getActionCommand()))
            {
                JPanel gioHangPanel =CreateJPanel_GioHang();      
            }
            else if("button3".equals(e.getActionCommand()))
            {
                JPanel taiKhoanPanel =CreateJPanel_TaiKhoan();
            }
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
            if("add".equals(e.getActionCommand()))
            {
                JOptionPane.showMessageDialog(this, "THEM THANH CONG");
               
            }
            if("delete".equals(e.getActionCommand()))
            {
                int input = JOptionPane.showConfirmDialog(this, "Ban Co Chac Muon Xoa?");
                if(input == 0){
                    JOptionPane.showMessageDialog(rootPane, "xoa Thanh Cong");
                }
                if(input == 1){
                    JOptionPane.showMessageDialog(rootPane, "Bạn chọn No");
                }
                if(input == 2){
                    JOptionPane.showMessageDialog(rootPane, "Thoát ");
                }  
            }
            if("change".equals(e.getActionCommand()))
            {
                JOptionPane.showMessageDialog(this, "Sua Thanh Cong");
               
            }
            
    }
        public static void main(String[] args) {
        HomeUser bd =new HomeUser();
    }
}
