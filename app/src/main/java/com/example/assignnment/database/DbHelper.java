package com.example.assignnment.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "PNLIBB";
    private static final int DB_VERSION = 26;
    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create GioHang table with quantity column
        String createTableGioHang = "CREATE TABLE GioHang (" +
                "maSach INTEGER PRIMARY KEY, " +
                "tenSach TEXT NOT NULL, " +
                "giaThue INTEGER NOT NULL, " +
                "quantity INTEGER DEFAULT 1)"; // Added quantity column with default value
        db.execSQL(createTableGioHang);



        // Tạo bảng thủ thư
        String createTableThuThu = "CREATE TABLE ThuThu (" +
                "maTT TEXT PRIMARY KEY, " +
                "hoTen TEXT NOT NULL, " +
                "matKhau TEXT NOT NULL)" ;
        db.execSQL(createTableThuThu);
        String insertDefaultThuThu = "INSERT INTO ThuThu (maTT, hoTen, matKhau) VALUES ('admin', 'Duy Hung', '123456')";
        db.execSQL(insertDefaultThuThu);
        // Tạo bảng thành viên
        String createTableThanhVien = "CREATE TABLE ThanhVien (" +
                "maTV INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "hoTen TEXT NOT NULL, " +
                "namSinh TEXT NOT NULL,"+
                ////////////////////////


                "Cccd TEXT NOT NULL,"+

                "haHa TEXT NOT NULL)"
                ;
        db.execSQL(createTableThanhVien);
        db.execSQL("INSERT INTO ThanhVien VALUES (1,'Nguyễn Ngọc Hưng','2000','ph43753','haha'),(2,'Trịnh Thị Nhung','2000','ph43753','bala'),(3,'Nguyễn Ngọc Minh','2000','ph43753','coca')");

        // Tạo bảng thể loại sách
        String createTableLoaiSach = "CREATE TABLE LoaiSach(" +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenLoai TEXT NOT NULl)";
        db.execSQL(createTableLoaiSach);
        db.execSQL("INSERT INTO LoaiSach VALUES (1, 'Hoa vi ni'),(2,'Tình cảm'),(3, 'Giáo khoa')");

        // Tạo bảng sách
        String createTableSach = "CREATE TABLE Sach (" +
                "maSach INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenSach TEXT NOT NULL, " +
                "giaThue INTEGER NOT NULL, " +
                "maLoai INTEGER REFERENCES LoaiSach(maLoai),"+

                /////////////////
                "nxb TEXT NOT NULL,"+
                "soTrang TEXT NOT NULL)"

                ;
        db.execSQL(createTableSach);
        db.execSQL("INSERT INTO Sach VALUES (1, 'Đắc Nhân Tâm', 2500, 1,'vn',30), (2, 'Nhà Giả Kim', 1000, 1,'hk',40), (3, 'Tây Du Ký', 2000, 3,'ncl',50)");
        // Tạo bảng phiếu mượn
        String createTablePhieuMuon = "CREATE TABLE PhieuMuon(" +
                "maPM INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maTT TEXT NOT NULL, " +
                "maTV INTEGER NOT NULL, " +
                "maSach INTEGER NOT NULL, " +
                "tienThue INTEGER NOT NULL," +
                "ngay DATE NOT NULL," +
                "traSach INTEGER NOT NULL," +

                "bienLai TEXT NOT NULL," +



                "FOREIGN KEY(maTT) REFERENCES ThuThu(maTT), " +
                "FOREIGN KEY(maTV) REFERENCES ThanhVien(maTV), " +
                "FOREIGN KEY(maSach) REFERENCES Sach(maSach))";
        db.execSQL(createTablePhieuMuon);
        db.execSQL("INSERT INTO PhieuMuon VALUES (1,'admin',1, 1,2500,'19/03/2022', 0,'New'),(2,'admin',2, 2,1000,'19/03/2022', 1,'Old'),(3,'admin',3, 3,2000,'19/03/2022', 0,'New')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
     String dropTableLoaiThuThu = "drop table if exists ThuThu";
     db.execSQL(dropTableLoaiThuThu);
        String dropTableThanhVien = "drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiLoaiSach);
        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);

        db.execSQL("DROP TABLE IF EXISTS GioHang");

        onCreate(db);
    }
}
