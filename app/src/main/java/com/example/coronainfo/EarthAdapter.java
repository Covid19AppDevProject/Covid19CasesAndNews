package com.example.coronainfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;

public class EarthAdapter extends ArrayAdapter<Earth> {

    public EarthAdapter(Activity context, ArrayList<Earth> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View earthlistitem = convertView;
        if (earthlistitem == null) {
            earthlistitem = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_item, parent, false);
        }
        Earth currentview = getItem(position);
        TextView counText = (TextView) earthlistitem.findViewById(R.id.country_view);
        counText.setText(currentview.getMcountry());
        TextView casesText = (TextView) earthlistitem.findViewById(R.id.cases_view);
        casesText.setText("Total Cases ->               "+String.valueOf(currentview.getMcases()));
        TextView todaycasesText = (TextView) earthlistitem.findViewById(R.id.todaycases_view);
        todaycasesText.setText("Today Cases ->               "+String.valueOf(currentview.getMtodayCases()));
        TextView deathsText = (TextView) earthlistitem.findViewById(R.id.deaths_view);
        deathsText.setText("deaths ->                    "+String.valueOf(currentview.getMdeaths()));
        TextView todayDeathsText = (TextView) earthlistitem.findViewById(R.id.todaydeaths_view);
        todayDeathsText.setText("Today deaths ->              "+String.valueOf(currentview.getMtodayDeaths()));
        TextView recoveredText = (TextView) earthlistitem.findViewById(R.id.recovered_view);
        recoveredText.setText("Recovered ->                 "+String.valueOf(currentview.getMrecovered()));
        TextView activeText = (TextView) earthlistitem.findViewById(R.id.active_view);
        activeText.setText("Active ->                    "+String.valueOf(currentview.getMactive()));
        TextView criticalText = (TextView) earthlistitem.findViewById(R.id.critical_view);
        criticalText.setText("Critical ->                  "+String.valueOf(currentview.getMcritical()));
        return earthlistitem;
    }
}