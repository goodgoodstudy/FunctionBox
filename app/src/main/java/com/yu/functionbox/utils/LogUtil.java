package com.yu.functionbox.utils;

import android.util.Log;

/**
 * Created by yuw on 2018-01-18.
 * description
 */

public class LogUtil {
    public static void i(Object object,String msg){
        Log.i("yuwei"+object.getClass().getName(),msg);
    }

    public static void e(Object object,String msg){
        Log.e("yuwei"+object.getClass().getName(),msg);
    }
}
