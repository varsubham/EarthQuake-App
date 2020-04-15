package com.example.earthquake;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CustomAdapter extends ArrayAdapter<EarthquakeClass> {

    public CustomAdapter(Context context, ArrayList<EarthquakeClass> al){
        super (context, 0 ,al);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View ItemView = convertView;

        if(ItemView==null){
            ItemView = LayoutInflater.from(getContext()).inflate(R.layout.cutom_earthquake_listview_layout, parent, false);
        }

        EarthquakeClass current_word = getItem(position);




        TextView mag = ItemView.findViewById(R.id.textview_mag);

        GradientDrawable magnitudeCircle = (GradientDrawable)mag.getBackground();

        int magnitudeColor = getMagnitudeColor(current_word.getMag());

        magnitudeCircle.setColor(magnitudeColor);

        double temp = current_word.getMag();


        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String output = decimalFormat.format(temp);
        mag.setText(output);

        TextView name1 = ItemView.findViewById(R.id.textview_name1);
        TextView name2 = ItemView.findViewById(R.id.textview_name2);

        String str = current_word.getName();
        if(str.contains(" of ")){
            int index = str.indexOf(" of ");
            String first_string = str.substring(0, index+3);
            String last_string = str.substring(index+4, str.length());
            name1.setText(first_string);
            name2.setText(last_string);
        }
        else{
            name1.setText("Near the");
            name2.setText(str);

        }


        Date dateobj = new Date(current_word.getTime());

        String final_date = formatdate(dateobj);
        TextView date = ItemView.findViewById(R.id.textview_date);
        date.setText(final_date);

        String final_time = formattime(dateobj);

        TextView time = ItemView.findViewById(R.id.textview_time);
        time.setText(final_time);


        return ItemView;
    }

    private int getMagnitudeColor(double mag){
        int temp=0;
        if(mag>=0 && mag<2)
            temp = ContextCompat.getColor(getContext(), R.color.magnitude1);
        else if(mag>=2 && mag<3)
            temp = ContextCompat.getColor(getContext(), R.color.magnitude2);
        else if(mag>=3 && mag<4)
            temp = ContextCompat.getColor(getContext(), R.color.magnitude3);
        else if(mag>=4 && mag<5)
            temp = ContextCompat.getColor(getContext(), R.color.magnitude4);
        else if(mag>=5 && mag<6)
            temp = ContextCompat.getColor(getContext(), R.color.magnitude5);
        else if(mag>=6 && mag<7)
            temp = ContextCompat.getColor(getContext(), R.color.magnitude6);
        else if(mag>=7 && mag<8)
            temp = ContextCompat.getColor(getContext(), R.color.magnitude7);
        else if(mag>=8 && mag<9)
            temp = ContextCompat.getColor(getContext(), R.color.magnitude8);
        else if(mag>=9 && mag<10)
            temp = ContextCompat.getColor(getContext(), R.color.magnitude9);
        else if(mag>=10)
            temp = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
        return temp;
    }


    private String formatdate(Date obj){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        String temp = simpleDateFormat.format(obj);
        return temp;
    }

    private String formattime(Date obj){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
        String temp = simpleDateFormat.format(obj);
        return temp;
    }
}
