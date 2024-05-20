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

import com.example.musicplayerapp.Activity.LoginActivity;
import com.example.musicplayerapp.Activity.PlayNhacActivity;
import com.example.musicplayerapp.Model.APIService;
import com.example.musicplayerapp.Model.BaiHat;
import com.example.musicplayerapp.Model.DataService;
import com.example.musicplayerapp.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> mangbaihat;
    public DanhsachbaihatAdapter(Context context, ArrayList<BaiHat> mangbaihat){
        this.context = context;
        this.mangbaihat = mangbaihat;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_bai_hat,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = mangbaihat.get(position);
        holder.txtcasi.setText(baiHat.getCaSi());
        holder.txttenbaihat.setText(baiHat.getTenBaiHat());
        holder.txtindex.setText(position + 1 + "");
        if (baiHat.getUsername().equals(LoginActivity.user)){
            holder.imgluotthich.setImageResource(R.drawable.baseline_favorite_red);
        }
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex, txttenbaihat, txtcasi;
        ImageView imgluotthich;
        public ViewHolder(View itemView) {
            super(itemView);
            txtcasi = itemView.findViewById(R.id.textviewcasi);
            txtindex = itemView.findViewById(R.id.textviewdanhsachindex);
            txttenbaihat  = itemView.findViewById(R.id.textviewtenbaihat);
            imgluotthich = itemView.findViewById(R.id.imageviewluotthichdanhsachbaihat);
            imgluotthich.setOnClickListener(view -> {
                int i= getAdapterPosition();
                BaiHat baihat= mangbaihat.get(i);
                if (baihat.getUsername().equals(LoginActivity.user)){
                    DataService dataService = APIService.getService();
                    Call<Void> callback = dataService.DeleteYeuThich(Integer.parseInt(baihat.getIdBaiHat()) ,LoginActivity.user);
                    callback.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            imgluotthich.setImageResource(R.drawable.baseline_favorite_black);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                } else{
                    DataService dataService = APIService.getService();
                    Call<Void> callback = dataService.AddYeuThich(Integer.parseInt(baihat.getIdBaiHat()) ,LoginActivity.user);
                    callback.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            imgluotthich.setImageResource(R.drawable.baseline_favorite_red);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                }
            });
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, PlayNhacActivity.class);
                intent.putExtra("cakhuc", mangbaihat.get(getPosition()));
                context.startActivity(intent);
            });
        }
    }

//    private void evenClisk(){
//        FloatingActionButton floatingActionButton.setEnable(true);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    Intent intent = new Intent(DanhsachbaihatActivity.this, PlayerActivity.class);
//                    intent.putExtra("cacbaihat", mangbaihat);
//                    startActivity(intent);
//            }
//        });
//
//    }
}
