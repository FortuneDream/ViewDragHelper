package com.example.q.viewdraghelpertest;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by YQ on 2016/7/29.
 */
//单例模式的Toast
public class Util {
    private static Toast toast;

    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        }
        toast.setText(content);
        toast.show();
    }
}
