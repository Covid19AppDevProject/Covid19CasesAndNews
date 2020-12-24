package com.example.coronainfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{
    ArrayList<CovidCase>mpostlist;
    Context mcontext;
    public PostAdapter(Context applicationContext, ArrayList<CovidCase> postlist) {

        mpostlist=postlist;
        mcontext=applicationContext;
    }


    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postwithvideo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {

        holder.topicname.setText(mpostlist.get(position).getmCountryStatename());
        holder.postdescription.setText(mpostlist.get(position).getMpostdescription());
        holder.time.setText(mpostlist.get(position).getMtime());

        try {

            URL myURL = new URL(mpostlist.get(position).getMpostlink());
           Glide.with(mcontext.getApplicationContext())
                    .load(myURL)
                    .into(holder.topicimage);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {

                URL myURL = new URL(mpostlist.get(position).getMpostimagelink());
                Glide.with(mcontext.getApplicationContext())
                        .load(myURL)
                        .into(holder.postimage);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return mpostlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircularImageView topicimage;
        ImageView postimage;


        TextView  topicname , postdescription,postlink1;
        TextView time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            topicimage= (CircularImageView) itemView.findViewById(R.id.topicimage);
            topicname= (TextView) itemView.findViewById(R.id.topicname);
            postdescription =(TextView) itemView.findViewById(R.id.postdes );
            postimage=(ImageView)itemView.findViewById(R.id.postimages);


            postlink1 = (TextView) itemView.findViewById(R.id.postlink1);

            time=(TextView)itemView.findViewById(R.id.posttime);
        }
    }
}