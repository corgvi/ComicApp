package com.example.cuongvvph18550_asm_mob403.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("id_truyen")
    @Expose
    private String idTruyen;
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("noi_dung")
    @Expose
    private String noiDung;

    public String getIdTruyen() {
        return idTruyen;
    }

    public void setIdTruyen(String idTruyen) {
        this.idTruyen = idTruyen;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Comment() {
    }

    public Comment(String idTruyen, String idUser, String noiDung) {
        this.idTruyen = idTruyen;
        this.idUser = idUser;
        this.noiDung = noiDung;
    }
}
