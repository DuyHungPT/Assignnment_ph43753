package com.example.assignnment.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assignnment.R;
import com.example.assignnment.dao.SachDAO;
import com.example.assignnment.dao.ThanhVienDAO;
import com.example.assignnment.fragment.PhieuMuonFragment;
import com.example.assignnment.model.PhieuMuon;
import com.example.assignnment.model.Sach;
import com.example.assignnment.model.ThanhVien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {

    private Context context;
    PhieuMuonFragment fragment;
    private ArrayList<PhieuMuon> lists;
    TextView tvMaPM, tvTenTV, tvTenSach,tvTienThue,tvNgay,tvTraSach;
    ImageView imgDel;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");

    public PhieuMuonAdapter(@NonNull Context context,PhieuMuonFragment fragment, ArrayList<PhieuMuon> lists) {
        super(context, 0,lists);
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
           v = inflater.inflate(R.layout.phieumuon_item,null);

       }
       final  PhieuMuon item = lists.get(position);
       if (item != null){
       tvMaPM = v.findViewById(R.id.tvMaPM);
       tvMaPM.setText("Ma Phieu"+ item.getMaPM());

       sachDAO = new SachDAO(context);
           Sach sach = sachDAO.getID(String.valueOf(item.getMaSach()));
           tvTenSach = v.findViewById(R.id.tvTenSach);
           tvTenSach.setText("Ten Sach"+sach.getTenSach());
           thanhVienDAO = new ThanhVienDAO(context);
           ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(item.getMaTV()));
           tvTenTV = v.findViewById(R.id.tvTenTV);
           tvTenTV.setText("Thanh Vien"+thanhVien.getHoTen());
           tvTienThue = v.findViewById(R.id.tvTienThue);
           tvTienThue.setText("Tien Thue"+item.getTienThue());
           tvNgay = v.findViewById(R.id.tvNgayPM);
           tvNgay.setText("Ngay thue"+ sdf.format(item.getNgay()));
           tvTraSach = v.findViewById(R.id.tvTraSach);

           if (item.getTraSach() == 1){
               tvTraSach.setTextColor(Color.BLUE);
               tvTraSach.setText("Da Tra Sach");

           }else {
               tvTraSach.setTextColor(Color.RED);
               tvTraSach.setText("Chua Tra Sach");
           }
       imgDel = v.findViewById(R.id.imgDeleteLS);

       }
       imgDel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
         fragment.xoa(String.valueOf(item.getMaPM()));
           }
       });
       return v;

    }
}
