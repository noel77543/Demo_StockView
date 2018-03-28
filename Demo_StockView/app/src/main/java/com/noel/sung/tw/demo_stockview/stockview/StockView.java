package com.noel.sung.tw.demo_stockview.stockview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by noel on 2018/3/28.
 */

public class StockView extends RelativeLayout implements Runnable {

    private Context context;
    private RelativeLayout.LayoutParams params;


    //預設寬高為600px
    private int defaultSize = 600;
    //折線圖的高度
    private int panelHeight = 0;
    //折線圖的寬度
    private int panelWidth = 0;
    //這會影響不論垂直線或者水平線外側的值
    private final int RECESSION = 100;

    private Paint paint;
    private MainPanel mainPanel;





    public StockView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public StockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();

    }

    public StockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();

    }
    //-------------

    /***
     * 只能透過此method調整layout大小
//     * @param size
     */
//    public void setSize(int size) {
//        this.defaultSize = size;
//        params.width = size;
//        params.height = size;
//        requestLayout();
//    }


    //-------------

    private void init() {
        initPanel();



        addView(mainPanel);
    }

    @Override
    public void run() {


    }

    //-------

    /***
     * 設置畫版
     */
    private void initPanel() {
        mainPanel = new MainPanel(context);

    }


    //-------




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        paint.setColor(Color.BLACK);//畫筆颜色
//        canvas.drawColor(Color.WHITE);//畫布背景顏色
//        paint.setStrokeWidth((float) 10.0);//線條寬度
//        canvas.drawLine(RECESSION, RECESSION, RECESSION, defaultSize - RECESSION, paint);
//        canvas.drawLine(RECESSION, defaultSize - RECESSION, defaultSize - RECESSION, defaultSize - RECESSION, paint);        //绘制直线


//        paint.setStrokeWidth((float) 5.0);              //设置线宽
//        canvas.drawLine(50, 150, 450, 150, paint);      //绘制直线
//        paint.setStrokeWidth((float) 10.0);             //设置线宽
//        canvas.drawLine(50, 250, 450, 250, paint);      //绘制直线
//        paint.setStrokeWidth((float) 15.0);             //设置线宽
//        canvas.drawLine(50, 350, 450, 350, paint);      //绘制直线
//        paint.setStrokeWidth((float) 20.0);             //设置线宽
//        canvas.drawLine(50, 450, 450, 450, paint);      //绘制直线

    }
}
