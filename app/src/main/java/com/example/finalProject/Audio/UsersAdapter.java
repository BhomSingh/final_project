package com.example.finalProject.Audio;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.androidlabs.R;
import com.example.finalProject.Audio.track_Been.Track;
import com.example.finalProject.Audio.track_Been.TrackBeen;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends ArrayAdapter<Track> {


    private int layoutResource;
    private  List<Track> list;

    public UsersAdapter(Context context, int layoutResource, List<Track> threeStringsList) {
        super(context, layoutResource, threeStringsList);
        this.layoutResource = layoutResource;
        list = threeStringsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        Track been = getItem(position);


        if (been != null) {
            TextView txt_singer_Name =  view.findViewById(R.id.txt_view_1);
            TextView txt_writer_Name =  view.findViewById(R.id.txt_view_2);

            txt_singer_Name.setText("Singer Name "+been.getStrAlbum());
            txt_writer_Name.setText("Artiest Name"+been.getStrArtist());


            txt_writer_Name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(parent.getContext(), webView_Activity.class);
                    i.putExtra("singer_Name",been.getStrArtist());
                    getContext().startActivity(i);
                }
            });

        }

        return view;
    }
}
