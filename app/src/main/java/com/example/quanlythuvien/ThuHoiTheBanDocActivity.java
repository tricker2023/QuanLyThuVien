package com.example.quanlythuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlythuvien.DataManagement.CardPeople;
import com.example.quanlythuvien.SQLHepler.SQLManagement;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThuHoiTheBanDocActivity extends AppCompatActivity{
    // Khai bao id
    ImageButton btnback; // khai bao imagebutton back
    Button btnthuhoithe; // khai báo id btn thu hồi
    TextInputEditText edtidmatheth; // khai báo id thu hồi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Init(); // gọi tới hàm init
        OnClickChangePage(); // gọi tới hàm OnClickChangePage

        // Xu ly click btnback
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTrangChu = getIntent();
                finish();
            }
        });
    }

    private void OnClickChangePage() {
        // xử lý sự kiện click btn thu hồi
        btnthuhoithe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mathe = edtidmatheth.getText().toString();

                // gọi tới hàm deleteThebandoc trong CardPeople
                try {
                    CardPeople.deleteTheBanDoc(mathe);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if(mathe.length()>0 ){
                    Toast.makeText(ThuHoiTheBanDocActivity.this, "Xóa thông tin thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(ThuHoiTheBanDocActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    private void Init() {
        setContentView(R.layout.activity_thu_hoi_the_ban_doc);
        //anh xa id image btnback
        btnback = findViewById(R.id.btnback);
        //anh xa id btn nhap thu hoi
        btnthuhoithe = findViewById(R.id.btnthuhoithe);
        //anh xa id thu hoi the
        edtidmatheth = findViewById(R.id.edtidmatheth);

    }
}