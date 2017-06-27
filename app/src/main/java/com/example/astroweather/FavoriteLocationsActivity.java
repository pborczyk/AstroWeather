package com.example.astroweather;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.astroweather.api.WeatherService;
import com.example.astroweather.locations.Location;
import com.example.astroweather.locations.LocationAdapter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteLocationsActivity extends AppCompatActivity {

    private static final String filename = "myfile";


    @BindView(R.id.places_list)
    public RecyclerView placesList;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.add_button)
    public Button addButton;

    @BindView(R.id.localization_edit_text)
    public EditText localizationEditText;

    private List<Location> locations = new ArrayList<>();

    private WeatherService weatherService;

    private LocationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_locations);
        ButterKnife.bind(this);
        readLocations();
        setSupportActionBar(toolbar);
        weatherService = new WeatherService();
        adapter = new LocationAdapter(locations);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        placesList.setLayoutManager(layoutManager);
        placesList.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String location = localizationEditText.getText().toString();
                    String woeid = weatherService.getVoid(location);
                    if (woeid != null) {
                        locations.add(new Location(location, woeid));
                        adapter.notifyDataSetChanged();
                    } else {
                        //ERROR
                    }
            }
        });
    }

    private void readLocations() {
        try {
            FileInputStream inputStream = openFileInput(filename);
            ObjectInputStream is = new ObjectInputStream(inputStream);
            locations = (List<Location>) is.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            FileOutputStream outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(locations);
            os.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
