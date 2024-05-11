package com.example.assignnment.model;

public class LoaiSachh {

    private int id;
    private String tenLoai;


    public LoaiSachh() {
    }

    public LoaiSachh(int id, String tenLoai) {
        this.id = id;
        this.tenLoai = tenLoai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
