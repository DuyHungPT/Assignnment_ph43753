package com.example.assignnment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assignnment.R;
import com.example.assignnment.model.LoaiSach;
import com.example.assignnment.model.Sach;
import com.example.assignnment.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienSpinerAdapter extends ArrayAdapter<ThanhVien> {

    Context context;
    ArrayList<ThanhVien> lists;
    TextView tvMaTV, tvTenTV;

    public ThanhVienSpinerAdapter(@NonNull Context context, ArrayList<ThanhVien> lists) {
        super(context, 0,lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanh_vien_item_spiner, null);
        }
        final ThanhVien item = lists.get(position);
        if (item != null){
            tvMaTV = v.findViewById(R.id.tvMaTVSP);
            tvMaTV.setText(item.getMaTV()+". ");
            tvTenTV = v.findViewById(R.id.tvTenTVSP);
            tvTenTV.setText(item.getHoTen());

        }
        return  v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanh_vien_item_spiner, null);
        }
        final ThanhVien item = lists.get(position);
        if (item != null){
            tvMaTV = v.findViewById(R.id.tvMaTVSP);
            tvMaTV.setText(item.getMaTV()+". ");
            tvTenTV = v.findViewById(R.id.tvTenTVSP);
            tvTenTV.setText(item.getHoTen());

        }
        return  v;
    }
}
