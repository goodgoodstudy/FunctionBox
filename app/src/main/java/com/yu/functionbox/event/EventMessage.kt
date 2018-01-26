package com.yu.functionbox.event

/**
 *
 * Created by admin on 2016/6/29.
 */
class EventMessage<T> {

    var mObj: T ?= null
    var mList: List<T> ?= null
    var mCode: Int = 0

    constructor(code: Int, list: List<T>) {
        this.mList = list
        this.mCode = code
    }

    constructor(code: Int) {
        mCode = code
    }

    constructor(code: Int, obj: T) {
        mCode = code
        this.mObj = obj
    }

}
