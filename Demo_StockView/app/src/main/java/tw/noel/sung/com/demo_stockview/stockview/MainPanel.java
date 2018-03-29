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

import tw.noel.sung.com.demo_stockview.R;


/**
 * Created by noel on 2018/3/28.
 */

public class MainPanel extends ImageView {
    private Context context;
    private RelativeLayout.LayoutParams layoutParams;

//    //畫板大小 預設 800px
//    private int panelSize = 800;
    //畫板大小 預設 800px
    private int panelWidth= 800;
    private int panelHeight= 800;

    //水平內縮 預設100
    private int recessionX = panelWidth / 8;
    //垂直內縮 預設100
    private int recessionY = panelHeight / 8;


    private Paint paint;
    private Path path;
    private float textSize = 20.0f;


    private Bitmap bitmap;
    private Canvas canvas;
    //最大值X與Y 預設1000
    private int maxX = 1000;
    private int maxY = 500;
    //x分為幾部分 預設10
    private int partOfX = 10;
    //y分為幾部分 預設10
    private int partOfY = 10;


    //x的單位
    private String unitX="";
    //y的單位
    private String unitY="";

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
     * @param panelWidth 圖片寬度
     * @param panelHeight 圖片高度
     * @param maxX 最大值X
     * @param maxY 最大值Y
     * @param unitX x單位
     * @param unitY y單位
     * @param partOfX x分成幾部分
     * @param partOfY y分成幾部分
     */
    public void setDataInfo(int panelWidth,int panelHeight, int maxX, int maxY, @Nullable String unitX, @Nullable String unitY, int partOfX, int partOfY) {
        this.panelWidth = panelWidth;
        this.panelHeight=panelHeight;
        this.maxX = maxX;
        this.maxY = maxY;
        this.unitX = unitX;
        this.unitY = unitY;
        this.partOfX = partOfX;
        this.partOfY = partOfY;

        recessionX = (panelWidth/10) +20;
        recessionY = (panelHeight/10) +20;

        textSize = panelWidth / 20;
        layoutParams.width = panelWidth;
        layoutParams.height = panelHeight;
    }


    //----------

    /***
     *
     */
    private void init() {
        //定義外圍畫筆路徑
        path = new Path();
        paint = new Paint();
        layoutParams = new RelativeLayout.LayoutParams(panelWidth, panelHeight);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        setLayoutParams(layoutParams);

    }


    //-----------

    /***
     *  設定好後 開始繪製
     */
    public void startDrawPanel() {
        bitmap = Bitmap.createBitmap(panelWidth, panelHeight, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);

        //文字大小
        paint.setTextSize(textSize);
        //畫筆颜色
        paint.setColor(Color.BLACK);
        //線條寬度
        paint.setStrokeWidth((float) panelWidth / 100);

        //畫筆移至Ｙ軸箭頭左邊
        path.moveTo(recessionX - textSize, recessionY + textSize);
        //畫筆畫線至Ｙ軸箭頭中間
        path.lineTo(recessionX, recessionY);
        //畫筆畫線至Ｙ軸箭頭右邊
        path.lineTo(recessionX + textSize, recessionY + textSize);
        //畫筆畫線至Ｙ軸箭頭中間
        path.moveTo(recessionX, recessionY);
        //畫筆畫線至Ｙ軸底端
        path.lineTo(recessionX, panelHeight - recessionY);
        //畫筆畫線至Ｘ軸底端
        path.lineTo(panelWidth - recessionX, panelHeight - recessionY);
        //畫筆移至Ｘ軸箭頭右側
        path.moveTo(panelWidth - recessionX - textSize, panelHeight - recessionY + textSize);
        //畫筆畫線至Ｘ軸箭頭中間
        path.lineTo(panelWidth - recessionX, panelHeight - recessionY);
        //畫筆移至Ｘ軸箭頭左側
        path.lineTo(panelWidth - recessionX - textSize, panelHeight - recessionY - textSize);


        canvas.drawText(unitY , 0, panelHeight- 20- textSize, paint);
        canvas.drawText(unitX, textSize, panelHeight - 20, paint);

        //筆刷為簍空
        paint.setStyle(Paint.Style.STROKE);

        //畫布背景顏色
        canvas.drawColor(context.getResources().getColor(android.R.color.transparent));
        //畫上路徑
        canvas.drawPath(path, paint);
        //新定義線條粗細
        paint.setStrokeWidth((float) 2.0);

        //計算 每個x間劇
        float intervalX = (panelWidth - (2 * recessionX)) / partOfX;

        //計算 每個y間劇
        float intervalY = (panelHeight - (2 * recessionY)) / partOfY;

        //水平第一個間距
        int startX = maxX / partOfX;
        //垂直第一個間距
        int startY = maxY / partOfY;

        //筆刷為填滿
        paint.setStyle(Paint.Style.FILL);

        //繪製水平數據
        //文字置中
        paint.setTextAlign(Paint.Align.CENTER);
        for (int i = 0; i < partOfX; i++) {
            //水平數據 由左而右畫
            canvas.drawLine((i + 1) * intervalX + recessionX, panelHeight - recessionY, (i + 1) * intervalX + recessionX, recessionY, paint);
            canvas.drawText(startX + "", (i + 1) * intervalX + recessionX, panelHeight - 20, paint);
            startX += startX / (i + 1);
        }

        //繪製垂直數據
        //文字靠左
        paint.setTextAlign(Paint.Align.LEFT);
        for (int i = 0; i < partOfY; i++) {
            //垂直數據 由下而上畫
            canvas.drawLine(recessionX, (partOfY - 1 - i) * intervalY + recessionY, panelWidth - recessionX, (partOfY - 1 - i) * intervalY + recessionY, paint);
            canvas.drawText(startY + "", 0, (partOfY - 1 - i) * intervalY + recessionY, paint);
            startY += startY / (i + 1);
        }

        //畫完 進行set
        setImageBitmap(bitmap);
    }

}
