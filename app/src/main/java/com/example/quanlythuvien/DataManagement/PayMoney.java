package com.example.quanlythuvien.DataManagement;

import com.example.quanlythuvien.SQLHepler.SQLManagement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PayMoney {
    private String IDthanhToan;
    private String IDSach;
    private String IDtraSach;
    private String IDmuonsach;
    private Integer TongNgayMuon;
    private Double SoTien;
    private String LuuY;
    // Kết nối với SQL Server
    public static void insertThanhToan(String IDthanhToan, String IDSach, String IDtraSach, String IDmuonsach, Integer tongNgayMuon, Double soTien, String luuY) throws SQLException {
        Connection connection = SQLManagement.connectionSQLSever(); //Kết nối với SQL server
        Statement statement = connection.createStatement(); // Tạo đối tượng Statement
        String sql ="insert into thanhToan(IDthanhToan,IDSach,idMuonSach,IDtraSach,TongNgayMuon,SoTien,LuuY) " +
                "values ("+IDthanhToan+","+IDSach+","+IDmuonsach+","+IDtraSach+","+tongNgayMuon+",'"+soTien+"','"+luuY+"')";
        statement.execute(sql); // thuc thi cau lenh
        statement.close(); // Dong doi tuong Statement
        connection.close(); // Dong ket noi
    }

    public PayMoney(String IDthanhToan, String IDSach, String IDtraSach, String IDmuonsach, Integer tongNgayMuon, Double soTien, String luuY) {
        this.IDthanhToan = IDthanhToan;
        this.IDSach = IDSach;
        this.IDtraSach = IDtraSach;
        this.IDmuonsach = IDmuonsach;
        TongNgayMuon = tongNgayMuon;
        SoTien = soTien;
        LuuY = luuY;
    }

    public String getIDthanhToan() {
        return IDthanhToan;
    }

    public void setIDthanhToan(String IDthanhToan) {
        this.IDthanhToan = IDthanhToan;
    }

    public String getIDSach() {
        return IDSach;
    }

    public void setIDSach(String IDSach) {
        this.IDSach = IDSach;
    }

    public String getIDtraSach() {
        return IDtraSach;
    }

    public void setIDtraSach(String IDtraSach) {
        this.IDtraSach = IDtraSach;
    }

    public String getIDmuonsach() {
        return IDmuonsach;
    }

    public void setIDmuonsach(String IDmuonsach) {
        this.IDmuonsach = IDmuonsach;
    }

    public Integer getTongNgayMuon() {
        return TongNgayMuon;
    }

    public void setTongNgayMuon(Integer tongNgayMuon) {
        TongNgayMuon = tongNgayMuon;
    }

    public Double getSoTien() {
        return SoTien;
    }

    public void setSoTien(Double soTien) {
        SoTien = soTien;
    }

    public String getLuuY() {
        return LuuY;
    }

    public void setLuuY(String luuY) {
        LuuY = luuY;
    }


}