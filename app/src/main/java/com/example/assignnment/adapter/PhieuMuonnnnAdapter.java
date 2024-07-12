package com.example.assignnment.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignnment.R;
import com.example.assignnment.dao.PhieuMuonnnDAO;
import com.example.assignnment.model.PhieuMuonnnnnn;

import java.util.ArrayList;

public class PhieuMuonnnnAdapter extends RecyclerView.Adapter<PhieuMuonnnnAdapter.ViewHolder> {

    private ArrayList<PhieuMuonnnnnn> list;
    private Context context;
    private OnUpdateClickListener onUpdateClickListener;

    public PhieuMuonnnnAdapter(ArrayList<PhieuMuonnnnnn> list, Context context, OnUpdateClickListener onUpdateClickListener) {
        this.list = list;
        this.context = context;
        this.onUpdateClickListener = onUpdateClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_phieumuon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhieuMuonnnnnn phieuMuon = list.get(position);

        holder.txtMaPM.setText("Mã PM: " + phieuMuon.getMaPM());
        holder.txtMaTV.setText("Mã TV: " + phieuMuon.getMaTV());
        holder.txtTenTV.setText("Tên TV: " + phieuMuon.getTentv());
        holder.txtMaTT.setText("Mã TT: " + phieuMuon.getMaTT());
        holder.txtTenTT.setText("Tên TT: " + phieuMuon.getTentt());
        holder.txtMaSach.setText("Mã Sách: " + phieuMuon.getMaSach());
        holder.txtTenSach.setText("Tên Sách: " + phieuMuon.getTenSach());
        holder.txtNgay.setText("Ngày: " + phieuMuon.getNgay());
        holder.txtTrangThai.setText("Trạng Thái: " + (phieuMuon.getTraSach() == 1 ? "Đã Trả Sách" : "Chưa Trả Sách"));
        holder.txtTien.setText("Tiền Thuê: " + phieuMuon.getTienThue());

        holder.txtbienLai.setText("Bien Lai: " + phieuMuon.getBienLai());


        holder.btnTraSach.setVisibility(phieuMuon.getTraSach() == 1 ? View.GONE : View.VISIBLE);
        holder.btnTraSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhieuMuonnnDAO phieuMuonDAO = new PhieuMuonnnDAO(context);
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    PhieuMuonnnnnn clickedItem = list.get(adapterPosition);
                    boolean kiemtra = phieuMuonDAO.thayDoiTrangThai(clickedItem.getMaPM());
                    if (kiemtra) {
                        Toast.makeText(context, "Trả Sách thành công", Toast.LENGTH_SHORT).show();
                        clickedItem.setTraSach(1); // Assuming you update your model or fetch fresh data from DAO
                        notifyDataSetChanged();
                    } else {
                        Toast.makeText(context, "Trả Sách không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa phiếu mượn này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int adapterPosition = holder.getAdapterPosition();
                        if (adapterPosition != RecyclerView.NO_POSITION) {
                            PhieuMuonnnnnn clickedItem = list.get(adapterPosition);
                            PhieuMuonnnDAO phieuMuonDAO = new PhieuMuonnnDAO(context);
                            boolean result = phieuMuonDAO.xoaPhieuMuon(clickedItem.getMaPM());
                            if (result) {
                                list.remove(clickedItem);
                                notifyItemRemoved(adapterPosition);
                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", null);
                builder.show();
            }
        });

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    PhieuMuonnnnnn clickedItem = list.get(adapterPosition);
                    onUpdateClickListener.onUpdateClick(clickedItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaPM, txtMaTV, txtTenTV, txtMaTT, txtTenTT, txtMaSach, txtTenSach, txtNgay, txtTrangThai, txtTien, txtbienLai;
        Button btnTraSach, btnDelete, btnUpdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaPM = itemView.findViewById(R.id.txtMaPM);
            txtMaTV = itemView.findViewById(R.id.txtMaTV);
            txtTenTV = itemView.findViewById(R.id.txtTenTV);
            txtMaTT = itemView.findViewById(R.id.txtMaTT);
            txtTenTT = itemView.findViewById(R.id.txtTenTT);
            txtMaSach = itemView.findViewById(R.id.txtMaSach);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtTrangThai = itemView.findViewById(R.id.txTrangThai);
            txtTien = itemView.findViewById(R.id.txtTienThue);

            txtbienLai = itemView.findViewById(R.id.txtbienLai);


            btnTraSach = itemView.findViewById(R.id.btnTraSach);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }
    }

    public interface OnUpdateClickListener {
        void onUpdateClick(PhieuMuonnnnnn phieuMuon);
    }
}
