package com.yu.functionbox.base;


import android.support.annotation.NonNull;


public interface BaseBizCallback<T, K extends BaseBizResult> {

    void onSucceed(T response, @NonNull K bizResult);

    void onFailed(String errorMsg, @NonNull K bizResult);
}