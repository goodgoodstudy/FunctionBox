package com.yu.functionbox.utils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yuw on 2017-05-09.
 * description
 */

public class EventBusUtils {
    /**
     * this
     * @param subscriber
     */
    public static void register(Object subscriber) {
        if (subscriber != null && !EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber);
        }
    }

    public static void unRegister(Object subscriber) {
        if (subscriber != null && EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().unregister(subscriber);
        }
    }
}
