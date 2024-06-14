package com.example.quanlythuvien.DataManagement;

import android.util.Log;
import com.example.quanlythuvien.SQLHepler.SQLManagement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Date;

public class DangKyMuonSach {
    private String IDttmuonsach;
    private String IDbandoc;
    private String IDbooks;
    private String IDbookcataloging;
    private Time ThoiGianMuon;
    private Date ngaydangkyMS;
    private String thoiGian;

    public DangKyMuonSach(String IDttmuonsach, String IDbandoc, String IDbooks, String IDbookcataloging, Time thoiGianMuon, Date ngaydangkyMS, String thoiGian) {
        this.IDttmuonsach = IDttmuonsach;
        this.IDbandoc = IDbandoc;
        this.IDbooks = IDbooks;
        this.IDbookcataloging = IDbookcataloging;
        ThoiGianMuon = thoiGianMuon;
        this.ngaydangkyMS = ngaydangkyMS;
        this.thoiGian = thoiGian;
    }

    // tạo kết nối với csdl vào bảng dangkymuonsach
    public static void insertDangKy(String IDttmuonsach, String IDbandoc, String IDbooks, String IDbookcataloging, String thoiGianMuon, String ngaydangkyMS, String thoiGian) throws SQLException {
        Connection connection = SQLManagement.connectionSQLSever(); //Kết nối với SQL server
        Statement statement = connection.createStatement(); // Tạo đối tượng Statement
        // câu lệnh thêm dữ liệu vào sql server
        String sql = "INSERT INTO THONGTINMUONSACH(IDTTMUONSACH,IDBANDOC,IdBooks,IdBookCataloging,ThoiGianMuon,NgayMuon,TongNgayMuon) VALUES ('"+IDttmuonsach+"','"+IDbandoc+"','"+IDbooks+"','"+IDbookcataloging+"','"+thoiGianMuon+"','"+ngaydangkyMS+"','"+thoiGian+"')";
        Log.e("DATA",sql);
        statement.execute(sql); // thuc thi cau lenh
        statement.close(); // Dong doi tuong Statement
        connection.close(); // Dong ket noi
    }

    public String getIDttmuonsach() {
        return IDttmuonsach;
    }

    public void setIDttmuonsach(String IDttmuonsach) {
        this.IDttmuonsach = IDttmuonsach;
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

    public Time getThoiGianMuon() {
        return ThoiGianMuon;
    }

    public void setThoiGianMuon(Time thoiGianMuon) {
        ThoiGianMuon = thoiGianMuon;
    }

    public Date getNgaydangkyMS() {
        return ngaydangkyMS;
    }

    public void setNgaydangkyMS(Date ngaydangkyMS) {
        this.ngaydangkyMS = ngaydangkyMS;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }
}


