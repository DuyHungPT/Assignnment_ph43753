package com.example.assignnment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignnment.database.DbHelper;
import com.example.assignnment.model.Sach;

import java.util.ArrayList;

public class SachhDDAO {
    DbHelper dbHelper;
    public SachhDDAO(Context context) {
        dbHelper = new DbHelper(context);
    }
    public ArrayList<Sach> getDSDauSach(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT sc.maSach, sc.tenSach, sc.giaThue, sc.maLoai, lo.tenLoai, sc.nxb, sc.soTrang " +
                        "FROM Sach sc " +
                        "JOIN LoaiSach lo ON sc.maLoai = lo.maLoai", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));

            }while (cursor.moveToNext());

        }
        return list;
    }
    public boolean themSachMoi(String tenSach,int giaTien,int maLoai, String nxb,String soTrang){
     SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSach",tenSach);
        contentValues.put("giaThue",giaTien);
        contentValues.put("maLoai",maLoai);

        contentValues.put("nxb", nxb);
        contentValues.put("soTrang ", soTrang);
        long check = sqLiteDatabase.insert("Sach", null,contentValues);
        if (check == -1)
            return false;
        return true;

    }
    public boolean capNhatThongTinSach(int maSach,String tenSach,int giaThue,int maLoai, String nxb,String soTrang){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSach",tenSach);
        contentValues.put("giaThue",giaThue);
        contentValues.put("maLoai",maLoai);

        contentValues.put("nxb",nxb);
        contentValues.put("soTrang",soTrang);

        long check = sqLiteDatabase.update("Sach",contentValues,"maSach = ?",new String[]{String.valueOf(maSach)});
        if (check == -1)
            return false;
        return true;
    }
    public int xoaSach(int maSach){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PhieuMuon WHERE maSach = ?",new String[]{String.valueOf(maSach)});
       if (cursor.getCount() != 0){
           return -1;
       }
       long check = sqLiteDatabase.delete("Sach","maSach = ?",new String[]{String.valueOf(maSach)});
       if (check == -1)
           return 0;
       return 1;
    }




    public ArrayList<Sach> timKiemSachTheoTen(String keyword) {
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT sc.maSach, sc.tenSach, sc.giaThue, sc.maLoai, lo.tenLoai, sc.nxb, sc.soTrang " +
                        "FROM Sach sc " +
                        "JOIN LoaiSach lo ON sc.maLoai = lo.maLoai " +
                        "WHERE sc.tenSach LIKE ?", new String[]{"%" + keyword + "%"});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6)));
            } while (cursor.moveToNext());
        }
        return list;
    }

}
