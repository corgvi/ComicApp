package com.example.cuongvvph18550_asm_mob403.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Truyen implements Serializable {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("ten_truyen")
    @Expose
    private String tenTruyen;
    @SerializedName("mo_ta")
    @Expose
    private String moTa;
    @SerializedName("ten_tac_gia")
    @Expose
    private String tenTacGia;
    @SerializedName("anh_bia")
    @Expose
    private String anhBia;
    @SerializedName("danh_sach_truyen")
    @Expose
    private List<String> danhSachTruyen = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public String getAnhBia() {
        return anhBia;
    }

    public void setAnhBia(String anhBia) {
        this.anhBia = anhBia;
    }

    public List<String> getDanhSachTruyen() {
        return danhSachTruyen;
    }

    public void setDanhSachTruyen(List<String> danhSachTruyen) {
        this.danhSachTruyen = danhSachTruyen;
    }

    public Truyen(String tenTruyen, String moTa, String tenTacGia, String anhBia, List<String> danhSachTruyen) {
        this.tenTruyen = tenTruyen;
        this.moTa = moTa;
        this.tenTacGia = tenTacGia;
        this.anhBia = anhBia;
        this.danhSachTruyen = danhSachTruyen;
    }

    public Truyen() {
    }
}
