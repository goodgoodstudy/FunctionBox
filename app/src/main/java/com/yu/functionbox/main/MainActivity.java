package com.yu.functionbox.main;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;

import com.yu.functionbox.R;
import com.yu.functionbox.adapter.FunctionAdapter;
import com.yu.functionbox.adapter.ScenesAdapter;
import com.yu.functionbox.adapter.SortAdapter;
import com.yu.functionbox.data.FunctionBean;
import com.yu.functionbox.data.SceneBean;
import com.yu.functionbox.data.SortBean;
import com.yu.functionbox.databinding.ActivityMainBinding;

public class MainActivity extends Activity {
    private ActivityMainBinding mBinding;
    private Context mContext;
    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = new MainViewModel();
        mBinding.setViewModel(mViewModel);
        initData();
        initAdapter();
    }

    private void initData() {
        mViewModel.initData();
    }

    private void initAdapter() {
        mBinding.rvSort.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.rvScenes.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.rvFunction.setLayoutManager(new LinearLayoutManager(mContext));
        SortAdapter sortAdapter = new SortAdapter(mContext);
        sortAdapter.setFooterView(LayoutInflater.from(this).inflate(R.layout.item_footer,mBinding.rvSort, false));
        sortAdapter.setOnItemClickListener(new SortAdapter.SortItemClickListener() {
            @Override
            public void onItemClick(int position) {
                sortAdapter.setSelectPos(position);
                sortAdapter.notifyDataSetChanged();
            }
        });
        ScenesAdapter scenesAdapter = new ScenesAdapter(mContext);
        scenesAdapter.setFooterView(LayoutInflater.from(this).inflate(R.layout.item_footer,mBinding.rvSort, false));
        FunctionAdapter functionAdapter = new FunctionAdapter(mContext);
        functionAdapter.setFooterView(LayoutInflater.from(this).inflate(R.layout.item_footer,mBinding.rvSort, false));
        mBinding.rvSort.setAdapter(sortAdapter);
        mBinding.rvScenes.setAdapter(scenesAdapter);
        mBinding.rvFunction.setAdapter(functionAdapter);
        mViewModel.mSortBeans.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<SortBean>>() {
            @Override
            public void onChanged(ObservableList<SortBean> sender) {

            }

            @Override
            public void onItemRangeChanged(ObservableList<SortBean> sender, int positionStart, int itemCount) {

            }

            @Override
            public void onItemRangeInserted(ObservableList<SortBean> sender, int positionStart, int itemCount) {
                sortAdapter.setDatas(sender);
            }

            @Override
            public void onItemRangeMoved(ObservableList<SortBean> sender, int fromPosition, int toPosition, int itemCount) {

            }

            @Override
            public void onItemRangeRemoved(ObservableList<SortBean> sender, int positionStart, int itemCount) {

            }
        });

        mViewModel.mSceneBeans.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<SceneBean>>() {
            @Override
            public void onChanged(ObservableList<SceneBean> sender) {

            }

            @Override
            public void onItemRangeChanged(ObservableList<SceneBean> sender, int positionStart, int itemCount) {

            }

            @Override
            public void onItemRangeInserted(ObservableList<SceneBean> sender, int positionStart, int itemCount) {
                scenesAdapter.setDatas(sender);
            }

            @Override
            public void onItemRangeMoved(ObservableList<SceneBean> sender, int fromPosition, int toPosition, int itemCount) {

            }

            @Override
            public void onItemRangeRemoved(ObservableList<SceneBean> sender, int positionStart, int itemCount) {

            }
        });

        mViewModel.mFunctionBeans.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<FunctionBean>>() {
            @Override
            public void onChanged(ObservableList<FunctionBean> sender) {

            }

            @Override
            public void onItemRangeChanged(ObservableList<FunctionBean> sender, int positionStart, int itemCount) {

            }

            @Override
            public void onItemRangeInserted(ObservableList<FunctionBean> sender, int positionStart, int itemCount) {
                functionAdapter.setDatas(sender);
            }

            @Override
            public void onItemRangeMoved(ObservableList<FunctionBean> sender, int fromPosition, int toPosition, int itemCount) {

            }

            @Override
            public void onItemRangeRemoved(ObservableList<FunctionBean> sender, int positionStart, int itemCount) {

            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.destroy();

    }
}
