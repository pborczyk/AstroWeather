package com.example.astroweather;

import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.example.astroweather.adapter.MainViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.main_view_pager)
    ViewPager mainViewPager;

    @Nullable
    @BindView(R.id.main_view_pager_tab)
    TabLayout mainViewPagerTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (mainViewPager != null) {
            mainViewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));
            mainViewPagerTab.setupWithViewPager(mainViewPager);
        }
    }

}
