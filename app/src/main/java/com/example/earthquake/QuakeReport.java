package com.example.earthquake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuakeReport extends AppCompatActivity {
    ArrayList<EarthquakeClass> earthquakes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        earthquakes = QueryUtils.extractEarthquakes();


        final ListView listView = findViewById(R.id.list_view);
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(QuakeReport.this, android.R.layout.simple_list_item_1, earthquakes);
        CustomAdapter customAdapter = new CustomAdapter(QuakeReport.this, earthquakes);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EarthquakeClass obj = earthquakes.get(position);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(obj.getUrl()));
                startActivity(intent);
            }
        });

    }
}
