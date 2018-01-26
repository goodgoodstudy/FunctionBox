package com.yu.functionbox.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yu.functionbox.R;
import com.yu.functionbox.base.HeaderFooterAdapter;
import com.yu.functionbox.data.FunctionBean;
import com.yu.functionbox.databinding.ItemFunctionBinding;
import com.yu.functionbox.function.FunctionActivity;
import com.yu.functionbox.main.FunctionItemViewModel;

/**
 * Created by yuw on 2017-12-29.
 * description
 */

public class FunctionAdapter extends HeaderFooterAdapter<FunctionBean> {
    private final static String TAG = "FunctionAdapter";

    public FunctionAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mFooterView != null && viewType == TYPE_FOOTER) {
            return new FunctionHolder(mFooterView);
        }
        if(mHeaderView != null && viewType == TYPE_HEADER){
            return new FunctionHolder(mHeaderView);
        }
        return new FunctionHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_function, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType() != TYPE_FOOTER && holder.getItemViewType() != TYPE_HEADER ){
            ((FunctionHolder)holder).bind(mList.get(position-1));
        }
    }

    class FunctionHolder extends RecyclerView.ViewHolder {
        ItemFunctionBinding mBinding;

        public FunctionHolder(ItemFunctionBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public FunctionHolder(View itemView) {
            super(itemView);
            if (itemView == mFooterView){
                itemView.setOnClickListener(view -> {
                   Intent intent = new Intent(mContext, FunctionActivity.class);
                   intent.putExtra(FunctionActivity.KEY_TYPE,FunctionActivity.FLAG_CREATE_NEW);
                    ((Activity)mContext).startActivityForResult(intent,FunctionActivity.REQUEST_FUNCTION);
                });
            }else if(itemView == mHeaderView){
                //
            }
        }

        public void bind(FunctionBean functionBean){
            if(mBinding.getViewModel() == null){
                mBinding.setViewModel(new FunctionItemViewModel(mContext));
            }
            mBinding.getViewModel().bind(functionBean);
            mBinding.executePendingBindings();
        }

    }


}
