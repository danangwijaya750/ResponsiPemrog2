package com.dngwjy.portal.presenters;



import android.util.Log;

import com.dngwjy.portal.data.models.DetailMovie;
import com.dngwjy.portal.data.remote.ApiClient;
import com.dngwjy.portal.views.BaseView;
import com.dngwjy.portal.views.DetailView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DetailPresenter {
    DetailView view;

    public DetailPresenter(DetailView view) {
        this.view = view;
    }
    public void getData(int id){
        view.isLoading(true);
        Call<DetailMovie> detailMovieCall= ApiClient.repository().getDetail(id);
        detailMovieCall.enqueue(new Callback<DetailMovie>() {
            @Override
            public void onResponse(Call<DetailMovie> call, Response<DetailMovie> response) {
                Log.d(TAG, "onResponse: "+response.raw());
                view.viewResult(true,response.body());
                view.isLoading(false);
            }

            @Override
            public void onFailure(Call<DetailMovie> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
                view.viewResult(false,null);
                view.isLoading(false);
            }
        });
    }
}
