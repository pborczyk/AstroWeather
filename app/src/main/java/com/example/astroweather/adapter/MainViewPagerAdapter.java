package com.example.astroweather.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.astroweather.fragment.MoonFragment;
import com.example.astroweather.fragment.SunFragment;

/**
 * Created by Piotr Borczyk on 04.05.2017.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SunFragment.newInstance("", "");

            case 1:
                return MoonFragment.newInstance("", "");

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Słońce";

            case 1:
                return "Księżyc";

            default:
                return super.getPageTitle(position);
        }
    }
}
