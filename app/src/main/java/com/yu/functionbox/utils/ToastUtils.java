package com.yu.functionbox.utils;

import android.widget.Toast;

import com.yu.functionbox.FunctionBoxApplication;


/**
 * Created by yianzhang on 16/8/21.
 */
public class ToastUtils {

    public static void show(String s) {
        Toast.makeText(FunctionBoxApplication.getApplication(), s, Toast.LENGTH_SHORT).show();
    }
}
