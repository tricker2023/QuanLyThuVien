package com.example.quanlythuvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlythuvien.AdapterManagement.AdapterBook;
import com.example.quanlythuvien.DataManagement.BookCatalogings;
import com.example.quanlythuvien.DataManagement.BookSummarys;
import com.example.quanlythuvien.DataManagement.Books;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookManagement extends AppCompatActivity {

    // tạo các biến
    private ListView listView;
    private ArrayList<Books> arrayList;
    private TextView noData;
    private Button buttonAddBook;
    private ImageButton backPage;
    private ArrayList<BookCatalogings> catalogingsArrayList;
    private ArrayList<BookSummarys> summarysArrayList;
    private AdapterBook adapterBook;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_management);
        Init(); // hàm khởi tạo
        changePageAddBook(); // hàm chuyển màn hình sang thêm sách
        setNoData(); // hàm xử lí hiển thị khi listview rỗng
        setClickSearchView(); // hàm cài đặt tìm kiếm
    }

    // hàm chuyển màn hình ang thêm sách
    private void changePageAddBook() {
        buttonAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(catalogingsArrayList.size() > 0 && summarysArrayList.size() > 0){
                    Intent intent = new Intent(BookManagement.this,AddBook.class);
                    startActivity(intent);
                }else if(catalogingsArrayList.size() == 0){
                    Toast.makeText(BookManagement.this, "Bạn chưa có biên mục sách nào", Toast.LENGTH_SHORT).show();
                }
                else if(summarysArrayList.size() == 0){
                    Toast.makeText(BookManagement.this, "Bạn chưa có tóm tắt sách nào", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // set sự kiện cho nút back
        backPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookManagement.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    // hàm xử lí hiển thị khi listview rỗng
    private void setNoData() {
        if(arrayList.size() == 0){
            listView.setVisibility(View.GONE);
            noData.setVisibility(View.VISIBLE);
        }else{
            listView.setVisibility(View.VISIBLE);
            noData.setVisibility(View.GONE);
        }
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
            listView.setVisibility(View.GONE);
            noData.setVisibility(View.VISIBLE);
        }else{
            adapterBook.setFilterList(booksArrayList);
            listView.setVisibility(View.VISIBLE);
            noData.setVisibility(View.GONE);
        }
    }

    private void Init() {
        // ánh xạ view
        listView = findViewById(R.id.bookManagement_listView);
        noData = findViewById(R.id.bookManagement_noData);
        buttonAddBook = findViewById(R.id.bookManagement_buttonAddBook);
        backPage = findViewById(R.id.bookManagement_back);
        try {
            catalogingsArrayList = BookCatalogings.getuserlist();
            summarysArrayList = BookSummarys.getuserlist();
            arrayList = Books.getuserlist();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        adapterBook = new AdapterBook(this,arrayList);
        listView.setAdapter(adapterBook);
        searchView = findViewById(R.id.bookManagement_searchView);
        searchView.clearFocus();
    }
}