package com.example.covid_19application.ui.qr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.covid_19application.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanQRActivity extends AppCompatActivity {

    private IntentIntegrator qrScan;
    TextView tvScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);
        setTitle("QR 코드 스캔");
        qrScan = new IntentIntegrator(this);
        qrScan.initiateScan();
        qrScan.setOrientationLocked(false);
//        qrScan.setPrompt("Sample Text!");
        tvScan=findViewById(R.id.tvScan);
        Button btn=findViewById(R.id.btnFinish);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "지원하지 않는 QR코드", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                tvScan.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}