package com.yu.functionbox.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yu.functionbox.R;
import com.yu.functionbox.base.BaseAdapter;
import com.yu.functionbox.databinding.ItemDbBinding;
import com.yu.functionbox.main.DbItemViewModel;

import java.io.File;

/**
 * Created by yuw on 2017-12-29.
 * description
 */

public class DbAdapter extends BaseAdapter<File> {
    private final static String TAG = "DbAdapter";

    public DbAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DbViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_db, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((DbViewHolder) holder).bind(mList.get(position));
    }

    class DbViewHolder extends RecyclerView.ViewHolder {
        ItemDbBinding mBinding;

        public DbViewHolder(ItemDbBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(File file) {
            if (mBinding.getViewModel() == null) {
                mBinding.setViewModel(new DbItemViewModel());
            }
            mBinding.getViewModel().bind(file);
            mBinding.executePendingBindings();
        }

    }
}
