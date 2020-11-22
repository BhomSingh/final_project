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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidlabs.R;
import com.example.finalProject.BeenClass.SingerNameBeen;
import com.example.finalProject.RetrofitApi.ApiClient;
import com.example.finalProject.RetrofitApi.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class AudioListFragment extends Fragment {

    private ListView lv;
    private ArrayAdapter<String> adapter;
    private ArrayList<Album> albums;
    ArrayList<String> list = new ArrayList<>();
    private AudioActivity activity;
    private ProgressBar progressbar;

    private String  singer_name;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (AudioActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         singer_name = getArguments().getString("singetName");
        return inflater.inflate(R.layout.fragment_audio_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        progressbar = view.findViewById(R.id.progressbar);
        getListData();
        findViews(view);


    }

    private void getListData(){

        progressbar.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient("https://www.theaudiodb.com/api/v1/json/1/").create(ApiInterface.class);

        Call<SingerNameBeen> call = apiService.getSingerNameListint(singer_name);
        call.enqueue(new Callback<SingerNameBeen>() {
            @Override
            public void onResponse(Call<SingerNameBeen> call, Response<SingerNameBeen> response) {

                for (int i=0; i<response.body().getAlbum().size(); i++){
                    list.add(response.body().getAlbum().get(i).getStrAlbum());
                }
                progressbar.setVisibility(View.GONE);
                buildListView(response.body());

//                Toast.makeText(getContext(),""+response.body().getAlbum().get(0).getStrArtist(),Toast.LENGTH_SHORT).show();
//                List<Movie> movies = response.body().getResults();
//                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<SingerNameBeen> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                progressbar.setVisibility(View.GONE);
            }
        });
    }

    private void findViews(View v) {
        lv = v.findViewById(R.id.lvAudioList);
    }

    private void buildListView(SingerNameBeen body) {

        adapter = new ArrayAdapter<>(activity, R.layout.row_audio_list, list);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener((adapterView, view, i, l) -> {

            AudioFragment_list_detial audioFragmentListdetial = new AudioFragment_list_detial();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frAudio, audioFragmentListdetial);
            fragmentTransaction.commit();

            Bundle bundle = new Bundle();
            bundle.putString("idAlbum", body.getAlbum().get(i).getIdAlbum());
            //set Fragmentclass Arguments
            audioFragmentListdetial.setArguments(bundle);

        });
    }
}
