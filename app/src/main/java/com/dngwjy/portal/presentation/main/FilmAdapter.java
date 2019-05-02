package com.dngwjy.portal.presentation.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dngwjy.portal.R;
import com.dngwjy.portal.data.models.Movie;
import com.dngwjy.portal.presentation.detail.DetailFilmActivity;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {
    private List<Movie> movieList;
    public FilmAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     holder.bindData(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView overview;
        ImageView poster;
        View itemView;
         static final String BASE_IMG_URL ="https://image.tmdb.org/t/p/w92";
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_list_movie_title);
            poster = itemView.findViewById(R.id.image_list_movie_poster);
            overview = itemView.findViewById(R.id.tv_list_movie_overview);
            this.itemView=itemView;
        }
        void bindData(final Movie data){
            title.setText(data.getTitle());
            overview.setText(data.getOverview());
            Glide.with(itemView).load(BASE_IMG_URL+data.getPosterPath()).into(poster);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                     itemView.getContext().startActivity(new Intent(itemView.getContext(), DetailFilmActivity.class).putExtra("data",data));
                }
            });
        }
    }
}
