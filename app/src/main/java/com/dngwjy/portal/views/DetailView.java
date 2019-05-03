package com.dngwjy.portal.views;

import com.dngwjy.portal.data.models.DetailMovie;

public interface DetailView {
    void isLoading(Boolean state);
    void viewResult(Boolean state,DetailMovie data);
}
