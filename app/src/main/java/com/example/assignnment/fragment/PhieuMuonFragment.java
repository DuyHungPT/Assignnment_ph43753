package com.example.assignnment.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import java.text.SimpleDateFormat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignnment.R;
import com.example.assignnment.adapter.PhieuMuonAdapter;
import com.example.assignnment.adapter.SachSpinerAdapter;
import com.example.assignnment.adapter.ThanhVienSpinerAdapter;
import com.example.assignnment.dao.PhieuMuonDAO;
import com.example.assignnment.dao.SachDAO;
import com.example.assignnment.dao.ThanhVienDAO;
import com.example.assignnment.dao.ThuThuDAO;
import com.example.assignnment.model.PhieuMuon;
import com.example.assignnment.model.Sach;
import com.example.assignnment.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;


public class PhieuMuonFragment extends Fragment {

    ListView lvPhieuMuon;
    ArrayList<PhieuMuon> list;
    static PhieuMuonDAO dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;

    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV,spSach;
    TextView tvNgay,tvTienThue;
    CheckBox chkTraSach;
    Button btnSave,btnCancel;

    ThanhVienSpinerAdapter thanhVienSpinerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;
    SachSpinerAdapter sachSpinerAdapter;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    Sach sach;
    int maSach,tienThue;
    int positionTV,positinSach;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        lvPhieuMuon = v.findViewById(R.id.lvPhieuMuon);
        fab = v.findViewById(R.id.fab);
        dao = new PhieuMuonDAO(getActivity());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             openDialog(getActivity(),0);
            }
        });
        lvPhieuMuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
       capnhatLv();
          return v ;
    }
    void capnhatLv(){
        list =(ArrayList<PhieuMuon>) dao.getAll();
        adapter = new PhieuMuonAdapter(getActivity(),this,list);
        lvPhieuMuon.setAdapter(adapter);

    }

    protected  void openDialog(final Context context,final  int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.phieu_muon_dialog);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        edMaPM = dialog.findViewById(R.id.edMaPM);
        spTV = dialog.findViewById(R.id.spMaTV);
        spSach = dialog.findViewById(R.id.spMaSach);

        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvTienThue = dialog.findViewById(R.id.tvTienThue);
        chkTraSach = dialog.findViewById(R.id.chkTraSach);
        btnCancel = dialog.findViewById(R.id.btnCancelPM);
        btnSave = dialog.findViewById(R.id.btnSavePM);



//        tvNgay.setText("Ngay Thue"+ sdf.format(new Date()));
        edMaPM.setEnabled(false);

       thanhVienDAO = new ThanhVienDAO(context);
       listThanhVien = new ArrayList<ThanhVien>();
       listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
       thanhVienSpinerAdapter = new ThanhVienSpinerAdapter(context,listThanhVien);
       spTV.setAdapter(thanhVienSpinerAdapter);
       spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               maThanhVien = listThanhVien.get(position).getMaTV();
//               Toast.makeText(context, "Chon"+listThanhVien.get(position).getHoTen(), Toast.LENGTH_SHORT).show();

           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
        sachDAO = new SachDAO(context);
        listSach = new ArrayList<Sach>();
        listSach = (ArrayList<Sach>) sachDAO.getAll();
        sachSpinerAdapter = new SachSpinerAdapter(context,listSach);
        spSach.setAdapter(sachSpinerAdapter);

        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).getMaSach();
                tienThue =listSach.get(position).getGiaThue();
                tvTienThue.setText("tien Thue:"+tienThue);

//                Toast.makeText(context, "chon"+listSach.get(position).getTenSach(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (type !=0 ){
            edMaPM.setText(String.valueOf(item.getMaPM()));
            for (int i = 0; i < listThanhVien.size(); i ++)
                if (item.getMaTV() == (listThanhVien.get(i).getMaTV())){
                    positionTV = i;
                }
            spTV.setSelection(positionTV);

            for (int i = 0; i < listSach.size(); i++)
                if (item.getMaSach() == (listSach.get(i).getMaSach())){
                    positinSach = i;
                }
            spSach.setSelection(positinSach);
//       tvNgay.setText("Ngay Thue:" + sdf.format(item.getNgay()));
       tvTienThue.setText("Tien thue" + item.getTienThue());
       if (item.getTraSach() == 1){
           chkTraSach.setChecked(true);
       }else{
           chkTraSach.setChecked(false);
       }
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
                item = new PhieuMuon();
                item.setMaSach(maSach);
                item.setMaTV(maThanhVien);
                item.setNgay(new Date());
                item.setTienThue(tienThue);
                if (chkTraSach.isChecked()){
                    item.setTraSach(1);
                }else{
                    item.setTraSach(0);
                }
                if (type == 0){
                    if (dao.insert(item) > 0) {
                        Toast.makeText(context, "Them Thanh Cong", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Them That Bai", Toast.LENGTH_SHORT).show();
                    }
                }else{
                item.setMaPM(Integer.parseInt(edMaPM.getText().toString()));
                if (dao.update(item)>0){
                    Toast.makeText(context, "Sua Thanh Cong", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Sua That bai", Toast.LENGTH_SHORT).show();
                }
                }
                capnhatLv();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Ban Co muon xoa khong");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                dao.delete(Id);
                capnhatLv();
                dialog.cancel();
                    }
                }
        );
        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                } );

        AlertDialog alert = builder.create();
        builder.show();
    }
}