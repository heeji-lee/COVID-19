package com.example.covid_19application.ui.mypage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covid_19application.R;

public class MyBagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bag);
        setTitle("장바구니");
    }
}