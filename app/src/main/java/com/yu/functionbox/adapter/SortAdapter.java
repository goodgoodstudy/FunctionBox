package com.yu.functionbox.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.yu.functionbox.R;
import com.yu.functionbox.base.HeaderFooterAdapter;
import com.yu.functionbox.data.SortBean;
import com.yu.functionbox.databinding.ItemSortBinding;
import com.yu.functionbox.event.EventMessage;
import com.yu.functionbox.event.MyEvent;
import com.yu.functionbox.main.SortItemViewModel;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yuw on 2017-12-29.
 * description
 */

public class SortAdapter extends HeaderFooterAdapter<SortBean> {
    private final static String TAG = "SortAdapter";
    private int mSelectPos = 0;

    public SortAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mFooterView != null && viewType == TYPE_FOOTER) {
            return new SortViewHolder(mFooterView);
        }
        if(mHeaderView != null && viewType == TYPE_HEADER){
            return new SortViewHolder(mHeaderView);
        }
        return new SortViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_sort, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder.getItemViewType() != TYPE_FOOTER && holder.getItemViewType() != TYPE_HEADER){
            ((SortViewHolder)holder).bind(position,mList.get(position-1));
        }
    }

    class SortViewHolder extends RecyclerView.ViewHolder {
        ItemSortBinding mBinding;

        public SortViewHolder(ItemSortBinding binding) {
            super(binding.getRoot());
            mBinding = binding;

        }

        public SortViewHolder(View itemView) {
            super(itemView);
            if (itemView == mFooterView){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final EditText editText = new EditText(mContext);
                        AlertDialog.Builder inputDialog =
                                new AlertDialog.Builder(mContext);
                        inputDialog.setTitle("请输入名称").setView(editText);
                        inputDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                 String title = editText.getText().toString();
                                if(!TextUtils.isEmpty(title)){
                                    EventBus.getDefault().post(new EventMessage<>(MyEvent.EVENT_INSERT_SORT,title));
                                }
                            }
                        }).show();
                    }
                });
            }
        }

        public void bind(final int pos,SortBean sortBean){
            if(mBinding.getViewModel() == null){
                mBinding.setViewModel(new SortItemViewModel());
            }
            mBinding.getViewModel().bind(sortBean,pos == mSelectPos);
            mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mSortItemClickListener.onItemClick(pos);
                    mBinding.getViewModel().clickSort();
                    notifyDataSetChanged();

                }
            });
            mBinding.executePendingBindings();
        }

    }

    private SortItemClickListener mSortItemClickListener;

    public interface SortItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(SortItemClickListener listener){
        mSortItemClickListener = listener;
    }

    public void setSelectPos(int selectPos) {
        mSelectPos = selectPos;
    }
}
