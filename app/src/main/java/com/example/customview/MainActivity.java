package com.example.customview;
//https://www.youtube.com/watch?v=KrXGfwZ7qW8
//目的:Android 提供了讓你很方便讓你將不同的UI元件組成一個客制化的View。並將這個組合的View裡的邏輯封裝在一起。這一篇我們要來示範如何透過Custom View Component提高可測試性。
//1.res/values/attrs.xml  自己定義屬性可以玩xml屬性
//2.寫一個View的類別繼承你要發揚光大的View 這邊用的是TextView => androidx.appcompat.widget.AppCompatTextView

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
