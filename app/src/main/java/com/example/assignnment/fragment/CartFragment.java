package com.example.assignnment.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignnment.R;
import com.example.assignnment.adapter.CartAdapter;
import com.example.assignnment.dao.GioHangDAO;
import com.example.assignnment.model.GioHang;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    RecyclerView recyclerCart;
    GioHangDAO gioHangDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerCart = view.findViewById(R.id.recyclerCart);
        gioHangDAO = new GioHangDAO(getContext());

        loadCartData();

        return view;
    }

    private void loadCartData() {
        ArrayList<GioHang> cartList = gioHangDAO.getAllItems();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerCart.setLayoutManager(layoutManager);
        CartAdapter adapter = new CartAdapter(getContext(), cartList);
        recyclerCart.setAdapter(adapter);
    }
}
