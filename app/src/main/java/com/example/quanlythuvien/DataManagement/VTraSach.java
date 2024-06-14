package com.example.quanlythuvien.DataManagement;

import com.example.quanlythuvien.SQLHepler.SQLManagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VTraSach {
    String idMuonSach,idSach,idBookCataloging;

    public VTraSach(){
        idMuonSach = "";
        idSach = "";
        idBookCataloging = "";
    };
    public VTraSach(String idSach,String idBookCataloging,String idMuonSach) {
        this.idSach = idSach;
        this.idBookCataloging = idBookCataloging;
        this.idMuonSach=idMuonSach;
    }
    public static VTraSach getuserlist1(String idSach,String idBookCataloging,String idMuonSach) throws SQLException {
        Connection connection = SQLManagement.connectionSQLSever();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        VTraSach vTraSach = new VTraSach();
        String sql1 = "select * from VKTRATRASACH where IdBooks = '" + idSach + "' and IdBookCataloging = '" + idBookCataloging +"' and IDTTMUONSACH = '" +idMuonSach+"'";
//        String SQL2 = "select * from BANDOC where IDBANDOC = '" + idBanDoc + "'";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs1 = statement.executeQuery(sql1);
        if(rs1.next()){
            vTraSach = new VTraSach(
                    rs1.getString(1).trim(),
                    rs1.getString(2).trim(),
                    rs1.getString(3).trim());
            // Đọc dữ liệu từ ResultSet)
        }
        statement.close();
        connection.close();// Đóng kết nối
        return vTraSach;
    }



    public String getidMuonSach() {
        return idMuonSach;
    }

    public void setidMuonSach(String idMuonSach) {
        this.idMuonSach = idMuonSach;
    }
    public String getidSach() {
        return idSach;
    }

    public void setidSach(String idSach) {
        this.idSach = idSach;
    }
    public String getidBookCataloging() {
        return idBookCataloging;
    }

    public void setidBookCataloging(String idBookCataloging) {
        this.idBookCataloging = idBookCataloging;
    }
}
