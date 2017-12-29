package com.yu.functionbox.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangr on 2017/3/23 0023.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<T> mList;
    protected Context mContext;

    public BaseAdapter(Context mContext) {
        this.mList = new ArrayList<>();
        this.mContext = mContext;
    }

    public void setDatas(List<T> datas) {
        mList.clear();
        addDatas(datas);
    }

    public void addDatas(List<T> datas) {
        if (datas != null && !datas.isEmpty()) {
            mList.addAll(datas);
        }
        notifyDataSetChanged();
    }

    @Nullable
    public T getItem(int position) {
        try {
            return mList.get(position);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getItemCount() {
        try {
            return mList.size();
        } catch (Exception e) {
            return 0;
        }
    }
}
