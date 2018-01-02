package com.yu.functionbox.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yu.functionbox.R;
import com.yu.functionbox.base.BaseAdapter;
import com.yu.functionbox.data.ScenesBean;
import com.yu.functionbox.databinding.ItemSimpleBinding;
import com.yu.functionbox.main.SimpleItemViewModel;

/**
 * Created by yuw on 2017-12-29.
 * description
 */

public class ScenesAdapter extends BaseAdapter<ScenesBean>{

    public ScenesAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SortViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_simple, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SortViewHolder)holder).bind(mList.get(position));
    }

    class SortViewHolder extends RecyclerView.ViewHolder {
        ItemSimpleBinding mBinding;

        public SortViewHolder(ItemSimpleBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(ScenesBean scenesBean){
            if(mBinding.getViewModel() == null){
                mBinding.setViewModel(new SimpleItemViewModel());
            }
            mBinding.getViewModel().bind(scenesBean.getName());
            mBinding.executePendingBindings();
        }

    }
}
