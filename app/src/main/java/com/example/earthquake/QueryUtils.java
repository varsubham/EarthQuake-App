package com.example.earthquake;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    //final String url_website = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";


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
    public static String makehttprequest(URL url) throws IOException {
        String jsonresponse="";
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            jsonresponse = getJsonResponse(inputStream);
            }
        catch (IOException e){}

        finally {
            if(httpURLConnection != null)
                httpURLConnection.disconnect();
            if(inputStream != null)
                inputStream.close();
        }
        return jsonresponse;

    }

    private static String getJsonResponse(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        if(inputStream == null)
            return null;
        else{
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader br = new BufferedReader(inputStreamReader);
        String temp = br.readLine();
        while(temp!=null)
        {
            stringBuilder.append(temp);
            temp = br.readLine();
        }}
        String response = stringBuilder.toString();
        return response;
    }
    private static URL createurl(String url_website){
        URL url=null;
        try{
        url = new URL(url_website);}
        catch (MalformedURLException m){}
        return url;
    }

    public static ArrayList<EarthquakeClass> extractEarthquakes(String USGC_WEBSITE) {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<EarthquakeClass> earthquakes = new ArrayList<>();

        URL url_new = createurl(USGC_WEBSITE);
        String jsonresponse=null;
        try{
        jsonresponse = makehttprequest(url_new);}
        catch (IOException e){}


        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            JSONObject jsonObject = new JSONObject(jsonresponse);
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