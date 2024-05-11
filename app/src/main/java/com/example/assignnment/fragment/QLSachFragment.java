package com.example.assignnment.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignnment.R;
import com.example.assignnment.adapter.SachhhhhAdapter;
import com.example.assignnment.dao.LoaiSaDAO;
import com.example.assignnment.dao.LoaiSachDAO;
import com.example.assignnment.dao.SachhDDAO;
import com.example.assignnment.model.LoaiSachh;
import com.example.assignnment.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class QLSachFragment extends Fragment {
    SachhDDAO sachhDDAO;
    RecyclerView recyclerSach;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlsach,container,false);

        recyclerSach = view.findViewById(R.id.recycleSach);

        FloatingActionButton floadAdd = view.findViewById(R.id.floadAdd);

         sachhDDAO = new SachhDDAO(getContext());
         loadData();


        floadAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();

            }
        });
        return view;
    }
    private void  loadData(){
        ArrayList<Sach> list = sachhDDAO.getDSDauSach();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerSach.setLayoutManager(linearLayoutManager);
        SachhhhhAdapter adapter = new SachhhhhAdapter(getContext(),list, getDSLoaiSach(),sachhDDAO);
        recyclerSach.setAdapter(adapter);
    }
    private  void  showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themsach,null);
        builder.setView(view);

        EditText edtTenSach = view.findViewById(R.id.edtTenSach);
        EditText edtTien = view.findViewById(R.id.edtTien);
        Spinner spnLoaiSach  = view.findViewById(R.id.spnLoaiSach);

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSLoaiSach(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenLoai"},
                new int[]{android.R.id.text1}
        );
        spnLoaiSach.setAdapter(simpleAdapter);
        builder.setNegativeButton("Them", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                 String tenSach = edtTenSach.getText().toString();
                int tien =Integer.parseInt(edtTien.getText().toString()) ;

                HashMap<String,Object> hs = (HashMap<String, Object>) spnLoaiSach.getSelectedItem();
                int maLoai = (int) hs.get("maLoai");
                boolean check = sachhDDAO.themSachMoi(tenSach,tien,maLoai);

            if (check){
                Toast.makeText(getContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                loadData();
            }else{
                Toast.makeText(getContext(), "Them that bai", Toast.LENGTH_SHORT).show();
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
   private ArrayList<HashMap<String,Object>> getDSLoaiSach(){
        LoaiSaDAO loaiSaDAO =new LoaiSaDAO(getContext());
        ArrayList<LoaiSachh> list = loaiSaDAO.getDSLoaiSach();
        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();

        for (LoaiSachh loai : list){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("maLoai",loai.getId());
            hs.put("tenLoai",loai.getTenLoai());
            listHM.add(hs);

        }
        return listHM;
   }
}
