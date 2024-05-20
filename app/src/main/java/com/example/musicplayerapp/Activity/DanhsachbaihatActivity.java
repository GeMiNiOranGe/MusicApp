package com.example.musicplayerapp.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayerapp.Class.DanhsachbaihatAdapter;
import com.example.musicplayerapp.Model.APIService;
import com.example.musicplayerapp.Model.Album;
import com.example.musicplayerapp.Model.BaiHat;
import com.example.musicplayerapp.Model.ChuDe;
import com.example.musicplayerapp.Model.DataService;
import com.example.musicplayerapp.Model.PlayList;
import com.example.musicplayerapp.Model.TheLoai;
import com.example.musicplayerapp.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachbaihat;
    FloatingActionButton floatingActionButton;
    ImageView imgdanhsachcakhuc;
    ArrayList<BaiHat> mangbaihat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;
    PlayList playList;
    TheLoai theLoai;
    ChuDe chuDe;
    Album album;
    String user;
    TextView tvNoData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DateIntent();
        anhxa();
        init();
        if(playList != null && !playList.getTenPlayList().equals("")){
            setValueInview(playList.getTenPlayList(), playList.getHinhNen());
            GetDataPlaylist(playList.getIdPlayList());
        }
        if(theLoai != null && !theLoai.getTenTheLoai().equals("")){
            setValueInview(theLoai.getTenTheLoai(), theLoai.getHinhTheLoai());
            GetDataTheLoai(theLoai.getIdTheLoai());
        }
        else if(chuDe != null && !chuDe.getTenChuDe().equals("")){
            setValueInview(chuDe.getTenChuDe(), chuDe.getHinhChuDe());
            GetDataChuDe(chuDe.getIdChuDe());
        }
        else if(album != null && !album.getTenAlbum().equals("")){
            setValueInview(album.getTenAlbum(), album.getHinhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }
        else if(user!=null){
            setValueInview("Bài hát yêu thích", "https://avatar-nct.nixcdn.com/topic/share/2016/08/23/a/7/4/b/1471930023672.jpg");
            GetDataBaiHatYeuThich();
        }
    }
    private void GetDataBaiHatYeuThich() {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhSachBaiHatYeuThich(user,"yeuthich");
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                if (mangbaihat.size()>0){
                    danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                    recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                    recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                    eventClick();
                }else{
                    tvNoData.setVisibility(View.VISIBLE);
                    recyclerViewdanhsachbaihat.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {
                Toast.makeText(DanhsachbaihatActivity.this, "thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetDataAlbum(String IdAlbum) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhSachBaiHatAlbum(IdAlbum,LoginActivity.user);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setValueInview(String ten, String hinhNen) {
        try{
            collapsingToolbarLayout.setTitle(ten);
            collapsingToolbarLayout.setExpandedTitleTextSize(60);
            URL url = new URL(hinhNen);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        }catch (Exception e){
            e.printStackTrace();
        }
        Picasso.get().load(hinhNen).into(imgdanhsachcakhuc);
    }
    private void GetDataTheLoai (String IdTheLoai){
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhSachBaiHatTheLoai(IdTheLoai,LoginActivity.user);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
    private void GetDataPlaylist(String IdPlayList) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhSachBaiHatPlayList(IdPlayList,LoginActivity.user);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
    private void GetDataChuDe(String IdChuDe) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhSachBaiHatChuDe(IdChuDe,LoginActivity.user);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
    private void init(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // tạo click để trở về trang trước
        toolbar.setNavigationOnClickListener(v -> finish());
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }
    private void anhxa() {
        tvNoData = findViewById(R.id.tvNoDataDS);
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbardanhsach);
        recyclerViewdanhsachbaihat = findViewById(R.id.recyclerviewdanhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        imgdanhsachcakhuc = findViewById(R.id.imageviewdanhsachcakhuc);
    }

    private void DateIntent() {
        Intent intent  = getIntent();
        if(intent !=  null){
            if(intent.hasExtra("itemplaylist")){
                playList = (PlayList) intent.getSerializableExtra("itemplaylist");
            }
            else if(intent.hasExtra("idtheloai")){
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            else if(intent.hasExtra("idchude")){
                chuDe = (ChuDe) intent.getSerializableExtra("idchude");
            }
            else if(intent.hasExtra("idalbum")){
                album = (Album) intent.getSerializableExtra("idalbum");
            }
            if(intent.hasExtra("iduser")){
                user = (String) intent.getSerializableExtra("iduser");
            }
        }
    }
    private void eventClick() {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(DanhsachbaihatActivity.this, PlayNhacActivity.class);
            intent.putExtra("cacbaihat", mangbaihat);
            startActivity(intent);
        });
    }
}
