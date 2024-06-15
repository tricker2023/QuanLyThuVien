package com.example.quanlythuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_sach);
        Init();
    }

    private void Init() {
        list_view = findViewById(R.id.bookManagement_listView);
        try {
            catalogingsArrayList = BookCatalogings.getuserlist();
            summarysArrayList = BookSummarys.getuserlist();
            arrayList = Books.getuserlist();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        adapterBook = new AdapterBook(this,arrayList);
        list_view.setAdapter(adapterBook);
        searchView = findViewById(R.id.bookManagement_searchView);
        searchView.clearFocus();
    }
}