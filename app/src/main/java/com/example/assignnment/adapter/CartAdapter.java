package com.example.assignnment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignnment.R;
import com.example.assignnment.model.GioHang;
import com.example.assignnment.dao.GioHangDAO;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private ArrayList<GioHang> cartList;
    private GioHangDAO gioHangDAO;

    public CartAdapter(Context context, ArrayList<GioHang> cartList) {
        this.context = context;
        this.cartList = cartList;
        this.gioHangDAO = new GioHangDAO(context);
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        GioHang gioHang = cartList.get(position);
        holder.tvTenSach.setText(gioHang.getTenSach());
        holder.tvGiaThue.setText(String.valueOf(gioHang.getGiaThue()));
        holder.tvQuantity.setText(String.valueOf(gioHang.getQuantity()));

        holder.btnIncrease.setOnClickListener(v -> {
            int newQuantity = gioHang.getQuantity() + 1;
            gioHang.setQuantity(newQuantity);
            gioHangDAO.updateQuantity(gioHang);
            holder.tvQuantity.setText(String.valueOf(newQuantity));
        });

        holder.btnDecrease.setOnClickListener(v -> {
            if (gioHang.getQuantity() > 1) {
                int newQuantity = gioHang.getQuantity() - 1;
                gioHang.setQuantity(newQuantity);
                gioHangDAO.updateQuantity(gioHang);
                holder.tvQuantity.setText(String.valueOf(newQuantity));
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSach, tvGiaThue, tvQuantity;
        Button btnIncrease, btnDecrease;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSach = itemView.findViewById(R.id.tvTenSach);
            tvGiaThue = itemView.findViewById(R.id.tvGiaThue);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
        }
    }
}
