package com.example.musicplayerapp.Class;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayerapp.Activity.DanhsachbaihatActivity;
import com.example.musicplayerapp.Model.Album;
import com.example.musicplayerapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachAlbumAdapter extends RecyclerView.Adapter<DanhSachAlbumAdapter.ViewHolder>{

    Context context;
    ArrayList<Album> albumArrayList;

    public DanhSachAlbumAdapter(Context context, ArrayList<Album> albumArrayList){
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_cac_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        Picasso.get().load(album.getHinhAlbum()).into(holder.imgallalbum);
        holder.txtallalbum.setText(album.getTenAlbum());
        holder.tvtencasi.setText(album.getTenCaSiAlbum());
    }


    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgallalbum;
        TextView txtallalbum;
        TextView tvtencasi;
        public ViewHolder(View itemView){
            super(itemView);
            imgallalbum = itemView.findViewById(R.id.imageviewalbum);
            txtallalbum = itemView.findViewById(R.id.textviewtenallalbum);
            tvtencasi = itemView.findViewById(R.id.textviewtencasialbum);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("idalbum", albumArrayList.get(getPosition()));
                context.startActivity(intent);
            });
        }
    }
}
