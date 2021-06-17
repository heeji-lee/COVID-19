package com.example.covid_19application.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covid_19application.R;

public class MaskViewActivity extends AppCompatActivity {
    TextView tvproduct, tvkind, tvprice, tvquantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mask_view);
        setTitle("제품 상세보기");
        tvproduct=findViewById(R.id.tvProduct);
        tvkind=findViewById(R.id.tvKind);
        tvprice=findViewById(R.id.tvPrice);
        tvquantity=findViewById(R.id.tvQuantity);

        Intent intent=getIntent();
        String product=intent.getStringExtra("product");
        String kind=intent.getStringExtra("kind");
        String price=intent.getStringExtra("price");
        String quantity=intent.getStringExtra("quantity");

        tvproduct.setText(product);
        tvkind.setText(kind);
        tvprice.setText(price);
        tvquantity.setText(quantity);
    }
}