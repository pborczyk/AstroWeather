package com.example.astroweather.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.astroweather.fragment.AdditionalConditions;
import com.example.astroweather.fragment.BasicConditions;
import com.example.astroweather.fragment.ForecastFragment;
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

            case 2:
                return new BasicConditions();

            case 3:
                return new AdditionalConditions();

            case 4:
                return new ForecastFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Słońce";

            case 1:
                return "Księżyc";

            case 2:
                return "Podstawowe informacje";

            case 3:
                return "Dodatkowe informacje";

            case 4:
                return "Prognoza";

            default:
                return super.getPageTitle(position);
        }
    }
}
