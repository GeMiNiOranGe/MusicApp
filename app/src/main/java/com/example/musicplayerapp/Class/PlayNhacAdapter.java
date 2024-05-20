package com.example.musicplayerapp.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayerapp.Model.BaiHat;
import com.example.musicplayerapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.BitSet;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> mamgbaihat;
    public PlayNhacAdapter(Context context, ArrayList<BaiHat> mangbaihat){
        this.context = context;
        this.mamgbaihat = mangbaihat;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_bai_hat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = mamgbaihat.get(position);
        holder.txtcasi.setText(baiHat.getCaSi());
        holder.txtindex.setText(position + 1 + "");
        holder.txttenbaihat.setText(baiHat.getTenBaiHat());
    }

    @Override
    public int getItemCount() {
        return mamgbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex, txttenbaihat, txtcasi;
        public ViewHolder(View itemView){
            super(itemView);
            txtcasi = itemView.findViewById(R.id.textviewplaynhactencasi);
            txtindex = itemView.findViewById(R.id.textviewplaynhacindex);
            txttenbaihat = itemView.findViewById(R.id.textviewplaynhactenbaihat);
        }
    }
}
