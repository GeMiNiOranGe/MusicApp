package com.example.musicplayerapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.musicplayerapp.Activity.DanhSachChuDeActivity;
import com.example.musicplayerapp.Activity.DanhsachbaihatActivity;
import com.example.musicplayerapp.Model.APIService;
import com.example.musicplayerapp.Model.ChuDe;
import com.example.musicplayerapp.Model.DataService;
import com.example.musicplayerapp.Model.TheLoai;
import com.example.musicplayerapp.Model.Theloaitrongngay;
import com.example.musicplayerapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe_TheLoai_ToDay extends Fragment {

    View view;
    Context context;
    HorizontalScrollView horizontalScrollView;
    TextView txtxemthemchudetheloai;
    final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
    final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chu_de_the_loai_to_day, container, false);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollview);
        txtxemthemchudetheloai = view.findViewById(R.id.textviewxemthem);

        txtxemthemchudetheloai.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DanhSachChuDeActivity.class);
            startActivity(intent);
        });

        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<Theloaitrongngay> callback = dataService.GetCategoryMusic();
        callback.enqueue(new Callback<Theloaitrongngay>() {
            @Override
            public void onResponse(Call<Theloaitrongngay> call, Response<Theloaitrongngay> response) {
                context = getContext(); // or getActivity()
                if (context != null) {
                    Theloaitrongngay theloaitrongngay = response.body();
                    theLoaiArrayList.addAll(theloaitrongngay.getTheLoai());
                    chuDeArrayList.addAll(theloaitrongngay.getChuDe());

                    LinearLayout linearLayout = new LinearLayout(getActivity());
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                    LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(550, 309);
                    layout.setMargins(10, 20, 10, 30);
                    for (int j = 0; j < theLoaiArrayList.size(); j++) {
                        CardView cardView = new CardView(getActivity());
                        cardView.setRadius(20);
                        ImageView imageView = new ImageView(getActivity());
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        if (theLoaiArrayList.get(j).getHinhTheLoai() != null) {
                            Picasso.get().load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);
                        }
                        int finalJ = j;
                        cardView.setLayoutParams(layout);
                        cardView.addView(imageView);
                        linearLayout.addView(cardView);
                        imageView.setOnClickListener(view -> {
                            Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("idtheloai", theLoaiArrayList.get(finalJ));
                            context.startActivity(intent);
                        });
                    }
                    for (int i = 0; i < chuDeArrayList.size(); i++) {
                        CardView cardView = new CardView(getActivity());
                        cardView.setRadius(20);
                        ImageView imageView = new ImageView(getActivity());
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        if (chuDeArrayList.get(i).getHinhChuDe() != null) {
                            Picasso.get().load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                        }
                        cardView.setLayoutParams(layout);
                        cardView.addView(imageView);
                        linearLayout.addView(cardView);
                        int finalI = i;
                        imageView.setOnClickListener(view -> {
                            Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("idchude", chuDeArrayList.get(finalI));
                            context.startActivity(intent);
                        });
                    }
                    horizontalScrollView.addView(linearLayout);
                } else {
                    // Handle the case where the context is null
                }

            }

            @Override
            public void onFailure(Call<Theloaitrongngay> call, Throwable t) {

            }
        });

    }
}