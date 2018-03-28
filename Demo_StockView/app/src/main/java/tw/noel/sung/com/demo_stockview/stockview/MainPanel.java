package tw.noel.sung.com.demo_stockview.stockview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.math.BigDecimal;


/**
 * Created by noel on 2018/3/28.
 */

public class MainPanel extends ImageView {
    private Context context;
    private RelativeLayout.LayoutParams layoutParams;

    //畫板大小 預設 800px
    private int panelSize = 800;
    //畫板背景色 預設 透明色
    private int panelColor = android.R.color.transparent;
    private final int RECESSION = 100;

    private Paint paint;
    private Path path;
    private final float textSize = 20.0f;


    private Bitmap bitmap;
    private Canvas canvas;
    //最大值X與Y 預設1000
    private float maxX = 1000;
    private float maxY = 1000;
    //分為幾部分 預設5
    private int partOf = 5;
    //單位
    private String unit;


    public MainPanel(Context context) {
        super(context);
        this.context = context;
        init();

    }

    public MainPanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();

    }

    public MainPanel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();

    }


    //----------


    /***
     * @param panelSize 圖片大小
     * @param maxX 最大值X
     * @param maxY 最大值Y
     * @param unit 單位
     * @param partOf 分成幾部分
     */
    public void setDataInfo(int panelSize,float maxX, float maxY,@Nullable String unit,int partOf) {
        this.panelSize = panelSize;
        layoutParams.width = panelSize;
        layoutParams.height = panelSize;
        this.maxX = maxX;
        this.maxY = maxY;
        this.unit = unit;
        this.partOf = partOf;
    }



    //-----------

    /***
     * 設置畫板背景色
     */
    public void setPanelBackgroundColor(int panelColor) {
        this.panelColor = panelColor;
    }

    //----------

    /***
     *
     */
    private void init() {


        //定義外圍畫筆路徑
        path = new Path();
        path.moveTo(RECESSION, RECESSION);
        path.lineTo(RECESSION, panelSize - RECESSION);
        path.lineTo(panelSize - RECESSION, panelSize - RECESSION);

        paint = new Paint();
        //畫筆颜色
        paint.setColor(Color.BLACK);
        //線條寬度
        paint.setStrokeWidth((float) 10.0);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(textSize);

        layoutParams = new RelativeLayout.LayoutParams(panelSize, panelSize);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        setLayoutParams(layoutParams);

    }


    //-----------

    /***
     *  設定好後 開始繪製
     */
    public void startDrawPanel() {
        bitmap = Bitmap.createBitmap(panelSize, panelSize, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(context.getResources().getColor(panelColor));//畫布背景顏色
        canvas.drawPath(path, paint);

        //計算 每個間劇
        float interval = (panelSize - (2 * RECESSION)) / 5;

        float startX = maxX / 5;
        float startY = maxY / 5;

        paint.setTextAlign(Paint.Align.CENTER);

        for (int i = 0; i < 5; i++) {
            Log.e("startX", startX + "");
            //水平數據
            canvas.drawLine((i + 1) * interval + RECESSION, panelSize - RECESSION - 20, (i + 1) * interval + RECESSION, panelSize - RECESSION + 20, paint);
            canvas.drawText(startX + "", (i + 1) * interval + RECESSION, panelSize, paint);
            startX += startX / (i + 1);
            startX = new BigDecimal(startX)
                    .setScale(1, BigDecimal.ROUND_HALF_UP)
                    .floatValue();
            //垂直數據
            canvas.drawLine(RECESSION - 20, (4 - i) * interval + RECESSION, RECESSION + 20, (4 - i) * interval + RECESSION, paint);
            canvas.drawText(startY + "", 0, (4 - i) * interval + RECESSION, paint);
            startY += startY / (i + 1);
            startY = new BigDecimal(startY)
                    .setScale(1, BigDecimal.ROUND_HALF_UP)
                    .floatValue();

        }
        setImageBitmap(bitmap);
    }

}
