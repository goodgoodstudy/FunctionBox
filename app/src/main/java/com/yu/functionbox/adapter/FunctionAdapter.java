package com.yu.functionbox.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yu.functionbox.R;
import com.yu.functionbox.base.BaseAdapter;
import com.yu.functionbox.data.FunctionBean;
import com.yu.functionbox.databinding.ItemFunctionBinding;
import com.yu.functionbox.main.FunctionItemViewModel;

/**
 * Created by yuw on 2017-12-29.
 * description
 */

public class FunctionAdapter extends BaseAdapter<FunctionBean>{

    public FunctionAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FunctionHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_function, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((FunctionHolder)holder).bind(mList.get(position));
    }

    class FunctionHolder extends RecyclerView.ViewHolder {
        ItemFunctionBinding mBinding;

        public FunctionHolder(ItemFunctionBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(FunctionBean functionBean){
            if(mBinding.getViewModel() == null){
                mBinding.setViewModel(new FunctionItemViewModel());
            }
            mBinding.getViewModel().bind(functionBean.getName());
            mBinding.executePendingBindings();
        }

    }
}
