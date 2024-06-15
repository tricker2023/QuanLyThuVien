package com.example.quanlythuvien.DataManagement;

import com.example.quanlythuvien.SQLHepler.SQLManagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VMuonSach {
    String idBanDoc,idSach,idBookCataloging;

    public VMuonSach(){
        idBanDoc = "";
        idSach = "";
        idBookCataloging = "";
    };
    public VMuonSach(String idSach,String idBookCataloging) {
        this.idSach = idSach;
        this.idBookCataloging = idBookCataloging;
    }
    public VMuonSach(String idBanDoc) {
        this.idBanDoc = idBanDoc;
    }

    public static VMuonSach getuserlist(String idSach,String idBookCataloging) throws SQLException {
        Connection connection = SQLManagement.connectionSQLSever();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        VMuonSach vMuonSach = new VMuonSach();
        String sql1 = "select * from VKTRAMUONSACH where IdBooks = '" + idSach + "' and IdBookCataloging = '" + idBookCataloging +"'";
//        String SQL2 = "select * from BANDOC where IDBANDOC = '" + idBanDoc + "'";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs1 = statement.executeQuery(sql1);
        if(rs1.next()){
            vMuonSach = new VMuonSach(
                    rs1.getString(1).trim(),
                    rs1.getString(2).trim());
            // Đọc dữ liệu từ ResultSet)
        }
        statement.close();
        connection.close();// Đóng kết nối
        return vMuonSach;
    }



    public String getidBanDoc() {
        return idBanDoc;
    }

    public void setidBanDoc(String idBanDoc) {
        this.idBanDoc = idBanDoc;
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
