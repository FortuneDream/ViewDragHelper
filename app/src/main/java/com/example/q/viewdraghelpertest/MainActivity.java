package com.example.q.viewdraghelpertest;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private DragLayout dl;
    private ListView lvLeft;
    private ListView lvMain;
    private ImageView ivMain;
    private MyLinearLayout myMainLl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        init();
        dl.setDragStatusListener(new DragLayout.onDragStatusChangeListener() {
            @Override
            public void onClose() {
                Util.showToast(getApplicationContext(), "onClose");
                //让图表晃动
                ObjectAnimator mAinm=ObjectAnimator.ofFloat(ivMain,"translationX",15.0f);
                mAinm.setDuration(500);
                mAinm.setInterpolator(new CycleInterpolator(4));//来回晃动四圈
                mAinm.start();

            }

            @Override
            public void onOpen() {
                Util.showToast(getApplicationContext(), "onOpen");
                //左面版listView随机设置一个条目
                Random random=new Random();
                int nextInt=random.nextInt();
                lvLeft.smoothScrollToPosition(nextInt);//平滑移动
            }

            @Override
            public void onDragging(float percent) {
                Util.showToast(getApplicationContext(), "onDragging");
//                Log.e("TAG", "percent:" + percent);
                ivMain.setAlpha(1-percent);
            }
        });
    }

    private void init() {
        //快速设置List字符串,重写方法，设置字体为白色
        lvLeft.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Cheeses.sCheeseStrings){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view=super.getView(position, convertView, parent);
                TextView mText= (TextView) view;
                mText.setTextColor(Color.WHITE);
                return mText;
            }
        });
        lvMain.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Cheeses.names));
        myMainLl.setDragLayout(dl);
    }

    private void findView() {
        dl = (DragLayout) findViewById(R.id.dl);
        lvLeft = (ListView) findViewById(R.id.lv_left);
        lvMain = (ListView) findViewById(R.id.lv_main);
        dl = (DragLayout) findViewById(R.id.dl);
        ivMain = (ImageView) findViewById(R.id.iv_main);
        myMainLl = (MyLinearLayout) findViewById(R.id.my_main_ll);
    }
}
