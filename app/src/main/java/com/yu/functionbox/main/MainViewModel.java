package com.yu.functionbox.main;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.yu.functionbox.base.BizCallback;
import com.yu.functionbox.base.BizResult;
import com.yu.functionbox.data.FunctionBean;
import com.yu.functionbox.data.ScenesBean;
import com.yu.functionbox.data.SortBean;
import com.yu.functionbox.db.DbService;

import java.util.ArrayList;

/**
 * Created by yuw on 2017-12-29.
 * description
 */

public class MainViewModel {
    public ObservableList<SortBean> mSortBeans = new ObservableArrayList<>();
    public ObservableList<ScenesBean> mScenesBeans = new ObservableArrayList<>();
    public ObservableList<FunctionBean> mFunctionBeans = new ObservableArrayList<>();


    public void initData() {
        DbService.get().getSorts(new BizCallback<ArrayList<SortBean>>() {
            @Override
            public void onSucceed(ArrayList<SortBean> response, @NonNull BizResult bizResult) {
                mSortBeans.clear();
                mSortBeans.addAll(response);
            }

            @Override
            public void onFailed(String errorMsg, @NonNull BizResult bizResult) {

            }

        });

        DbService.get().getScenes(new BizCallback<ArrayList<ScenesBean>>() {
            @Override
            public void onSucceed(ArrayList<ScenesBean> response, @NonNull BizResult bizResult) {
                mScenesBeans.clear();
                mScenesBeans.addAll(response);
            }

            @Override
            public void onFailed(String errorMsg, @NonNull BizResult bizResult) {

            }

        });

        DbService.get().getFuncitons(new BizCallback<ArrayList<FunctionBean>>() {
            @Override
            public void onSucceed(ArrayList<FunctionBean> response, @NonNull BizResult bizResult) {
                mFunctionBeans.clear();
                mFunctionBeans.addAll(response);
            }

            @Override
            public void onFailed(String errorMsg, @NonNull BizResult bizResult) {

            }

        });
    }
}
