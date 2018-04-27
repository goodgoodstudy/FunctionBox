package com.yu.functionbox.function

import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.graphics.drawable.Drawable

import com.yu.functionbox.R
import com.yu.functionbox.data.FunctionBean
import com.yu.functionbox.databinding.BindingView
import com.yu.functionbox.event.EventMessage
import com.yu.functionbox.event.MyEvent
import com.yu.functionbox.utils.ResourceUtil

import org.greenrobot.eventbus.EventBus

/**
 * Created by yuw on 2018-01-25.
 * description
 */
@BindingView(R.layout.activity_function)
class FunctionViewModel(private val mContext: Context) {
    var mContent = ObservableField<String>()
    var enable = ObservableBoolean()
    var mIconDrawable = ObservableField<Drawable>(ResourceUtil.getDrawable(android.R.drawable.ic_menu_edit))
    private var mIsCheck: Boolean = false
    private var mId: Long = 0
    var isEdited: Boolean = false
        private set

    fun setContent(id: Long, content: String) {
        mId = id
        mContent.set(content)
        mIsCheck = true
    }

    fun enableEdit(isEnable: Boolean) {
        enable.set(isEnable)
    }

    fun action() {
        if (enable.get()) {
            completeEdit()
            enableEdit(false)
            mIconDrawable.set(ResourceUtil.getDrawable(android.R.drawable.ic_menu_edit))
        } else {
            enableEdit(true)
            mIconDrawable.set(ResourceUtil.getDrawable(android.R.drawable.ic_menu_save))
        }
    }

    private fun completeEdit() {
        isEdited = true
        if (mIsCheck) {
            val bean = FunctionBean()
            bean.id = mId
            bean.detail = mContent.get()?:""
            EventBus.getDefault().post(EventMessage(MyEvent.EVENT_SAVE_FUNCTION, bean))
        } else {
            EventBus.getDefault().post(EventMessage(MyEvent.EVENT_INSERT_FUNCTION, mContent.get()))
        }

    }
}
