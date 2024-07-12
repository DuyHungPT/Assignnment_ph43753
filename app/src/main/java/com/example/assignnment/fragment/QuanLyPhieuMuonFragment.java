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
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assignnment.R;
import com.example.assignnment.adapter.PhieuMuonnnnAdapter;
import com.example.assignnment.adapter.SachhhhhAdapter;
import com.example.assignnment.dao.PhieuMuonnnDAO;
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

public class QuanLyPhieuMuonFragment extends Fragment implements PhieuMuonnnnAdapter.OnUpdateClickListener {

    private PhieuMuonnnDAO phieuMuonnnDAO;
    private RecyclerView recyclerViewQLPM;
    private ArrayList<PhieuMuonnnnnn> list;

    private EditText edtSearchTenTV;
    private Button btnSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_phieu_muon, container, false);

        recyclerViewQLPM = view.findViewById(R.id.recyclerQLPhieuMuon);
        FloatingActionButton floadAdd = view.findViewById(R.id.floadAdd);

        edtSearchTenTV = view.findViewById(R.id.edtSearchTenTV);
        btnSearch = view.findViewById(R.id.btnSearch);


//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String keyword = edtSearchTenTV.getText().toString();
//                searchPhieuMuon(keyword);
//            }
//        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = edtSearchTenTV.getText().toString().trim();
                loadData(keyword);
            }
        });

        loadData();

        floadAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return view;
    }

    private void loadData() {
        phieuMuonnnDAO = new PhieuMuonnnDAO(getContext());
        list = phieuMuonnnDAO.getDSPhieuMuon();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewQLPM.setLayoutManager(linearLayoutManager);

        PhieuMuonnnnAdapter adapter = new PhieuMuonnnnAdapter(list, getContext(), this);
        recyclerViewQLPM.setAdapter(adapter);
    }

    private void loadData(String keyword) {
//        ArrayList<Sach> list = sachhDDAO.searchPhieuMuon(keyword);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewQLPM.setLayoutManager(linearLayoutManager);
        PhieuMuonnnnAdapter adapter = new PhieuMuonnnnAdapter(list, getContext(), this);
        recyclerViewQLPM.setAdapter(adapter);
    }

