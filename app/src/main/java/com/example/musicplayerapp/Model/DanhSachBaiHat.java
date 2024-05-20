package com.example.musicplayerapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DanhSachBaiHat {

@SerializedName("IdBaiHat")
@Expose
private String idBaiHat;
@SerializedName("TenBaiHat")
@Expose
private String tenBaiHat;
@SerializedName("TenCaSi")
@Expose
private String tenCaSi;
@SerializedName("HinhBaiHat")
@Expose
private String hinhBaiHat;
@SerializedName("LinkBaiHat")
@Expose
private String linkBaiHat;
@SerializedName("Username")
@Expose
private String username;

public String getIdBaiHat() {
return idBaiHat;
}

public void setIdBaiHat(String idBaiHat) {
this.idBaiHat = idBaiHat;
}

public String getTenBaiHat() {
return tenBaiHat;
}

public void setTenBaiHat(String tenBaiHat) {
this.tenBaiHat = tenBaiHat;
}

public String getTenCaSi() {
return tenCaSi;
}

public void setTenCaSi(String tenCaSi) {
this.tenCaSi = tenCaSi;
}

public String getHinhBaiHat() {
return hinhBaiHat;
}

public void setHinhBaiHat(String hinhBaiHat) {
this.hinhBaiHat = hinhBaiHat;
}

public String getLinkBaiHat() {
return linkBaiHat;
}

public void setLinkBaiHat(String linkBaiHat) {
this.linkBaiHat = linkBaiHat;
}

public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
}

}