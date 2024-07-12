package com.example.assignnment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignnment.database.DbHelper;
import com.example.assignnment.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienDDAO {
    DbHelper dbHelper;

    public ThanhVienDDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<ThanhVien> getDSThanhVien(){
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ThanhVien",null);

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            do {
                list.add(new ThanhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4)));

            } while (cursor.moveToNext());
        }
        return  list;

    }
                                               ///////////////////////////////////////////////////////////////////////
    public boolean themThanhVien(String hoTen, String namSinh, String cccd,String haHa){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen",hoTen);
        contentValues.put("namSinh",namSinh);
        ///////////////////////////////////////////////////////////////
        contentValues.put("Cccd",cccd);
        contentValues.put("HaHa",haHa);
        long check = sqLiteDatabase.insert("ThanhVien",null,contentValues);
        if (check == -1)
            return false;
        return true;
    }
                                                            ///////////////////////////////////////
    public boolean capNhatThongTin( int maTV,String hoTen, String namSinh, String cccd,String haHa){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen",hoTen);
        contentValues.put("namSinh",namSinh);
        /////////////////////////////////////////////////////////
        contentValues.put("Cccd",cccd);
        contentValues.put("haHa",haHa);
        long check = sqLiteDatabase.update("ThanhVien",contentValues, "maTV = ?",new String[]{String.valueOf(maTV)} );
        if (check == -1)
            return false;
        return true;
    }

    public  int xoaThongTin(int maTV){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PhieuMuon WHERE maTV = ?", new String[]{String.valueOf(maTV)});
        if (cursor.getCount() != 0){
            return  -1;
        }

        long check = sqLiteDatabase.delete("ThanhVien", "maTV = ?",new String[]{String.valueOf(maTV)});
     if (check == -1)
         return 0;
     return 1;
    }


    //////////////

    public ArrayList<ThanhVien> timKiemThanhVien(String keyword){
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ThanhVien WHERE hoTen LIKE ?", new String[]{"%" + keyword + "%"});

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();

            do {
                list.add(new ThanhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4)));

            } while (cursor.moveToNext());
        }
        cursor.close(); // Close the cursor to prevent memory leaks
        return list;
    }

}
