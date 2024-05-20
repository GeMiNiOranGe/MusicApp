package com.example.musicplayerapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.musicplayerapp.Class.DanhSachTheLoaiTheoChuDeAdapter;
import com.example.musicplayerapp.Model.APIService;
import com.example.musicplayerapp.Model.ChuDe;
import com.example.musicplayerapp.Model.DataService;
import com.example.musicplayerapp.Model.TheLoai;
import com.example.musicplayerapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTheLoaiTheoChuDeActivity extends AppCompatActivity {

    ChuDe chuDe;
    RecyclerView recyclerViewtheloaitheochude;
    Toolbar toolbartheloaitheochude;
    DanhSachTheLoaiTheoChuDeAdapter danhSachTheLoaiTheoChuDeAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_the_loai_theo_chu_de);
        GetIntent();
        init();
        GetData();

    }

    private void GetData(){
        DataService dataService = APIService.getService();
        Call<List<TheLoai>> callback = dataService.GetTheLoaiTheoChuDe(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> mangtheloai = (ArrayList<TheLoai>) response.body();
                danhSachTheLoaiTheoChuDeAdapter = new DanhSachTheLoaiTheoChuDeAdapter(DanhSachTheLoaiTheoChuDeActivity.this, mangtheloai);
                recyclerViewtheloaitheochude.setLayoutManager(new GridLayoutManager(DanhSachTheLoaiTheoChuDeActivity.this, 2));
                recyclerViewtheloaitheochude.setAdapter(danhSachTheLoaiTheoChuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init(){
        recyclerViewtheloaitheochude = findViewById(R.id.recyclerviewtheloaithetheochude);
        toolbartheloaitheochude = findViewById(R.id.toolbartheloaitheochude);
        setSupportActionBar(toolbartheloaitheochude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbartheloaitheochude.setNavigationOnClickListener(v -> finish());
    }



    private void GetIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("chude")){
            chuDe = (ChuDe) intent.getSerializableExtra("chude");


        }

    }


}