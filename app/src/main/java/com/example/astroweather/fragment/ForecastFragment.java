package com.example.astroweather.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.astroweather.R;
import com.example.astroweather.api.pojo.weather.Forecast;
import com.example.astroweather.presenter.ForecastPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForecastFragment extends Fragment {

    @BindView(R.id.days)
    public RecyclerView days;

    private ForecastPresenter presenter;

    public ForecastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        ButterKnife.bind(this, view);

        presenter = new ForecastPresenter(getActivity());
        presenter.attachView(this);
        presenter.onCreate();

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.onDestroy();
    }
}
