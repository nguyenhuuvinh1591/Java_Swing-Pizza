/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.AccountBUS;
import BUS.Common;
import BUS.LoginBUS;
import BUS.SanPhamBUS;
import DAO.AccountDAO;
import DAO.LoginDAO;
import DTO.ProductsDTO;
import DAO.MySQLConnect;
import DTO.AccountDTO;
import DTO.LoginDTO;
import java.awt.*;
import static java.awt.Font.BOLD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import ExcelAndReport.*;

/**
 *
 * @author nguyen thanh sang
 */
public class HomeUser extends JFrame implements MouseListener, ActionListener ,KeyListener ,ItemSelectable{
    JPanel menuPanel,headerPanel,contentPanel,sanPhamPanel, iconPanel = new JPanel(),ptaikhoan,pnhanvien;
    JTable productTable,accountTabel,nhanvienTable,gioHangTable;
    JLabel iconPizza,menuLabel[],taikhoanLabel,matkhauLabel,idLabel,typeLabel,hotenLabel,ngaysinhLabel,idnhanvienLabel,gioitinhLabel,diachiLabel,sdtLabel;
    JTextField countTXT, seachtxt,menuTextField[],menuTextFieldADMIN[],giaTriDauTXT,giaTriCuoiTXT,tongTienTXT, taikhoan_TextField, matkhau_TextField, id_TextField,type_TextField,
    idnhanvien_TextField,hoten_TextField,ngaysinh_TextField,gioitinh_TextField,diachi_TextField,sdt_TextField;
    static JButton  refresh;
    JButton menuButton[],find, suataikhoanButton,themtaikhoanButton,xoataikhoanButton,suataikhoan1Button,themtaikhoan1Button,xoataikhoan1Button,QLNVExelButton,QLTKExelButton;
    JComboBox loaiSanPhamCBB;
    DefaultTableModel model = new DefaultTableModel();
    static DefaultTableModel modelGioHang = new DefaultTableModel();
    DefaultTableModel modelTaiKhoan = new DefaultTableModel();
    DefaultTableModel modelNhanvien = new DefaultTableModel();
    Vector headerSP,headerGH,headerTK,dataTK,headerNV,dataNV;
    JScrollPane scrollPanel,scrollPanel1;  
    ProductsDTO Products = new ProductsDTO();
    LoginDTO login = new LoginDTO();
    LoginBUS login_bus = new LoginBUS();
    AccountDTO account = new AccountDTO();
    AccountBUS account_bus = new AccountBUS();
//    private ArrayList<navItemDTO> navObj = new ArrayList<>();  //Chứa các nút nhấn trên thanh menu -> được tạo từ các panel
    public static ArrayList<ProductsDTO> Arr_GioHang = new ArrayList();
    int count=1 , check=0;
    double Tong = 0.0;
    
    

    public HomeUser() 
    {
        init();
        //Lay DU lieu ra (VINH)
        SanPhamBUS bus = new SanPhamBUS();
        if (SanPhamBUS.Arr_products.size() == 0) {
            try {
                bus.docSanPham();
            } catch (Exception ex) {
                Logger.getLogger(HomeUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Add_header("Mã sản phẩm","Tên sản phẩm","Đơn giá(VNĐ)","Loại Sản Phẩm");
        for (ProductsDTO products : SanPhamBUS.Arr_products) {
            if(products.getTrangThai() == 1)
                Add_row_SanPham(products);
        }
        productTable.setModel(model);
    }
    public void init()
    {
        Font font = new Font("Segoe UI",Font.BOLD,14);
        setSize(1280,700);
        setLayout(new BorderLayout(3,3));
        
        //if(LoginBUS.Arr_login.get(login.getType()).equals(1)){
            menuPanel=CreateJPanel_Menu();
            menuPanel.setPreferredSize(new Dimension(200, 1000));
        //}
//        if(LoginBUS.Arr_login.get(login.getType()).equals(0)){
//            menuPanel=CreateJPanel_Menu_TYPE_0();
//            menuPanel.setPreferredSize(new Dimension(200, 1000));
//        }

        
        headerPanel= CreateJPanel_Header();
        headerPanel.setPreferredSize(new Dimension(0,50));
        
        contentPanel=new JPanel();      
        contentPanel = CreateJPanel_TrangChu();
        
        add(menuPanel,BorderLayout.WEST);
        add(headerPanel,BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    public JPanel CreateJPanel_Menu(){
        JPanel pMenu = new JPanel();
        pMenu.setLayout(null);
        pMenu.setBackground(Color.PINK);           
        Border menuTitleBoder = BorderFactory.createTitledBorder("Menu");
        pMenu.setBorder(menuTitleBoder);
        if(Login.idhienhanh.equals("ADMIN")){
            JLabel iconHeader = CreateJLable_Icon(10, 20, 180, 200, "/Image/admin.PNG");
            pMenu.add(iconHeader);  
            menuButton=new JButton[8];
            String[] arrMenuAdmin = {"Quản Lí Sản Phẩm","Quản Lí Nhân Viên", "Quản Lí Tài Khoản","Quản Lí Khách Hàng",
                "Quản Lí Hoá Đơn","Thống Kê","Thoát"} ;
            int toaDoXMenuButton=5,toaDoYMenuButton=230;
            for(int i=0;i<arrMenuAdmin.length; i++)
            {
                menuButton [i] = new JButton(arrMenuAdmin[i]);
                menuButton[i].setBounds(toaDoXMenuButton, toaDoYMenuButton, 190, 50);
                menuButton[i].setFont(new Font("Arial", Font.PLAIN, 16));
                menuButton[i].setActionCommand("buttonAdmin_"+i);
                menuButton[i].addActionListener(this);
                menuButton[i].setBackground(new Color(255, 190, 133));
                //menuButton[i].setForeground(Color.CYAN);
                pMenu.add(menuButton[i]);
                toaDoYMenuButton += 50;
            }
        }else{
            JLabel iconHeader = CreateJLable_Icon(10, 20, 180, 200, "/Image/sv.jpg");
            pMenu.add(iconHeader); 
            menuButton=new JButton[5];
            String[] arrMenu = {"Trang Chủ","Bán Hàng", "Tài Khoản","Đăng Xuất","Thoát"} ; 
            int toaDoXMenuButton=5,toaDoYMenuButton=230;
             for(int i=0;i<arrMenu.length; i++)     
            {
                menuButton [i] = new JButton(arrMenu[i]);
                menuButton[i].setBounds(toaDoXMenuButton, toaDoYMenuButton, 190, 50);
                menuButton[i].setFont(new Font("Arial", Font.PLAIN, 16));
                menuButton[i].setActionCommand("buttonStaff_"+i);
                menuButton[i].addActionListener(this);
                menuButton[i].setBackground(new Color(255, 190, 133));
//                menuButton[i].setHorizontalAlignment(RIGHT_ALIGNMENT);
                //menuButton[i].setForeground(Color.CYAN);
                pMenu.add(menuButton[i]);
                toaDoYMenuButton += 50;
            }
            menuButton[0].setIcon(new ImageIcon("./src/Image/home.PNG"));
            menuButton[1].setIcon(new ImageIcon("./src/Image/banhang.png"));
            menuButton[2].setIcon(new ImageIcon("./src/Image/taikhoan.png"));
            menuButton[3].setIcon(new ImageIcon("./src/Image/logout.PNG"));
            menuButton[4].setIcon(new ImageIcon("./src/Image/exitx60.png"));
        }
        
        return pMenu;
    }

    public JLabel CreateJLable_Icon(int x , int y , int w , int h , String imgpath){
        Icon icon  = new ImageIcon( getClass().getResource(imgpath) );
        JLabel iconlable = new JLabel(icon);
        iconlable.setBounds(x, y, w,h);
        return iconlable;
    }
    
    public JPanel CreateJPanel_Header (){
        JPanel pHeader = new JPanel(null);
        pHeader.setBackground(Color.pink);

        JLabel title = new JLabel("PIZZA HÚP");
        title.setBounds(80, 10, 300, 30);
        title.setFont(new Font("Arial", 0, 20));
        title.setForeground(Color.black);
        JLabel logo = new JLabel(new ImageIcon("./src/Image/banhang.png"));
        logo.setBounds(-50, 0, 200, 48);
        pHeader.add(logo);
        pHeader.add(title);

////        JLabel logout=new JLabel();
////        logout.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/icons8_exit_30px.png")));
//        logout.setBounds(width*0, 0, 30, 30);
//        logout.addMouseListener(new MouseAdapter(){
//            @Override
//            public void mousePressed(MouseEvent evt){
//                //viết code logout
//            }
//        });
//        header.add(logout);
        JLabel minimize = new JLabel(new ImageIcon("./src/Image/minisize.png"), JLabel.LEFT);
        minimize.setBounds(1180, 0, 60, 50);
        minimize.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                minimize.setBackground(Color.yellow);
            }

            public void mousePressed(MouseEvent evt) {
                setState(JFrame.ICONIFIED);
                minimize.setBackground(Color.yellow);
            }
        });
        pHeader.add(minimize);

        JLabel exit = new JLabel(new ImageIcon("./src/Image/exit.png"), JLabel.LEFT);
//        exit.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/icons8_shutdown_30px_1.png")));
        exit.setBounds(1230, 0, 60, 50);
        exit.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                exit.setBackground(Color.red);
            }

            public void mousePressed(MouseEvent evt) {
                dispose();
            }
        });
        pHeader.add(exit);
        this.setUndecorated(true);
        return pHeader;

    }
    
    public JPanel CreateJPanel_TrangChu (){
        JPanel trangChuPanel = new JPanel();
        trangChuPanel.setBackground(Color.PINK);
        trangChuPanel.setLayout(null);
        trangChuPanel.setBounds(0, 0, 1080, 770);
        JLabel chao = CreateJLable_Icon(-60,-70, 1200, 786, "/Image/backgroudpizza.jpg");
        chao.setFont(new Font("Arial", BOLD, 80));
        trangChuPanel.add(chao);
        this.setVisible(true);
        return trangChuPanel;
    }
    
    //TYPE = 0;
    public JPanel CreateJPanel_SanphamTable() {
        JPanel psanPham = new JPanel();
        psanPham.setLayout(null);
        psanPham.setBackground(Color.GRAY);
        //add tim kiem theo gia
        JPanel timKiemTheoGiaPanel = CreatePanel_TimKiemTheoGia();
        psanPham.add(timKiemTheoGiaPanel);
        //add tim kiem theo loai sp
        JPanel timKiemTheoLoaiPanel = CreatePanel_TimKiemLoai();
        psanPham.add(timKiemTheoLoaiPanel);

        //--------- Main table 
        headerSP = new Vector();
        headerSP.add("Mã sản phẩm");
        headerSP.add("Tên sản phẩm");
        headerSP.add("Đơn giá(VNĐ)");
        headerSP.add("Loại Sản Phẩm");
        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(headerSP, 0);
        }
        productTable = new JTable(null, headerSP);
        psanPham.setBounds(0, 0, 1080, 660);
        productTable.setFont(new Font("Arial", 0, 15));
        productTable.setModel(model);//add model len table
        productTable.getTableHeader().setFont(new Font("Arial", BOLD, 18)); //set font cho vector header
        productTable.getTableHeader().setForeground(Color.black); //set màu chữ cho header
        productTable.getTableHeader().setPreferredSize(new Dimension(30, 40));//set độ dài độ rộng của header
        productTable.getTableHeader().setBackground(Color.RED);//set background cho header

        scrollPanel = new JScrollPane(productTable);
        scrollPanel.setBounds(10, 75, 710, 300);
        // tblQLS.getTableHeader().setBorder(BorderFactory.createLineBorder(null, 0, true));
        productTable.setPreferredSize(new Dimension(500, 500));
        productTable.setRowHeight(40);
        productTable.setGridColor(Color.GREEN);
        // scrollPanel.setPreferredSize(new Dimension(500,500));
