package com.example.assignnment.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assignnment.R;
import com.example.assignnment.adapter.PhieuMuonAdapter;
import com.example.assignnment.adapter.PhieuMuonnnnAdapter;
import com.example.assignnment.dao.PhieuMuonnnDAO;
import com.example.assignnment.dao.SachDAO;
import com.example.assignnment.dao.SachhDDAO;
import com.example.assignnment.dao.ThanhVienDDAO;
import com.example.assignnment.model.PhieuMuonnnnnn;
import com.example.assignnment.model.Sach;
import com.example.assignnment.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class QuanLyPhieuMuonFragment extends Fragment {

    PhieuMuonnnDAO phieuMuonnnDAO;
    RecyclerView recyclerViewQLPM;
    ArrayList<PhieuMuonnnnnn> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quan_ly_phieu_muon, container, false);

         recyclerViewQLPM = view.findViewById(R.id.recyclerQLPhieuMuon);
        FloatingActionButton floadAdd = view.findViewById(R.id.floadAdd);

        loadData();

        floadAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

          return view;
    }
    private  void loadData(){
        phieuMuonnnDAO = new PhieuMuonnnDAO(getContext());
        list = phieuMuonnnDAO.getDSPhieuMuon();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewQLPM.setLayoutManager(linearLayoutManager);
        PhieuMuonnnnAdapter adapter = new PhieuMuonnnnAdapter(list,getContext());
        recyclerViewQLPM.setAdapter(adapter);
    }
    private  void  showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_them_phieumuon,null);
        Spinner spnThanhVien = view.findViewById(R.id.spnThanhVien);
        Spinner spnSach = view.findViewById(R.id.spnSach);

        getDataThanhVien(spnThanhVien);
        getDataSach(spnSach);
        builder.setView(view);
        builder.setPositiveButton("them", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HashMap<String,Object> hsTV = (HashMap<String,Object>) spnThanhVien.getSelectedItem();
                int maTV = (int) hsTV.get("maTV");
                HashMap<String,Object> hsSach = (HashMap<String,Object>) spnSach.getSelectedItem();
                int maSach = (int) hsSach.get("maSach");

                int tien = (int) hsSach.get("giaThue");

              themPhieuMuon(maTV,maSach,tien);
            }
        });
        builder.setNegativeButton("Huy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    private  void getDataThanhVien(Spinner spnThanhVien){
        ThanhVienDDAO thanhVienDDAO = new ThanhVienDDAO(getContext());
        ArrayList<ThanhVien> list = thanhVienDDAO.getDSThanhVien();

        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();
        for (ThanhVien tv : list){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("maTV",tv.getMaTV());
            hs.put("hoTen",tv.getHoTen());
            listHM.add(hs);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"hoTen"},
                new int[]{android.R.id.text1}

        );
        spnThanhVien.setAdapter(simpleAdapter);
    }

    private  void getDataSach(Spinner spnSach){
        SachhDDAO sachhDDAO = new SachhDDAO(getContext());
        ArrayList<Sach> list = sachhDDAO.getDSDauSach();

        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();
        for (Sach sc : list){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("maSach",sc.getMaSach());
            hs.put("tenSach",sc.getTenSach());
            hs.put("giaThue",sc.getGiaThue());
            listHM.add(hs);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tenSach"},
                new int[]{android.R.id.text1}

        );
        spnSach.setAdapter(simpleAdapter);
    }
    private void themPhieuMuon(int maTV,int maSach,int tien){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String maTT = sharedPreferences.getString("USERNAME","");

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngay = simpleDateFormat.format(currentTime);

        PhieuMuonnnnnn phieuMuonnnnnn = new PhieuMuonnnnnn(maTV,maTT,maSach,ngay,0,tien);
        boolean kiemtra = phieuMuonnnDAO.themPhieuMuon(phieuMuonnnnnn);
        if (kiemtra){
            Toast.makeText(getContext(), "Them Thanh Cong", Toast.LENGTH_SHORT).show();
            loadData();
        }else {
            Toast.makeText(getContext(), "Them That Bai", Toast.LENGTH_SHORT).show();
        }
    }
}