package com.beingteach.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.beingteach.universalhelper.UniversalHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new UniversalHelper(MainActivity.this).updateApp(false);
    }
}