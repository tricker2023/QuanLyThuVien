package com.example.quanlythuvien.DataManagement;

import android.util.Log;

import com.example.quanlythuvien.SQLHepler.SQLManagement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Date;

public class SachTra {
    private String IDtraSach;
    private String IDbandoc;
    private String IDbooks;
    private String IDbookcataloging;
    private String IDttmuonsach;
    private Time ThoiGianTra;
    private Date ngayTra;

    public static void insertTraSach(String IDtraSach, String IDbandoc, String IDbooks, String IDbookcataloging, String IDttmuonsach, String thoiGianTra, String ngayTra) throws SQLException {
        Connection connection = SQLManagement.connectionSQLSever(); // Kết nối với SQL server
        Statement statement = connection.createStatement(); // tạo đối tượng statement
        // Tạo câu lệnh thêm dữ liệu vào SQL Server
        String sql ="INSERT INTO THONGTINTRASACH(IDTRASACH,IDBANDOC,IdBooks,IdBookCataloging,IDTTMUONSACH,THOIGIANTRA,NgayTra) VALUES ('"+IDtraSach+"','"+IDbandoc+"','"+IDbooks+"','"+IDbookcataloging+"','"+IDttmuonsach+"','"+thoiGianTra+"','"+ngayTra+"')"; // tạo câu lệnh
        Log.e("DATA",sql);
        statement.execute(sql); // khởi chạy câu lệnh sql
        statement.close(); // đóng statement
        connection.close();// đóng kết nói sql
    }

    public SachTra(String IDtraSach, String IDbandoc, String IDbooks, String IDbookcataloging, String IDttmuonsach, Time thoiGianTra, Date ngayTra) {
        this.IDtraSach = IDtraSach;
        this.IDbandoc = IDbandoc;
        this.IDbooks = IDbooks;
        this.IDbookcataloging = IDbookcataloging;
        this.IDttmuonsach = IDttmuonsach;
        ThoiGianTra = thoiGianTra;
        this.ngayTra = ngayTra;
    }

    public String getIDtraSach() {
        return IDtraSach;
    }

    public void setIDtraSach(String IDtraSach) {
        this.IDtraSach = IDtraSach;
    }

    public String getIDbandoc() {
        return IDbandoc;
    }

    public void setIDbandoc(String IDbandoc) {
        this.IDbandoc = IDbandoc;
    }

    public String getIDbooks() {
        return IDbooks;
    }

    public void setIDbooks(String IDbooks) {
        this.IDbooks = IDbooks;
    }

    public String getIDbookcataloging() {
        return IDbookcataloging;
    }

    public void setIDbookcataloging(String IDbookcataloging) {
        this.IDbookcataloging = IDbookcataloging;
    }

    public String getIDttmuonsach() {
        return IDttmuonsach;
    }

    public void setIDttmuonsach(String IDttmuonsach) {
        this.IDttmuonsach = IDttmuonsach;
    }

    public Time getThoiGianTra() {
        return ThoiGianTra;
    }

    public void setThoiGianTra(Time thoiGianTra) {
        ThoiGianTra = thoiGianTra;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }
}

