package com.example.assignnment.model;

import java.util.Date;

public class PhieuMuonnnnnn {

    private int maPM;
    private String maTT;
    private int maTV;
    private int maSach;
    private String ngay;
    private int tienThue;
    private int traSach;

    private String tentv;
    private String tentt;
    private String tenSach;

    private String bienLai;




    public PhieuMuonnnnnn() {
    }

    public PhieuMuonnnnnn(int maPM,int maTV, String tentv, String maTT,String tentt,  int maSach, String tenSach, String ngay,int traSach, int tienThue , String bienLai) {
        this.maPM = maPM;
        this.maTT = maTT;
        this.maTV = maTV;
        this.maSach = maSach;
        this.ngay = ngay;
        this.tienThue = tienThue;
        this.traSach = traSach;
        this.tentv = tentv;
        this.tentt = tentt;
        this.tenSach = tenSach;
        this.bienLai = bienLai;

    }

    public PhieuMuonnnnnn( int maTV, String maTT, int maSach, String ngay, int traSach,int tienThue,String bienLai) {
        this.maTV = maTV;
        this.maTT = maTT;
        this.maSach = maSach;
        this.ngay = ngay;
        this.traSach = traSach;
        this.tienThue = tienThue;
        this.bienLai = bienLai;

    }


    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }

    public String getTentv() {
        return tentv;
    }

    public void setTentv(String tentv) {
        this.tentv = tentv;
    }

    public String getTentt() {
        return tentt;
    }

    public void setTentt(String tentt) {
        this.tentt = tentt;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getBienLai() {
        return bienLai;
    }

    public void setBienLai(String bienLai) {
        this.bienLai = bienLai;
    }
}
