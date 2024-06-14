package com.example.quanlythuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class XemThongTinBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_thong_tin_book);
        //khai báo các id
        ImageButton btnback;
        TextView twmasach,twtensach,twtentacgia,twtieude,twnhaxuatban,twnamxuatban,twngonngu,twtheloai,twgiathue,twtinhtrang,twdanhgia;
            // anh xa id
            twmasach = findViewById(R.id.twmasach); // anh xa id cho text mã sách
            twtensach = findViewById(R.id.twtensach); // anh xa id cho text tên sách
            twtentacgia = findViewById(R.id.twtentacgia); // anh xa id cho text tên giả
            twtieude = findViewById(R.id.twtieude); // anh xa id cho text tiêu đề
            twnhaxuatban = findViewById(R.id.twnhaxuatban); // anh xa id cho text nhà xuất bản
            twnamxuatban = findViewById(R.id.twnamxuatban); // anh xa id cho text năm xuất bản
            twngonngu = findViewById(R.id.twngonngu); // anh xa id cho text ngôn ngữ
            twtheloai = findViewById(R.id.twtheloai);  // anh xa id cho text thể loại
            twgiathue = findViewById(R.id.twgiathue); // anh xa id cho text giá thuê
            twtinhtrang = findViewById(R.id.twtinhtrang); // anh xa id cho text tình trạng
            twdanhgia = findViewById(R.id.twdanhgia); // anh xa id cho text đánh giá
            btnback = findViewById(R.id.btnback);

            // xử lý click button back
            btnback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
    }
}