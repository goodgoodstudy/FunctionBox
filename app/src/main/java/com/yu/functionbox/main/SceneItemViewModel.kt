package com.yu.functionbox.main

import android.databinding.ObservableBoolean
import android.databinding.ObservableField

import com.yu.functionbox.R
import com.yu.functionbox.data.SceneBean
import com.yu.functionbox.databinding.BaseViewModel
import com.yu.functionbox.databinding.BindingView
import com.yu.functionbox.event.EventMessage
import com.yu.functionbox.event.MyEvent

import org.greenrobot.eventbus.EventBus

/**
 * Created by yuw on 2017-12-29.
 * description
 */
@BindingView(R.layout.item_scene)
class SceneItemViewModel : BaseViewModel() {
    private var mSceneBean: SceneBean? = null
    var mTitle = ObservableField<String>()
    var mIsSelect = ObservableBoolean()

    fun bind(sceneBean: SceneBean, isSelect: Boolean) {
        mSceneBean = sceneBean
        mTitle.set(sceneBean.name)
        mIsSelect.set(isSelect)
    }

    fun clickScene() {
        EventBus.getDefault().post(EventMessage(MyEvent.EVENT_CLICK_SCENE, mSceneBean?.id))
    }
}