//        scrollPanel.setViewportView(productTable);
        psanPham.add(scrollPanel); // add table vào scrollPanel
        productTable.addMouseListener(this);
        productTable.setFillsViewportHeight(true);//hiển thị table     
        productTable.setShowGrid(true);
        productTable.setDefaultEditor(Object.class, null);
        //---------------------- canh chinh do dai cot header
        productTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        productTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        productTable.getColumnModel().getColumn(2).setPreferredWidth(70);
        //--------end Main table
        //user
        if (Login.type == 0) {
            //-----------------table gio hang
            JButton gioHangTitle = new JButton("Giỏ Hàng của Bạn");
            gioHangTitle.setFont(new Font("Arial", BOLD, 15));
            //ImageIcon tempIcon = new ImageIcon(getClass().getResource("./src/Image/refresh.png"));
            gioHangTitle.setIcon(new ImageIcon("./src/Image/cart.png"));
            gioHangTitle.setBounds(10, 395, 710, 30);
            psanPham.add(gioHangTitle);
            JTable gioHangTable = CreateTable_GioHang();
            scrollPanel = new JScrollPane(gioHangTable);
            scrollPanel.setBounds(10, 420, 710, 220);
            psanPham.add(scrollPanel);
            //----------------end table gio hang
            //button
        }
        //admin
        if (Login.type == 1) {
            menuButton = new JButton[6];
            menuTextFieldADMIN = new JTextField[6];
            String[] arrMenu = {"Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Loại sản phẩm","Trạng thái", "Tên ảnh"};
            int toaDoXMenuButton = 20, toaDoYMenuButton = 400;
            for (int i = 0; i < arrMenu.length; i++) {
                menuButton[i] = new JButton(arrMenu[i]);
                menuButton[i].setBounds(toaDoXMenuButton, toaDoYMenuButton, 150, 30);
                menuButton[i].setFont(new Font("Arial", Font.PLAIN, 15));
                menuButton[i].setBackground(Color.GREEN);
                psanPham.add(menuButton[i]);
                
                menuTextFieldADMIN[i] = new JTextField();
                menuTextFieldADMIN[i].setBounds(toaDoXMenuButton + 160, toaDoYMenuButton, 170, 30);
                psanPham.add(menuTextFieldADMIN[i]);
                toaDoYMenuButton += 40;
            }
        }
        //------------end 
        //-----------------admin----------------
        JButton addADMIN = new JButton("Thêm");
        JButton deADMIN = new JButton("Xoá");
        JButton changeADMIN = new JButton("Sửa");
        JButton xuatexcel = new JButton("Xuất Excel");
        JButton chonAnhADMIN = new JButton("Chọn Ảnh");
        
        addADMIN.setBounds(400, 420, 100, 50);
        deADMIN.setBounds(400, 480, 100, 50);
        changeADMIN.setBounds(400, 540, 100, 50);
        xuatexcel.setBounds(550, 430, 100, 50);
        chonAnhADMIN.setBounds(550, 500, 100, 50);
        
        addADMIN.setBackground(Color.GREEN);
        deADMIN.setBackground(Color.GREEN);
        changeADMIN.setBackground(Color.GREEN);
        xuatexcel.setBackground(Color.GREEN);
        chonAnhADMIN.setBackground(Color.GREEN);
        
        addADMIN.setActionCommand("addADMIN");
        deADMIN.setActionCommand("xoaADMIN");
        changeADMIN.setActionCommand("suaADMIN");
        xuatexcel.setActionCommand("xuatEXCEL");
        chonAnhADMIN.setActionCommand("report");
        
        addADMIN.addActionListener(this);
        deADMIN.addActionListener(this);
        changeADMIN.addActionListener(this);
        chonAnhADMIN.addActionListener(this);
        xuatexcel.addActionListener(this);
        //------------------------------end
        seachtxt = new JTextField();
        JButton exe = new JButton("Lọc>>>");
        JButton add = new JButton("Thêm");
        JButton print = new JButton("In hoá đơn ");
        JButton addCount = new JButton("+");
        JButton subtractCount = new JButton("-");
        refresh = new JButton(new ImageIcon("./src/Image/refresh.png"));
        countTXT = new JTextField();
        find = new JButton(new ImageIcon("./src/Image/seach.png"));


        //Jlable

        exe.setBounds(323, 15, 80, 50);
        refresh.setBounds(690, 10, 30, 30);
        seachtxt.setBounds(407, 45, 280, 30);
        find.setBounds(690, 45, 30, 30);
        add.setBounds(780, 330, 110, 50);
        print.setBounds(920, 330, 110, 50);
        addCount.setBounds(940, 280, 60, 30);
        subtractCount.setBounds(800, 280, 60, 30);
        countTXT.setBounds(880, 280, 40, 30);
        countTXT.setText(String.valueOf(count));
        countTXT.setEditable(false);
        countTXT.setHorizontalAlignment(countTXT.CENTER);

        exe.setBackground(Color.red);
        add.setBackground(Color.red);
        add.setForeground(Color.white);
        print.setBackground(Color.BLUE);
        print.setForeground(Color.white);
        addCount.setBackground(Color.green);
        addCount.setForeground(Color.white);
        subtractCount.setBackground(Color.green);
        subtractCount.setForeground(Color.white);

        if (Login.type == 1) {
            psanPham.add(addADMIN);
            psanPham.add(deADMIN);
            psanPham.add(changeADMIN);
            psanPham.add(xuatexcel);
            psanPham.add(chonAnhADMIN);
        }
        if (Login.type == 0) {
            psanPham.add(add);
            psanPham.add(print);
            psanPham.add(addCount);
            psanPham.add(subtractCount);
            psanPham.add(countTXT);
        }
        psanPham.add(exe);
        psanPham.add(refresh);
        psanPham.add(seachtxt);

        psanPham.add(find);


        exe.setActionCommand("exe");
        add.setActionCommand("add");
        print.setActionCommand("print");
        addCount.setActionCommand("+");
        subtractCount.setActionCommand("-");
        find.setActionCommand("find");
        refresh.setActionCommand("refresh");
        exe.addActionListener(this);
        add.addActionListener(this);
        print.addActionListener(this);
        addCount.addActionListener(this);
        subtractCount.addActionListener(this);
        find.addActionListener(this);
        refresh.addActionListener(this);
       
        
        this.setVisible(true);
        return psanPham;

    }

    public JPanel createJpanel_TxTAdmin() {
        JPanel pTXT = new JPanel();
        pTXT.setLayout(null);
        pTXT.setBounds(0, 420, 720, 300);
        pTXT.setBackground(Color.pink);
//        pTXT.add(lb);
        return pTXT;
    }

    public JTable CreateTable_GioHang() {
        gioHangTable = new JTable();
        headerGH = new Vector();
        headerGH.add("Mã sản phẩm");
        headerGH.add("Tên sản phẩm");
        headerGH.add("SL");
        headerGH.add("Đơn giá(VNĐ)");
        modelGioHang = new DefaultTableModel(headerGH, 0);
        gioHangTable = new JTable(null, headerGH);
        gioHangTable.setFont(new Font("Arial", 0, 15));
        gioHangTable.setModel(model);//add model len table
        gioHangTable.getTableHeader().setFont(new Font("Arial", BOLD, 16)); //set font cho vector header
        gioHangTable.getTableHeader().setForeground(Color.black); //set màu chữ cho header
        gioHangTable.getTableHeader().setPreferredSize(new Dimension(30, 40));//set độ dài độ rộng của header
        gioHangTable.getTableHeader().setBackground(Color.RED);//set background cho header

        // tblQLS.getTableHeader().setBorder(BorderFactory.createLineBorder(null, 0, true));
        //tGioHang.setPreferredSize(new Dimension(500,500));
        gioHangTable.setRowHeight(40);
        gioHangTable.setGridColor(Color.GREEN);
        // scrollPanel.setPreferredSize(new Dimension(500,500));
//        scrollPanel.setViewportView(productTable);
        gioHangTable.addMouseListener(this);
        gioHangTable.setFillsViewportHeight(true);//hiển thị table     
        gioHangTable.setShowGrid(false);
        gioHangTable.setDefaultEditor(Object.class, null);
        //---------------------- canh chinh do dai cot header
        gioHangTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        gioHangTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        gioHangTable.getColumnModel().getColumn(2).setPreferredWidth(20);
        for (ProductsDTO product : Arr_GioHang) {
            Add_row_SanPham(product);
        }
        gioHangTable.setModel(modelGioHang);
        gioHangTable.updateUI();
        return gioHangTable;
    }
    
    public JPanel CreatePanel_QuanLiNhanVien(){
        pnhanvien = new JPanel();
        gioitinh_TextField = new JTextField();
        hoten_TextField = new JTextField();
        ngaysinh_TextField = new JTextField();
        idnhanvien_TextField = new JTextField();
        diachi_TextField = new JTextField();
        sdt_TextField = new JTextField();
        
        hotenLabel = new JLabel("Họ tên:");
        ngaysinhLabel = new JLabel("Ngày sinh:");
        idnhanvienLabel = new JLabel("ID nhân viên:");
        gioitinhLabel = new JLabel("Giới tính:");
        diachiLabel = new JLabel("Địa chỉ:");
        sdtLabel = new JLabel("SDT:");
        
        hotenLabel.setBounds(100, 450, 100, 40);
        ngaysinhLabel.setBounds(100, 500, 100, 40);
        idnhanvienLabel.setBounds(100, 400, 100, 40);
        gioitinhLabel.setBounds(100, 550, 100, 40);
        diachiLabel.setBounds(450, 400, 100, 40);
        sdtLabel.setBounds(450, 450, 100, 40);
        
        hoten_TextField.setBounds(200, 450, 200, 40);
        ngaysinh_TextField.setBounds(200, 500, 200, 40);
        idnhanvien_TextField.setBounds(200, 400, 200, 40);
        gioitinh_TextField.setBounds(200, 550, 200, 40);
        diachi_TextField.setBounds(520, 400, 200, 40);
        sdt_TextField.setBounds(520, 450, 200, 40);
        
        
        suataikhoan1Button = new JButton("Sửa");
        suataikhoan1Button.setActionCommand("suanhanvien");
        suataikhoan1Button.addActionListener(this);
        suataikhoan1Button.setBounds(750, 450, 200, 40);
        themtaikhoan1Button = new JButton("Thêm");
        themtaikhoan1Button.setActionCommand("themnhanvien");
        themtaikhoan1Button.addActionListener(this);
        themtaikhoan1Button.setBounds(750, 400, 200, 40);
        xoataikhoan1Button = new JButton("Xoá");
        xoataikhoan1Button.setActionCommand("xoanhanvien");
        xoataikhoan1Button.addActionListener(this);
        xoataikhoan1Button.setBounds(750, 500, 200, 40);
        QLNVExelButton = new JButton("Xuất Excel");
        QLNVExelButton.setActionCommand("QLNVExcel");
        QLNVExelButton.addActionListener(this);
        QLNVExelButton.setBackground(Color.green);
        QLNVExelButton.setBounds(750, 550, 200, 40);
        
        pnhanvien.add(suataikhoan1Button);
        pnhanvien.add(themtaikhoan1Button);
        pnhanvien.add(xoataikhoan1Button);
        pnhanvien.add(QLNVExelButton);
        pnhanvien.add(hoten_TextField);
        pnhanvien.add(ngaysinh_TextField);
        pnhanvien.add(gioitinh_TextField);
        pnhanvien.add(idnhanvien_TextField);
        pnhanvien.add(diachi_TextField);
        pnhanvien.add(sdt_TextField);
        pnhanvien.add(hotenLabel);
        pnhanvien.add(ngaysinhLabel);
        pnhanvien.add(idnhanvienLabel);
        pnhanvien.add(gioitinhLabel);
        pnhanvien.add(diachiLabel);
        pnhanvien.add(sdtLabel);
        
        pnhanvien.setLayout(null);
        pnhanvien.setBounds(0,0,1080,660);
        nhanvienTable = new JTable(null, headerNV);
        headerNV = new Vector();
        dataNV = new Vector();
        headerNV.add("ID");
        headerNV.add("Họ tên");
        headerNV.add("Ngày sinh");
        headerNV.add("Giới tính");
        headerNV.add("Địa chỉ");
        headerNV.add("SDT");
        modelNhanvien = new DefaultTableModel(headerNV, 0);
        nhanvienTable.setFont(new Font("Arial", 0, 15));
        nhanvienTable.setModel(modelNhanvien);//add model len table
        nhanvienTable.getTableHeader().setFont(new Font("Arial", BOLD, 18)); //set font cho vector header
        nhanvienTable.getTableHeader().setForeground(Color.black); //set màu chữ cho header
        nhanvienTable.getTableHeader().setPreferredSize(new Dimension(30, 40));//set độ dài độ rộng của header
        nhanvienTable.getTableHeader().setBackground(Color.RED);//set background cho header
        
        pnhanvien.add(nhanvienTable);
        
        scrollPanel1 = new JScrollPane(nhanvienTable);
        scrollPanel1.setBounds(50, 75, 950, 300);
        nhanvienTable.setPreferredSize(new Dimension(500,500));
        nhanvienTable.setRowHeight(40);
        nhanvienTable.setGridColor(Color.GREEN);

        pnhanvien.add(scrollPanel1);
        nhanvienTable.setFillsViewportHeight(true);
        nhanvienTable.setShowGrid(true);
        nhanvienTable.setDefaultEditor(Object.class, null); 

        nhanvienTable.getColumnModel().getColumn(0).setPreferredWidth(50); 
        nhanvienTable.getColumnModel().getColumn(1).setPreferredWidth(150); 
        nhanvienTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        nhanvienTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        nhanvienTable.getColumnModel().getColumn(4).setPreferredWidth(200);
                try {
                    account_bus.docaccount();
                    for (AccountDTO acc : AccountBUS.Arr_account){
                        Vector tempp = new Vector();
                            tempp.add(acc.getId_nhanvien());
                            tempp.add(acc.getHoten());
                            tempp.add(acc.getNgaysinh());
                            tempp.add(acc.getGioitinh());
                            tempp.add(acc.getDiachi());
                            tempp.add(acc.getSodienthoai());
                            dataNV = tempp;
                            modelNhanvien.addRow(dataNV);
                        
                    }
                    nhanvienTable.setModel(modelNhanvien);
                } catch (Exception ae){
                }
        pnhanvien.setVisible(true);
        nhanvienTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked (MouseEvent e){
                int j = nhanvienTable.getSelectedRow();
                if (j>=0){
                    idnhanvien_TextField.setText((String)nhanvienTable.getModel().getValueAt(j, 0));
                    hoten_TextField.setText((String)nhanvienTable.getModel().getValueAt(j, 1));
                    ngaysinh_TextField.setText((String)nhanvienTable.getModel().getValueAt(j, 2));
                    gioitinh_TextField.setText((String)nhanvienTable.getModel().getValueAt(j, 3));
                    diachi_TextField.setText((String)nhanvienTable.getModel().getValueAt(j, 4));
                    sdt_TextField.setText((String)nhanvienTable.getModel().getValueAt(j, 5));
                }
            }
        });
        return pnhanvien;
    }
    public JPanel CreatePanel_QuanLiTaiKhoan() {
        ptaikhoan = new JPanel();
        type_TextField = new JTextField();
        taikhoan_TextField = new JTextField();
        matkhau_TextField = new JTextField();
        id_TextField = new JTextField();
        
        taikhoanLabel = new JLabel("Tài khoản:");
        matkhauLabel = new JLabel("Mật khẩu:");
        idLabel = new JLabel("ID:");
        typeLabel = new JLabel("Phân quyền:");
        
        taikhoanLabel.setBounds(150, 450, 100, 40);
        matkhauLabel.setBounds(150, 500, 100, 40);
        idLabel.setBounds(150, 400, 100, 40);
        typeLabel.setBounds(150, 550, 100, 40);
        
        taikhoan_TextField.setBounds(250, 450, 200, 40);
        matkhau_TextField.setBounds(250, 500, 200, 40);
        id_TextField.setBounds(250, 400, 200, 40);
        type_TextField.setBounds(250, 550, 200, 40);
        
        suataikhoanButton = new JButton("Sửa");
        suataikhoanButton.setActionCommand("suataikhoan");
        suataikhoanButton.addActionListener(this);
        suataikhoanButton.setBounds(650, 450, 200, 40);
        themtaikhoanButton = new JButton("Thêm");
        themtaikhoanButton.setActionCommand("themtaikhoan");
        themtaikhoanButton.addActionListener(this);
        themtaikhoanButton.setBounds(650, 400, 200, 40);
        xoataikhoanButton = new JButton("Xoá");
        xoataikhoanButton.setActionCommand("xoataikhoan");
        xoataikhoanButton.addActionListener(this);
        xoataikhoanButton.setBounds(650, 500, 200, 40);
        QLTKExelButton = new JButton("Xuất Exel");
        QLTKExelButton.setActionCommand("QLTKExcel");
        QLTKExelButton.addActionListener(this);
        QLTKExelButton.setBackground(Color.green);
        QLTKExelButton.setBounds(650, 550, 200, 40);
        
        ptaikhoan.add(suataikhoanButton);
        ptaikhoan.add(themtaikhoanButton);
        ptaikhoan.add(QLTKExelButton);
        ptaikhoan.add(xoataikhoanButton);
        ptaikhoan.add(taikhoan_TextField);
        ptaikhoan.add(matkhau_TextField);
        ptaikhoan.add(type_TextField);
        ptaikhoan.add(id_TextField);
        ptaikhoan.add(taikhoanLabel);
        ptaikhoan.add(matkhauLabel);
        ptaikhoan.add(idLabel);
        ptaikhoan.add(typeLabel);
        
        ptaikhoan.setLayout(null);
        ptaikhoan.setBounds(0,0,1080,660);
        accountTabel = new JTable(null, headerTK);
        headerTK = new Vector();
        dataTK = new Vector();
        headerTK.add("ID");
        headerTK.add("Tài khoản");
        headerTK.add("Mật khẩu");
        headerTK.add("Phân quyền");
        modelTaiKhoan = new DefaultTableModel(headerTK, 0);
        accountTabel.setFont(new Font("Arial", 0, 15));
        accountTabel.setModel(modelTaiKhoan);//add model len table
        accountTabel.getTableHeader().setFont(new Font("Arial", BOLD, 18)); //set font cho vector header
        accountTabel.getTableHeader().setForeground(Color.black); //set màu chữ cho header
        accountTabel.getTableHeader().setPreferredSize(new Dimension(30, 40));//set độ dài độ rộng của header
        accountTabel.getTableHeader().setBackground(Color.RED);//set background cho header
        
        
        scrollPanel = new JScrollPane(accountTabel);
        scrollPanel.setBounds(150, 75, 710, 300);
        // tblQLS.getTableHeader().setBorder(BorderFactory.createLineBorder(null, 0, true));
        accountTabel.setPreferredSize(new Dimension(500,500));
        accountTabel.setRowHeight(40);
        accountTabel.setGridColor(Color.GREEN);
       // scrollPanel.setPreferredSize(new Dimension(500,500));
//        scrollPanel.setViewportView(productTable);
        ptaikhoan.add(scrollPanel); // add table vào scrollPanel
        accountTabel.setFillsViewportHeight(true);//hiển thị table     
        accountTabel.setShowGrid(true);
        accountTabel.setDefaultEditor(Object.class, null); 
        //---------------------- canh chinh do dai cot header
        accountTabel.getColumnModel().getColumn(0).setPreferredWidth(80); 
        accountTabel.getColumnModel().getColumn(1).setPreferredWidth(200); 
        accountTabel.getColumnModel().getColumn(2).setPreferredWidth(70);
       
                try {
                    login_bus.docdangnhap();
                    
                    for (LoginDTO login : LoginBUS.Arr_login){
                        Vector temp = new Vector();
                            temp.add(login.getId());
                            temp.add(login.getUsername());
                            temp.add(login.getPassword());
                            temp.add(login.getType());
                            dataTK = temp;
                            modelTaiKhoan.addRow(dataTK);
                        
                    }
                    accountTabel.setModel(modelTaiKhoan);
                } catch (Exception ae){
                }
                
                
        accountTabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked (MouseEvent e){
                int j = nhanvienTable.getSelectedRow();
                if (j>=0){
                    id_TextField.setText((String)accountTabel.getModel().getValueAt(j, 0));
                    taikhoan_TextField.setText((String)accountTabel.getModel().getValueAt(j, 1));
                    matkhau_TextField.setText((String)accountTabel.getModel().getValueAt(j, 2));
                    type_TextField.setText((String)accountTabel.getModel().getValueAt(j, 3).toString());
                }
            }
        });        
        
        ptaikhoan.setVisible(true);
        return ptaikhoan;
    }
    
    public JPanel CreatePanel_ThongKe(){
        JPanel psanpham = new JPanel();
        psanpham.setLayout(null);
        psanpham.setBounds(0, 0, 1080, 660);
        JLabel lb = new JLabel(new ImageIcon("./src/Image/baotri.PNG"));
        lb.setBounds(-100, 20, 1199, 601);
        psanpham.add(lb);
        return psanpham;
    }
    
    public JPanel CreatePanel_QuanLiHoaDon(){
        JPanel psanpham = new JPanel();
        psanpham.setLayout(null);
        psanpham.setBounds(0, 0, 1080, 660);
        JLabel lb = new JLabel(new ImageIcon("./src/Image/baotri.PNG"));
        lb.setBounds(-100, 20, 1199, 601);
        psanpham.add(lb);
        return psanpham;
    }
    
    public JPanel CreatePanel_QuanLiKhachHang(){
        JPanel psanpham = new JPanel();
        psanpham.setLayout(null);
        psanpham.setBounds(0, 0, 1080, 660);
        JLabel lb = new JLabel(new ImageIcon("./src/Image/baotri.PNG"));
        lb.setBounds(-100, 20, 1199, 601);
        psanpham.add(lb);
        return psanpham;
    }
    
    public JPanel CreatePanel_TimKiemTheoGia(){
        JPanel ptimkiem = new JPanel();
        ptimkiem.setLayout(null);
//        ptimkiem.setBackground(Color.white);
        ptimkiem.setBounds(10, 10, 150, 60);
        
        Border menuTitleBoder = BorderFactory.createTitledBorder("Khoảng Giá");
        ptimkiem.setBorder(menuTitleBoder);
        
        Checkbox x = new Checkbox();
        giaTriDauTXT = new JTextField();
        giaTriCuoiTXT = new JTextField();
        
        giaTriDauTXT.setBounds(10, 20, 60, 25);
        giaTriCuoiTXT.setBounds(80, 20, 60, 25);     
        //ptimkiem.add(khoangGia);
        ptimkiem.add(giaTriCuoiTXT);
        ptimkiem.add(giaTriDauTXT);
        return ptimkiem;
    }
    
    public JPanel CreatePanel_TimKiemLoai(){
        JPanel ptimkiem = new JPanel();
  //      Vector VTCombobox = SanPhamBUS.timkiemTheoLoai();
        ptimkiem.setLayout(null);
//      ptimkiem.setBackground(Color.white);
        ptimkiem.setBounds(170, 10, 150, 60);
        
        Border menuTitleBoder = BorderFactory.createTitledBorder("Theo Loại Sản Phẩm");
        ptimkiem.setBorder(menuTitleBoder);
   
        loaiSanPhamCBB = new JComboBox(SanPhamBUS.CreateComboBOX());
        loaiSanPhamCBB.setBounds(5, 20, 140, 30);
        ptimkiem.add(loaiSanPhamCBB);
        loaiSanPhamCBB.setActionCommand("combobox");
        loaiSanPhamCBB.addActionListener(this);
        
        return ptimkiem;
    }
    
    private void Add_header(String a,String b,String c,String d) {
        Vector header = new Vector();
        header.add(a);
        header.add(b);
        header.add(c);
        header.add(d);
        if (model.getRowCount() == 0) {
            model = new DefaultTableModel(header, 0);
        }
    }
    
    private void Add_header(String a,String b,String c,String d,String e) {
        Vector header = new Vector();
        header.add(a);
        header.add(b);
        header.add(c);
        header.add(d);
        header.add(e);
        if (modelGioHang.getRowCount() == 0) {
            modelGioHang = new DefaultTableModel(header, 0);
        }
    }

    private void Add_row_SanPham(ProductsDTO products) {
        Vector row = new Vector();
        row.add(products.getID_Product());
        row.add(products.getName());
        row.add(products.getPice());
        row.add(products.getCategory());     
        model.addRow(row);
//      productTable.setModel(model);
    }  
    public static void Add_row_GioHang(ProductsDTO products) {
        Vector row = new Vector();
        row.add(products.getID_Product());
        row.add(products.getName());
        row.add(products.getAmount());
        row.add(products.getPice());  
        modelGioHang.addRow(row);
//      productTable.setModel(model);
    }
    
    public JPanel CreateJPanel_TaiKhoan (){
        JPanel taiKhoanPanel = new JPanel();
        AccountBUS bus = new AccountBUS();
        taiKhoanPanel.setLayout(null);
        taiKhoanPanel.setBackground(Color.pink);
        taiKhoanPanel.setBounds(0, 0, 1080, 660);
        
        menuLabel = new JLabel[4];
        menuTextField = new JTextField[4];
        String[] arrtaikhoan = {"Họ và tên:","Ngày sinh:", "Giới tính:", "Số điện thoại:",} ;
        JLabel header_taikhoan = new JLabel("Thông tin cá nhân");
        header_taikhoan.setBounds(400, 30, 300, 50);
        header_taikhoan.setFont(new Font("Arial",Font.PLAIN,28));
        taiKhoanPanel.add(header_taikhoan);
        int toaDoXMenuButton=500,toaDoYMenuButton=100;
        for(int i=0;i<arrtaikhoan.length; i++)
        {
            menuLabel[i] = new JLabel(arrtaikhoan[i]);
            menuLabel[i].setBounds(toaDoXMenuButton, toaDoYMenuButton, 190, 50);
            menuLabel[i].setFont(new Font("Arial", Font.PLAIN, 20));
            menuLabel[i].setBackground(Color.GREEN);
            taiKhoanPanel.add(menuLabel[i]);
            toaDoYMenuButton += 80;
        }
        int toaDoXMenuText=650,toaDoYMenuText=100;
        for(int i=0;i<arrtaikhoan.length; i++){
            menuTextField[i] = new JTextField();
            menuTextField[i].setBounds(toaDoXMenuText, toaDoYMenuText, 190, 40);
            menuTextField[i].setFont(new Font("Arial", Font.PLAIN, 20));
//            menuTextField[i].setEditable(false);          
            taiKhoanPanel.add(menuTextField[i]);
            toaDoYMenuText += 80;      
        }
        
            if (AccountBUS.Arr_account.size() == 0){
                try {
                    bus.docaccount();
                    for (AccountDTO account : AccountBUS.Arr_account){
                        if (Login.idhienhanh.equals(account.getId_nhanvien())){
                            menuTextField[0].setText(account.hoten);
                            menuTextField[1].setText(account.ngaysinh);
                            menuTextField[2].setText(account.gioitinh);
                            menuTextField[3].setText(account.sodienthoai);
                            break;
                        }
                        
                    }
                } catch (Exception ae){
                }
            }
            else{
                try {
                    for (AccountDTO account : AccountBUS.Arr_account){
                        if (Login.idhienhanh.equals(account.getId_nhanvien())){
                        menuTextField[0].setText(account.hoten);
                        menuTextField[1].setText(account.ngaysinh);
                        menuTextField[2].setText(account.gioitinh);
                        menuTextField[3].setText(account.sodienthoai);
                        break;
                        }
                        
                    }
                }catch(Exception ex){
                }
            }
        JButton confirm_taikhoan = new JButton("Cập nhật");
        confirm_taikhoan.setBounds(600, 450, 200, 40);
        confirm_taikhoan.setFont(new Font("Arial", Font.PLAIN, 16));
        confirm_taikhoan.setBackground(Color.GREEN);
        confirm_taikhoan.setActionCommand("confirm");
        confirm_taikhoan.addActionListener(this);
        JButton change_taikhoan = new JButton("Đổi mật khẩu");
        change_taikhoan.setBounds(300, 450, 150, 40);
        change_taikhoan.setFont(new Font("Arial", Font.PLAIN, 16));
        change_taikhoan.setBackground(Color.GREEN);
        change_taikhoan.setActionCommand("changepass");
        change_taikhoan.addActionListener(this);
        
        taiKhoanPanel.add(confirm_taikhoan);
        taiKhoanPanel.add(change_taikhoan);
        taiKhoanPanel.updateUI();
        return taiKhoanPanel;
    }
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override   
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
        case KeyEvent.VK_ENTER :{
            if(e.getSource() == find)
               System.out.println("A and Ctrl are pressed.");
            else
                System.out.println("Only A is pressed");
            break;
        }
        case KeyEvent.VK_F5 :{
            seachtxt.setText("");
                model.setRowCount(0);
                for (int i =0; i < SanPhamBUS.Arr_products.size();i++) {
                    Add_row_SanPham(SanPhamBUS.Arr_products.get(i));
                }
            break;
        }
    }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //Product Table
        iconPanel.removeAll();
        JButton tongTienButton = new JButton("Tạm Tính");
        tongTienTXT = new JTextField();
        
        tongTienTXT.setBounds(730, 435, 340, 40);
        tongTienButton.setBounds(730, 400, 340, 30);
        tongTienButton.setFont(new Font("Arial", BOLD, 16));
        tongTienTXT.setFont(new Font("Arial", BOLD, 15));
        sanPhamPanel.add(tongTienButton);
        sanPhamPanel.add(tongTienTXT);
        int  i=productTable.getSelectedRow();
        if(i>=0)
        {   
            iconPanel.setLayout(null);
            iconPanel.setBackground(Color.PINK);
            iconPanel.setBounds(730, 0, 350, 650);
            Products = SanPhamBUS.Arr_products.get(i);
            iconPizza = CreateJLable_Icon(0, -10, 350, 300, Products.img_path);
            iconPanel.add(iconPizza);
            sanPhamPanel.add(iconPanel);
            sanPhamPanel.updateUI();
        }
                //event
        if (Login.type == 1) {
            try {
                SanPhamBUS.docSanPham();
            } catch (Exception ex) {
                Logger.getLogger(HomeUser.class.getName()).log(Level.SEVERE, null, ex);
            }
            int j = productTable.getSelectedRow();
            if (j>=0){
                menuTextFieldADMIN[0].setText((String)productTable.getModel().getValueAt(j, 0));
                menuTextFieldADMIN[1].setText((String)productTable.getModel().getValueAt(j, 1));
                menuTextFieldADMIN[2].setText((String)productTable.getModel().getValueAt(j, 2).toString());
                menuTextFieldADMIN[3].setText((String)productTable.getModel().getValueAt(j, 3));
                menuTextFieldADMIN[4].setText(String.valueOf(SanPhamBUS.Arr_products.get(j).getTrangThai()));
                menuTextFieldADMIN[5].setText(String.valueOf(SanPhamBUS.Arr_products.get(j).getImg_path()));
            }
        }
            
        //-----------------------------------------
        
    }
            
    @Override
    public void mousePressed(MouseEvent e){
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
           //-------NhanVIen
            //panel trang chu
            if("buttonStaff_0".equals(e.getActionCommand()))
            {    
                contentPanel.removeAll();
                JPanel trangChuPanel = CreateJPanel_TrangChu();
                contentPanel.add(trangChuPanel);
                contentPanel.setLayout(null);
                contentPanel.updateUI();
            }
            //panel san pham
            else if("buttonStaff_1".equals(e.getActionCommand()))
            {   
                contentPanel.removeAll();
                sanPhamPanel =CreateJPanel_SanphamTable(); 
                contentPanel.add(sanPhamPanel);
                contentPanel.setLayout(null);
                contentPanel.updateUI();     
                //trangChuPanel.setVisible(false);  
               
            }
            //panel tai khoan
           else if("buttonStaff_2".equals(e.getActionCommand()))
            {   
                contentPanel.removeAll();
                JPanel taiKhoanPanel =CreateJPanel_TaiKhoan(); 
                contentPanel.add(taiKhoanPanel);
                contentPanel.setLayout(null);
                contentPanel.updateUI();
            }
            else if("buttonStaff_3".equals(e.getActionCommand()))
            {
                dispose();
                new Login();
            }
            else if("buttonStaff_4".equals(e.getActionCommand()))
            {   
                    JOptionPane.showMessageDialog(this, "Cảm ơn đã sử dụng Phần Mềm");
                    System.exit(0);
               
            }
            
            //-----------admin
            if("buttonAdmin_0".equals(e.getActionCommand()))
            {    
                contentPanel.removeAll();
                sanPhamPanel =CreateJPanel_SanphamTable(); 
                contentPanel.add(sanPhamPanel);
                contentPanel.setLayout(null);
                contentPanel.updateUI(); 
                
            }
            if("buttonAdmin_1".equals(e.getActionCommand()))
            {    
  
                contentPanel.removeAll();
                JPanel QLNV_Panel =CreatePanel_QuanLiNhanVien();
                contentPanel.add(QLNV_Panel);
                contentPanel.setLayout(null);
                contentPanel.updateUI();  
            }
            if("buttonAdmin_2".equals(e.getActionCommand()))
            {    
                contentPanel.removeAll();
                JPanel QLTK_Panel =CreatePanel_QuanLiTaiKhoan();
                contentPanel.add(QLTK_Panel);
                contentPanel.setLayout(null);
                contentPanel.updateUI();      
            }
            if("buttonAdmin_3".equals(e.getActionCommand()))
            {    
                contentPanel.removeAll();
                JPanel sanPhamPanel =CreatePanel_QuanLiKhachHang();
                contentPanel.add(sanPhamPanel);
                contentPanel.setLayout(null);
                contentPanel.updateUI();     
            }
            if("buttonAdmin_4".equals(e.getActionCommand()))
            {    
                contentPanel.removeAll();
                JPanel sanPhamPanel =CreatePanel_QuanLiHoaDon();
                contentPanel.add(sanPhamPanel);
                contentPanel.setLayout(null);
                contentPanel.updateUI();      
            }
            if("buttonAdmin_5".equals(e.getActionCommand()))
            {    
                contentPanel.removeAll();
                JPanel sanPhamPanel =CreatePanel_ThongKe();
                contentPanel.add(sanPhamPanel);
                contentPanel.setLayout(null);
                contentPanel.updateUI();       
            }
            if("buttonAdmin_6".equals(e.getActionCommand()))
            {    
                System.exit(0);  
            }
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
            if("add".equals(e.getActionCommand()))
            {
                ArrayList<ProductsDTO> tempArr = new ArrayList<>();
                int  i=productTable.getSelectedRow();
                if(i>=0)
                {   
                    ProductsDTO product = new ProductsDTO(); 
                    product = SanPhamBUS.Arr_products.get(i);
                    product.setAmount(Integer.valueOf(countTXT.getText()));
                    if (!Arr_GioHang.contains(product)){
                        Arr_GioHang.add(product);
                        Add_row_GioHang(Arr_GioHang.get(check));                     
                        Tong = Tong + Arr_GioHang.get(check).getAmount()*Arr_GioHang.get(check).getPice();
                        check++;  
                    }else{
                        if (JOptionPane.showConfirmDialog(null, "Sản Phẩm đã tồn tại, Bạn có muốn Thay Đổi", "Thông báo",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                            JOptionPane.showMessageDialog(this, "Thành công");
                        }
                    }
                } 
                tongTienTXT.requestFocus();
                tongTienTXT.setText(String.valueOf(Tong));
                tongTienTXT.setEditable(false);
                count = 1;
                countTXT.setText(String.valueOf(count));
                System.out.println(Tong);
            }            
            if("find".equals(e.getActionCommand()))
            {
                String temp = seachtxt.getText();
                model.setRowCount(0);
                for (int i =0; i < SanPhamBUS.timkiemALL(temp).size();i++) {
                    Add_row_SanPham(SanPhamBUS.timkiemALL(temp).get(i));
                }
            }
            if("refresh".equals(e.getActionCommand()))
            {
                seachtxt.setText("");
                model.setRowCount(0);
                modelGioHang.setRowCount(0);
                for (int i =0; i < SanPhamBUS.Arr_products.size();i++) {
                    if(SanPhamBUS.Arr_products.get(i).getTrangThai() == 1)
                    Add_row_SanPham(SanPhamBUS.Arr_products.get(i));
                }
                Arr_GioHang.removeAll(Arr_GioHang);
                
            }
            if("print".equals(e.getActionCommand()))
            { 
                try {
                    new HoaDonGUI();
                } catch (Exception ex) {
                    Logger.getLogger(HomeUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if("combobox".equals(e.getActionCommand())){
                Vector temp = SanPhamBUS.CreateComboBOX();
                model.setRowCount(0); 
                    if(loaiSanPhamCBB.getSelectedItem().toString() == temp.get(0)){
                        Vector tempLoai = new Vector(SanPhamBUS.timKiemTheoLoai(temp.get(0).toString()));
                        for (int i =0; i < tempLoai.size();i++) {
                            Add_row_SanPham((ProductsDTO) tempLoai.get(i));
                        }
                    }
                    if(loaiSanPhamCBB.getSelectedItem().toString() == temp.get(1)){
                        Vector tempLoai = new Vector(SanPhamBUS.timKiemTheoLoai(temp.get(1).toString()));
                        for (int i =0; i < tempLoai.size();i++) {
                            Add_row_SanPham((ProductsDTO) tempLoai.get(i));
                        }
                    }
                    if(loaiSanPhamCBB.getSelectedItem().toString() == temp.get(2)){
                        Vector tempLoai = new Vector(SanPhamBUS.timKiemTheoLoai(temp.get(2).toString()));
                        for (int i =0; i < tempLoai.size();i++) {
                            Add_row_SanPham((ProductsDTO) tempLoai.get(i));
                        }
                    }
                    
            }
            if("exe".equals(e.getActionCommand()))
            {
                try {
                    String gtBDtemp = giaTriDauTXT.getText();
                    String gtKTtemp = giaTriCuoiTXT.getText();
                    double gtBD = Double.parseDouble(gtBDtemp);
                    double gtKT = Double.parseDouble(gtKTtemp);
                    if(gtBD >= gtKT){
                        JOptionPane.showMessageDialog(this, "Giá trị nhập không hợp lệ! Vui lòng thử lại!");
                        giaTriDauTXT.setText("");
                        giaTriCuoiTXT.setText("");
                    }
                    else{
                        SanPhamBUS.timkiemTheoGia(Double.valueOf(gtBD), Double.valueOf(gtKT));
                        model.setRowCount(0);
                        for (int i =0; i < SanPhamBUS.timkiemTheoGia(Double.valueOf(gtBD), Double.valueOf(gtKT)).size();i++) {
                            Add_row_SanPham(SanPhamBUS.timkiemTheoGia(Double.valueOf(gtBD), Double.valueOf(gtKT)).get(i));
                        }
                    }
                } catch (Exception ev) {
                    JOptionPane.showMessageDialog(this, "Giá trị nhập không hợp lệ! Vui lòng thử lại!");
                    giaTriDauTXT.setText("");
                        giaTriCuoiTXT.setText("");
                }               
               
               
            }
            if("+".equals(e.getActionCommand()))
            {
                count++;
                countTXT.setText(String.valueOf(count));
                if(count == 10){
                    int input = JOptionPane.showConfirmDialog(this, "MAY BOM HANG A????");
                    if(input == 0){
                        JOptionPane.showMessageDialog(rootPane, "CUTTTTTTTTTT");
                }
                    if(input == 1){
                        JOptionPane.showMessageDialog(rootPane, "À ừ thì xin lỗi");
                    }
                }
                SanPhamBUS BUS = new SanPhamBUS();
                try {
                    BUS.docSanPham();           
                } catch (Exception ex) {
                    Logger.getLogger(HomeUser.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
            if("-".equals(e.getActionCommand()))
            {
                if(count>1){
                    count--;
                    countTXT.setText(String.valueOf(count));
                }          
            }
            if ("confirm".equals(e.getActionCommand())){
                AccountDAO update = new AccountDAO();
//                account.hoten = this.menuTextField[0].getText();
//                account.ngaysinh = this.menuTextField[1].getText();
//                account.gioitinh = this.menuTextField[2].getText();
//                account.sodienthoai = this.menuTextField[3].getText();
                String hoten = this.menuTextField[0].getText();
                String ngaysinh = this.menuTextField[1].getText();
                String gioitinh = this.menuTextField[2].getText();
                String sodienthoai = this.menuTextField[3].getText();
                String id = Login.idhienhanh;
                int trangthai=1;
                update.updateThongtin(hoten, ngaysinh, gioitinh, sodienthoai, id,trangthai);
            }
            if ("suataikhoan".equals(e.getActionCommand())){
                LoginDAO update = new LoginDAO();
                String taikhoan = this.taikhoan_TextField.getText();
                String matkhau = this.matkhau_TextField.getText();
                String id = this.id_TextField.getText();
                update.updateTaikhoan(taikhoan,matkhau,id);
                JOptionPane.showMessageDialog(this,"Sửa thông tin thành công!");
                taikhoan_TextField.setText(null);
                matkhau_TextField.setText(null);
                id_TextField.setText(null);
                type_TextField.setText(null);
                modelTaiKhoan.setRowCount(0);
                
                try {
                    login_bus.docdangnhap();
                    
                    for (LoginDTO login : LoginBUS.Arr_login){
                        Vector temp = new Vector();
                            temp.add(login.getId());
                            temp.add(login.getUsername());
                            temp.add(login.getPassword());
                            temp.add(login.getType());
                            dataTK = temp;
                            modelTaiKhoan.addRow(dataTK);
                            System.out.println(dataTK);
                        
                    }
                    accountTabel.setModel(modelTaiKhoan);
                } catch (Exception ae){
                }
                   
            }
            if ("themtaikhoan".equals(e.getActionCommand())){
                LoginDAO update = new LoginDAO();
                String taikhoan = this.taikhoan_TextField.getText();
                String matkhau = this.matkhau_TextField.getText();
                String id = this.id_TextField.getText();
                String type = this.type_TextField.getText();
                update.addTaikhoan(taikhoan,matkhau,id,type);
                JOptionPane.showMessageDialog(this,"Thêm thông tin thành công!");
                taikhoan_TextField.setText(null);
                matkhau_TextField.setText(null);
                id_TextField.setText(null);
                type_TextField.setText(null);
                modelTaiKhoan.setRowCount(0);
                try {
                    login_bus.docdangnhap();
                    
                    for (LoginDTO login : LoginBUS.Arr_login){
                        Vector temp = new Vector();
                            temp.add(login.getId());
                            temp.add(login.getUsername());
                            temp.add(login.getPassword());
                            temp.add(login.getType());
                            dataTK = temp;
                            modelTaiKhoan.addRow(dataTK);
                            System.out.println(dataTK);
                        
                    }
                    accountTabel.setModel(modelTaiKhoan);
                } catch (Exception ae){
                }
                   
            }
            if ("xoataikhoan".equals(e.getActionCommand())){
                LoginDAO update = new LoginDAO();
                String taikhoan = this.taikhoan_TextField.getText();
                String matkhau = this.matkhau_TextField.getText();
                String id = this.id_TextField.getText();
                String type = this.type_TextField.getText();
                update.deleteTaikhoan(taikhoan,matkhau,id,type);
                JOptionPane.showMessageDialog(this,"Xoá thông tin thành công!");
                taikhoan_TextField.setText(null);
                matkhau_TextField.setText(null);
                id_TextField.setText(null);
                type_TextField.setText(null);
                modelTaiKhoan.setRowCount(0);
                
                try {
                    login_bus.docdangnhap();
                    
                    for (LoginDTO login : LoginBUS.Arr_login){
                        Vector temp = new Vector();
                            temp.add(login.getId());
                            temp.add(login.getUsername());
                            temp.add(login.getPassword());
                            temp.add(login.getType());
                            dataTK = temp;
                            modelTaiKhoan.addRow(dataTK);
                            System.out.println(dataTK);
                        
                    }
                    accountTabel.setModel(modelTaiKhoan);
                } catch (Exception ae){
                }
                   
            }
            if ("suanhanvien".equals(e.getActionCommand())){
                AccountDAO update = new AccountDAO();
                String hoten = this.hoten_TextField.getText();
                String ngaysinh = this.ngaysinh_TextField.getText();
                String gioitinh = this.gioitinh_TextField.getText();
                String diachi = this.diachi_TextField.getText();
                String sdt = this.sdt_TextField.getText();
                String id = this.idnhanvien_TextField.getText();
                int trangthai = 1;
                update.updateNhanvien(hoten,ngaysinh,gioitinh,diachi,sdt,id,trangthai);
                JOptionPane.showMessageDialog(this,"Sửa thông tin thành công!");
                hoten_TextField.setText(null);
                ngaysinh_TextField.setText(null);
                idnhanvien_TextField.setText(null);
                gioitinh_TextField.setText(null);
                diachi_TextField.setText(null);
                sdt_TextField.setText(null);
                modelNhanvien.setRowCount(0); 
                try {
                    account_bus.docaccount();
                    for (AccountDTO acc : AccountBUS.Arr_account){
                        Vector tempp = new Vector();
                            tempp.add(acc.getId_nhanvien());
                            tempp.add(acc.getHoten());
                            tempp.add(acc.getNgaysinh());
                            tempp.add(acc.getGioitinh());
                            tempp.add(acc.getDiachi());
                            tempp.add(acc.getSodienthoai());
                            dataNV = tempp;
                            modelNhanvien.addRow(dataNV);
                        
                    }
                    nhanvienTable.setModel(modelNhanvien);
                } catch (Exception ae){
                }
            }
            if ("xoanhanvien".equals(e.getActionCommand())){
                AccountDAO update = new AccountDAO();
                String hoten = this.hoten_TextField.getText();
                String ngaysinh = this.ngaysinh_TextField.getText();
                String gioitinh = this.gioitinh_TextField.getText();
                String diachi = this.diachi_TextField.getText();
                String sdt = this.sdt_TextField.getText();
                String id = this.idnhanvien_TextField.getText();
                int trangthai = 1;
                update.deleteNhanvien(hoten,ngaysinh,gioitinh,diachi,sdt,id,trangthai);
                JOptionPane.showMessageDialog(this,"Sửa thông tin thành công!");
                hoten_TextField.setText(null);
                ngaysinh_TextField.setText(null);
                idnhanvien_TextField.setText(null);
                gioitinh_TextField.setText(null);
                diachi_TextField.setText(null);
                sdt_TextField.setText(null);
                modelNhanvien.setRowCount(0); 
                try {
                    account_bus.docaccount();
                    for (AccountDTO acc : AccountBUS.Arr_account){
                        Vector tempp = new Vector();
                            tempp.add(acc.getId_nhanvien());
                            tempp.add(acc.getHoten());
                            tempp.add(acc.getNgaysinh());
                            tempp.add(acc.getGioitinh());
                            tempp.add(acc.getDiachi());
                            tempp.add(acc.getSodienthoai());
                            dataNV = tempp;
                            modelNhanvien.addRow(dataNV);
                        
                    }
                    nhanvienTable.setModel(modelNhanvien);
                } catch (Exception ae){
                }
            }
            if ("themnhanvien".equals(e.getActionCommand())){
                AccountDAO update = new AccountDAO();
                String hoten = this.hoten_TextField.getText();
                String ngaysinh = this.ngaysinh_TextField.getText();
                String gioitinh = this.gioitinh_TextField.getText();
                String diachi = this.diachi_TextField.getText();
                String sdt = this.sdt_TextField.getText();
                String id = this.idnhanvien_TextField.getText();
                int trangthai = 1;
                update.addNhanvien(hoten,ngaysinh,gioitinh,diachi,sdt,id,trangthai);
                JOptionPane.showMessageDialog(this,"Sửa thông tin thành công!");
                hoten_TextField.setText(null);
                ngaysinh_TextField.setText(null);
                idnhanvien_TextField.setText(null);
                gioitinh_TextField.setText(null);
                diachi_TextField.setText(null);
                sdt_TextField.setText(null);
                modelNhanvien.setRowCount(0); 
                try {
                    account_bus.docaccount();
                    for (AccountDTO acc : AccountBUS.Arr_account){
                        Vector tempp = new Vector();
                            tempp.add(acc.getId_nhanvien());
                            tempp.add(acc.getHoten());
                            tempp.add(acc.getNgaysinh());
                            tempp.add(acc.getGioitinh());
                            tempp.add(acc.getDiachi());
                            tempp.add(acc.getSodienthoai());
                            dataNV = tempp;
                            modelNhanvien.addRow(dataNV);
                    }
                    nhanvienTable.setModel(modelNhanvien);
                } catch (Exception ae){
                }
                
            }
            if ("QLNVExcel".equals(e.getActionCommand())){
                    ExportExcel abc = new ExportExcel();
                    String input = JOptionPane.showInputDialog("Đặt tên cho Table");
                    abc.ExportExcel(input, nhanvienTable);
            }
            if ("QLTKExcel".equals(e.getActionCommand())){
                    ExportExcel abc = new ExportExcel();
                    String input = JOptionPane.showInputDialog("Đặt tên cho Table");
                    abc.ExportExcel(input, accountTabel);
            }
            if ("addADMIN".equals(e.getActionCommand())) {
                ProductsDTO products =new ProductsDTO();
                String temp = menuTextFieldADMIN[0].getText();
                String duongDan = "/Image/";
                    if(SanPhamBUS.kt_trung_ma(menuTextFieldADMIN[0].getText(), SanPhamBUS.Arr_products)||menuTextFieldADMIN[0].getText().equals("")){
                       JOptionPane.showMessageDialog(this, "Mã sản phẩm không hợp lệ! Vui lòng nhập lại");
                       menuTextFieldADMIN[0].setText("");
                    }else{
                        products.setID_Product(menuTextFieldADMIN[0].getText());
                        products.setName(menuTextFieldADMIN[1].getText());
                        products.setPice(Double.valueOf(menuTextFieldADMIN[2].getText()));
                        products.setCategory(menuTextFieldADMIN[3].getText());
                        products.setTrangThai(Integer.valueOf(menuTextFieldADMIN[4].getText()));
                        products.setImg_path(duongDan+menuTextFieldADMIN[5].getText());
                        products.setAmount(0);
                        try {
                            SanPhamBUS.themSanPham(products);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        for(int j = 0 ; j < menuTextFieldADMIN.length ; j ++){
                            menuTextFieldADMIN[j].setText("");
                        }
                        model.setRowCount(0);
                            for (ProductsDTO product : SanPhamBUS.Arr_products){
                               if(product.getTrangThai() == 1)
                                    Add_row_SanPham(product);
                            }
                            productTable.setModel(model);
                    }           

        }
        if ("xoaADMIN".equals(e.getActionCommand())) {
            ProductsDTO products =new ProductsDTO();
                Common.trangThai = products;
                int  i=productTable.getSelectedRow();
                String duongDan = "/Image/";
                products.setID_Product(menuTextFieldADMIN[0].getText());
                products.setName(menuTextFieldADMIN[1].getText());
                products.setPice(Double.valueOf(menuTextFieldADMIN[2].getText()));
                products.setCategory(menuTextFieldADMIN[3].getText());
                products.setImg_path(duongDan+menuTextFieldADMIN[4].getText());
                products.setTrangThai(0);
                products.setAmount(0);
                if (JOptionPane.showConfirmDialog(null, "Xoá sản Phẩm?", "Tiếp Tục",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {      
                    try {
                         SanPhamBUS.suaSanPham(i,products);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
                for(int j = 0 ; j < menuTextFieldADMIN.length ; j++){
                    menuTextFieldADMIN[j].setText("");
                }
                model.setRowCount(0);
                try {
                    SanPhamBUS.docSanPham();
                    
                    for (ProductsDTO product : SanPhamBUS.Arr_products){
                        if(product.getTrangThai() == 1)
                            Add_row_SanPham(product);
                    }
                    productTable.setModel(model);
                } catch (Exception ae){
                }
        }
        if ("suaADMIN".equals(e.getActionCommand())) {
                ProductsDTO products =new ProductsDTO();
                int  i=productTable.getSelectedRow();
                String duongDan = "/Image/";
                products.setID_Product(menuTextFieldADMIN[0].getText());
                products.setName(menuTextFieldADMIN[1].getText());
                products.setPice(Double.valueOf(menuTextFieldADMIN[2].getText()));
                products.setCategory(menuTextFieldADMIN[3].getText());
                products.setTrangThai(Integer.valueOf(menuTextFieldADMIN[4].getText()));
                products.setImg_path(duongDan+menuTextFieldADMIN[5].getText());
                products.setAmount(0);
                
                SanPhamBUS.suaSanPham(i,products);
                model.setRowCount(0);
                for(int j = 0 ; j < menuTextFieldADMIN.length ; j++){
                    menuTextFieldADMIN[j].setText("");
                }
                try {
                    SanPhamBUS.docSanPham();
                    for (ProductsDTO product : SanPhamBUS.Arr_products){
                        if(product.getTrangThai() == 1)
                            Add_row_SanPham(product);
                    }
                    productTable.setModel(model);
                } catch (Exception ae){
                }
        }
        if ("xuatEXCEL".equals(e.getActionCommand())) {
            ExportExcel ab = new ExportExcel();
           String input = JOptionPane.showInputDialog("Chọn Tên bảng trong Excel:");
            ab.ExportExcel(input, productTable);
        }
        if ("report".equals(e.getActionCommand())) {
        }
    }

    @Override
    public Object[] getSelectedObjects() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addItemListener(ItemListener il) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeItemListener(ItemListener il) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    
}
