package com.example.finalProject.RetrofitApi;


import com.example.finalProject.Audio.track_Been.TrackBeen;
import com.example.finalProject.BeenClass.SingerNameBeen;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
 
 
public interface ApiInterface {

    @GET("searchalbum.php?")
    Call<SingerNameBeen> getSingerNameListint(@Query("s") String apiKey);

    // set idAlbum
    @GET("track.php?")
    Call<TrackBeen> getAlbumList(@Query("m") int idAlbum);
}