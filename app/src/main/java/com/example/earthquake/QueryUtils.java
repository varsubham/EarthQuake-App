package com.example.earthquake;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

   

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link } objects that has been built up from
     * parsing a JSON response.
     */



    public static ArrayList<EarthquakeClass> extractEarthquakes() {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<EarthquakeClass> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            JSONObject jsonObject = new JSONObject(SAMPLE_JSON_RESPONSE);
            JSONArray jsonArray = jsonObject.optJSONArray("features");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                JSONObject obj1 = obj.getJSONObject("properties");
                String url = obj1.optString("url");
                double mag = obj1.optDouble("mag");
                String place = obj1.optString("place").toString();
                String time = obj1.optString("time").toString();
                long time_long = obj1.optLong("time");
                earthquakes.add(new EarthquakeClass(mag, place, time_long, url));


            }

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}