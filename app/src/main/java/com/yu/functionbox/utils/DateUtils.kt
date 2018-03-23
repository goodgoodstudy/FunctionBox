package com.yu.functionbox.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by admin on 2016/7/7.
 */
object DateUtils {
    val stringDate: String
        get() {
            val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
            return df.format(System.currentTimeMillis())
        }

}
