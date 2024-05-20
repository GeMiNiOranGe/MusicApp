package com.example.musicplayerapp.Class;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayerapp.Activity.DanhSachTheLoaiTheoChuDeActivity;
import com.example.musicplayerapp.Model.ChuDe;
import com.example.musicplayerapp.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class DanhSachChuDeAdapter extends RecyclerView.Adapter<DanhSachChuDeAdapter.ViewHolder>{
    @NonNull
    Context context;
    ArrayList<ChuDe> mangchude;

    public DanhSachChuDeAdapter(@NonNull Context context, ArrayList<ChuDe> mangchude) {
        this.context = context;
        this.mangchude = mangchude;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_cac_chu_de, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe = mangchude.get(position);
        Picasso.get().load(chuDe.getHinhChuDe()).into(holder.imgchude);
    }

    @Override
    public int getItemCount() {
        return mangchude.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgchude;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgchude = itemView.findViewById(R.id.imageviewdongcacchude);
            imgchude.setOnClickListener(v -> {
                Intent intent = new Intent(context, DanhSachTheLoaiTheoChuDeActivity.class);
                intent.putExtra("chude",mangchude.get(getPosition()));
                context.startActivity(intent);
            });

        }
    }
}
