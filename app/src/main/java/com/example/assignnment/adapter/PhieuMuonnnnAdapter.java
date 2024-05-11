package com.example.assignnment.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignnment.R;
import com.example.assignnment.dao.PhieuMuonnnDAO;
import com.example.assignnment.model.PhieuMuonnnnnn;

import java.util.ArrayList;

public class PhieuMuonnnnAdapter extends RecyclerView.Adapter<PhieuMuonnnnAdapter.ViewHolder>{

    private ArrayList<PhieuMuonnnnnn> list;
    private Context context;

    public PhieuMuonnnnAdapter(ArrayList<PhieuMuonnnnnn> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycle_phieumuon,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.txtMaPM.setText("Ma PM:" + list.get(position).getMaPM());
        holder.txtMaTV.setText("Ma TV:" + list.get(position).getMaTV());
        holder.txtTenTV.setText("Ten TV:" + list.get(position).getTentv());
        holder.txtMaTT.setText("Ma TT:" + list.get(position).getMaTT());
        holder.txtTenTT.setText("Ten TT:" + list.get(position).getTentt());
        holder.txtMaSach.setText("Ma Sach:" + list.get(position).getMaSach());
        holder.txtTenSach.setText("Ten Sach:" + list.get(position).getTenSach());
        holder.txtNgay.setText("Ngay:" + list.get(position).getNgay());
        String trangthai = "";
        if (list.get(position).getTraSach() == 1){
            trangthai = "Da tra sach";
            holder.btnTraSach.setVisibility(View.GONE);

        }else {
            trangthai = "Chua Tra Sach";
            holder.btnTraSach.setVisibility(View.VISIBLE);
        }
        holder.txtTrangThai.setText("Trang Thai:" + trangthai);

        holder.txtTien.setText("Tien Thue:" + list.get(position).getTienThue());

        holder.btnTraSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhieuMuonnnDAO phieuMuonnnDAO =new PhieuMuonnnDAO(context);
                boolean kiemtra = phieuMuonnnDAO.thayDoiTrangThai(list.get(holder.getAdapterPosition()).getMaPM());
             if (kiemtra){
                 list.clear();
                 list = phieuMuonnnDAO.getDSPhieuMuon();
                 notifyDataSetChanged();
             }else{
                 Toast.makeText(context, "Tra Sach khong thanh cong", Toast.LENGTH_SHORT).show();
             }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder {


        private TextView txtMaPM,txtMaTV,txtTenTV,txtMaTT, txtTenTT,txtMaSach,txtTenSach,txtNgay,txtTrangThai,txtTien;
        private Button btnTraSach;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaPM = itemView.findViewById(R.id.txtMaPM);
            txtMaTV = itemView.findViewById(R.id.txtMaTV);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);

            txtTenTV = itemView.findViewById(R.id.txtTenTV);
            txtMaTT = itemView.findViewById(R.id.txtMaTT);
            txtTenTT = itemView.findViewById(R.id.txtTenTT);
            txtMaSach = itemView.findViewById(R.id.txtMaSach);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtTrangThai = itemView.findViewById(R.id.txTrangThai);
            txtTien = itemView.findViewById(R.id.txtTienThue);
            btnTraSach = itemView.findViewById(R.id.btnTraSach);

        }
    }
}
