package com.example.quanlythuvien;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

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

public class XemThongTinTraSach extends AppCompatActivity {
    //khai báo các biến giao diện
    TextView timKiemTS,tenXemTS,lopXemTS,sdtXemTS,idMuonSachTS,tenSachXemTS,dateMuonXemTS,dateTraXemTS;
    Button btnBackXemTS;
    ImageButton btnTimKiemTS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xem_thong_tin_tra_sach);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //ánh xạ id giao diện với các biến giao diện tương ứng
        timKiemTS=findViewById(R.id.timKiemTS);
        tenXemTS=findViewById(R.id.tenXemTS);
        lopXemTS=findViewById(R.id.lopXemTS);
        sdtXemTS=findViewById(R.id.sdtXemTS);
        idMuonSachTS=findViewById(R.id.idMuonSachTS);
        tenSachXemTS=findViewById(R.id.tenSachXemTS);
        dateMuonXemTS=findViewById(R.id.dateMuonXemTS);
        dateTraXemTS=findViewById(R.id.dateTraXemTS);
        btnBackXemTS=findViewById(R.id.btnBackXemTS);
        btnTimKiemTS=findViewById(R.id.btnTimKiemTS);
        //hàm xử lý sự kiện khi click vào nút quay lại
        btnBackXemTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(XemThongTinTraSach.this, TrangChuQlyTraSach.class);
                startActivity(myintent);
            }
        });

        //hàm xử lý sự kiện khi click vào nut tìm kiếm
        btnTimKiemTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nôi csdl
                Connection connection= SQLManagement.connectionSQLSever();
                try {
                    if(connection!=null){
                        BreakIterator id;
                        //tạo câu lệnh sql xem thông tin bảng ThongTinPhat
                        String sqlXem="select * from VTRASACH where IDTRASACH = '"+timKiemTS.getText().toString()+"'";
                        //tạo đối tượng statement
                        Statement st=connection.createStatement();
                        //chạy câu lệnh sql
                        ResultSet rs=st.executeQuery(sqlXem);
                        while (rs.next()){
                            //ánh xạ các thông tin vưà truy xuất từ câu lệnh sql lên id của các thành phần giao diện tương ứng
                            tenXemTS.setText(rs.getString(2));
                            lopXemTS.setText(rs.getString(3));
                            sdtXemTS.setText(rs.getString(4));
                            idMuonSachTS.setText(rs.getString(5));
                            tenSachXemTS.setText(rs.getString(6));
                            dateMuonXemTS.setText(rs.getString(7));
                            dateTraXemTS.setText(rs.getString(8));
                        }
                    }
                } catch (SQLException exception) {
                    Log.e("Errol",exception.getMessage());
                }
            }
        });
    }
}