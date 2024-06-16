package com.example.quanlythuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button managementBookCataloging, managementBookSummary, managementBooks;

    private Button btnMsTC, btnTsTC, btnDtTC, btnPsTC;
    private Button btncapthe, btnthuhoithe, btncapnhatthongtinbd, btnthemthongtinbd;
    private Button btnxemThongtin, btndangkyms, btntrasach, btnthanhtoan, btnlogout;
    private SharedPreferences sharedPreferences;
    private String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        setViewInPosition();
        onClickChangePage();
    }

    private void setViewInPosition() {
        if(position.equals("Thủ kho sách")){
            managementBookCataloging.setVisibility(View.VISIBLE);
            managementBooks.setVisibility(View.VISIBLE);
            managementBookSummary.setVisibility(View.VISIBLE);
        }else if(position.equals("Thủ thư")){
            btnMsTC.setVisibility(View.VISIBLE);
            btnTsTC.setVisibility(View.VISIBLE);
            btnDtTC.setVisibility(View.VISIBLE);
            btnPsTC.setVisibility(View.VISIBLE);
            btnthanhtoan.setVisibility(View.VISIBLE);
        }else if(position.equals("Quản lí thẻ")){
            btncapthe.setVisibility(View.VISIBLE);
            btnthuhoithe.setVisibility(View.VISIBLE);
            btnthemthongtinbd.setVisibility(View.VISIBLE);
            btncapnhatthongtinbd.setVisibility(View.VISIBLE);
        }else{
            btnxemThongtin.setVisibility(View.VISIBLE);
            btndangkyms.setVisibility(View.VISIBLE);
            btntrasach.setVisibility(View.VISIBLE);
        }
    }

    private void Init() {
        sharedPreferences = getSharedPreferences("loginData",MODE_PRIVATE);
        position = sharedPreferences.getString("Position","");
        managementBookCataloging = findViewById(R.id.main_managementBookCataloging);
        managementBookSummary = findViewById(R.id.main_managementBookSummary);
        managementBooks = findViewById(R.id.main_managementBooks);

        btnMsTC = findViewById(R.id.btnMsTC);
        btnTsTC = findViewById(R.id.btnTsTC);
        btnDtTC = findViewById(R.id.btnDtTC);
        btnPsTC = findViewById(R.id.btnPsTC);


        btncapthe = findViewById(R.id.btncapthett);
        btnthuhoithe = findViewById(R.id.btnthuhoithett);
        btnthemthongtinbd = findViewById(R.id.btnthemthongtinbd);
        btncapnhatthongtinbd = findViewById(R.id.btncapnhatthongtinbdtt);


        btnxemThongtin = findViewById(R.id.btnxemThongtin);
        btndangkyms = findViewById(R.id.btndangkyms);
        btntrasach = findViewById(R.id.btntrasach);
        btnthanhtoan = findViewById(R.id.btnthanhtoan);
        btnlogout = findViewById(R.id.btnlogout);
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
                Intent myintent = new Intent(MainActivity.this, TrangChuQlyTraSach.class);
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


        btncapthe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CapTheBanDocActivity.class);
                startActivity(intent);
            }
        });
        btnthuhoithe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThuHoiTheBanDocActivity.class);
                startActivity(intent);
            }
        });
        btnthemthongtinbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThemThongTinBanDocActivity.class);
                startActivity(intent);
            }
        });
        btncapnhatthongtinbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CapNhatThongTinBanDocActivity.class);
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
                //khai báo intent cho phép chuyển giao diện từ giao diện trang chủ đến giao diện đăng nhâp
                Intent myintent = new Intent(MainActivity.this, Login.class);
                startActivity(myintent);
                Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
