package com.example.assignnment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assignnment.R;
import com.example.assignnment.fragment.ThanhVienFragment;
import com.example.assignnment.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    ThanhVienFragment fragment;
    private ArrayList<ThanhVien> lists;
    TextView tvMaTV,tvTenTV,tvNamSinh;
   ImageView imgDel;

    public ThanhVienAdapter(@NonNull Context context, ThanhVienFragment fragment, ArrayList<ThanhVien> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanhvien_item,null);
        }
        final ThanhVien item = lists.get(position);
       if (item != null){
           tvMaTV = v.findViewById(R.id.tvMaTV);
           tvMaTV.setText("Ma thanh vien:" + item.getMaTV());

           tvTenTV = v.findViewById(R.id.tvTenTV);
           tvTenTV.setText("Ten thanh vien:" + item.getHoTen());

           tvNamSinh = v.findViewById(R.id.tvNamSinh);
           tvNamSinh.setText("Nam Sinh:" + item.getNamSinh());

           imgDel = v.findViewById(R.id.imgDeleteLs);

       }
     imgDel.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
       fragment.xoa(String.valueOf(item.getMaTV()));
         }
     });

       return v;

    }
}
