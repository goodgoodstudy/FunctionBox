package com.yu.functionbox.main;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.yu.functionbox.R;
import com.yu.functionbox.adapter.SortAdapter;
import com.yu.functionbox.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    ActivityMainBinding mBinding;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel mViewModel = new MainViewModel();
        mBinding.setViewModel(mViewModel);
        initData();
        initAdapter();
    }

    private void initData() {

    }

    private void initAdapter() {
        List fakeData = new ArrayList();
        for (int i = 0; i < 10; i++) {
            fakeData.add(i + "");
        }
        mBinding.rvSort.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.rvScenes.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.rvFunction.setLayoutManager(new LinearLayoutManager(mContext));
        SortAdapter sortAdapter = new SortAdapter(mContext);
        sortAdapter.setDatas(fakeData);
        mBinding.rvSort.setAdapter(sortAdapter);
        mBinding.rvScenes.setAdapter(sortAdapter);
        mBinding.rvFunction.setAdapter(sortAdapter);

    }


}
