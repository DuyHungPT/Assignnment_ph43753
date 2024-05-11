package com.example.assignnment.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assignnment.database.DbHelper;
import com.example.assignnment.model.ThanhVien;
import com.example.assignnment.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class DemoDB {

    private SQLiteDatabase db;

    ThanhVienDAO thanhVienDAO;
    ThuThuDAO thuThuDAO;
    static final String TAG = "//=========";
    public DemoDB(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();

        thanhVienDAO = new ThanhVienDAO(context);
        thuThuDAO = new ThuThuDAO(context);
    }
   public void thanhVien(){
//       List<ThanhVien> ls = new ArrayList<>();
       ThanhVien tv = new ThanhVien(1,"Duy Hung","2001");
       if (thanhVienDAO.insert(tv)>0){
           Log.i(TAG,"Them Thanh Cong");
       }else {
           Log.i(TAG,"Them That bai");
       }
       Log.i(TAG,"++++++++++++++++++++++");
       Log.i(TAG,"Tong So Thanh Vien" + thanhVienDAO.getAll().size());

       Log.i(TAG,"+++++++++Sau Khi Sua+++++++++++++");
       tv= new ThanhVien(1,"Duy Hung DZ","2001");
       thanhVienDAO.update(tv);
       Log.i(TAG,"Tong So Thanh Vien" + thanhVienDAO.getAll().size());

       thanhVienDAO.delete("2");
       Log.i(TAG,"+++++++++Sau Khi Xoa+++++++++++++");
       Log.i(TAG,"Tong So Thanh Vien" + thanhVienDAO.getAll().size());
   }

   public void thuThu() {
       ThuThu tt = new ThuThu("admin","Duy Hung","admin123");
       thuThuDAO.insert(tt);
       Log.i(TAG,"++++++++++++++++++++++");
       Log.i(TAG,"Tong So Thanh Vien" + thuThuDAO.getAll().size());

        tt = new ThuThu("admin","Tran ADMIN", "123456");
       thuThuDAO.updatePass(tt);
       Log.i(TAG,"+++++++++++Sau update+++++++++++");
       Log.i(TAG,"Tong So Thanh Vien" + thuThuDAO.getAll().size());

       if (thuThuDAO.CheckLogin("admin","123456")>0){
           Log.i(TAG,"Dang Nhap Thanh Cong");
       }else{
           Log.i(TAG,"Dang Nhap khong Thanh Cong");
       }
   }
}
