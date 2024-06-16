package com.example.quanlythuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.quanlythuvien.DataManagement.DangKyMuonSach;
import com.example.quanlythuvien.SQLHepler.SQLManagement;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NhapMuonSachActivity extends AppCompatActivity{
    private TextView date_timeTextInputEditText,timeTextInputEditText;
    //Khai bao cac id
    private ImageButton btnback;
    private TextInputEditText edtmabd,edtidmams,edtmasach,edtmabienmuc,edtthoigianmuonms;
    private Button btnnhapms;

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_muon_sach);
        Init();
        onClickChangePage();
        clickEditText();
        // xu ly click back
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // trở lại giao diện trước
                finish();
            }
        });

    }
    private void clickEditText(){


        date_timeTextInputEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate(calendar,simpleDateFormat);
            }
        });
        timeTextInputEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTime(calendar,simpleTimeFormat);
            }
        });
    }
    private void selectedTime(Calendar calendar,SimpleDateFormat simpleTimeFormat){
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(0,0,0,hourOfDay,minute);

                timeTextInputEditText.setText(simpleTimeFormat.format(calendar.getTime()));
            }
        },hour,minute,true);
        timePickerDialog.show();
    }
    private void selectedDate(Calendar calendar,SimpleDateFormat simpleDateFormat){

        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);

                date_timeTextInputEditText.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year,month,day);
        datePickerDialog.show();

    }
    private void onClickChangePage() {
        btnnhapms.setOnClickListener(new View.OnClickListener() { // xử lý click nhập mượn sách
            @Override
            public void onClick(View v) {
                date_timeTextInputEditText.setText(simpleDateFormat.format(calendar.getTime()));
                timeTextInputEditText.setText(simpleTimeFormat.format(calendar.getTime()));
                String maMS = edtidmams.getText().toString();
                String maBD = edtmabd.getText().toString();
                String maSach = edtmasach.getText().toString();
                String maBienMucSach = edtmabienmuc.getText().toString();
                String ngayMuon = date_timeTextInputEditText.getText().toString();
                String thoiGianMuon = timeTextInputEditText.getText().toString();
                String tongNgayMuon = edtthoigianmuonms.getText().toString();
                try {
                    DangKyMuonSach.insertDangKy(maMS,maBD,maSach,maBienMucSach,thoiGianMuon,ngayMuon, tongNgayMuon);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            if(!maMS.isEmpty() && !maBD.isEmpty() && !maSach.isEmpty() && !maBienMucSach.isEmpty() && !tongNgayMuon.isEmpty()){
                    Toast.makeText(NhapMuonSachActivity.this, "Nhập đăng ký mượn sách thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(NhapMuonSachActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void Init() {
        // anh xa id
        btnback = findViewById(R.id.btnback); // anh xa id cho nút back
        edtidmams = findViewById(R.id.edtidmams); // anh xa id cho form ho ten
        edtmabd = findViewById(R.id.edtmabd); // anh xạ id cho form lớp
        edtmasach = findViewById(R.id.edtmasach); // ánh xạ id cho form nhập sdt
        edtmabienmuc = findViewById(R.id.edtmabienmuc); // ánh xạ id cho form nhập gmail
        date_timeTextInputEditText = findViewById(R.id.edtngaydangkyMS); // ánh xạ id cho form ngày mượn sách
        edtthoigianmuonms = findViewById(R.id.edtthoigianmuonms); // ánh xạ id cho form thời gian mượn sách
        timeTextInputEditText = findViewById(R.id.edtgiodangkyMS);// ánh xạ id cho form tên sách
        btnnhapms = findViewById(R.id.btnnhapms); // ánh xạ id cho button mượn sách

    }
}