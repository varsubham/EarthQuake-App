package com.example.earthquake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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
    String  url_website_usgs = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        earthquakes = QueryUtils.extractEarthquakes(url_website_usgs);

        new myasyncclass().execute(url_website_usgs);

        final ListView listView = findViewById(R.id.list_view);
//        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(QuakeReport.this, android.R.layout.simple_list_item_1, earthquakes);
//        CustomAdapter customAdapter = new CustomAdapter(QuakeReport.this, earthquakes);
//        listView.setAdapter(customAdapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                EarthquakeClass obj = earthquakes.get(position);
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(obj.getUrl()));
//                startActivity(intent);
//            }
//        });



    }

    private class myasyncclass extends AsyncTask<String, Void, ArrayList<EarthquakeClass>>{
        @Override
        protected void onPostExecute(ArrayList<EarthquakeClass> earthquakeClasses) {
            final ListView listView = findViewById(R.id.list_view);
            //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(QuakeReport.this, android.R.layout.simple_list_item_1, earthquakes);
            CustomAdapter customAdapter = new CustomAdapter(QuakeReport.this, earthquakeClasses);
            listView.setAdapter(customAdapter);
        }

        @Override
        protected ArrayList<EarthquakeClass> doInBackground(String... strings) {
            ArrayList<EarthquakeClass> earth;
            earth = QueryUtils.extractEarthquakes(url_website_usgs);
            return earth;
        }
    }

}
