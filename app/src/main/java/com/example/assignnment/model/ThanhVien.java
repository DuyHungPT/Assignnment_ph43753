package com.example.assignnment.model;

public class ThanhVien {

    private  int maTV;
    private String hoTen;
    private String namSinh;
    private String cccd;
    private String haHa;

    public ThanhVien() {
    }

    public ThanhVien(int maTV, String hoTen, String namSinh, String cccd, String haHa) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.cccd = cccd;
        this.haHa = haHa;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getHaHa() {
        return haHa;
    }

    public void setHaHa(String haHa) {
        this.haHa = haHa;
    }

    @Override
    public String toString() {
        return "ThanhVien{" +
                "maTV=" + maTV +
                ", hoTen='" + hoTen + '\'' +
                ", namSinh='" + namSinh + '\'' +

                '}';
    }
}
