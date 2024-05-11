package com.example.assignnment.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignnment.R;
import com.example.assignnment.dao.LoaiSaDAO;
import com.example.assignnment.model.ItemClick;
import com.example.assignnment.model.LoaiSach;
import com.example.assignnment.model.LoaiSachh;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHoder>{

    public Context context;
    private ArrayList<LoaiSachh> list;
    private ItemClick itemClick;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSachh> list,ItemClick itemClick) {
        this.context = context;
        this.list = list;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_loaisach,parent,false);


        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
     holder.txtTenLoai.setText("Ten loai:"+list.get(position).getTenLoai());
     holder.txtMaLoai.setText("Ma loai"+ String.valueOf(list.get(position).getId()));

     holder.ivDel.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             LoaiSaDAO loaiSaDAO = new LoaiSaDAO(context);
             int check = loaiSaDAO.xoaLoaiSach(list.get(holder.getAdapterPosition()).getId());
          switch (check){
              case  1:
                  list.clear();
                  list = loaiSaDAO.getDSLoaiSach();
                  notifyDataSetChanged();
                  break;
              case  -1:
                  Toast.makeText(context, "Khong the xoa loai sach nay", Toast.LENGTH_SHORT).show();
            break;
              case 0:
                  Toast.makeText(context, "xoa ko thanh cong", Toast.LENGTH_SHORT).show();
                  break;
              default:
                  break;
          }
         
         }
     });
     holder.ivEdit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             LoaiSachh loaiSachh = list.get(holder.getAdapterPosition());
             itemClick.onClickLoaiSach(loaiSachh);
         }
     });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        TextView txtMaLoai, txtTenLoai;
        ImageView ivDel,ivEdit;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            txtMaLoai = itemView.findViewById(R.id.txtMaLoai);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);

            ivDel = itemView.findViewById(R.id.ivDel);
            ivEdit = itemView.findViewById(R.id.ivEdit);

        }
    }
}
