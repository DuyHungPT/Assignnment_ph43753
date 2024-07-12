package com.example.assignnment.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignnment.database.DbHelper;
import com.example.assignnment.model.GioHang;

import java.util.ArrayList;

public class GioHangDAO {
    private DbHelper dbHelper;

    public GioHangDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public boolean addToCart(GioHang gioHang) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maSach", gioHang.getMaSach());
        contentValues.put("tenSach", gioHang.getTenSach());
        contentValues.put("giaThue", gioHang.getGiaThue());
        contentValues.put("quantity", gioHang.getQuantity());

        long check = sqLiteDatabase.insert("GioHang", null, contentValues);
        return check != -1;
    }

    public ArrayList<GioHang> getAllItems() {
        ArrayList<GioHang> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM GioHang", null);
        if (cursor.moveToFirst()) {
            do {
                int maSach = cursor.getInt(0);
                String tenSach = cursor.getString(1);
                int giaThue = cursor.getInt(2);
                int quantity = cursor.getInt(3);
                GioHang gioHang = new GioHang(maSach, tenSach, giaThue, quantity);
                list.add(gioHang);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public boolean removeFromCart(int maSach) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("GioHang", "maSach = ?", new String[]{String.valueOf(maSach)});
        return check != -1;
    }

    public boolean updateQuantity(GioHang gioHang) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("quantity", gioHang.getQuantity());
        long check = sqLiteDatabase.update("GioHang", contentValues, "maSach = ?", new String[]{String.valueOf(gioHang.getMaSach())});
        return check != -1;
    }
}
