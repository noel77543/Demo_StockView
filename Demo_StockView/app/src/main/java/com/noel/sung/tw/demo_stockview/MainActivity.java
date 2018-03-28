package com.noel.sung.tw.demo_stockview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.noel.sung.tw.demo_stockview.stockview.StockView;

public class MainActivity extends AppCompatActivity {

    private StockView stockView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stockView = findViewById(R.id.stockView);
//        stockView.setSize(800);
    }
}
