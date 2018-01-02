package com.yu.functionbox.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yu.functionbox.R;
import com.yu.functionbox.base.BaseAdapterWithFooter;
import com.yu.functionbox.data.SortBean;
import com.yu.functionbox.databinding.ItemSimpleBinding;
import com.yu.functionbox.main.SimpleItemViewModel;

/**
 * Created by yuw on 2017-12-29.
 * description
 */

public class SortAdapter extends BaseAdapterWithFooter<SortBean> {

    public SortAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mFooterView != null && viewType == TYPE_FOOTER) {
            return new SortViewHolder(mFooterView);
        }
        return new SortViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_simple, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType() != TYPE_FOOTER){
            ((SortViewHolder)holder).bind(mList.get(position));
        }
    }

    class SortViewHolder extends RecyclerView.ViewHolder {
        ItemSimpleBinding mBinding;

        public SortViewHolder(ItemSimpleBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public SortViewHolder(View itemView) {
            super(itemView);
            if (itemView == mFooterView){
                return;
            }
        }

        public void bind(SortBean sortBean){
            if(mBinding.getViewModel() == null){
                mBinding.setViewModel(new SimpleItemViewModel());
            }
            mBinding.getViewModel().bind(sortBean.getName());
            mBinding.executePendingBindings();
        }

    }
}
