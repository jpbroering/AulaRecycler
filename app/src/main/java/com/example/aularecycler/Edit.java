package com.example.aularecycler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Edit extends AppCompatActivity {
    static Produto p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().hide();
    }
}