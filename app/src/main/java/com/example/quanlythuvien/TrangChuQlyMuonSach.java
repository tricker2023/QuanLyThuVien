package com.example.quanlythuvien;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quanlythuvien.DataManagement.VMuonSach;
import com.example.quanlythuvien.SQLHepler.SQLManagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.BreakIterator;

public class TrangChuQlyMuonSach extends AppCompatActivity {


    EditText maMS,maBanDoc,maSach,timeMS,dateMS,maBienMuc;
    Button btnThemMS,btnSuaMS,btnXoaMS,btnXemMS;
    ImageButton btnBackMS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trang_chu_qly_muon_sach);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //ánh xạ id giao diện tương ứng với các biến giao diện
        maMS=findViewById(R.id.maMS);
        maBanDoc=findViewById(R.id.maBanDoc);
        maSach=findViewById(R.id.maSach);
        maBienMuc=findViewById(R.id.maBienMuc);
        timeMS=findViewById(R.id.timeMS);
        dateMS=findViewById(R.id.dateMS);
        btnThemMS=findViewById(R.id.btnThemMS);
        btnSuaMS=findViewById(R.id.btnSuaMS);
        btnXoaMS=findViewById(R.id.btnXoaMS);
        btnXemMS=findViewById(R.id.btnXemMS);
        btnBackMS=findViewById(R.id.btnBackMS);

        //hàm xử lý sự kiện khi click vào nút quay lại
        btnBackMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //khai báo intent cho phép chuyển giao diện từ giao diện quản lý mợn sách đến giao diện trang chủ
                Intent myintent = new Intent(TrangChuQlyMuonSach.this, MainActivity.class);
                startActivity(myintent);
            }
        });

        //hàm xử lý sự kiện khi click vào nút thêm
        btnThemMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nối csdl
                Connection connection=SQLManagement.connectionSQLSever();
                String MaMS = maMS.getText().toString();
                String MaBanDoc = maBanDoc.getText().toString();
                String MaSach = maSach.getText().toString();
                String MaBienMuc = maBienMuc.getText().toString();
                VMuonSach vMuonSach = new VMuonSach();
                try {
                    vMuonSach = VMuonSach.getuserlist(MaSach,MaBienMuc);
                    if(connection!=null){
                        if(vMuonSach.getidSach().equals(MaSach) && vMuonSach.getidBookCataloging().equals(MaBienMuc)) {
                            BreakIterator id;
                            //tạo câu lệnh sql thêm thông tin vào bảng PhieuMuon
                            String sqlThem = "insert into THONGTINMUONSACH values ('"+maMS.getText().toString()+"','"+maBanDoc.getText().toString()+"','"+maSach.getText().toString()+"','"+maBienMuc.getText().toString()+"','"+timeMS.getText().toString()+"','"+dateMS.getText().toString()+"')";
                            //tạo đối tượng statement
                            Statement st = connection.createStatement();
                            //chạy câu lệnh sql
                            ResultSet rs = st.executeQuery(sqlThem);
                            Toast.makeText(TrangChuQlyMuonSach.this, "Thêm thông tin mượn sách thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(TrangChuQlyMuonSach.this, "Thông tin ID vừa nhập không chính xác, không thể sửa", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (SQLException exception) {
                    Log.e("Errol",exception.getMessage());
                }
            }
        });
        //hàm xử lý sự kiện khi click vào nút sửa
        btnSuaMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nối csdl
                Connection connection= SQLManagement.connectionSQLSever();
                String MaMS = maMS.getText().toString();
                String MaBanDoc = maBanDoc.getText().toString();
                String MaSach = maSach.getText().toString();
                String MaBienMuc = maBienMuc.getText().toString();
                VMuonSach vMuonSach = new VMuonSach();
                try {
                    if(connection!=null){
                        vMuonSach = VMuonSach.getuserlist(MaSach,MaBienMuc);
                        if(vMuonSach.getidSach().equals(MaSach) && vMuonSach.getidBookCataloging().equals(MaBienMuc)) {
                            BreakIterator id;
                            //tạo câu lệnh sql sửa thông tin trong bảng PhieuMuon
                            String sqlSua = "update THONGTINMUONSACH set IDBANDOC = '" + maBanDoc.getText().toString() + "',IdBooks='" + maSach.getText().toString() + "',IdBookCataloging='" + maBienMuc.getText().toString() + "',ThoiGianMuon='" + timeMS.getText().toString() + "',NgayMuon='" + dateMS.getText().toString() + "'where IDTTMUONSACH ='" + maMS.getText().toString() + "'";
                            //tạo đối tượng statement
                            Statement st = connection.createStatement();
                            //chạy câu lệnh sql
                            ResultSet rs = st.executeQuery(sqlSua);
                            Toast.makeText(TrangChuQlyMuonSach.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(TrangChuQlyMuonSach.this, "Thông tin ID vừa nhập không chính xác", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (SQLException exception) {
                    Log.e("Errol",exception.getMessage());
                }
            }
        });


        //hàm xử lý sự kiện khi click vào nút xóa
        btnXoaMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nối csdl
                Connection connection=SQLManagement.connectionSQLSever();
                Toast.makeText(TrangChuQlyMuonSach.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                try {
                    if(connection!=null){
                        BreakIterator id;
                        //tạo câu lệnh sql xóa thông tin trong bảng PhieuMuon
                        String sqlXoa="delete THONGTINMUONSACH where IDTTMUONSACH = '"+maMS.getText().toString()+"'";
                        Log.e("DATA",sqlXoa);
                        //tạo đối tượng statement
                        Statement st=connection.createStatement();
                        //chạy câu lệnh sql
                        ResultSet rs=st.executeQuery(sqlXoa);
                    }
                } catch (SQLException exception) {
                    Log.e("Errol",exception.getMessage());
                }
            }
        });

        //hàm xử lý sự kiện khi click vào nút xem
        btnXemMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //khai báo intent cho phép chuyển giao diện từ giao diện quản lý mợn sách đến giao diện xem thông tin mượn sách
                Intent myintent = new Intent(TrangChuQlyMuonSach.this, XemThongTinMuonSach.class);
                startActivity(myintent);
            }
        });
    }
}