package com.example.ng.foodsh;

import java.io.Serializable;

public class DiaDiem implements Serializable{
    private int id_dd;
    private String tendd;
    private String lienhe;
    private String giohoatdong;
    private String diachi;
    private int giacaonhat;
    private int giathapnhat;
    private int id_vitri;
    private int id_us;
    private int id_danhgia;
    private String image;


    public DiaDiem(int id_dd, String tendd, String lienhe, String giohoatdong, String diachi, int giacaonhat, int giathapnhat, int id_vitri, int id_us, int id_danhgia, String image) {
        this.id_dd = id_dd;
        this.tendd = tendd;
        this.lienhe = lienhe;
        this.giohoatdong = giohoatdong;
        this.diachi = diachi;
        this.giacaonhat = giacaonhat;
        this.giathapnhat = giathapnhat;
        this.id_vitri = id_vitri;
        this.id_us = id_us;
        this.id_danhgia = id_danhgia;
        this.image = image;
    }

    public int getId_dd() {
        return id_dd;
    }

    public void setId_dd(int id_dd) {
        this.id_dd = id_dd;
    }

    public String getTendd() {
        return tendd;
    }

    public void setTendd(String tendd) {
        this.tendd = tendd;
    }

    public String getLienhe() {
        return lienhe;
    }

    public void setLienhe(String lienhe) {
        this.lienhe = lienhe;
    }

    public String getGiohoatdong() {
        return giohoatdong;
    }

    public void setGiohoatdong(String giohoatdong) {
        this.giohoatdong = giohoatdong;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getGiacaonhat() {
        return giacaonhat;
    }

    public void setGiacaonhat(int giacaonhat) {
        this.giacaonhat = giacaonhat;
    }

    public int getGiathapnhat() {
        return giathapnhat;
    }

    public void setGiathapnhat(int giathapnhat) {
        this.giathapnhat = giathapnhat;
    }

    public int getId_vitri() {
        return id_vitri;
    }

    public void setId_vitri(int id_vitri) {
        this.id_vitri = id_vitri;
    }

    public int getId_us() {
        return id_us;
    }

    public void setId_us(int id_us) {
        this.id_us = id_us;
    }

    public int getId_danhgia() {
        return id_danhgia;
    }

    public void setId_danhgia(int id_danhgia) {
        this.id_danhgia = id_danhgia;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