private void searchPhieuMuon(String keyword) {
    phieuMuonnnDAO = new PhieuMuonnnDAO(getContext());
    list = phieuMuonnnDAO.searchPhieuMuonByTenTV(keyword);
    updateRecyclerView(list);
}
    private void updateRecyclerView(ArrayList<PhieuMuonnnnnn> list) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewQLPM.setLayoutManager(linearLayoutManager);

        PhieuMuonnnnAdapter adapter = new PhieuMuonnnnAdapter(list, getContext(), this);
        recyclerViewQLPM.setAdapter(adapter);
    }





    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_them_phieumuon, null);
        Spinner spnThanhVien = view.findViewById(R.id.spnThanhVien);
        Spinner spnSach = view.findViewById(R.id.spnSach);

        EditText edtbienLai = view.findViewById(R.id.edtbienLai);



        getDataThanhVien(spnThanhVien);
        getDataSach(spnSach);

        builder.setView(view);
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HashMap<String, Object> hsTV = (HashMap<String, Object>) spnThanhVien.getSelectedItem();
                int maTV = (int) hsTV.get("maTV");
                HashMap<String, Object> hsSach = (HashMap<String, Object>) spnSach.getSelectedItem();
                int maSach = (int) hsSach.get("maSach");
                int tien = (int) hsSach.get("giaThue");


                String bienLai = edtbienLai.getText().toString();




                themPhieuMuon(maTV, maSach, tien, bienLai);
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getDataThanhVien(Spinner spnThanhVien) {
        ThanhVienDDAO thanhVienDDAO = new ThanhVienDDAO(getContext());
        ArrayList<ThanhVien> list = thanhVienDDAO.getDSThanhVien();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (ThanhVien tv : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maTV", tv.getMaTV());
            hs.put("hoTen", tv.getHoTen());
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

    private void getDataSach(Spinner spnSach) {
        SachhDDAO sachhDDAO = new SachhDDAO(getContext());
        ArrayList<Sach> list = sachhDDAO.getDSDauSach();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (Sach sc : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maSach", sc.getMaSach());
            hs.put("tenSach", sc.getTenSach());
            hs.put("giaThue", sc.getGiaThue());
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
 ///////////////////////////////////////////////////////////////////////////////////////
    private void themPhieuMuon(int maTV, int maSach, int tien, String bienLai) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String maTT = sharedPreferences.getString("USERNAME", "");

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngay = simpleDateFormat.format(currentTime);

        PhieuMuonnnnnn phieuMuonnnnnn = new PhieuMuonnnnnn(maTV, maTT, maSach, ngay, 0, tien, bienLai);
        boolean kiemtra = phieuMuonnnDAO.themPhieuMuon(phieuMuonnnnnn);
        if (kiemtra) {
            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            loadData();
        } else {
            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpdateClick(PhieuMuonnnnnn phieuMuon) {
        showEditDialog(phieuMuon);
    }

    private void showEditDialog(PhieuMuonnnnnn phieuMuon) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_sua_phieumuon, null);
        Spinner spnThanhVien = view.findViewById(R.id.spnThanhVien);
        Spinner spnSach = view.findViewById(R.id.spnSach);
        /////////////////////////////////////////////////////////////
        EditText edtbienLai = view.findViewById(R.id.edtbienLai);


        setDataToSpinners(spnThanhVien, spnSach, phieuMuon);

        edtbienLai.setText(phieuMuon.getBienLai());




        builder.setView(view);
        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HashMap<String, Object> hsTV = (HashMap<String, Object>) spnThanhVien.getSelectedItem();
                int maTV = (int) hsTV.get("maTV");
                HashMap<String, Object> hsSach = (HashMap<String, Object>) spnSach.getSelectedItem();
                int maSach = (int) hsSach.get("maSach");

                String bienLai = edtbienLai.getText().toString();


                phieuMuon.setMaTV(maTV);
                phieuMuon.setMaSach(maSach);
                phieuMuon.setBienLai(bienLai);


                boolean result = phieuMuonnnDAO.suaPhieuMuon(phieuMuon);
                if (result) {
                    Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    loadData(); // Reload data in RecyclerView after update
                } else {
                    Toast.makeText(getContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setDataToSpinners(Spinner spnThanhVien, Spinner spnSach, PhieuMuonnnnnn phieuMuon) {
        // Load data into ThanhVien Spinner
        ThanhVienDDAO thanhVienDDAO = new ThanhVienDDAO(getContext());
        ArrayList<ThanhVien> listTV = thanhVienDDAO.getDSThanhVien();
        ArrayList<HashMap<String, Object>> listHMTV = new ArrayList<>();
        int tvPosition = -1;
        for (int i = 0; i < listTV.size(); i++) {
            ThanhVien tv = listTV.get(i);
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maTV", tv.getMaTV());
            hs.put("hoTen", tv.getHoTen());
            listHMTV.add(hs);
            if (tv.getMaTV() == phieuMuon.getMaTV()) {
                tvPosition = i;
            }
        }
        SimpleAdapter simpleAdapterTV = new SimpleAdapter(
                getContext(),
                listHMTV,
                android.R.layout.simple_list_item_1,
                new String[]{"hoTen"},
                new int[]{android.R.id.text1}
        );
        spnThanhVien.setAdapter(simpleAdapterTV);
        if (tvPosition != -1) {
            spnThanhVien.setSelection(tvPosition);
        }

        // Load data into Sach Spinner
        SachhDDAO sachhDDAO = new SachhDDAO(getContext());
        ArrayList<Sach> listSach = sachhDDAO.getDSDauSach();
        ArrayList<HashMap<String, Object>> listHMSach = new ArrayList<>();
        int sachPosition = -1;
        for (int i = 0; i < listSach.size(); i++) {
            Sach sc = listSach.get(i);
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maSach", sc.getMaSach());
            hs.put("tenSach", sc.getTenSach());
            hs.put("giaThue", sc.getGiaThue());
            listHMSach.add(hs);
            if (sc.getMaSach() == phieuMuon.getMaSach()) {
                sachPosition = i;
            }
        }
        SimpleAdapter simpleAdapterSach = new SimpleAdapter(
                getContext(),
                listHMSach,
                android.R.layout.simple_list_item_1,
                new String[]{"tenSach"},
                new int[]{android.R.id.text1}
        );
        spnSach.setAdapter(simpleAdapterSach);
        if (sachPosition != -1) {
            spnSach.setSelection(sachPosition);
        }
    }
}
