package com.example.quanlythuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.quanlythuvien.DataManagement.PayMoney;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLException;

public class ThanhToanActivity extends AppCompatActivity {
    // khai báo các id
    ImageButton btnback;
    TextInputEditText idthanhtoan,idsach,idmuonsach,idtrasach,edttongngaymuon,edtthanhtoan,edtbosung;
    Button btnnhaptt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        Init();
        onClickChangePage();


        // xu ly click back
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // trở lại giao diện trước
                finish();
            }
        });
    }

    private void onClickChangePage() {
        btnnhaptt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maThanhToan = idthanhtoan.getText().toString();
                String maSach = idsach.getText().toString();
                String maMuonSach = idmuonsach.getText().toString();
                String maTraSach = idtrasach.getText().toString();
                int tongNgayMuon = Integer.parseInt(edttongngaymuon.getText().toString());
                double thanhToan = Double.parseDouble(edtthanhtoan.getText().toString());
                String bosung = edtbosung.getText().toString();
                try {
                    PayMoney.insertThanhToan(maThanhToan,maSach,maMuonSach,maTraSach,tongNgayMuon,thanhToan,bosung);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if(maThanhToan.length()>0 && maSach.length()>+0 && maMuonSach.length()>0 && maTraSach.length()>0 && tongNgayMuon>0 && thanhToan>0 && bosung.length() != 0){
                    Toast.makeText(ThanhToanActivity.this, "Nhập thanh toán thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(ThanhToanActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Init() {
        // anh xa id
        btnback = findViewById(R.id.btnback); // anh xa id cho nút back
        idthanhtoan = findViewById(R.id.idthanhtoan); // anh xa id form nhập mã thanh toán
        idsach = findViewById(R.id.idsach); // anh xạ id cho form nhập mã sách
        idmuonsach = findViewById(R.id.idmuonsach); // ánh xạ id cho form nhập mã mượn sách
        idtrasach = findViewById(R.id.idtrasach); // ánh xạ id cho form nhập mã trả sách
        edttongngaymuon = findViewById(R.id.edttongngaymuon); // ánh xạ id cho form ngày tổng mượn sách
        edtthanhtoan = findViewById(R.id.edtthanhtoan); // ánh xạ id cho form thanh toán
        edtbosung = findViewById(R.id.edtbosung); // ánh xạ id cho form nhập lưu ý
        btnnhaptt = findViewById(R.id.btnnhaptt);// ánh xạ id cho button nhập thanh toán
    }
}