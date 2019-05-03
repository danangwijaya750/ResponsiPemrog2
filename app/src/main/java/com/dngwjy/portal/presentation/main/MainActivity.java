package com.dngwjy.portal.presentation.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.dngwjy.portal.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private  Boolean inMain=true;
    private FrameLayout frameLayout;
    private  BottomNavigationView bottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Boolean result=false;
                    switch (menuItem.getItemId()){
                        case R.id.next:
                                replaceFragment(new UpcommingFilm());
                                inMain=true;
                                result=true;
                            break;
                        case R.id.last:
                            replaceFragment(new NowPlayingFilm());
                            inMain=false;
                            result=true;
                            break;
                    }
                    return result;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout=findViewById(R.id.deploy);
        bottomNavigationView=findViewById(R.id.navControl);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        bottomNavigationView.setSelectedItemId(R.id.last);

    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.deploy,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
