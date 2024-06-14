package com.example.quanlythuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button managementBookCataloging,managementBookSummary,managementBooks;
    private Button btnxemThongtin,btndangkyms,btntrasach,btnthanhtoan,btnlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        onClickChangePage();
    }
    private void onClickChangePage() {
        managementBookCataloging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookCatalogingManagement.class);
                startActivity(intent);
            }
        });
        managementBookSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookSummaryManagement.class);
                startActivity(intent);
            }
        });
        managementBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookManagement.class);
                startActivity(intent);
            }
        });
        // xu ly click button xem thong tin sach
        btnxemThongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent xemttintent = new Intent(MainActivity.this, ListViewSachActivity.class); // khoi tao Intent xem thong tin
                startActivity(xemttintent); // bat dau intent
            }
        });
        // xu ly click button dang ky muon sach
        btndangkyms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dangkymsintent = new Intent(MainActivity.this, NhapMuonSachActivity.class); // khoi tao Intent dang ky muon sach
                startActivity(dangkymsintent); // bat dau Intent
            }
        });
        // xu ly click button tra sach
        btntrasach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trasachintent = new Intent(MainActivity.this, TraSachActivity.class);  // khoi tao Intent tra sach
                startActivity(trasachintent);
            }
        });
        // xu ly click button thanh toan
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thanhtoanintent = new Intent(MainActivity.this, ThanhToanActivity.class); // khoi tao Intent thanh toan
                startActivity(thanhtoanintent);
            }
        });

        // xử lý click button đăng xuất
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void Init() {
        managementBookCataloging = findViewById(R.id.main_managementBookCataloging);
        managementBookSummary = findViewById(R.id.main_managementBookSummary);
        managementBooks = findViewById(R.id.main_managementBooks);
        btnxemThongtin = findViewById(R.id.btnxemThongtin);
        btndangkyms = findViewById(R.id.btndangkyms);
        btntrasach = findViewById(R.id.btntrasach);
        btnthanhtoan = findViewById(R.id.btnthanhtoan);
        btnlogout = findViewById(R.id.btnlogout);
    }
}