package com.example.quanlythuvien.AdapterManagement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlythuvien.AddBookSummary;
import com.example.quanlythuvien.DataManagement.BookSummarys;
import com.example.quanlythuvien.R;

import java.util.List;

public class AdapterSpinnerSummary extends ArrayAdapter<BookSummarys> {
    public AdapterSpinnerSummary(@NonNull Context context, int resource, @NonNull List<BookSummarys> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected,parent,false);
        // ánh xạ
        TextView title = convertView.findViewById(R.id.itemSelected_text);

        BookSummarys bookSummarys = this.getItem(position); // tạo và set giá trị cho đối tượng
        if(bookSummarys != null){
            if(bookSummarys.getIdBookSummary().equals("Thêm tóm tắt")){
                // chuyển màn hình
                Intent intent = new Intent(getContext(), AddBookSummary.class);
                getContext().startActivity(intent);
            }else{
                title.setText(bookSummarys.getIdBookSummary()); // set giá trị cho title
            }
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner,parent,false);
        // ánh xạ
        TextView title = convertView.findViewById(R.id.itemSpinner_title);

        BookSummarys bookSummarys = this.getItem(position); // tạo và set giá trị cho đối tượng
        if(bookSummarys != null){
            title.setText(bookSummarys.getIdBookSummary()); // set giá trị cho textview
        }
        return convertView;
    }
}
