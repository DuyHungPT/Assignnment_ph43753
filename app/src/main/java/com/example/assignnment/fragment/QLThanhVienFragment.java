package com.example.assignnment.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignnment.R;
import com.example.assignnment.adapter.ThanhVienAAdapter;
import com.example.assignnment.dao.ThanhVienDDAO;
import com.example.assignnment.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QLThanhVienFragment extends Fragment {
    ThanhVienDDAO thanhVienDDAO;
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     View view = inflater.inflate(R.layout.fragment_quanlithanhvien,container,false);
         recyclerView = view.findViewById(R.id.recycleThanhVien);
        FloatingActionButton floadAdd = view.findViewById(R.id.floadAdd);

         thanhVienDDAO = new ThanhVienDDAO(getContext());
        loadData();
      floadAdd.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            showDialog();
          }
      });
        return view;
    }
    public void loadData(){
        ArrayList<ThanhVien> list = thanhVienDDAO.getDSThanhVien();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ThanhVienAAdapter adapter = new ThanhVienAAdapter(getContext(),list, thanhVienDDAO);
        recyclerView.setAdapter(adapter);
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_thanhvien,null);
        builder.setView(view);

        EditText edtHoTen = view.findViewById(R.id.edtHoTen);
        EditText edtNamSinh = view.findViewById(R.id.edtNamSinh);

       builder.setNegativeButton("Them", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int i) {
           String hoTen = edtHoTen.getText().toString();
           String namSinh = edtNamSinh.getText().toString();

           boolean check = thanhVienDDAO.themThanhVien(hoTen,namSinh);
          if (check){
              Toast.makeText(getContext(), "Them Thanh Vien", Toast.LENGTH_SHORT).show();
          loadData();
          }else{
              Toast.makeText(getContext(), "Them That Bai", Toast.LENGTH_SHORT).show();
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
}
