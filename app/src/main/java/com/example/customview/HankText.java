package com.example.customview;
//2.1:繼承androidx.appcompat.widget.AppCompatTextView
//2.2:準備老爸建構式這三種
//*public HankText(Context context) {
//        super(context);
//    }
//*public HankText(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//    }
//*public HankText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//2.3:宣告自己要玩的屬性這邊是 title,style
//2.4:寫自己的新方法這裡是兩個為主
//2.5當你直接用Xml創建時會被調用(老爸建構式)
//2.6對應自訂的View賦予值 <==> attrs.xnl
        /*
        <declare-styleable name="HankText">
        <attr name="title" format="string"/>
        <attr name="style" format="boolean"/>
        </declare-styleable>
         */
//TypedArray obtainStyledAttributes( AttributeSet set, @NonNull @StyleableRes int[] attrs) //獲取樣式屬性(1.指定的attr屬性 2.自訂的屬性名稱)

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;


import androidx.annotation.Nullable;

//2.1繼承androidx.appcompat.widget.AppCompatTextView
public class HankText extends androidx.appcompat.widget.AppCompatTextView {

    //2.3:宣告自己要玩的屬性這邊是 title,style
    private String title;
    private boolean style;
    private boolean animation;
    private String TAG = "hank";

    //老爸建構式
    public HankText(Context context) {
        super(context);

        setMyTextTitle();
        Log.v(TAG, "HankText(Context context)");
    }

    //2.5當你直接用Xml創建時會被調用(老爸建構式)
    public HankText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //2.6對應自訂的View賦予值 <==> attrs.xnl
        /*
        <declare-styleable name="HankText">
        <attr name="title" format="string"/>
        <attr name="style" format="boolean"/>
        </declare-styleable>
         */
        //TypedArray obtainStyledAttributes( AttributeSet set, @NonNull @StyleableRes int[] attrs) //獲取樣式屬性(1.指定的attr屬性 2.自訂的屬性名稱)
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HankText); //獲取樣式屬性(1.指定的attr屬性 2.自訂的屬性名稱)
        int count = typedArray.getIndexCount(); //取得索引次數. 2=>因為設定attrs設定兩個屬性

        try {
            for (int i = 0; i < count; ++i) {
                int attr = typedArray.getIndex(i); //取得索引的第i位置元素
                               switch (attr){
                    case R.styleable.HankText_title:
                        title = typedArray.getString(attr);//取得該值得String給值
                        Log.v(TAG, "HankText(Context context, @Nullable AttributeSet attrs) title =>" + "/typedArray.getIndexCount():" + count + "/typedArray.getIndex:" + attr + "/typedArray.getString(attr) :" + title);
                        setMyTextTitle();//設定Title
                        break;
                    case R.styleable.HankText_style:
                        style = typedArray.getBoolean(attr, false);
                        Log.v(TAG, "HankText(Context context, @Nullable AttributeSet attrs) style =>" + "/typedArray.getIndexCount():" + count + "/typedArray.getIndex:" + attr + "/typedArray.getboolean(attr) :" + style);
                        setMyTextStyle();//設定Style(true/false)
                        break;
                    case R.styleable.HankText_animation:
                        animation = typedArray.getBoolean(attr, false);
                        Log.v(TAG, "HankText(Context context, @Nullable AttributeSet attrs) animation =>" + "/typedArray.getIndexCount():" + count + "/typedArray.getIndex:" + attr + "/typedArray.getboolean(attr) :" + style);
                        setMyTextAnimationScaleBigRepeat();
                        break;
                }

            }
        } finally { // finally{此區為最後處理}
            typedArray.recycle(); //回收typedArray,讓調用者重新使用
        }
    }

    //老爸建構式
    public HankText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setMyTextTitle();
        Log.v(TAG, "HankText(Context context, @Nullable AttributeSet attrs, int defStyleAttr)");
    }

    //2.4: 自訂設定TextTitle方法
    private void setMyTextTitle() {
        //如果朕的值有人賦予了才可設定Title
        if (this.title != null) {
            setText(this.title);
            Log.v(TAG, "setMyTextTitle");
        }
    }

    //2.4  自訂設定TextStyle => true/flase (設定與不設定)
    private void setMyTextStyle() {
        //如果朕的style為true才改Style
        if (this.style) {
            setTextColor(Color.GREEN);
            setTextSize(50f);

            Log.v(TAG, "setMyTextStyle");
        }
    }

    //2.4 自訂無限縮放效果
    private void setMyTextAnimationScaleBigRepeat(){
        if(this.animation){
            //ScaleAnimation(float fromX, float toX, float fromY, float toY)
            //(fromX,toX)X軸從fromX的倍率放大/縮小至toX的倍率
            //(fromY,toY)X軸從fromY的倍率放大/縮小至toX的倍率
            Animation am = new ScaleAnimation(0.0f, 4.0f, 0.0f, 4.0f);
            //setDuration (long durationMillis) 設定動畫開始到結束的執行時間
            am.setDuration(2000);
            //setRepeatCount (int repeatCount) 設定重複次數 -1為無限次數 0
            am.setRepeatCount(-1);
            //將動畫參數設定到圖片並開始執行動畫
            setAnimation(am);
            Log.v(TAG, "setMyTextAnimation");
        }

    }
}
