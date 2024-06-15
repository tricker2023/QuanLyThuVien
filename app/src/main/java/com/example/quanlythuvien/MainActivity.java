package com.example.quanlythuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button managementBookCataloging,managementBookSummary,managementBooks;
    Button btnMsTC,btnTsTC,btnDtTC,btnPsTC,btnDxTC;
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
        //hàm xử lý sự kiện khi click vào nút quản lý mượn sách
        btnMsTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //khai báo intent cho phép chuyển giao diện từ giao diện trang chủ đến giao diện quản lý mượn sách
                Intent myintent = new Intent(MainActivity.this, TrangChuQlyMuonSach.class);
                startActivity(myintent);
            }
        });

        //hàm xử lý sự kiện khi click vào nút quản lý trả sách
        btnTsTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //khai báo intent cho phép chuyển giao diện từ giao diện trang chủ đến giao diện quản lý trả sách
                Intent myintent = new Intent(MainActivity.this,TrangChuQlyTraSach.class);
                startActivity(myintent);
            }
        });

        //hàm xử lý sự kiện khi click vào nút đốc thúc hạn trả
        btnDtTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //khai báo intent cho phép chuyển giao diện từ giao diện trang chủ đến giao diện đốc thúc hạn trả
                Intent myintent = new Intent(MainActivity.this, docthuc.class);
                startActivity(myintent);
            }
        });
        // hàm xử lý sự kiện khi click vào nút phạt sách trả muộn
        btnPsTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //khai báo intent cho phép chuyển giao diện từ giao diện trang chủ đến giao diện phạt sách trả muộn
                Intent myintent = new Intent(MainActivity.this, phatTraSachMuon.class);
                startActivity(myintent);
            }
        });

        // hàm xử lý sự kiện khi click vào nút đăng xuất
        btnDxTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //khai báo intent cho phép chuyển giao diện từ giao diện trang chủ đến giao diện đăng nhâp
                Intent myintent = new Intent(MainActivity.this, Login.class);
                startActivity(myintent);
                Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Init() {
        managementBookCataloging = findViewById(R.id.main_managementBookCataloging);
        managementBookSummary = findViewById(R.id.main_managementBookSummary);
        managementBooks = findViewById(R.id.main_managementBooks);
        btnMsTC=findViewById(R.id.btnMsTC);
        btnTsTC=findViewById(R.id.btnTsTC);
        btnDtTC=findViewById(R.id.btnDtTC);
        btnPsTC=findViewById(R.id.btnPsTC);
        btnDxTC=findViewById(R.id.btnDxTC);
    }
}