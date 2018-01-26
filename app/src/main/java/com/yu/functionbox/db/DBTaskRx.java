package com.yu.functionbox.db;

import android.support.annotation.Nullable;

import com.yu.functionbox.base.BaseBizResult;
import com.yu.functionbox.base.BizCallback;
import com.yu.functionbox.base.BizResult;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yanzhang on 2017/2/7.
 */

public abstract class DBTaskRx {

    public DBTaskRx(@Nullable BizCallback BizCallback) {
        mCallback = BizCallback;
    }

    private BizCallback mCallback;

    protected abstract BizResult doInBackground();

    public void execute() {
        Observable.create(new ObservableOnSubscribe<BaseBizResult>() {
            @Override
            public void subscribe(ObservableEmitter<BaseBizResult> e) throws Exception {
                BizResult bizResult = DBTaskRx.this.doInBackground();
                if (bizResult != null) {
                    e.onNext(bizResult);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BaseBizResult>() {
            @Override
            public void accept(BaseBizResult bizResult) throws Exception {
                if (mCallback != null && bizResult != null) {
                    if (bizResult.getSucceed()) {
                        mCallback.onSucceed(bizResult.getData(), bizResult);
                    } else {
                        mCallback.onFailed(bizResult.getErrorMsg(), bizResult);
                    }
                }
            }
        });
    }

}
