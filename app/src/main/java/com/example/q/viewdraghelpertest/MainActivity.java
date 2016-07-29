package com.example.q.viewdraghelpertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private DragLayout dl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dl = (DragLayout) findViewById(R.id.dl);
        dl.setDragStatusListener(new DragLayout.onDragStatusChangeListener() {
            @Override
            public void onClose() {
                Util.showToast(getApplicationContext(),"onClose");
            }

            @Override
            public void onOpen() {
                Util.showToast(getApplicationContext(),"onOpen");
            }

            @Override
            public void onDragging(float percent) {
                Util.showToast(getApplicationContext(),"onDragging");
            }
        });
    }
}
