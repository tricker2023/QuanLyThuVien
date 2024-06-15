package com.example.quanlythuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.quanlythuvien.DataManagement.InfReader;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLException;

public class CapNhatThongTinBanDocActivity extends AppCompatActivity {
    // Khai bao id
    ImageButton btnback; // khai bao imagebutton back
    Button btncapnhatthongtinbd,btnxoathongtinbd; // khai báo btn cập nhật , xóa TT bạn đọc
    TextInputEditText edthotencn, edtsdtcn, edtdiachicn, edtlopcn, edtkhoaviencn,edtmabandoccn; // khai báo id cập nhật TT bạn đọc
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_thong_tin_ban_doc);
        Init1(); // gọi tới hàm init1
        OnClickChangePage1(); // gọi tới hàm OnClickChangePage1
        OnClickChangePage2(); // gọi tới hàm OnClickChangePage2

        // xử lý sự kiện btn back
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTrangChu = getIntent();
                finish();
            }
        });
    }

    private void Init1() {
        //anh xa id image btnback
        btnback = findViewById(R.id.btnback);
        btncapnhatthongtinbd = findViewById(R.id.btncapnhatthongtinbd);//anh xa id btn capnhat
        btnxoathongtinbd = findViewById(R.id.btnxoathongtinbd);// ánh xạ id btn xoatt
        // anh xa id cap nhat thong tin
        edthotencn = findViewById(R.id.edthotencn);
        edtsdtcn = findViewById(R.id.edtsdtcn);
        edtdiachicn = findViewById(R.id.edtdiachicn);
        edtlopcn = findViewById(R.id.edtlopcn);
        edtkhoaviencn = findViewById(R.id.edtkhoaviencn);
        edtmabandoccn = findViewById(R.id.edtmabandoccn);

    }

    private void OnClickChangePage2() {
        // xử lý sự kiện btn xóa TT
        btnxoathongtinbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String IDThemTT = edtmabandoccn.getText().toString();
                //gọi tới hàm deleteTTBD của InfReader trong datamanagerment
                try {
                    InfReader.deleteTTBD(IDThemTT);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if(IDThemTT.length()>0 ){
                    Toast.makeText(CapNhatThongTinBanDocActivity.this, "Xóa thông tin thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(CapNhatThongTinBanDocActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    private void OnClickChangePage1() {
        // xử lý sự kiện btn capnhat TT
        btncapnhatthongtinbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String IDThemTT = edtmabandoccn.getText().toString();
                String hoten = edthotencn.getText().toString();
                String lopHC = edtlopcn.getText().toString();
                String SDT = edtsdtcn.getText().toString();
                String diachi = edtdiachicn.getText().toString();
                String khoa = edtkhoaviencn.getText().toString();
                //gọi tới hàm updateTTBD của InfReader trong datamanagerment
                try {
                    InfReader.updateTTBD(IDThemTT, hoten, lopHC,khoa,diachi,SDT);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                if(IDThemTT.length()>0 && hoten.length()>0 && lopHC.length()>0 && SDT.length()>0 && diachi.length()>0 && khoa.length()>0 ){
                    Toast.makeText(CapNhatThongTinBanDocActivity.this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(CapNhatThongTinBanDocActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}