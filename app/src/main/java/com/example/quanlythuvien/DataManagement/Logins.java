package com.example.quanlythuvien.DataManagement;

import com.example.quanlythuvien.Login;
import com.example.quanlythuvien.SQLHepler.SQLManagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Logins {
    private String IDLOG;
    private String PASSWORDS;
    private String CHUCVU;
    public Logins(){};

    public Logins(String IDLOG, String PASSWORDS, String CHUCVU) {
        this.IDLOG = IDLOG;
        this.PASSWORDS = PASSWORDS;
        this.CHUCVU = CHUCVU;
    }

    // hàm lấy tài khoản
    public static Logins getuserlist(String ID,String passWords) throws SQLException {
        Connection connection = SQLManagement.connectionSQLSever();
        Logins logins = new Logins();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "select * from LOGINS where IDLOG = '" + ID + "' and PASSWORDS = '" + passWords +"'";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        if(rs.next()){
            logins = new Logins(
                    rs.getString(1).trim(),
                    rs.getString(2).trim(),
                    rs.getString(3).trim());// Đọc dữ liệu từ ResultSet)
        }
        statement.close();
        connection.close();// Đóng kết nối
        return logins;
    }

    // hàm ínsert logins
    public static void insertList(String ID, String passWords,String position) throws SQLException{
        Connection connection = SQLManagement.connectionSQLSever(); // Kết nối với SQL Server
        Statement statement = connection.createStatement(); // Tạo đối tượng Statement.
        String sql = "insert into LOGINS(IDLOG,PASSWORDS,CHUCVU) values (" +
                "'" + ID + "','" + passWords + "',N'" +  position + "')"; // Câu lênh SQL Server thêm hàng mới trong bảng Logins
        statement.execute(sql); // Thực thi câu lệnh
        statement.close(); // Đóng đối tượng Statement
        connection.close(); // Đóng kết nối
    }
    public String getIDLOG() {
        return IDLOG;
    }

    public void setIDLOG(String IDLOG) {
        this.IDLOG = IDLOG;
    }

    public String getPASSWORDS() {
        return PASSWORDS;
    }

    public void setPASSWORDS(String PASSWORDS) {
        this.PASSWORDS = PASSWORDS;
    }

    public String getCHUCVU() {
        return CHUCVU;
    }

    public void setCHUCVU(String CHUCVU) {
        this.CHUCVU = CHUCVU;
    }
}
