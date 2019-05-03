package com.dngwjy.portal.presentation.main;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dngwjy.portal.R;
import com.dngwjy.portal.data.models.Movie;
import com.dngwjy.portal.presenters.UpcommingPresenter;
import com.dngwjy.portal.views.BaseView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class UpcommingFilm extends Fragment implements BaseView {
    private FilmAdapter adapter;
    private ArrayList<Movie> movies= new ArrayList<>();
    private UpcommingPresenter presenter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_next_match,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Upcomming Film");
        initiations(view);
    }

    private  void initiations(View view){
        presenter=new UpcommingPresenter(this);
        adapter = new FilmAdapter(movies);
        recyclerView=view.findViewById(R.id.rec_upc);
        refreshLayout=view.findViewById(R.id.swiper);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        presenter.getData();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getData();
            }
        });
    }

    @Override
    public void isLoading(Boolean state) {
        if(state){
            refreshLayout.setRefreshing(true);
        }else{
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void viewResult(Boolean state,List<Movie> data) {
        if(state){
            Log.d(TAG, "viewResult: "+data.size());
            movies.clear();
            movies.addAll(data);
            adapter.notifyDataSetChanged();
        }else{
            //error
           Toast toast= Toast.makeText(this.getContext(), "Error dalam mengambil data dari server, Cek koneksi internet anda atau coba beberapa saat lagi", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
    }
}
