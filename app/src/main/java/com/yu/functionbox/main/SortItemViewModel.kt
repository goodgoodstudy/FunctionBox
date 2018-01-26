package com.yu.functionbox.main

import android.databinding.ObservableBoolean
import android.databinding.ObservableField

import com.yu.functionbox.R
import com.yu.functionbox.data.SortBean
import com.yu.functionbox.databinding.BaseViewModel
import com.yu.functionbox.databinding.BindingView
import com.yu.functionbox.event.EventMessage
import com.yu.functionbox.event.MyEvent

import org.greenrobot.eventbus.EventBus

/**
 * Created by yuw on 2017-12-29.
 * description
 */
@BindingView(R.layout.item_sort)
class SortItemViewModel : BaseViewModel() {
    private var mSortBean: SortBean? = null
    var mTitle = ObservableField<String>()
    var mIsSelect = ObservableBoolean()

    fun bind(sortBean: SortBean, isSelect: Boolean) {
        mSortBean = sortBean
        mTitle.set(mSortBean?.name)
        mIsSelect.set(isSelect)
    }

    fun clickSort() {
        EventBus.getDefault().post(EventMessage(MyEvent.EVENT_CLICK_SORT, mSortBean?.id))
    }
}
