package com.example.quanlythuvien;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quanlythuvien.DataManagement.VTraSach;
import com.example.quanlythuvien.SQLHepler.SQLManagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.BreakIterator;

public class docthuc extends AppCompatActivity {
    ImageButton btnBackDT;
    EditText idDocThuc,idBanDocDT,idSachDT,idPhieuMuonDT,idBienMucDT,hanTraDT,ghiChuDT;
    Button btnGuiDT,btnXoaDT,btnXemDT,btnSuaDT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_docthuc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //ánh xạ id
        btnBackDT=findViewById(R.id.btnBackDT);
        idDocThuc=findViewById(R.id.idDocThuc);
        idBanDocDT=findViewById(R.id.idBanDocDT);
        idSachDT=findViewById(R.id.idSachDT);
        idPhieuMuonDT=findViewById(R.id.idPhieuMuonDT);
        idBienMucDT=findViewById(R.id.idBienMucDT);
        hanTraDT=findViewById(R.id.hanTraDT);
        ghiChuDT=findViewById(R.id.ghiChuDT);
        btnGuiDT=findViewById(R.id.btnGuiDT);
        btnXoaDT=findViewById(R.id.btnXoaDT);
        btnXemDT=findViewById(R.id.btnXemDT);
        btnSuaDT=findViewById(R.id.btnSuaDT);


        //hàm xử lý sự kiện khi click vào nút quay lại
        btnBackDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //khởi tạo đối tượng intent cho phép chuuyển giao diện
                Intent myintent = new Intent(docthuc.this, MainActivity.class);
                startActivity(myintent);
            }
        });


        //hàm xử lý sự kiện khi click vào nut gửi đốc thúc
        btnGuiDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nối với csdl
                Connection connection= SQLManagement.connectionSQLSever();
                String MaMS = idPhieuMuonDT.getText().toString();
                String MaSach = idSachDT.getText().toString();
                String MaBienMuc = idBienMucDT.getText().toString();
                VTraSach vTraSach = new VTraSach();
                Toast.makeText(docthuc.this, "Gửi thành công", Toast.LENGTH_SHORT).show();
                try {
                    vTraSach = VTraSach.getuserlist1(MaSach,MaBienMuc,MaMS);
                    if(connection!=null){
                        if(vTraSach.getidSach().equals(MaSach) && vTraSach.getidBookCataloging().equals(MaBienMuc) && vTraSach.getidMuonSach().equals(MaMS)) {
                            BreakIterator id;
                            //khởi tạo câu lệnh sql thêm thông tin vào bảng ThongTinDocThuc
                            String sqlThem = "insert into ThongTinDocThuc values ('" +
                                    idDocThuc.getText().toString() + "','" +
                                    idBanDocDT.getText().toString() + "','" +
                                    idSachDT.getText().toString() + "','" +
                                    idBienMucDT.getText().toString() + "','" +
                                    idPhieuMuonDT.getText().toString() + "','" +
                                    hanTraDT.getText().toString() + "','" +
                                    ghiChuDT.getText().toString() + "')";
                            //khởi tạo đối tượng statement
                            Statement st = connection.createStatement();
                            //thực thi câu lệnh sql
                            ResultSet rs = st.executeQuery(sqlThem);
                        }else{
                            Toast.makeText(docthuc.this, "Thông tin ID vừa nhập không chính xác", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (SQLException exception) {
                    Log.e("Errol",exception.getMessage());
                }
            }
        });


        //hàm xử lý sự kiện khi click vào nut sửa đốc thúc
        btnSuaDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nối với csdl
                Connection connection=SQLManagement.connectionSQLSever();
                String MaMS = idPhieuMuonDT.getText().toString();
                String MaSach = idSachDT.getText().toString();
                String MaBienMuc = idBienMucDT.getText().toString();
                VTraSach vTraSach = new VTraSach();
                Toast.makeText(docthuc.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                try {
                    vTraSach = VTraSach.getuserlist1(MaSach,MaBienMuc,MaMS);
                    if(connection!=null){
                        if(vTraSach.getidSach().equals(MaSach) && vTraSach.getidBookCataloging().equals(MaBienMuc) && vTraSach.getidMuonSach().equals(MaMS)) {
                            BreakIterator id;
                            //khởi tạo câu lệnh sql sưa thông tin trong bảng ThongTinDocThuc
                            String sqlSua = "update ThongTinDocThuc set IDBANDOC='" + idBanDocDT.getText().toString() + "',IdBooks='" + idSachDT.getText().toString() + "',IDTTMUONSACH='" + idPhieuMuonDT.getText().toString() + "',IdBookCataloging='" + idBienMucDT.getText().toString() + "',HanTra='" + hanTraDT.getText().toString() + "',GhiChu='" + ghiChuDT.getText().toString() + "'where IDDOCTHUC = '" + idDocThuc.getText().toString() + "'";
                            //khởi tạo đối tượng statement
                            Statement st = connection.createStatement();
                            //thực thi câu lệnh sql
                            ResultSet rs = st.executeQuery(sqlSua);
                        }else{
                            Toast.makeText(docthuc.this, "Thông tin ID vừa nhập không chính xác", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (SQLException exception) {
                    Log.e("Errol",exception.getMessage());
                }
            }
        });

        //hàm xử lý sự kiện khi click vào nut xóa đốc thúc
        btnXoaDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nối với csdl
                Connection connection=SQLManagement.connectionSQLSever();
                Toast.makeText(docthuc.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                try {
                    if(connection!=null){
                        BreakIterator id;
                        //khởi tạo câu lệnh sql xóa thông tin trong bảng ThongTinDocThuc
                        String sqlXoa="delete ThongTinDocThuc where IDDOCTHUC = '"+idDocThuc.getText().toString()+"'";
                        //khởi tạo đối tượng statement
                        Statement st=connection.createStatement();
                        //thực thi câu lệnh sql
                        ResultSet rs=st.executeQuery(sqlXoa);
                    }
                } catch (SQLException exception) {
                    Log.e("Errol",exception.getMessage());
                }
            }
        });


        //hàm xử lý sự kiện khi click vào nut sửa đốc thúc
        btnXemDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //khởi tạo đối tượng intent cho phép chuuyển giao diện
                Intent myintent = new Intent(docthuc.this, XemThongTinDocThuc.class);
                startActivity(myintent);
            }
        });
    }
}