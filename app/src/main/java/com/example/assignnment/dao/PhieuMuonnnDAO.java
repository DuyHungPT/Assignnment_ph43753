package com.example.assignnment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignnment.database.DbHelper;
import com.example.assignnment.model.PhieuMuonnnnnn;

import java.util.ArrayList;

public class PhieuMuonnnDAO {
    DbHelper dbHelper;
    public PhieuMuonnnDAO(Context context){
        dbHelper = new DbHelper(context);

    }
    public ArrayList<PhieuMuonnnnnn> getDSPhieuMuon(){
        ArrayList<PhieuMuonnnnnn> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT pm.maPM, pm.maTV, tv.hoTen, pm.maTT, tt.hoTen, pm.maSach, book.tenSach, pm.ngay, pm.traSach, pm.tienThue FROM PhieuMuon pm, ThanhVien tv, ThuThu tt, Sach book  WHERE pm.maTV = tv.maTV and pm.maTT = tt.maTT AND pm.maSach = book.maSach ORDER BY pm.maPM", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do{
                list.add(new PhieuMuonnnnnn(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8), cursor.getInt(9)));

            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean thayDoiTrangThai(int mapm){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trasach",1);
        long check = sqLiteDatabase.update("PhieuMuon",contentValues,"mapm =?",new String[]{String.valueOf(mapm)});
        if (check == -1){
            return false;
        }else{
            return  true;
        }
    }
    public boolean themPhieuMuon(PhieuMuonnnnnn phieuMuonnnnnn){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        // txtMaPM,txtMaTV,txtTenTV,txtMaTT, txtTenTT,txtMaSach,txtTenSach,txtNgay,txtTrangThai,txtTien;
//        contentValues.put("maPM",phieuMuonnnnnn.getMaPM());
        contentValues.put("maTV",phieuMuonnnnnn.getMaTV());
        contentValues.put("maTT",phieuMuonnnnnn.getMaTT());

        contentValues.put("maSach",phieuMuonnnnnn.getMaSach());
        contentValues.put("ngay",phieuMuonnnnnn.getNgay());
        contentValues.put("trasach",phieuMuonnnnnn.getTraSach());
        contentValues.put("TienThue",phieuMuonnnnnn.getTienThue());
    long check = sqLiteDatabase.insert("PhieuMuon",null,contentValues);

    if (check == -1){
        return  false;
    }else{
        return true;
    }
    }
}
