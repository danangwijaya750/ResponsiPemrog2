package com.dngwjy.portal.views;

import com.dngwjy.portal.data.models.Movie;

import java.util.List;

public interface BaseView {
    void isLoading(Boolean state);
    void viewResult(Boolean state,List<Movie> data);
}
