package com.example.quanlythuvien.DataManagement;

import android.util.Log;

import com.example.quanlythuvien.SQLHepler.SQLManagement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InfReader {
    private Integer IDThemTT;
    private String Hoten;
    private String LopHC;
    private Integer SDT;
    private String Diachi;
    private Integer MaSinhVien;
    private String Khoa;

    // tạo kết nối với csdl vào bảng TTBANDOC , tạo hàm thêm TT
//    public static void insertTTBD(String IDThemTT, String hoten, String lopHC, String SDT, String diachi, String maSinhVien, String khoa ) throws SQLException {
//        Connection connection = SQLManagement.connectionSQLSever(); // tạo kết nối với sqlsever
//        Statement statement = connection.createStatement(); // tạo đối tượng statement
//        String sql = "INSERT INTO TTBANDOC(IDTHEMTT,HOTEN,LOPHANHCHINH,SODIENTHOAI,DIACHI,MSV,KHOA) VALUES ('"+IDThemTT+"','"+hoten+"','"+lopHC+"','"+SDT+"','"+diachi+"','"+maSinhVien+"','"+khoa+"')";
//        statement.execute(sql); // thực thi câu lệnh
//        statement.close(); // đóng đối tượng statement
//        connection.close(); // đóng kết nối
//    }
    // tạo kết nối với csdl vào bảng TTBANDOC , tạo hàm sửa TT
        public static void updateTTBD(String IDThemTT, String hoten, String lopHC, String khoa,String diachi, String sdt) throws SQLException {
            Connection connection = SQLManagement.connectionSQLSever();// tạo kết nối với sqlsever
            Statement statement = connection.createStatement();// tạo đối tượng statement
            String sql = "UPDATE BANDOC SET TEN = '"+hoten+"', LOPHC = '"+lopHC+"', KHOA = '"+khoa+"', DIACHI = '"+diachi+"', SDT = '" + sdt + "' where IDBANDOC = '" + IDThemTT + "'";
            Log.e("Data",sql);
            statement.execute(sql);
            statement.close();// đóng đối tượng statement
            connection.close();// đóng kết nối
        }
    // tạo kết nối với csdl vào bảng TTBANDOC , tạo hàm xóa TT
    public static void deleteTTBD(String IDThemTT) throws SQLException {
        Connection connection = SQLManagement.connectionSQLSever();// tạo kết nối với sqlsever
        Statement statement = connection.createStatement();// tạo đối tượng statement
        String sql = "DELETE FROM BANDOC WHERE IDBANDOC = '" + IDThemTT + "'";
        statement.execute(sql);// thực thi câu lệnh
        statement.close();// đóng đối tượng statement
        connection.close();// đóng kết nối
    }

    public InfReader(Integer IDThemTT, String hoten, String lopHC, Integer SDT, String diachi, Integer maSinhVien, String khoa) {
        this.IDThemTT = IDThemTT;
        Hoten = hoten;
        LopHC = lopHC;
        this.SDT = SDT;
        Diachi = diachi;
        MaSinhVien = maSinhVien;
        Khoa = khoa;
    }

    public Integer getIDThemTT() {
        return IDThemTT;
    }

    public void setIDThemTT(Integer IDThemTT) {
        this.IDThemTT = IDThemTT;
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

    public Integer getSDT() {
        return SDT;
    }

    public void setSDT(Integer SDT) {
        this.SDT = SDT;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public Integer getMaSinhVien() {
        return MaSinhVien;
    }

    public void setMaSinhVien(Integer maSinhVien) {
        MaSinhVien = maSinhVien;
    }

    public String getKhoa() {
        return Khoa;
    }

    public void setKhoa(String khoa) {
        Khoa = khoa;
    }
}
