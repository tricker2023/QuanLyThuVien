package com.example.quanlythuvien;

import static com.example.quanlythuvien.SQLHepler.SQLManagement.connectionSQLSever;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlythuvien.DataManagement.CardPeople;
import com.example.quanlythuvien.DataManagement.CardPeople;
import com.example.quanlythuvien.SQLHepler.SQLManagement;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CapTheBanDocActivity extends AppCompatActivity {
    // Khai bao id
    TextView edtngaydangkyct;
    ImageButton btnback; // khai bao imgbtn back
    Button btncapthe; // khai báo btn cấp thẻ
    TextInputEditText edtidmathect,edtidmabandoc, edtnguoidangkyct, edttrangthaict;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_the_ban_doc);
        Init();
        onClickChangePage();


        // Xu ly click btnback
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // xử lý text sự kiện chọn ngày
        edtngaydangkyct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate(calendar,simpleDateFormat);
            }
        });
    }

    private void onClickChangePage() {
        // xử lý btn nhập cấp thẻ
        btncapthe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mathe = edtidmathect.getText().toString();
                String mabandoc = edtidmabandoc.getText().toString();
                String nguoidangky = edtnguoidangkyct.getText().toString();
                String trangthai = edttrangthaict.getText().toString();
                String ngaydangky = edtngaydangkyct.getText().toString();
                // gọi tới hàm insert thẻ bạn đọc
                try {
                    CardPeople.insertTheBanDoc(mathe,mabandoc,ngaydangky,nguoidangky,trangthai);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if(mathe.length()>0 && mabandoc.length()>0 && ngaydangky.length() != 0 && nguoidangky.length()>0 && trangthai.length()>0 ){
                    Toast.makeText(CapTheBanDocActivity.this, "Nhập thông tin thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(CapTheBanDocActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    private void Init() {
        //anh xa id image btnback
        btnback = findViewById(R.id.btnback);
        btncapthe = findViewById(R.id.btncapthe);//anh xa id btn nhap cap the
        edtidmathect = findViewById(R.id.edtidmathect);// ánh xạ id mã thẻ
        //anh xa id capthe
        edtidmabandoc = findViewById(R.id.edtidmabandoc);
        edtngaydangkyct = findViewById(R.id.edtngaydangkyct);
        edtnguoidangkyct = findViewById(R.id.edtnguoidangkyct);
        edttrangthaict = findViewById(R.id.edttrangthaict);
    }

    private void selectedDate(Calendar calendar,SimpleDateFormat simpleDateFormat){
        int day = calendar.get(Calendar.DATE); // gắn biến day là biến được lấy dữ liệu từ date trong lịch
        int month = calendar.get(Calendar.MONTH); // gắn biến month là biến được lấy dữ liệu từ month trong lịch
        int year = calendar.get(Calendar.YEAR); // gắn biến year là biến được lấy dữ liệu từ year trong lịch
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);

                edtngaydangkyct.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year,month,day);
        datePickerDialog.show();
    }

}