package com.yu.functionbox.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.ObservableField

import com.yu.functionbox.R
import com.yu.functionbox.data.FunctionBean
import com.yu.functionbox.databinding.BaseViewModel
import com.yu.functionbox.databinding.BindingView
import com.yu.functionbox.function.FunctionActivity

/**
 * Created by yuw on 2017-12-29.
 * description
 */
@BindingView(R.layout.item_function)
class FunctionItemViewModel(private val mContext: Context) : BaseViewModel() {
    private var mFunctionBean: FunctionBean? = null
    var mDetail = ObservableField<String>()

    fun bind(functionBean: FunctionBean) {
        mFunctionBean = functionBean
        mDetail.set(functionBean.detail)
    }

    fun clickFunction() {
        val intent = Intent(mContext, FunctionActivity::class.java)
        intent.putExtra(FunctionActivity.KEY_TYPE, FunctionActivity.FLAG_CHECK)
        intent.putExtra(FunctionActivity.KEY_ID, mFunctionBean?.id)
        intent.putExtra(FunctionActivity.KEY_CONTENT, mFunctionBean!!.detail)
        (mContext as Activity).startActivityForResult(intent, FunctionActivity.REQUEST_FUNCTION)
    }
}
