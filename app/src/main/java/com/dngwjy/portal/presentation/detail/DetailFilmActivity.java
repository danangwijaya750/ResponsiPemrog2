package com.dngwjy.portal.presentation.detail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dngwjy.portal.R;
import com.dngwjy.portal.data.models.DetailMovie;
import com.dngwjy.portal.data.models.Movie;
import com.dngwjy.portal.presenters.DetailPresenter;
import com.dngwjy.portal.views.DetailView;
import com.github.siyamed.shapeimageview.CircularImageView;

import static androidx.constraintlayout.widget.Constraints.TAG;
public class DetailFilmActivity extends AppCompatActivity implements DetailView {
    private DetailPresenter presenter;
    private DetailMovie detailMovie;
    CircularImageView poster;
    ImageView imek;
    TextView title, tagline, rating, language, runtime, overview, release;
    SwipeRefreshLayout refreshLayout;
    LinearLayout contentDetail;
    Movie movie;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);
        initiations();
    }

    private void initiations(){
        presenter=new DetailPresenter(this);
        movie=getIntent().getParcelableExtra("data");
        imek = findViewById(R.id.image_detail_movie);
        title = findViewById(R.id.tv_detail_title_movie);
        tagline = findViewById(R.id.tv_detail_tagline);
        language = findViewById(R.id.tv_detail_language);
        runtime = findViewById(R.id.tv_detail_runtime);
        overview = findViewById(R.id.tv_detail_overview);
        release = findViewById(R.id.tv_detail_release);
        rating = findViewById(R.id.tv_detail_rating);
        refreshLayout=findViewById(R.id.detail_swipe_refresh);
        contentDetail = findViewById(R.id.content_detail_movie);
        presenter.getData(movie.getId());
        toolbar = findViewById(R.id.toolbar);
    }
    private void setData(){

    }


    @Override
    public void isLoading(Boolean state) {
        title.setText(movie.getTitle());
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w780" + movie.getPosterPath())
                .into(imek);
        if(state){
            refreshLayout.setRefreshing(true);
            contentDetail.setVisibility(View.GONE);
        }else{
            refreshLayout.setRefreshing(false);
            contentDetail.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void viewResult(Boolean state, DetailMovie data) {
        if(state){
            detailMovie=data;
            tagline.setText(detailMovie.getTagline());
            rating.setText(detailMovie.getVoteAverage()+"");
            language.setText(detailMovie.getOriginalLanguage());
            overview.setText(detailMovie.getOverview());
            release.setText(detailMovie.getReleaseDate());
            runtime.setText(detailMovie.getRuntime()+"");

        }else{
            Toast toast= Toast.makeText(this, "Error dalam mengambil data dari server, Cek koneksi internet anda atau coba beberapa saat lagi", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
