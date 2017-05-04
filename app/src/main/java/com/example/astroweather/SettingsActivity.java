package com.example.astroweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.astroweather.presenter.SettingsPresenter;
import com.example.astroweather.settings.UpdateTimeIntervalValues;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.longtitude_input)
    public EditText longtitudeInput;

    @BindView(R.id.latitude_input)
    public EditText latitudeInput;

    @BindView(R.id.update_interval_spinner)
    public Spinner updateIntervalSpinner;

    @BindView(R.id.save_button)
    public Button saveButton;

    private SettingsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        List<String> intervalNames = new ArrayList<>();
        for (UpdateTimeIntervalValues value : UpdateTimeIntervalValues.values()) {
            intervalNames.add(value.name());
        }


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.interval_values, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        updateIntervalSpinner.setAdapter(adapter);

        presenter = new SettingsPresenter();
        presenter.attachView(this);
        presenter.onCreate();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setSettingsValues();
            }
        });
    }
}
