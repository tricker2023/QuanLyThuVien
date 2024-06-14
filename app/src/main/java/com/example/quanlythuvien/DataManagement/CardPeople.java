package com.example.quanlythuvien.DataManagement;

import com.example.quanlythuvien.SQLHepler.SQLManagement;
import com.example.quanlythuvien.SQLHepler.SQLManagement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class CardPeople {
    private Integer IDcapthe;
    private String Hoten;
    private String LopHC;
    private Integer SoDT;
    private Date NgayDangKy;
    private String NguoiDangKy;
    private String TrangThai;
    // tạo kết nối với csdl vào bảng THEBANDOC , tạo hàm thêm Thẻ
    public static void insertTheBanDoc(String IDcapthe, String mabandoc, String ngayDangKy, String nguoiDangKy, String trangThai) throws SQLException {
        Connection connection = SQLManagement.connectionSQLSever();// tạo kết nối với sqlsever
        Statement statement = connection.createStatement();// tạo đối tượng statement
        String sql = "insert into THEBANDOC(IDCAPTHE,IDBANDOC,NGAYDANGKY,NGUOIDANGKY,TRANGTHAI) values ('" + IDcapthe + "','" + mabandoc + "','" + ngayDangKy +
                "',N'" + nguoiDangKy + "',N'" + trangThai + "')";
        statement.execute(sql);// thực thi câu lệnh
        statement.close();// đóng đối tượng statement
        connection.close();// đóng kết nối
    }
    // tạo kết nối với csdl vào bảng THEBANDOC , tạo hàm thu hồi Thẻ
    public static void deleteTheBanDoc(String IDcapthe) throws SQLException {
        Connection connection = SQLManagement.connectionSQLSever();// tạo kết nối với sqlsever
        Statement statement = connection.createStatement();// tạo đối tượng statement
        String sql = "DELETE FROM THEBANDOC WHERE IDCAPTHE = '" + IDcapthe + "'";
        statement.execute(sql);// thực thi câu lệnh
        statement.close();// đóng đối tượng statement
        connection.close();// đóng kết nối
    }
    public CardPeople(Integer IDcapthe, String hoten, String lopHC, Integer soDT, Date ngayDangKy, String nguoiDangKy, String trangThai) {
        this.IDcapthe = IDcapthe;
        Hoten = hoten;
        LopHC = lopHC;
        SoDT = soDT;
        NgayDangKy = ngayDangKy;
        NguoiDangKy = nguoiDangKy;
        TrangThai = trangThai;
    }

    public Integer getIDcapthe() {
        return IDcapthe;
    }

    public void setIDcapthe(Integer IDcapthe) {
        this.IDcapthe = IDcapthe;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public String getLopHC() {
        return LopHC;
    }

    public void setLopHC(String lopHC) {
        LopHC = lopHC;
    }

    public Integer getSoDT() {
        return SoDT;
    }

    public void setSoDT(Integer soDT) {
        SoDT = soDT;
    }

    public Date getNgayDangKy() {
        return NgayDangKy;
    }

    public void setNgayDangKy(Date ngayDangKy) {
        NgayDangKy = ngayDangKy;
    }

    public String getNguoiDangKy() {
        return NguoiDangKy;
    }

    public void setNguoiDangKy(String nguoiDangKy) {
        NguoiDangKy = nguoiDangKy;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}
