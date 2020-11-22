package com.example.finalProject.Audio;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidlabs.R;
import com.example.finalProject.Audio.track_Been.TrackBeen;
import com.example.finalProject.BeenClass.SingerNameBeen;
import com.example.finalProject.RetrofitApi.ApiClient;
import com.example.finalProject.RetrofitApi.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class AudioFragment_list_detial extends Fragment {

    private  String idAlbum;
    private ProgressBar progressbar;
    private ListView list_detial;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        idAlbum = getArguments().getString("idAlbum");
        return inflater.inflate(R.layout.fragment_audio_favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        findViews(view);
        getListData();

    }

    private void getListData(){

        progressbar.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient("https://theaudiodb.com/api/v1/json/1/").create(ApiInterface.class);

        Call<TrackBeen> call = apiService.getAlbumList(Integer.parseInt(idAlbum));
        call.enqueue(new Callback<TrackBeen>() {
            @Override
            public void onResponse(Call<TrackBeen> call, Response<TrackBeen> response) {

                progressbar.setVisibility(View.GONE);
                buildListView(response.body());

            }

            @Override
            public void onFailure(Call<TrackBeen> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                progressbar.setVisibility(View.GONE);
            }
        });
    }

    private void findViews(View v) {

        list_detial = v.findViewById(R.id.list_detial);
        progressbar = v.findViewById(R.id.progressbar);

    }

    private void buildListView(TrackBeen body) {

        UsersAdapter customAdapter = new UsersAdapter(getContext(),R.layout.row_audio_list_detial, body.getTrack());

        list_detial.setAdapter(customAdapter);
    }

}
