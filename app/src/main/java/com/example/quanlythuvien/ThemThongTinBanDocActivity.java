package com.example.quanlythuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.quanlythuvien.DataManagement.InfReader;
import com.example.quanlythuvien.SQLHepler.SQLManagement;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLException;

public class ThemThongTinBanDocActivity extends AppCompatActivity {
    // Khai bao id
    ImageButton btnback; // khai bao imagebutton back
    Button btnthemthongtinbd; // khai báo btn thêm tt
    TextInputEditText edtmabandocttt,edthotenttt, edtlopttt, edtsdtttt, edtdiachittt, edtkhoavienttt; // khai báo id thêm tt
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_thong_tin_ban_doc);
        Init(); // gọi tới hàm init
        onClickChangePage(); // gọi tới hàm onClickChangePage
        // Xu ly click btnback
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void onClickChangePage() {
        // xử lý sự kiện click btn thêm TT
        btnthemthongtinbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String IDThemTT = edtmabandocttt.getText().toString();
                String hoten = edthotenttt.getText().toString();
                String lopHC = edtlopttt.getText().toString();
                String SDT = edtsdtttt.getText().toString();
                String diachi = edtdiachittt.getText().toString();
                String khoa = edtkhoavienttt.getText().toString();
                 //gọi tới hàm insertTTBD trong InfReader của datamanagement
                try {
                    InfReader.insertTTBD(IDThemTT,hoten,lopHC,SDT,diachi,khoa);
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if(IDThemTT.length()>0 && hoten.length()>0 && lopHC.length()>0 && SDT.length()>0 && diachi.length()>0 && khoa.length()>0 ){
                    Toast.makeText(ThemThongTinBanDocActivity.this, "Nhập thông tin thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(ThemThongTinBanDocActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    private void Init() {
        //anh xa id image btnback
        btnback = findViewById(R.id.btnback);
        // anh xa id btn nhap them tt ban doc
        btnthemthongtinbd = findViewById(R.id.btnthemthongtinbd);
        // anh xa id them thong tin
        edtmabandocttt = findViewById(R.id.edtmabandocttt);
        edthotenttt = findViewById(R.id.edthotenttt);
        edtlopttt = findViewById(R.id.edtlopttt);
        edtsdtttt = findViewById(R.id.edtsdtttt);
        edtdiachittt = findViewById(R.id.edtdiachittt);
        edtkhoavienttt = findViewById(R.id.edtkhoavienttt);
    }
}