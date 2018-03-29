package tw.noel.sung.com.demo_stockview.stockview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import tw.noel.sung.com.demo_stockview.R;

/**
 * Created by noel on 2018/3/28.
 */

public class StockView extends RelativeLayout {

    private Context context;
    private LayoutParams params;


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


    }


    //-------

    /***
     * 設置畫版
     */
    private void initPanel() {
        mainPanel = new MainPanel(context);
        addView(mainPanel);
    }


    //-------

    /***
     * 設置畫版外部參數
     */
    public void setOutSideDataOnStockView(int viewlWidth,int viewHeight, int maxX, int maxY, @Nullable String unitX, @Nullable String unitY, int partOfX, int partOfY){
        mainPanel.setDataInfo(viewlWidth,viewHeight,maxX,maxY,unitX,unitY,partOfX,partOfY);
    }




    //------
    /***
     * 在最後要呼叫此method 才進行繪製
     */
    public void draw(){
        mainPanel.startDrawPanel();
    }


}
