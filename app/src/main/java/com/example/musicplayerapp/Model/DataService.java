package com.example.musicplayerapp.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("BaiHat.php")
    Call<List<BaiHat>> GetDataSong();

    @FormUrlEncoded
    @POST("playlist.php")
    Call<List<PlayList>> GetPlayListCurrentDay(@Field("user") String user);

    @GET("TheLoaiChuDe.php")
    Call<Theloaitrongngay> GetCategoryMusic();

    @GET("album.php")
    Call<List<Album>> GetAlbumHot();
    @FormUrlEncoded
    @POST("search.php")
    Call<List<BaiHat>> GetRandomSong(@Field("random") String random,@Field("user") String user);
    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatYeuThich(@Field("user") String user,@Field("yeuthich") String yeuthich);
    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatAlbum(@Field("IdAlbum") String IdAlbum, @Field("user") String user);
    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatPlayList(@Field("IdPlayList") String IdPlayList, @Field("user") String user);
    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheLoai(@Field("IdTheLoai") String IdTheLoai, @Field("user") String user);
    @FormUrlEncoded
    @POST("DanhSachBaiHat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatChuDe(@Field("IdChuDe") String IdChuDe, @Field("user") String user);
    @FormUrlEncoded
    @POST("search.php")
    Call<List<BaiHat>> GetSearchBaiHat (@Field("tukhoa") String tukhoa, @Field("user") String user);

    @FormUrlEncoded
    @POST("deleteYeuThich.php")
    Call<Void> DeleteYeuThich (@Field("IdBaiHat") int IdBaiHat, @Field("Username") String Username);
    @FormUrlEncoded
    @POST("addYeuThich.php")
    Call<Void> AddYeuThich (@Field("IdBaiHat") int IdBaiHat, @Field("Username") String Username);

    @GET("TatCaChuDe.php")
    Call<List<ChuDe>> GetAllChuDe();
    @GET("TatCaAlbum.php")
    Call<List<Album>> GetAllAlbum();
    @GET("DanhSachPlayListt.php")
    Call<List<PlayList>> GetDanhSachPlayList();

    @FormUrlEncoded
    @POST("TheLoaiTheoChuDe.php")
    Call<List<TheLoai>> GetTheLoaiTheoChuDe ( @Field("idchude")String idchude);
}
