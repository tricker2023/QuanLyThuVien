package com.example.quanlythuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.quanlythuvien.AdapterManagement.AdapterBook;
import com.example.quanlythuvien.DataManagement.BookCatalogings;
import com.example.quanlythuvien.DataManagement.BookSummarys;
import com.example.quanlythuvien.DataManagement.Books;

import java.sql.SQLException;
import java.util.ArrayList;

public class ListViewSachActivity extends AppCompatActivity {
    SearchView searchView;
    ListView list_view;
    private ArrayList<Books> arrayList;
    private ArrayList<BookCatalogings> catalogingsArrayList;
    private ArrayList<BookSummarys> summarysArrayList;
    private AdapterBook adapterBook;
    private TextView noData;
    private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_sach);
        Init();
        setClickSearchView(); // hàm cài đặt tìm kiếm
        changePageBack(); // hàm quay lại màn hình chính
    }
    // hàm cài đặt tìm kiếm
    private void setClickSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }
    private void changePageBack() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToPage(); // quay lại trang quản lí sách
            }
        });
    }
    private void changeToPage(){
        // quay lại màn hình chính
        Intent intent = new Intent(ListViewSachActivity.this,MainActivity.class);
        startActivity(intent);

    }

    // hàm tìm sách có cùng kí tự với tìm kiếm
    private void filterList(String newText) {
        ArrayList<Books> booksArrayList = new ArrayList<>();
        for(Books books : arrayList){
            if(books.getIdBooks().toString().toLowerCase().contains(newText.toString().toLowerCase())){
                booksArrayList.add(books);
            }
        }

        // hàm set hiển thị lại danh sách
        if(booksArrayList.isEmpty()){
            list_view.setVisibility(View.GONE);
            noData.setVisibility(View.VISIBLE);
        }else{
            adapterBook.setFilterList(booksArrayList);
            list_view.setVisibility(View.VISIBLE);
            noData.setVisibility(View.GONE);
        }
    }



    private void Init() {
        list_view = findViewById(R.id.list_view);
        noData = findViewById(R.id.noData);
        back = findViewById(R.id.back);
        try {
            catalogingsArrayList = BookCatalogings.getuserlist();
            summarysArrayList = BookSummarys.getuserlist();
            arrayList = Books.getuserlist();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        adapterBook = new AdapterBook(this,arrayList,true);
        Log.e("DATA",arrayList.get(0).getIdBooks());
        list_view.setAdapter(adapterBook);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
    }
}