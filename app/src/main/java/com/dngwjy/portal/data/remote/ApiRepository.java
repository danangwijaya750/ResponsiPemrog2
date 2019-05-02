package com.dngwjy.portal.data.remote;

import com.dngwjy.portal.data.models.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRepository {
    @GET("3/movie/now_playing?api_key=" + "75afbeac6103b3207a6e977639450db4" + "&page=1")
    public Call<MovieResponse> getNowPlaying();

    @GET("3/movie/upcoming?api_key="+ "75afbeac6103b3207a6e977639450db4" +"&language=en-US&page=1")
    public Call<MovieResponse> getUpComing();
}
