package com.yu.functionbox.utils

import org.greenrobot.eventbus.EventBus

/**
 * Created by yuw on 2017-05-09.
 * description
 */

object EventBusUtils {
    /**
     * this
     * @param subscriber
     */
    fun register(subscriber: Any?) {
        if (subscriber != null && !EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber)
        }
    }

    fun unRegister(subscriber: Any?) {
        if (subscriber != null && EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().unregister(subscriber)
        }
    }
}
