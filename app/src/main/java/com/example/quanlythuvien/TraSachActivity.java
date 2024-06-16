package com.example.quanlythuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.quanlythuvien.DataManagement.SachTra;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TraSachActivity extends AppCompatActivity {
    // khai báo các id
    private TextView datetraSach,timeTextInputEditText;
    private ImageButton btnback;
    private TextInputEditText edtidtrasach,edtidbd,edtmasach,edtbms,edtmams;
    private Button btnnhapts;

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd"); // hiển thị dữ liệu ngày tháng dưới dạng tháng , ngày ,năm
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra_sach);
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

        datetraSach.setOnClickListener(new View.OnClickListener() {
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

                datetraSach.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year,month,day);
        datePickerDialog.show();

    }
    private void onClickChangePage() {
        //xử lý click button nhập trả sách
        btnnhapts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeTextInputEditText.setText(simpleTimeFormat.format(calendar.getTime()));
                datetraSach.setText(simpleDateFormat.format(calendar.getTime()));
                String masach = edtmasach.getText().toString();
                String maTraSach = edtidtrasach.getText().toString();
                String maBanDoc = edtidbd.getText().toString();
                String maBienMucSach = edtbms.getText().toString();
                String maMuonSach = edtmams.getText().toString();
                String thoiGianTra = timeTextInputEditText.getText().toString();
                String ngayTra = datetraSach.getText().toString();
                if(maTraSach.length() == 0){
                    Toast.makeText(TraSachActivity.this, "Vui lòng nhập mã trả sách", Toast.LENGTH_SHORT).show();
                    edtidtrasach.requestFocus();
                    edtidtrasach.selectAll();
                }


                // gọi tới hàm insertTraSach bên sách trả của datamanagment
                try {
                    SachTra.insertTraSach(maTraSach,maBanDoc,masach,maBienMucSach,maMuonSach,thoiGianTra,ngayTra);
                }catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if(maTraSach.length()!=0 && maBanDoc.length()!=0 && maBienMucSach.length()!=0 && maMuonSach.length()!=0 && masach.length()!=0 ){
                    Toast.makeText(TraSachActivity.this, "Nhập trả sách thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(TraSachActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Init() {
        // anh xa id
        btnback = findViewById(R.id.btnback); // anh xa id cho nút back
        edtidbd = findViewById(R.id.edtidbd); // anh xa id cho form ho ten
        edtmasach = findViewById(R.id.edtmasach); // anh xạ id cho form lớp
        edtmams = findViewById(R.id.edtmams); // ánh xạ id cho form nhập sdt
        edtbms = findViewById(R.id.edtbms); // ánh xạ id cho form nhập gmail
        datetraSach = findViewById(R.id.edtngaytra); // ánh xạ id cho form ngày trả sách
        edtidtrasach = findViewById(R.id.edtidtrasach); // ánh xạ id cho form trả sách
        btnnhapts = findViewById(R.id.btnnhapts); // ánh xạ id cho button nhập trả sách
        timeTextInputEditText = findViewById(R.id.edtthoigiantra);
    }
}