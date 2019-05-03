package com.dngwjy.portal.presenters;

import android.util.Log;

import com.dngwjy.portal.data.models.MovieResponse;
import com.dngwjy.portal.data.remote.ApiClient;
import com.dngwjy.portal.views.BaseView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcommingPresenter {
    private BaseView view;
    private final String TAG=this.getClass().getSimpleName();

    public UpcommingPresenter(BaseView view) {
        this.view = view;
    }
    public void getData(){
        view.isLoading(true);
        Call<MovieResponse> movieResponseCall = ApiClient.repository().getUpComing();
        movieResponseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.d(TAG, "onResponse: "+response.raw());
                view.viewResult(true,response.body().result);
                view.isLoading(false);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                view.viewResult(false,null);
                view.isLoading(false);
            }
        });
    }
}
