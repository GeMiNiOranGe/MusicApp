package com.example.musicplayerapp.Activity;

import static com.example.musicplayerapp.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayerapp.Class.DanhSachChuDeAdapter;
import com.example.musicplayerapp.Model.APIService;
import com.example.musicplayerapp.Model.ChuDe;
import com.example.musicplayerapp.Model.DataService;
import com.example.musicplayerapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachChuDeActivity extends AppCompatActivity {

    RecyclerView recyclerViewtatcachude;
    Toolbar toolbartatcachude;

    DanhSachChuDeAdapter danhSachChuDeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_danh_sach_chu_de);
        init();
        GetData();
    }
    private void GetData(){
        DataService dataservice = APIService.getService();
        Call<List<ChuDe>> callback = dataservice.GetAllChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> mangchude = (ArrayList<ChuDe>) response.body();
                danhSachChuDeAdapter = new DanhSachChuDeAdapter(DanhSachChuDeActivity.this, mangchude);
                recyclerViewtatcachude.setLayoutManager(new GridLayoutManager(DanhSachChuDeActivity.this, 1 ));
                recyclerViewtatcachude.setAdapter(danhSachChuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void init(){
        recyclerViewtatcachude = findViewById(R.id.recyclerviewAllChuDe);
        toolbartatcachude = findViewById(R.id.toolbarallchude);
        setSupportActionBar(toolbartatcachude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbartatcachude.setNavigationOnClickListener(v -> finish());
    }
}