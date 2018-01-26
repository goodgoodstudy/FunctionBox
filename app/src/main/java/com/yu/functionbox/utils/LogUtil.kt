package com.yu.functionbox.utils

import android.util.Log

/**
 * Created by yuw on 2018-01-18.
 * description
 */

object LogUtil {
    fun i(`object`: Any, msg: String) {
        Log.i("yuwei" + `object`.javaClass.name, msg)
    }

    fun e(`object`: Any, msg: String) {
        Log.e("yuwei" + `object`.javaClass.name, msg)
    }
}
