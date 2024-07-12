//package com.example.assignnment.dao;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.widget.ArrayAdapter;
//
//import com.example.assignnment.database.DbHelper;
//import com.example.assignnment.model.Sach;
//
//import java.util.ArrayList;
//
//public class ThongKeDAO {
//    DbHelper dbHelper;
//    public ThongKeDAO(Context context){
//        dbHelper = new DbHelper(context);
//    }
//
//    public ArrayList<Sach> getTop10(){
//        ArrayList<Sach> list = new ArrayList<>();
//        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT pm.maSach, sc.tenSach, COUNT(pm.maSach) FROM PhieuMuon pm, Sach sc WHERE pm.maSach = sc.maSach GROUP BY pm.maSach, sc.tenSach ORDER BY COUNT(pm.maSach) DESC LIMIT 10", null);
//        if (cursor.getCount() != 0){
//            cursor.moveToFirst();
//            do {
//                list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3)));
//            }while (cursor.moveToNext());
//        }
//        return  list;
//    }
////public ArrayList<Sach> getTop10() {
////    ArrayList<Sach> list = new ArrayList<>();
////    SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
////    Cursor cursor = null;
////
////    try {
////        cursor = sqLiteDatabase.rawQuery("SELECT pm.maSach, sc.tenSach, COUNT(pm.maSach) FROM PhieuMuon pm, Sach sc WHERE pm.maSach = sc.maSach GROUP BY pm.maSach, sc.tenSach ORDER BY COUNT(pm.maSach) DESC LIMIT 10", null);
////        if (cursor != null && cursor.moveToFirst()) {
////            do {
////                int maSach = cursor.getInt(0);
////                String tenSach = cursor.getString(1);
////                int soLanMuon = cursor.getInt(2);
////
////                // Adjust constructor parameters if needed, only using available columns
////                Sach sach = new Sach(maSach, tenSach, soLanMuon);
////                list.add(sach);
////            } while (cursor.moveToNext());
////        }
////    } catch (Exception e) {
////        e.printStackTrace();
////    } finally {
////        if (cursor != null) {
////            cursor.close();
////        }
////    }
////    return list;
////}
//
//    public int getDoanhThu(String ngaybatdau, String ngayketthuc){
//        ngaybatdau = ngaybatdau.replace("/","");
//        ngayketthuc = ngayketthuc.replace("/","");
//        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(tienThue) FROM PhieuMuon WHERE substr(ngay,7) || substr(ngay,4,2) || substr(ngay,1,2) BETWEEN ? AND ? ",new String[]{ngaybatdau,ngayketthuc});
//     if (cursor.getCount() !=  0){
//         cursor.moveToFirst();
//         return cursor.getInt(0);
//     }
//     return 0;
//    }
//}
package com.example.assignnment.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignnment.database.DbHelper;
import com.example.assignnment.model.Sach;

import java.util.ArrayList;

public class ThongKeDAO {
    DbHelper dbHelper;
    public ThongKeDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Sach> getTop10(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = sqLiteDatabase.rawQuery("SELECT pm.maSach, sc.tenSach, COUNT(pm.maSach) FROM PhieuMuon pm, Sach sc WHERE pm.maSach = sc.maSach GROUP BY pm.maSach, sc.tenSach ORDER BY COUNT(pm.maSach) DESC LIMIT 10", null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int maSach = cursor.getInt(0);
                    String tenSach = cursor.getString(1);
                    int soluongdamuon = cursor.getInt(2);

                    // Sử dụng hàm khởi tạo phù hợp với dữ liệu trả về từ truy vấn
                    Sach sach = new Sach(maSach, tenSach, soluongdamuon, "");
                    list.add(sach);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }

    public int getDoanhThu(String ngaybatdau, String ngayketthuc){
        ngaybatdau = ngaybatdau.replace("/","");
        ngayketthuc = ngayketthuc.replace("/","");
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(tienThue) FROM PhieuMuon WHERE substr(ngay,7) || substr(ngay,4,2) || substr(ngay,1,2) BETWEEN ? AND ? ",new String[]{ngaybatdau,ngayketthuc});
        if (cursor.getCount() !=  0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }
}
