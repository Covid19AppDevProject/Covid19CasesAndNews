package com.example.coronainfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
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

public class EarthAdapter extends   RecyclerView.Adapter<EarthAdapter.ViewHolder> {

       ArrayList<CovidCase>mcaseslist;
       Context mcontext;
    public EarthAdapter(Context applicationContext, ArrayList<CovidCase> caseslist) {
        mcaseslist=caseslist;
         mcontext=applicationContext;
    }


    @NonNull
    @Override
    public EarthAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthAdapter.ViewHolder holder, int position) {
        holder.countryname.setText(mcaseslist.get(position).getmCountryStatename());
        holder.activecase.setText("ActiveCase      "+mcaseslist.get(position).getmActivecase());
        holder.totalcase.setText("TotalCase       "+mcaseslist.get(position).getmTotalCase());
        holder.recoveredcase.setText("RecoveredCase     "+mcaseslist.get(position).getmRecoveredcase());
        holder.death.setText("DeathCase      "+mcaseslist.get(position).getmDeath());

    }

    @Override
    public int getItemCount() {
        return mcaseslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView countryname,totalcase,activecase,recoveredcase,death;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryname=(TextView)itemView.findViewById(R.id.country_view);
            totalcase=(TextView)itemView.findViewById(R.id.cases_view);
           activecase=(TextView)itemView.findViewById(R.id.active_view);
           recoveredcase=(TextView)itemView.findViewById(R.id.recovered_view);
           death=(TextView)itemView.findViewById(R.id.deaths_view);



        }
    }
}