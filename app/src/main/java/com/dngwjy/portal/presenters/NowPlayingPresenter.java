package com.dngwjy.portal.presenters;

import android.util.Log;

import com.dngwjy.portal.data.models.Movie;
import com.dngwjy.portal.data.models.MovieResponse;
import com.dngwjy.portal.data.remote.ApiClient;
import com.dngwjy.portal.views.BaseView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlayingPresenter {
    private BaseView view;
    private final String TAG=this.getClass().getSimpleName();
    public NowPlayingPresenter(BaseView view) {
        this.view = view;
    }
    public void getData(){
        Call<MovieResponse> listCall= ApiClient.repository().getNowPlaying();
        listCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse: "+response.raw());
                    view.viewResult(response.body().result);
                    view.isLoading(false);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
                view.isLoading(false);
            }
        });
    }

}
