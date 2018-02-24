package com.yu.functionbox.utils

import android.os.Environment
import com.yu.functionbox.FunctionBoxApplication
import com.yu.functionbox.R
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException







/**
 * Created by yuw on 2018-01-18.
 * description
 */

object DbBackUpUtils {
    private val DB_PATH_SD = Environment.getExternalStorageDirectory().absolutePath + File.separator + "functionBox" + File.separator
    private val DB_PATH = (File.separator + "data"
            + Environment.getDataDirectory().absolutePath + File.separator
            + FunctionBoxApplication.application?.packageName + File.separator + "databases" + File.separator)
    fun backUpDb(){
        val dbFile = File(DB_PATH,"function.db")
        val toDir = File(DB_PATH_SD)
        if (!toDir.exists()) {
            toDir.mkdirs()
        }

        val toDb = File(DB_PATH_SD + "function.db") //外部存储数据库

        val inputStream = FileInputStream(dbFile)
        val outputStream = FileOutputStream(toDb)
        val buffer = ByteArray(1024)
        try {
            while (inputStream.read(buffer, 0, buffer.size) > 0) {
                outputStream.write(buffer, 0, inputStream.read(buffer, 0, buffer.size))
            }
            outputStream.flush()
            outputStream.close()
            inputStream.close()
            ToastUtils.show(ResourceUtil.getString(R.string.back_up_success))
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
