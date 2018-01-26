package com.yu.functionbox.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yu.functionbox.R;
import com.yu.functionbox.adapter.FunctionAdapter;
import com.yu.functionbox.adapter.ScenesAdapter;
import com.yu.functionbox.adapter.SortAdapter;
import com.yu.functionbox.data.FunctionBean;
import com.yu.functionbox.data.SceneBean;
import com.yu.functionbox.data.SortBean;
import com.yu.functionbox.databinding.ActivityMainBinding;
import com.yu.functionbox.event.EventMessage;
import com.yu.functionbox.event.MyEvent;
import com.yu.functionbox.function.FunctionActivity;
import com.yu.functionbox.utils.EventBusUtils;

import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends Activity {
    private ActivityMainBinding mBinding;
    private Context mContext;
    private MainViewModel mViewModel;
    private SortAdapter sortAdapter;
    private ScenesAdapter scenesAdapter;
    private FunctionAdapter functionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        EventBusUtils.register(this);
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
        sortAdapter = new SortAdapter(mContext);
        View sortHeadView = LayoutInflater.from(this).inflate(R.layout.item_header,mBinding.rvSort,false);
        ((TextView)sortHeadView.findViewById(R.id.header_text)).setText(R.string.sort);
        sortAdapter.setHeaderView(sortHeadView);
        sortAdapter.setFooterView(LayoutInflater.from(this).inflate(R.layout.item_footer, mBinding.rvSort, false));
        sortAdapter.setOnItemClickListener(sortAdapter::setSelectPos);

        scenesAdapter = new ScenesAdapter(mContext);
        View scenesHeadView = LayoutInflater.from(this).inflate(R.layout.item_header,mBinding.rvSort,false);
        ((TextView)scenesHeadView.findViewById(R.id.header_text)).setText(R.string.scenes);
        scenesAdapter.setHeaderView(scenesHeadView);
        scenesAdapter.setFooterView(LayoutInflater.from(this).inflate(R.layout.item_footer, mBinding.rvSort, false));
        scenesAdapter.setOnItemClickListener((int selectPos) -> {
            scenesAdapter.setSelectPos(selectPos);
        });

        functionAdapter = new FunctionAdapter(mContext);
        View functionHeadView = LayoutInflater.from(this).inflate(R.layout.item_header,mBinding.rvSort,false);
        ((TextView)functionHeadView.findViewById(R.id.header_text)).setText(R.string.function);
        functionAdapter.setHeaderView(functionHeadView);
        functionAdapter.setFooterView(LayoutInflater.from(this).inflate(R.layout.item_footer, mBinding.rvSort, false));
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
                scenesAdapter.setDatas(sender);
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
                functionAdapter.setDatas(sender);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtils.unRegister(this);
        mViewModel.destroy();

    }

    @Subscribe
    public void onEventUpdate(EventMessage event) {
        switch (event.mCode) {
            case MyEvent.EVENT_SELECT_LAST_SORT:
                sortAdapter.setSelectPos((Integer) event.mObj);
                break;
            case MyEvent.EVENT_SELECT_LAST_SCENE:
                scenesAdapter.setSelectPos((Integer) event.mObj);
                break;
            case MyEvent.EVENT_SELECT_FIRST_SORT:
                sortAdapter.setSelectPos(1);
                break;
            case MyEvent.EVENT_SELECT_FIRST_SCENE:
                scenesAdapter.setSelectPos(1);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == FunctionActivity.REQUEST_FUNCTION && resultCode == FunctionActivity.RESULT_FUNCTION){
            mViewModel.updateFunction();
        }
    }
}
