package com.example.coronainfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {
    private TextView posttab,casetab ;
    private ArrayList<CovidCase> postlist;
    private RecyclerView postrecyclerview;
    ProgressBar mProgressBar;
    private  String id,mcountrystate,  mtime ,  mpostdescription, mpostlink,mpostimagelink , m__v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        postrecyclerview = (RecyclerView) findViewById(R.id.postrecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
       postrecyclerview.setLayoutManager(layoutManager);
        postrecyclerview.setHasFixedSize(true);
        postrecyclerview.setNestedScrollingEnabled(false);
        mProgressBar=(ProgressBar) findViewById(R.id.progressbarpost);
        if(isNetworkAvailable(getApplicationContext())) {
            Runnable job = new Runnable() {
                @Override
                public void run() {

                    mProgressBar.setVisibility(View.GONE);
                    mProgressBar.postDelayed(this, 2200);

                }
            };

            mProgressBar.postDelayed(job, 2200);
        }
        else{
            Runnable job = new Runnable() {
                @Override
                public void run() {


                   mProgressBar.setVisibility(View.GONE);
                   mProgressBar.postDelayed(this, 220000);

                }
            };

            mProgressBar.postDelayed(job, 2200000);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://still-inlet-42287.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       postlist = new ArrayList<CovidCase>();

        //calling api
        CovidCasesApi jsonPlaceholderApi = retrofit.create(CovidCasesApi.class);
        Call<List<Earth>> call = jsonPlaceholderApi.getPost();
        //all the data collect as a response
        call.enqueue(new Callback<List<Earth>>() {
            @Override
            public void onResponse(Call<List<Earth>> call, Response<List<Earth>> response) {
                if (!response.isSuccessful()) {
//                  apidata.setText("Code:"+response.code());
                    return;

                }
                //here in posts variable whole response body in which many similar object are stored
                List<Earth> posts = response.body();


                for (Earth post : posts) {

                    id = post.get_id();
                    mcountrystate = post.getCountryStatename();
                    mpostdescription=post.getPostDescription();
                    mpostimagelink=post.getPostimageslink();
                    mpostlink=post.getPostlink();
                    m__v = post.get__v();
                    postlist.add(0, new CovidCase(id, mcountrystate, mpostdescription,mpostimagelink,mpostlink,mtime ));

                }

                PostAdapter interestsadapter = new PostAdapter(getApplicationContext(), postlist);
                postrecyclerview.setAdapter(interestsadapter);

            }

            @Override
            public void onFailure(Call<List<Earth>> call, Throwable t) {

            }
        });

        posttab=findViewById(R.id.posttab);
        casetab=findViewById(R.id.casetab);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

            posttab.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_info_tile) );
            casetab.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_nav_tile) );
        }


        casetab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    posttab.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_nav_tile) );
                    casetab.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_info_tile) );
                    Intent intent=new Intent(getApplicationContext(),CoronaActivity.class);
                    intent.putExtra(Intent.EXTRA_TEXT,"student");
                    startActivity(intent);
                    finish();

                }
            }
        });

        posttab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    posttab.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_info_tile) );
                    casetab.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_nav_tile) );




                }
            }
        });
    }

    private boolean isNetworkAvailable(Context mcontext) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) mcontext.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}