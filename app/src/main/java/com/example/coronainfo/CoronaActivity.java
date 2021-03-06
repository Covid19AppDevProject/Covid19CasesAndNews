package com.example.coronainfo;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CoronaActivity extends AppCompatActivity   {
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private static final String LOG_TAG = CoronaActivity.class.getSimpleName();
   private RecyclerView casesrecyclerview;
   private ArrayList<CovidCase> caseslist;
    private EarthAdapter mAdapter;
    private  String id,mcountrystate, mTotalCase , mActivecase, mRecoveredcase,mDeath , m__v;
    private TextView posttab,casetab ;
    private TextView mEmptyStateTextView;
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona);

        posttab=findViewById(R.id.posttab);
        casetab=findViewById(R.id.casetab);

        casesrecyclerview = (RecyclerView) findViewById(R.id.casesrecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        casesrecyclerview.setLayoutManager(layoutManager);
        casesrecyclerview.setHasFixedSize(true);
        casesrecyclerview.setNestedScrollingEnabled(false);
        mProgressBar=(ProgressBar) findViewById(R.id.progressbarcase);
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
        caseslist = new ArrayList<CovidCase>();

        //calling api
        CovidCasesApi jsonPlaceholderApi = retrofit.create(CovidCasesApi.class);
        Call<List<Earth>> call = jsonPlaceholderApi.getCases();
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
                    mTotalCase = post.getTotalCase();
                    mActivecase = post.getActivecase();
                    mRecoveredcase = post.getRecoveredcase();
                    mDeath = post.getDeath();
                    m__v = post.get__v();
                    caseslist.add( new CovidCase(id, mcountrystate, mTotalCase , mActivecase, mRecoveredcase, mDeath, m__v));

                }

                EarthAdapter interestsadapter = new EarthAdapter(getApplicationContext(), caseslist);
                casesrecyclerview.setAdapter(interestsadapter);

            }

            @Override
            public void onFailure(Call<List<Earth>> call, Throwable t) {

            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

            casetab.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_info_tile) );
            posttab.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_nav_tile) );
        }


        casetab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                    posttab.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_nav_tile) );
                    casetab.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_info_tile) );


                }
            }
        });

        posttab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                    posttab.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_info_tile) );
                    casetab.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_nav_tile) );

                    Intent intent=new Intent(getApplicationContext(),PostActivity.class);
                    intent.putExtra(Intent.EXTRA_TEXT,"student");
                    startActivity(intent);
                    finish();

                }
            }
        });


    }

    private boolean isNetworkAvailable(Context mcontext) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) mcontext.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


}




