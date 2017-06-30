package com.example.astroweather.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.astroweather.R;
import com.example.astroweather.presenter.BasicConditionsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasicConditions extends Fragment {

    @BindView(R.id.temperature)
    public TextView temperature;

    @BindView(R.id.pressure)
    public TextView pressure;

    @BindView(R.id.weather_image)
    public ImageView weatherImage;

    @BindView(R.id.description)
    public TextView description;

    @BindView(R.id.time)
    public TextView time;

    private BasicConditionsPresenter presenter;

    public BasicConditions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basic_conditions, container, false);
        ButterKnife.bind(this, view);

        presenter = new BasicConditionsPresenter(getActivity());
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
