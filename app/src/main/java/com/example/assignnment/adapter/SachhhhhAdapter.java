package com.example.assignnment.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignnment.R;
import com.example.assignnment.dao.GioHangDAO;
import com.example.assignnment.dao.SachhDDAO;
import com.example.assignnment.model.GioHang;
import com.example.assignnment.model.Sach;

import java.util.ArrayList;
import java.util.HashMap;

public class SachhhhhAdapter extends RecyclerView.Adapter<SachhhhhAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Sach> list;
    private ArrayList<HashMap<String,Object>> listHM;
    private SachhDDAO sachhDDAO;
    private GioHangDAO gioHangDAO;




    public SachhhhhAdapter(Context context, ArrayList<Sach> list,ArrayList<HashMap<String,Object>> listHM,SachhDDAO sachhDDAO,GioHangDAO gioHangDAO) {
        this.context = context;
        this.list = list;
        this.listHM = listHM;
        this.sachhDDAO = sachhDDAO;
        this.gioHangDAO = gioHangDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_recycle_sach,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtMaSach.setText("Ma Sach: "+ list.get(position).getMaSach());
        holder.txtTenSach.setText("Ten Sach: "+ list.get(position).getTenSach());
        holder.txtGiaThue.setText("Gia Thue Sach: "+ list.get(position).getGiaThue());
        holder.txtMaLoai.setText("Ma Loai: "+ list.get(position).getMaLoai());
        holder.txtTenLoai.setText("Ten Loai: "+ list.get(position).getTenloai());

        ///////////////////////////////////////////
        holder.txtNxb.setText("Nxb: "+ list.get(position).getNxb());
        holder.txtsoTrang.setText("So trang: "+ list.get(position).getSoTrang());

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(list.get(holder.getAdapterPosition()));
            }
        });
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = sachhDDAO.xoaSach(list.get(holder.getAdapterPosition()).getMaSach());
           switch (check){
               case 1:
                   Toast.makeText(context, "xoa thanh cong", Toast.LENGTH_SHORT).show();
                   loadData();
                   break;
               case 0:
                   Toast.makeText(context, "xoa that bai", Toast.LENGTH_SHORT).show();
                  break;
               case -1:
                   Toast.makeText(context, "Sach Nay Khong xoa Duoc", Toast.LENGTH_SHORT).show();
                   break;
               default:
                   break;
           }
            }
        });
        ///////////////////////////////////////

        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sach sach = list.get(holder.getAdapterPosition());

                // Create a GioHang object from Sach
                GioHang gioHang = new GioHang(
                        sach.getMaSach(),
                        sach.getTenSach(),
                        sach.getGiaThue(),
                        1
                );

//                GioHang gioHang = new GioHang();
//                gioHang.setMaSach(sach.getMaSach());
//                gioHang.setTenSach(sach.getTenSach());
//                gioHang.setGiaThue(sach.getGiaThue());
//                gioHang.setQuantity(1); // Set default quantity or get it from user input if needed

                // Add to cart
                boolean check = gioHangDAO.addToCart(gioHang);
                if (check) {
                    Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to add to cart", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /////////////////
        TextView txtMaSach,txtTenSach, txtGiaThue,txtMaLoai,txtTenLoai, txtNxb,txtsoTrang;
        ImageView ivEdit,ivDel;
        Button btnBuy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            txtMaSach = itemView.findViewById(R.id.txtMaSach);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtGiaThue = itemView.findViewById(R.id.txtGiaThueSach);
            txtMaLoai = itemView.findViewById(R.id.txtMaLoai);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);

            //////////////////////////////////////////////////
            txtNxb = itemView.findViewById(R.id.txtNxb);
            txtsoTrang = itemView.findViewById(R.id.txtsoTrang);

            ivDel =itemView.findViewById(R.id.ivDel);
            ivEdit = itemView.findViewById(R.id.ivEdit);

            btnBuy = itemView.findViewById(R.id.btnBuy);
        }
    }
   public void showDialog(Sach sach){
       AlertDialog.Builder builder = new AlertDialog.Builder(context);
       LayoutInflater inflater = ((Activity)context).getLayoutInflater();
       View view = inflater.inflate(R.layout.dialog_suasach,null);
       builder.setView(view);

       EditText edtTenSach = view.findViewById(R.id.edtTenSach);
       EditText edtTien = view.findViewById(R.id.edtTien);
       ////////////////////////////////////////////////////////
       EditText edtNxb = view.findViewById(R.id.edtNxb);
       EditText edtsoTrang = view.findViewById(R.id.edtsoTrang);


       TextView txtMaSach = view.findViewById(R.id.txtMaSach);

       Spinner spnLoaiSach = view.findViewById(R.id.spnLoaiSach);

       txtMaSach.setText("Ma Sach" + sach.getMaSach());
       edtTenSach.setText(sach.getTenSach());
       edtTien.setText(String.valueOf(sach.getGiaThue()));
       //////////////////////////////////////////
       edtNxb.setText(sach.getNxb());
       edtsoTrang.setText(sach.getSoTrang());

       SimpleAdapter simpleAdapter = new SimpleAdapter(
               context,
               listHM,
               android.R.layout.simple_list_item_1,
               new String[]{"tenLoai"},
               new int[]{android.R.id.text1}
       );
       spnLoaiSach.setAdapter(simpleAdapter);
       int index = 0;
       int position = -1;
       for (HashMap<String,Object> item: listHM){
           if ((int)item.get("maLoai") == sach.getMaLoai()){
               position = index;
           }
           index ++;

       }
       spnLoaiSach.setSelection(position);
       builder.setNegativeButton("Cap nhat", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int id) {
               String tenSach = edtTenSach.getText().toString();
               int tien =Integer.parseInt(edtTien.getText().toString()) ;

               ///////////////////////////////////////////
               String nxb  = edtNxb.getText().toString() ;
               String soTrang  = edtsoTrang.getText().toString() ;


               HashMap<String,Object> hs = (HashMap<String, Object>) spnLoaiSach.getSelectedItem();
               int maLoai = (int) hs.get("maLoai");
                                                         //////////////////////////////////////////////////////////////////
               boolean check = sachhDDAO.capNhatThongTinSach(sach.getMaSach(),tenSach,tien,maLoai,nxb,soTrang);

               if (check){
                   Toast.makeText(context, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                   loadData();
               }else{
                   Toast.makeText(context, "Cap Nhat that bai", Toast.LENGTH_SHORT).show();
               }
           }
       });
       builder.setPositiveButton("Huy", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int id) {

           }
       });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
   }
private  void loadData(){
        list.clear();
        list = sachhDDAO.getDSDauSach();
        notifyDataSetChanged();
}
}
