package com.example.assignnment.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignnment.R;
import com.example.assignnment.dao.ThanhVienDDAO;
import com.example.assignnment.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienAAdapter extends RecyclerView.Adapter<ThanhVienAAdapter.ViewHolder>{
   private Context context;
   private ArrayList<ThanhVien> list;
    private ThanhVienDDAO thanhVienDDAO;

    public ThanhVienAAdapter(Context context, ArrayList<ThanhVien> list,ThanhVienDDAO thanhVienDDAO) {
        this.context = context;
        this.list = list;
        this.thanhVienDDAO = thanhVienDDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_thanhvien,parent,false);



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.txtMaTV.setText("Ma TV" + list.get(position).getMaTV());
        holder.txtHoTen.setText("Ten TV" + list.get(position).getHoTen());
        holder.txtNamSinh.setText("Nam Sinh TV" + list.get(position).getNamSinh());

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showCapNhatThongTin(list.get(holder.getAdapterPosition()));
            }
        });
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          int check = thanhVienDDAO.xoaThongTin(list.get(holder.getAdapterPosition()).getMaTV());
             switch (check){
                 case 1:
                     Toast.makeText(context, "xoa thanh cong", Toast.LENGTH_SHORT).show();
                loadData();
                break;
                 case 0:
                     Toast.makeText(context, "xoa that bai", Toast.LENGTH_SHORT).show();
                  break;
                 case -1:
                     Toast.makeText(context, "Khong the xoa thanh vien nay", Toast.LENGTH_SHORT).show();
                 break;

                 default:
                     break;
             }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtMaTV,txtHoTen,txtNamSinh;
        ImageView ivEdit,ivDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaTV = itemView.findViewById(R.id.txtMaTV);
            txtHoTen = itemView.findViewById(R.id.txtHoTen);
            txtNamSinh = itemView.findViewById(R.id.txtNamSinh);
            ivDel = itemView.findViewById(R.id.ivDel);
            ivEdit = itemView.findViewById(R.id.ivEdit);


        }
    }
    public  void showCapNhatThongTin (ThanhVien thanhVien){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_sua_thanhvien,null);
        builder.setView(view);

        TextView txtMaTV = view.findViewById(R.id.txtMaTV);
        EditText edtHoTen = view.findViewById(R.id.edtHoTen);
        EditText edtNamSinh = view.findViewById(R.id.edtNamSinh);

        txtMaTV.setText("Ma TV" + thanhVien.getMaTV());
        edtHoTen.setText(thanhVien.getHoTen());
        edtNamSinh.setText(thanhVien.getNamSinh());

        builder.setNegativeButton("Cap Nhat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
               String hoTen = edtHoTen.getText().toString();
               String namSinh = edtNamSinh.getText().toString();
               int id = thanhVien.getMaTV();

               boolean check = thanhVienDDAO.capNhatThongTin(id,hoTen,namSinh);
               if (check){
                   Toast.makeText(context, "Cap Nhat Thanh Cong", Toast.LENGTH_SHORT).show();
                  loadData();
               }else{
                   Toast.makeText(context, "Cap Nhat That bai", Toast.LENGTH_SHORT).show();
               }
            }
        });

        builder.setPositiveButton("Huy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    private  void  loadData(){
        list.clear();
        list = thanhVienDDAO.getDSThanhVien();
        notifyDataSetChanged();
    }
}
