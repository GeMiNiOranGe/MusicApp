package com.example.musicplayerapp.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayerapp.Class.DanhSachPlayListAdapter;
import com.example.musicplayerapp.Model.APIService;
import com.example.musicplayerapp.Model.DataService;
import com.example.musicplayerapp.Model.PlayList;
import com.example.musicplayerapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachPlayListActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachplaylist;
    DanhSachPlayListAdapter danhSachPlayListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_play_list);
        anhxa();
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<PlayList>> callback = dataService.GetDanhSachPlayList();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                ArrayList<PlayList> mangplaylist = (ArrayList<PlayList>) response.body();
                danhSachPlayListAdapter = new DanhSachPlayListAdapter(DanhSachPlayListActivity.this, mangplaylist);
                recyclerViewdanhsachplaylist.setLayoutManager(new GridLayoutManager(DanhSachPlayListActivity.this, 2));
                recyclerViewdanhsachplaylist.setAdapter(danhSachPlayListAdapter);
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbardanhsachplaylist);
        recyclerViewdanhsachplaylist = findViewById(R.id.recyclerviewdanhsachplaylist);

    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("PlayLists");
        toolbar.setTitleTextColor(getResources().getColor(R.color.my_purple));
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}