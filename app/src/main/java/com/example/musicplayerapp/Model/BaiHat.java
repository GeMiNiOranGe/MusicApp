package com.example.musicplayerapp.Model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaiHat implements Parcelable {
    @SerializedName("IdBaiHat")
    @Expose
    private String idBaiHat;
    @SerializedName("IdTheLoai")
    @Expose
    private String theLoai;
    @SerializedName("TenBaiHat")
    @Expose
    private String tenBaiHat;
    @SerializedName("TenCaSi")
    @Expose
    private String caSi;
    @SerializedName("HinhBaiHat")
    @Expose
    private String hinh;
    @SerializedName("LinkBaiHat")
    @Expose
    private String link;
    @SerializedName("Username")
    @Expose
    private String username;

    public String getIdBaiHat() {
        return idBaiHat;
    }

    public void setIdBaiHat(String idBaiHat) {
        this.idBaiHat = idBaiHat;
    }

    public String getIdTheLoai() {
        return theLoai;
    }

    public void setIdTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public String getCaSi() {
        return caSi;
    }

    public void setTenCaSi(String tenCaSi) {
        this.caSi = tenCaSi;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinhBaiHat(String hinhBaiHat) {
        this.hinh = hinhBaiHat;
    }

    public String getLink() {
        return link;
    }

    public void setLinkBaiHat(String linkBaiHat) {
        this.link = linkBaiHat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    protected BaiHat(Parcel in) {
        idBaiHat = in.readString();
        theLoai = in.readString();
        tenBaiHat = in.readString();
        caSi = in.readString();
        hinh = in.readString();
        link = in.readString();
        username = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idBaiHat);
        dest.writeString(theLoai);
        dest.writeString(tenBaiHat);
        dest.writeString(caSi);
        dest.writeString(hinh);
        dest.writeString(link);
        dest.writeString(username);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BaiHat> CREATOR = new Creator<BaiHat>() {
        @Override
        public BaiHat createFromParcel(Parcel in) {
            return new BaiHat(in);
        }

        @Override
        public BaiHat[] newArray(int size) {
            return new BaiHat[size];
        }
    };
}