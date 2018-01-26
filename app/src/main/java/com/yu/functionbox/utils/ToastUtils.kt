package com.yu.functionbox.utils

import android.widget.Toast

import com.yu.functionbox.FunctionBoxApplication


/**
 * Object声明的对象实际为单例模式的对象
 */
object ToastUtils {

    fun show(s: String) {
        Toast.makeText(FunctionBoxApplication.application, s, Toast.LENGTH_SHORT).show()
    }
}
