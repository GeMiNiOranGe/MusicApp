package com.example.musicplayerapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChuDe implements Serializable {

    @SerializedName("TenChuDe")
    @Expose
    private String tenChuDe;
    @SerializedName("IdChuDe")
    @Expose
    private String idChuDe;
    @SerializedName("HinhChuDe")
    @Expose
    private String hinhChuDe;

    public String getTenChuDe() {
        return tenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        this.tenChuDe = tenChuDe;
    }

    public String getHinhChuDe() {
        return hinhChuDe;
    }

    public void setHinhChuDe(String idChuDe) {
        this.idChuDe = idChuDe;
    }
    public String getIdChuDe() {
        return idChuDe;
    }

    public void setIdChuDe(String idChuDe) {
        this.idChuDe = idChuDe;
    }

}