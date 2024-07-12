package com.example.assignnment.model;

public class Sach {
    private int maSach;
    private String tenSach;
    private int giaThue;
    private int maLoai;
    private int soluongdamuon;
    private String tenloai;

    private String nxb;

    private String hung;
    private String soTrang;


    public Sach(int maSach, String tenSach, int giaThue, int maLoai, int soluongdamuon, String tenloai, String nxb,String hung,String soTrang) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
        this.soluongdamuon = soluongdamuon;
        this.tenloai = tenloai;
        this.nxb = nxb;
        this.hung = hung;
        this.soTrang = soTrang;
    }
    public Sach(int maSach, String tenSach, int giaThue, int maLoai, String tenloai, String nxb,String soTrang) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
        this.tenloai = tenloai;
        this.nxb = nxb;
        this.soTrang = soTrang;
    }
    public Sach(int maSach, String tenSach, int soluongdamuon, String tenloai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.soluongdamuon = soluongdamuon;
        this.tenloai = tenloai;
    }
    public Sach(int maSach, String tenSach, int giaThue) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
    }

    public Sach() {
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public int getSoluongdamuon() {
        return soluongdamuon;
    }

    public void setSoluongdamuon(int soluongdamuon) {
        this.soluongdamuon = soluongdamuon;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public String getHung() {
        return hung;
    }

    public void setHung(String hung) {
        this.hung = hung;
    }

    public String getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(String soTrang) {
        this.soTrang = soTrang;
    }
}
