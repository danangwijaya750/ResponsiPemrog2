package com.dngwjy.portal.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private  static Retrofit.Builder builder(){
        return new Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create());
    }
    private static Retrofit retrofit(){
        return builder().build();
    }
    public static  ApiRepository repository(){
        return retrofit().create(ApiRepository.class);
    }
}
