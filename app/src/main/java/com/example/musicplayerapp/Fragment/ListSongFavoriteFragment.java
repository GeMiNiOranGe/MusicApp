package com.example.musicplayerapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.musicplayerapp.Activity.DanhSachPlayListActivity;
import com.example.musicplayerapp.Activity.DanhsachbaihatActivity;
import com.example.musicplayerapp.Activity.LoginActivity;
import com.example.musicplayerapp.R;


public class ListSongFavoriteFragment extends Fragment {
    View view;
    LinearLayout linearLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_song_favorite, container, false);

        linearLayout = view.findViewById(R.id.itembaihatyeuthich);

        linearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
            intent.putExtra("iduser", LoginActivity.user);
            startActivity(intent);
        });
        return view;
    }
}
