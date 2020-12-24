package com.example.coronainfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Timer timer;
    private ImageView logo;
    private static int splashTimeOut=1200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),PostActivity.class);
                startActivity(i);

                finish();
            }
        },splashTimeOut);

    }
}
