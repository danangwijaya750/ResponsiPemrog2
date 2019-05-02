package com.dngwjy.portal.presentation.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dngwjy.portal.R;
import com.dngwjy.portal.data.models.Movie;
import com.dngwjy.portal.presenters.NowPlayingPresenter;
import com.dngwjy.portal.views.BaseView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NowPlayingFilm extends Fragment implements BaseView {
    private final String TAG=this.getClass().getSimpleName();
    private ArrayList<Movie> dataFilm= new ArrayList<>();
    private NowPlayingPresenter presenter;
    private FilmAdapter adapter;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_last_match,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initiations(view);
    }

    private void initiations(View view){
        presenter=new NowPlayingPresenter(this);
        presenter.getData();
        adapter=new FilmAdapter(dataFilm);
        recyclerView=view.findViewById(R.id.rec_now);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void isLoading(Boolean state) {
        if(state){

        }else{

        }
    }

    @Override
    public void viewResult(List<Movie> data) {
        dataFilm.clear();
        dataFilm.addAll(data);
        adapter.notifyDataSetChanged();
        Log.d(TAG, "viewResult: "+dataFilm.size());
    }
}
