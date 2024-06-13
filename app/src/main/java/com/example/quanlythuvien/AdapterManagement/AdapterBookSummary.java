package com.example.quanlythuvien.AdapterManagement;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.quanlythuvien.AddBookSummary;
import com.example.quanlythuvien.DataManagement.BookSummarys;
import com.example.quanlythuvien.R;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdapterBookSummary extends BaseAdapter {
    Context context;
    ArrayList<BookSummarys> arrayList;
    LayoutInflater layoutInflater;
    public AdapterBookSummary(Context context,ArrayList<BookSummarys> arrayList){
        this.context = context;
        this.arrayList = arrayList;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }


    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.item_book_summary,null);
        // ánh xạ
        TextView textView = convertView.findViewById(R.id.itemBookSummary_textView);
        ImageButton imageButton = convertView.findViewById(R.id.itemBookSummary_imageButton);

        BookSummarys bookSummarys = arrayList.get(position); // tạo đối tượng


        textView.setText(bookSummarys.getIdBookSummary());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // tạo dialog và set layout
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_supsumary);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                // ánh xạ
                ImageButton back = dialog.findViewById(R.id.dialogSupSummary_back);
                TextView IdBookSummary = dialog.findViewById(R.id.dialogSupSummary_idBookSummary);
                TextView MainContent = dialog.findViewById(R.id.dialogSupSummary_mainContent);
                TextView Meaning = dialog.findViewById(R.id.dialogSupSummary_meaning);
                TextView CommunicationGoals = dialog.findViewById(R.id.dialogSupSummary_communicationGoals);

                // set giá trị
                IdBookSummary.setText(bookSummarys.getIdBookSummary());
                MainContent.setText(bookSummarys.getMainContent());
                Meaning.setText(bookSummarys.getMeaning());
                CommunicationGoals.setText(bookSummarys.getCommunicationGoals());
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    } // ẩn dialog
                });
                dialog.show();
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, imageButton); // tạo popupmenu
                popupMenu.getMenuInflater().inflate(R.menu.menu_edit,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.menuEdit){ // hàm sử lí sự kiện sửa
                            Intent intent = new Intent(context, AddBookSummary.class);
                            // gửi giá trị thông qua intent
                            intent.putExtra("initData",true);
                            intent.putExtra("IdBookSummary",bookSummarys.getIdBookSummary());
                            context.startActivity(intent);

                        }else if(item.getItemId() == R.id.menuDelete){ // hàm sử lí sự kiện xóa
                            try {
                                BookSummarys.deleteList(bookSummarys.getIdBookSummary()); // xóa dữ liệu trong SQL
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            arrayList.remove(position); // xóa dữ liệu lại vị trí position
                            notifyDataSetChanged(); // cập nhật dữ liệu hiển thị
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        return convertView;
    }
    public void setFilterList(ArrayList<BookSummarys> filterList){
        this.arrayList = filterList; // cập nhật giá trị với cho danh sách
        notifyDataSetChanged(); // cập nhật dữ liệu hiển thị
    }
}
