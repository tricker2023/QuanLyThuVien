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

import com.example.quanlythuvien.DataManagement.VTraSach;
import com.example.quanlythuvien.SQLHepler.SQLManagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.BreakIterator;

public class TrangChuQlyTraSach extends AppCompatActivity {
    ImageButton btnBackTS;//khai báo biến ImgButton
    EditText idTraSach,idMuonSach,idBanDoc,idSachTS,idBienMuc,timeTra,dateTraSach;//khai báo các biến eidtext
    Button btnThemTS,btnSuaTS,btnXoaTS,btnXemTS;//khai báo các biến button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trang_chu_qly_tra_sach);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //ánh xạ id của các thành phần giao diện tương ứng với các biến giao diện đã khai báo ở trên
        idTraSach=findViewById(R.id.idTraSach);
        idMuonSach=findViewById(R.id.idMuonSach);
        idBanDoc=findViewById(R.id.idBanDoc);
        idSachTS=findViewById(R.id.idSachTS);
        idBienMuc=findViewById(R.id.idBienMuc);
        timeTra=findViewById(R.id.timeTra);
        dateTraSach=findViewById(R.id.dateTraSach);
        btnThemTS=findViewById(R.id.btnThemTS);
        btnSuaTS=findViewById(R.id.btnSuaTS);
        btnXoaTS=findViewById(R.id.btnXoaTS);
        btnXemTS=findViewById(R.id.btnXemTS);
        btnBackTS=findViewById(R.id.btnBackTS);

        //xử lý sự kiện khi click vào ô quay lại
        btnBackTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(TrangChuQlyTraSach.this, MainActivity.class);//khai báo intent cho phép chuyển từ giao diện naỳ qua giao diện khác
                startActivity(myintent);//thực thi câu lệnh
            }
        });

        //xử lý sự kiện khi click vào ô thêm
        btnThemTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection= SQLManagement.connectionSQLSever();//kết nối với csdl
                String MaMS = idMuonSach.getText().toString();
                String MaSach = idSachTS.getText().toString();
                String MaBienMuc = idBienMuc.getText().toString();
                VTraSach vTraSach = new VTraSach();
                try {
                    vTraSach = VTraSach.getuserlist1(MaSach,MaBienMuc,MaMS);
                    if(connection!=null){
                        if(vTraSach.getidSach().equals(MaSach) && vTraSach.getidBookCataloging().equals(MaBienMuc) && vTraSach.getidMuonSach().equals(MaMS)) {
                            BreakIterator id;
                            //câu lệnh thêm dòng mới vào bảng ThongTinTraSach trong csdl
                            String sqlThem = "insert into THONGTINTRASACH values ('" + idTraSach.getText().toString() + "','" + idBanDoc.getText().toString() + "','" + idSachTS.getText().toString() + "','" + idBienMuc.getText().toString() + "','" + idMuonSach.getText().toString() + "','" + timeTra.getText().toString() + "','" + dateTraSach.getText().toString() + "')";
                            //tạo đối tượng statement
                            Statement st = connection.createStatement();
                            //Thực thi câu lệnh sql
                            ResultSet rs = st.executeQuery(sqlThem);
                        }else{
                            Toast.makeText(TrangChuQlyTraSach.this, "Thông tin ID vừa nhập không chính xác", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (SQLException exception) {
                    Log.e("Errol",exception.getMessage());
                }
            }
        });

        //xử lý sự kiện khi click vào ô sửa
        btnSuaTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MaMS = idMuonSach.getText().toString();
                String MaSach = idSachTS.getText().toString();
                String MaBienMuc = idBienMuc.getText().toString();
                VTraSach vTraSach = new VTraSach();
                Connection connection=SQLManagement.connectionSQLSever();//kết nối với csdl;
                try {
                    vTraSach = VTraSach.getuserlist1(MaSach,MaBienMuc,MaMS);
                    if(connection!=null){
                        if(vTraSach.getidSach().equals(MaSach) && vTraSach.getidBookCataloging().equals(MaBienMuc) && vTraSach.getidMuonSach().equals(MaMS)) {
                            BreakIterator id;
                            //tạo câu lệnh sql update bảng ThongTinTraSach trong csdl
                            String sqlSua = "update THONGTINTRASACH set IDBANDOC = '" + idBanDoc.getText().toString() + "',IdBooks='" + idSachTS.getText().toString() + "',IdBookCataloging='" + idBienMuc.getText().toString() + "',IDTTMUONSACH='" + idMuonSach.getText().toString() + "',THOIGIANTRA='" + timeTra.getText().toString() + "',NgayTra='" + dateTraSach.getText().toString() + "'where IDTRASACH = '" + idTraSach.getText().toString() + "'";
                            //tạo đối tượng statement
                            Statement st = connection.createStatement();
                            //thực thi câu lệnh sql
                            ResultSet rs = st.executeQuery(sqlSua);
                        }else{
                            Toast.makeText(TrangChuQlyTraSach.this, "Thông tin ID vừa nhập không chính xác, c", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (SQLException exception) {
                    Log.e("Errol",exception.getMessage());
                }
            }
        });


        //xử lý sự kiện khi click vào ô xóa
        btnXoaTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection=SQLManagement.connectionSQLSever();//kết nối với csdl
                Toast.makeText(TrangChuQlyTraSach.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                try {
                    if(connection!=null){
                        BreakIterator id;
                        //tạo câu lệnh sql xóa dòng trong bảng ThongTinTraSach
                        String sqlXoa="delete THONGTINTRASACH where IDTRASACH = '"+idTraSach.getText().toString()+"'";
                        //tạo đối tượng statement
                        Statement st=connection.createStatement();
                        //thực thi câu lệnh
                        ResultSet rs=st.executeQuery(sqlXoa);
                    }
                } catch (SQLException exception) {
                    Log.e("Errol",exception.getMessage());
                }
            }
        });

        //xử lý sự kiện khi click vào ô xem
        btnXemTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //khai báo intent cho phép chuyên từ giao diện này đến giao diện khác
                Intent myintent = new Intent(TrangChuQlyTraSach.this, XemThongTinTraSach.class);
                //thực thi câu lệnh
                startActivity(myintent);
            }
        });
    }
}