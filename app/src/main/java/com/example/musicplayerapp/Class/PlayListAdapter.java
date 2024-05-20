package com.example.musicplayerapp.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.musicplayerapp.Model.PlayList;
import com.example.musicplayerapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayListAdapter extends ArrayAdapter<PlayList> {

    public PlayListAdapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
        super(context, resource, objects);
    }
    class ViewHolder{
        TextView txttenplaylist;
        ImageView imgbackground, imgplaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Context context = getContext(); // or getActivity()
        if (context != null) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.dong_playlist, null);
                viewHolder = new ViewHolder();
                viewHolder.txttenplaylist = convertView.findViewById(R.id.textviewtenplaylist);
                viewHolder.imgplaylist = convertView.findViewById(R.id.imageviewplaylist);
                viewHolder.imgbackground = convertView.findViewById(R.id.imageviewbackgroundplaylist);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            PlayList playList = getItem(position);
            Picasso.get().load(playList.getHinhNen()).into(viewHolder.imgbackground);
            Picasso.get().load(playList.getHinhIcon()).into(viewHolder.imgplaylist);
            viewHolder.txttenplaylist.setText(playList.getTenPlayList());

        } else {
            // Handle the case where the context is null
        }
        return convertView;
    }
}
