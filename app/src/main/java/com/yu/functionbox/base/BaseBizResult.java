package com.yu.functionbox.base;

import android.support.annotation.Nullable;

/**
 * Created by shigaoyang on 2016/12/16 0016.
 */
public class BaseBizResult<T> {

    private final T mId;
    private boolean mSucceed;
    private Object mData;
    private int mErrorCode;
    private String mErrorMsg;

    public BaseBizResult(T id) {
        mId = id;
    }

    public T getId() {
        return mId;
    }

    public void setSucceed(boolean succeed) {
        mSucceed = succeed;
    }

    public boolean getSucceed() {
        return mSucceed;
    }

    public void setData(Object data) {
        mData = data;
    }

    @Nullable
    public Object getData() {
        return mData;
    }

    public void setErrorCode(int errorCode) {
        mErrorCode = errorCode;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public void setErrorMsg(String errorMsg) {
        mErrorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return mErrorMsg;
    }

}