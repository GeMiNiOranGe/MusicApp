package com.example.musicplayerapp.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.musicplayerapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_dia_nhac extends Fragment {
    View view;
    CircleImageView circleImageView;
    ObjectAnimator objectAnimator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dia_nhac, container, false);

        circleImageView = view.findViewById(R.id.imageviewcircle);

        objectAnimator = ObjectAnimator.ofFloat(circleImageView, "rotation", 0f, 360f);

        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
        return view;

    }

    public void PauseOrPlay(String s) {
        if (s == "pause") {
            objectAnimator.pause();
        }
        if (s == "play") {
            objectAnimator.start();

        }
    }

    public void Playnhac(final String hinhanh) {
        Handler handler = new Handler();
        handler.postDelayed(() -> Picasso.get().load(hinhanh).into(circleImageView), 300);
    }
}
