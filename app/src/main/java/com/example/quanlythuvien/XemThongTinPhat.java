package com.example.quanlythuvien;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quanlythuvien.SQLHepler.SQLManagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.BreakIterator;

public class XemThongTinPhat extends AppCompatActivity {
    //khai báo các biến giao diện
    EditText timKiem,tenDocGiaXemPS,sdtXemPS,maSachXemPS,tenSachXemPS,hanTraXemPS,ngayTraXemPS,tienPhatXemPS;
    Button btnBackXemPS;
    ImageButton btnTimKiemPS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xem_thong_tin_phat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //ánh xạ id giao diện với các biến giao diện tương ứng
        timKiem=findViewById(R.id.timKiem);
        tenDocGiaXemPS=findViewById(R.id.tenDocGiaXemPS);
        sdtXemPS=findViewById(R.id.sdtXemPS);
        maSachXemPS=findViewById(R.id.maSachXemPS);
        tenSachXemPS=findViewById(R.id.tenSachXemPS);
        hanTraXemPS=findViewById(R.id.hanTraXemPS);
        ngayTraXemPS=findViewById(R.id.ngayTraXemPS);
        tienPhatXemPS=findViewById(R.id.tienPhatXemPS);
        btnBackXemPS=findViewById(R.id.btnBackXemPS);
        btnTimKiemPS=findViewById(R.id.btnTimKiemPS);

        //hàm xử lý sự kiện khi click vào nut tìm kiếm
        btnTimKiemPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nôi csdl
                Connection connection= SQLManagement.connectionSQLSever();
                try {
                    if(connection!=null){
                        BreakIterator id;
                        //tạo câu lệnh sql xem thông tin bảng ThongTinPhat
                        String sqlXem="select * from VPHATSACH where IDPHAT = '"+timKiem.getText().toString()+"'";
                        //tạo đối tượng statement
                        Statement st=connection.createStatement();
                        //chạy câu lệnh sql
                        ResultSet rs=st.executeQuery(sqlXem);
                        while (rs.next()){
                            //ánh xạ các thông tin vưà truy xuất từ câu lệnh sql lên id của các thành phần giao diện tương ứng
                            tenDocGiaXemPS.setText(rs.getString(2));
                            sdtXemPS.setText(rs.getString(3));
                            maSachXemPS.setText(rs.getString(4));
                            tenSachXemPS.setText(rs.getString(5));
                            hanTraXemPS.setText(rs.getString(6));
                            ngayTraXemPS.setText(rs.getString(7));
                            tienPhatXemPS.setText(rs.getString(8));
                        }
                    }
                } catch (SQLException exception) {
                    Log.e("Errol",exception.getMessage());
                }
            }
        });
        //hàm xử lý sự kiện khi click vào nút quay lại
        btnBackXemPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(XemThongTinPhat.this, phatTraSachMuon.class);
                startActivity(myintent);
            }
        });
    }
}