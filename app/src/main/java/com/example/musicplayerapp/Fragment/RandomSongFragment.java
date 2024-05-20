package com.example.musicplayerapp.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicplayerapp.Activity.LoginActivity;
import com.example.musicplayerapp.Class.SearchAdapter;
import com.example.musicplayerapp.Model.APIService;
import com.example.musicplayerapp.Model.BaiHat;
import com.example.musicplayerapp.Model.DataService;
import com.example.musicplayerapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomSongFragment extends Fragment {
    View view;
    SearchAdapter searchAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_random_song, container, false);

        recyclerView = view.findViewById(R.id.recyclerviewRandomSong);

        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetRandomSong("random", LoginActivity.user);

        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> mangbaihat = (ArrayList<BaiHat>) response.body();
                searchAdapter = new SearchAdapter(getActivity(), mangbaihat);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(searchAdapter);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {
            }
        });
    }
}

