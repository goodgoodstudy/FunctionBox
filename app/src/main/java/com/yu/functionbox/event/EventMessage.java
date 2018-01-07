package com.yu.functionbox.event;

import java.util.List;

/**
 *
 * Created by admin on 2016/6/29.
 */
public class EventMessage<T> {

    public T mObj;
    public List<T> mList;
    public int mCode;

    public EventMessage(int code, List<T> list){
        this.mList = list;
        this.mCode = code;
    }

    public EventMessage(int code) {
        mCode = code;
    }

    public EventMessage(int code, T obj) {
        mCode = code;
        this.mObj = obj;
    }

}
