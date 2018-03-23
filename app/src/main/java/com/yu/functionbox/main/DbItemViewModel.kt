package com.yu.functionbox.main

import android.databinding.ObservableField
import com.yu.functionbox.R
import com.yu.functionbox.databinding.BaseViewModel
import com.yu.functionbox.databinding.BindingView
import com.yu.functionbox.event.EventMessage
import com.yu.functionbox.event.MyEvent
import com.yu.functionbox.utils.DbBackUpUtils
import org.greenrobot.eventbus.EventBus
import java.io.File

/**
 * Created by yuw on 2017-12-29.
 * description
 */
@BindingView(R.layout.item_db)
class DbItemViewModel : BaseViewModel() {
    var mTitle = ObservableField<String>()
    var dbFile:File?=null
    fun bind(file: File) {
        dbFile = file
        mTitle.set(file.name)
    }

    fun clickDb() {
        dbFile?.let { DbBackUpUtils.recoverDb(it) }
        EventBus.getDefault().post(EventMessage<String>(MyEvent.EVENT_CLICK_Db))
    }
}
