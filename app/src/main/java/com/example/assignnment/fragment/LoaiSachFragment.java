package com.example.assignnment.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignnment.R;
import com.example.assignnment.adapter.LoaiSachAdapter;
import com.example.assignnment.dao.LoaiSaDAO;
import com.example.assignnment.model.ItemClick;
import com.example.assignnment.model.LoaiSachh;

import java.util.ArrayList;


public class LoaiSachFragment extends Fragment {

    RecyclerView recyclerLoaiSach;
    LoaiSaDAO dao;
    EditText edtLoaiSach;
    int maloai;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_loai_sach, container, false);
         recyclerLoaiSach = view.findViewById(R.id.recycleLoaiSach);

         edtLoaiSach = view.findViewById(R.id.edtLoaiSach);
        Button btnThem = view.findViewById(R.id.btnThem);
        Button btnSua = view.findViewById(R.id.btnSua);

         dao = new LoaiSaDAO(getContext());
     loadData();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = edtLoaiSach.getText().toString();

                if (dao.themLoaiSach(tenloai)){
                loadData();
                edtLoaiSach.setText("");
                }else{
                    Toast.makeText(getContext(), "them loai sach khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = edtLoaiSach.getText().toString();
                LoaiSachh loaiSachh = new LoaiSachh(maloai,tenloai);
                if (dao.thayDoiLoaiSach(loaiSachh)){
                    loadData();
                    edtLoaiSach.setText("");
                }else{
                    Toast.makeText(getContext(), "Sua khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    return view;
    }
    private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerLoaiSach.setLayoutManager(linearLayoutManager);

        ArrayList<LoaiSachh> list = dao.getDSLoaiSach();
        LoaiSachAdapter adapter = new LoaiSachAdapter(getContext(), list, new ItemClick() {
            @Override
            public void onClickLoaiSach(LoaiSachh loaiSachh) {
               edtLoaiSach.setText(loaiSachh.getTenLoai());
               maloai = loaiSachh.getId();
            }
        });
        recyclerLoaiSach.setAdapter(adapter);
    }
}