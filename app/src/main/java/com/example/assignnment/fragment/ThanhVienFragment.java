package com.example.assignnment.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.assignnment.R;
import com.example.assignnment.adapter.ThanhVienAdapter;
import com.example.assignnment.dao.ThanhVienDAO;
import com.example.assignnment.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ThanhVienFragment extends Fragment {

  ListView lvThanhVien;

  ArrayList<ThanhVien> list;
  static ThanhVienDAO dao;
  ThanhVienAdapter adapter;
  ThanhVien item;
  FloatingActionButton fab;
  Dialog dialog;
  EditText edMaTV,edTenTV,edNamSinh;
  Button btnSave, btnCancel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
    lvThanhVien = v.findViewById(R.id.lvThanhVien);
    fab = v.findViewById(R.id.fab);
    dao = new ThanhVienDAO(getActivity());
   capNhatLv();
    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openDialog(getActivity(),0);
        }
    });
    lvThanhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        item = list.get(position);
        openDialog(getActivity(),1);

            return false;
        }
    });
    return v;
    }
    void capNhatLv() {
        list = (ArrayList<ThanhVien>) dao.getAll();
        adapter = new ThanhVienAdapter(getActivity(),this,list);
        lvThanhVien.setAdapter(adapter);
    }
    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Ban Co Muon Xoa Khong");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                dao.delete(Id);
                capNhatLv();
                dialog.cancel();
                    }
                }
        );
        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }
        );
        AlertDialog alert = builder.create();
        builder.show();
    }
    protected  void openDialog(final Context context,final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.thanhvien_dialog);
        edMaTV = dialog.findViewById(R.id.edMaTV);
        edTenTV = dialog.findViewById(R.id.edTenTV);
        edNamSinh = dialog.findViewById(R.id.edNamSinh);
        btnCancel = dialog.findViewById(R.id.btnCancelTV);
        btnSave = dialog.findViewById(R.id.btnSaveTV);
       // kiem tra type insert 0 hay update 1
        edMaTV.setEnabled(false);
        if (type != 0) {
            edMaTV.setText(String.valueOf(item.getMaTV()));
            edTenTV.setText(item.getHoTen());
            edNamSinh.setText(item.getNamSinh());
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item= new ThanhVien();
                item.setHoTen(edTenTV.getText().toString());
                item.setNamSinh(edNamSinh.getText().toString());
             if (validate()>0){
                 if (type==0){
                  if (dao.insert(item)>0){
                      Toast.makeText(context, "Them Thanh Cong", Toast.LENGTH_SHORT).show();
                  }else{
                      Toast.makeText(context, "Them That Bai", Toast.LENGTH_SHORT).show();
                  }
                 }else{
                 item.setMaTV(Integer.parseInt(edMaTV.getText().toString()));
                 if (dao.update(item)>0){
                     Toast.makeText(context, "Sua Thanh Cong", Toast.LENGTH_SHORT).show();
                 }else{
                     Toast.makeText(context, "Sua That Bai", Toast.LENGTH_SHORT).show();
                 }
                 }
                 capNhatLv();
                 dialog.dismiss();
             }
            }
        });
        dialog.show();
    }

    public int validate(){
        int check = 1;
        if (edTenTV.getText().length() == 0 || edNamSinh.getText().length()==0){
            Toast.makeText(getContext(), "Khong duoc de trong", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}