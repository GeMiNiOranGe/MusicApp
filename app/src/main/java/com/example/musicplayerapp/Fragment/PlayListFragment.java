package com.example.musicplayerapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.musicplayerapp.Activity.DanhSachPlayListActivity;
import com.example.musicplayerapp.Activity.DanhsachbaihatActivity;
import com.example.musicplayerapp.Activity.LoginActivity;
import com.example.musicplayerapp.Class.PlayListAdapter;
import com.example.musicplayerapp.Model.APIService;
import com.example.musicplayerapp.Model.DataService;
import com.example.musicplayerapp.Model.PlayList;
import com.example.musicplayerapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayListFragment extends Fragment {
    View view;
    ListView lvplaylist;
    TextView txttitleplaylist, txtviewxemthemplaylist;
    PlayListAdapter playListAdapter;
    ArrayList<PlayList> mangplaylist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);
        lvplaylist = view.findViewById(R.id.listviewplaylist);
        txttitleplaylist = view.findViewById(R.id.textviewtitleplaylist);
        txtviewxemthemplaylist = view.findViewById(R.id.textviewmoreplaylist);
        txtviewxemthemplaylist.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DanhSachPlayListActivity.class);
            startActivity(intent);
        });
        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<PlayList>> callback = dataService.GetPlayListCurrentDay(LoginActivity.user);
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                mangplaylist = (ArrayList<PlayList>) response.body();
                Log.d("BBB", mangplaylist.get(0).getTenPlayList());
                playListAdapter = new PlayListAdapter(getActivity(), android.R.layout.simple_list_item_1, mangplaylist);
                lvplaylist.setAdapter(playListAdapter);
                setListViewHeightBasedOnChildren(lvplaylist);
                lvplaylist.setOnItemClickListener((parent, view, position, id) -> {
                    Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                    intent.putExtra("itemplaylist", mangplaylist.get(position));
                    startActivity(intent);
                });
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
