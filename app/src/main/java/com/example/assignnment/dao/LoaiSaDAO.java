package com.example.assignnment.dao;

import static java.lang.String.valueOf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignnment.database.DbHelper;
import com.example.assignnment.model.LoaiSach;
import com.example.assignnment.model.LoaiSachh;

import java.util.ArrayList;

public class LoaiSaDAO {
    DbHelper dbHelper;
    public LoaiSaDAO(Context context){
        dbHelper = new DbHelper(context);
    }

     public ArrayList<LoaiSachh> getDSLoaiSach(){
        ArrayList<LoaiSachh> list = new ArrayList<>();
         SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
         Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LoaiSach",null);
         if (cursor.getCount() != 0){
             cursor.moveToFirst();
             do {
                 list.add(new LoaiSachh(cursor.getInt(0),cursor.getString(1)));
             }while (cursor.moveToNext());
         }
         return list;
     }
   public boolean themLoaiSach(String tenLoai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
       ContentValues contentValues = new ContentValues();
       contentValues.put("tenloai",tenLoai);
       long check = sqLiteDatabase.insert("LoaiSach",null,contentValues);
       if (check == -1) {
           return false;
       }else{
           return true;
       }


   }

   public int xoaLoaiSach(int id) {
       SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
       Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Sach WHERE maloai = ? ", new String[]{valueOf(id)});
       if (cursor.getCount() != 0) {

           return -1;
           }
           long check = sqLiteDatabase.delete("LoaiSach", "maLoai =?", new String[]{valueOf(id)});

           if (check == -1)
               return 0;
           return 1;

   }
   public  boolean thayDoiLoaiSach(LoaiSachh loaiSachh){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai", loaiSachh.getTenLoai());
        long check = sqLiteDatabase.update("LoaiSach",contentValues,"maloai = ?",new String[]{valueOf(loaiSachh.getId())});
       if (check == -1)
           return false;
       return true;

    }

}
