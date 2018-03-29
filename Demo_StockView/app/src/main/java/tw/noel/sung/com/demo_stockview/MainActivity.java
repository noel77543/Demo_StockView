package tw.noel.sung.com.demo_stockview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tw.noel.sung.com.demo_stockview.stockview.StockView;

public class MainActivity extends AppCompatActivity {

    private StockView stockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stockView = (StockView) findViewById(R.id.stockView);


        stockView.setOutSideDataOnStockView(1000,1000,12,100,"月","%",12,10);
        stockView.draw();


    }
}
