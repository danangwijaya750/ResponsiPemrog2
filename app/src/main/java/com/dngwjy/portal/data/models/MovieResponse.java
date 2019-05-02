package com.dngwjy.portal.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("results")
    public List<Movie> result;
}
