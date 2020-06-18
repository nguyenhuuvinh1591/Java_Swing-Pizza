-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 04, 2020 lúc 03:31 AM
-- Phiên bản máy phục vụ: 10.4.11-MariaDB
-- Phiên bản PHP: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `pizza`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietgiamgia`
--

CREATE TABLE `chitietgiamgia` (
  `ID_Giamgia` varchar(10) CHARACTER SET utf8 NOT NULL,
  `ID_Product` varchar(10) CHARACTER SET utf8 NOT NULL,
  `NoiDungGiamGia` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `ID_Hoadon` varchar(10) CHARACTER SET utf8 NOT NULL,
  `ID_Product` varchar(10) CHARACTER SET utf8 NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` double NOT NULL,
  `ThanhTien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chuongtrinhgiamgia`
--

CREATE TABLE `chuongtrinhgiamgia` (
  `ID_Giamgia` varchar(10) CHARACTER SET utf8 NOT NULL,
  `TenChuongTrinh` varchar(30) CHARACTER SET utf8 NOT NULL,
  `LoaiChuongTrinh` varchar(30) CHARACTER SET utf8 NOT NULL,
  `ThoiGiamBatDau` date NOT NULL,
  `ThoiGiamKetThuc` date NOT NULL,
  `NoiDungGiamGIa` varchar(100) CHARACTER SET utf8 NOT NULL,
  `DieuKienChiTieu` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dangnhap`
--

CREATE TABLE `dangnhap` (
  `username` varchar(30) CHARACTER SET utf8 NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 NOT NULL,
  `ID_Nhanvien` varchar(10) CHARACTER SET utf8 NOT NULL,
  `Type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `dangnhap`
--

INSERT INTO `dangnhap` (`username`, `password`, `ID_Nhanvien`, `Type`) VALUES
('nv1', 'nv1', 'NV1', 1),
('nv2', 'nv2', 'NV2', 1),
('nv3', 'nv3', 'NV3', 1),
('nv4', 'nv4', 'NV4', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `ID_Hoadon` varchar(10) CHARACTER SET utf8 NOT NULL,
  `ID_Nhanvien` varchar(10) CHARACTER SET utf8 NOT NULL,
  `ID_khachhang` varchar(10) CHARACTER SET utf32 NOT NULL,
  `ID_Giamgia` varchar(10) CHARACTER SET utf8 NOT NULL,
  `NgayLap` date NOT NULL,
  `ThanhTien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`ID_Hoadon`, `ID_Nhanvien`, `ID_khachhang`, `ID_Giamgia`, `NgayLap`, `ThanhTien`) VALUES
('HD1', 'NV1', 'KH1', 'GG1', '2020-05-22', 200),
('HD2', 'NV3', 'KH3', 'GG3', '2020-03-17', 238);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `ID_Khachhang` varchar(10) CHARACTER SET utf32 NOT NULL,
  `TenKhachHang` varchar(30) CHARACTER SET utf8 NOT NULL,
  `SDT` int(10) NOT NULL,
  `Gmail` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`ID_Khachhang`, `TenKhachHang`, `SDT`, `Gmail`) VALUES
('KH1', 'Huấn ROSE', 978448746, 'huanrose@gmail.com'),
('KH2', 'Lê Minh Hiếu', 153478956, 'hieuduongquyen@gmail.com'),
('KH3', 'Ngô Khá Bảnh Toản', 169856325, 'khabanh@gmail.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `ID_Loaisanpham` varchar(10) CHARACTER SET utf8 NOT NULL,
  `TenLoaiSanPham` varchar(30) CHARACTER SET utf8 NOT NULL,
  `DacTinh` varchar(100) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ncc`
--

CREATE TABLE `ncc` (
  `ID_NCC` varchar(10) NOT NULL,
  `Ten_NCC` varchar(30) NOT NULL,
  `DiaChi_NCC` varchar(50) NOT NULL,
  `SDT_NCC` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `ncc`
--

INSERT INTO `ncc` (`ID_NCC`, `Ten_NCC`, `DiaChi_NCC`, `SDT_NCC`) VALUES
('NCC1', 'Pizza Real', '27 Phạm Hùng,Quận 8 , TP.HCM', 658945698),
('NCC2', 'Pizza Thiệc', '58 Lê Hồng Phong, Quận 10, TP.HCM', 654789658);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `ID_NhanVien` varchar(10) NOT NULL,
  `TenNhanVien` varchar(30) CHARACTER SET utf8 NOT NULL,
  `NgaySinh` date NOT NULL,
  `GioiTinh` varchar(3) CHARACTER SET utf8 NOT NULL,
  `DiaChi` varchar(50) CHARACTER SET utf8 NOT NULL,
  `SDT` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`ID_NhanVien`, `TenNhanVien`, `NgaySinh`, `GioiTinh`, `DiaChi`, `SDT`) VALUES
('NV1', 'Phạm Nhật Minh', '0000-00-00', 'Nam', '0 biết', 252625959),
('NV2', 'Nguyễn Quốc Tuấn', '0000-00-00', 'Nam', 'Nhà 0 có số, Bình chánh', 113),
('NV3', 'Nguyễn Hữu Vinh', '0000-00-00', 'Nam', 'Hẻm 48, Bùi Thị Xuân, TP.HCM', 974086701),
('NV4', 'Đặng Hải Long', '2000-01-08', 'Nam', 'Nhà 0 có địa chỉ, Quận 10', 115);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhapkho`
--

CREATE TABLE `nhapkho` (
  `ID_NhapKho` varchar(10) CHARACTER SET utf8 NOT NULL,
  `ID_NhanVien` varchar(10) CHARACTER SET utf8 NOT NULL,
  `ID_NCC` varchar(10) CHARACTER SET utf8 NOT NULL,
  `NgayNhap` date NOT NULL,
  `TinhTrang` varchar(10) CHARACTER SET utf8 NOT NULL,
  `TongGiaTri` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `ID_Product` varchar(5) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Price` double NOT NULL,
  `amount` int(11) NOT NULL,
  `Category` varchar(30) CHARACTER SET utf32 NOT NULL,
  `img_path` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`ID_Product`, `Name`, `Price`, `amount`, `Category`, `img_path`) VALUES
('PZS4', 'Pizza Hải Sản Cao Cấp', 129, 0, 'Hải Sản', '/Image/PizzaSP_4.PNG'),
('PZS3', 'Pizza Hải Sản Cocktail', 129, 0, 'Hải Sản', '/Image/PizzaSP_3.PNG'),
('PZS2', 'Pizza Hải Sản Nhiệt Đới', 129, 0, 'Hải Sản', '/Image/PizzaSP_2.PNG'),
('PZS1', 'Pizza Hải Sản Pesto Xanh', 169, 0, 'Hải Sản', '/Image/PizzaSP_1.PNG'),
('PZR1', 'Pizza Rau Trộn xà lách bơ', 200, 0, 'Healthy', '');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietgiamgia`
--
ALTER TABLE `chitietgiamgia`
  ADD PRIMARY KEY (`ID_Giamgia`);

--
-- Chỉ mục cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`ID_Hoadon`);

--
-- Chỉ mục cho bảng `chuongtrinhgiamgia`
--
ALTER TABLE `chuongtrinhgiamgia`
  ADD PRIMARY KEY (`ID_Giamgia`);

--
-- Chỉ mục cho bảng `dangnhap`
--
ALTER TABLE `dangnhap`
  ADD PRIMARY KEY (`ID_Nhanvien`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`ID_Hoadon`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`ID_Khachhang`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`ID_Loaisanpham`);

--
-- Chỉ mục cho bảng `ncc`
--
ALTER TABLE `ncc`
  ADD PRIMARY KEY (`ID_NCC`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`ID_NhanVien`);

--
-- Chỉ mục cho bảng `nhapkho`
--
ALTER TABLE `nhapkho`
  ADD PRIMARY KEY (`ID_NhapKho`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`Name`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
