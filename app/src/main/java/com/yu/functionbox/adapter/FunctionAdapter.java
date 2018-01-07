package com.yu.functionbox.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.yu.functionbox.R;
import com.yu.functionbox.base.BaseAdapterWithFooter;
import com.yu.functionbox.data.FunctionBean;
import com.yu.functionbox.databinding.ItemFunctionBinding;
import com.yu.functionbox.event.EventMessage;
import com.yu.functionbox.event.MyEvent;
import com.yu.functionbox.main.FunctionItemViewModel;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yuw on 2017-12-29.
 * description
 */

public class FunctionAdapter extends BaseAdapterWithFooter<FunctionBean> {
    private final static String TAG = "FunctionAdapter";

    public FunctionAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mFooterView != null && viewType == TYPE_FOOTER) {
            return new FunctionHolder(mFooterView);
        }
        return new FunctionHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_function, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType() != TYPE_FOOTER){
            ((FunctionHolder)holder).bind(mList.get(position));
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
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i(TAG, "onClick: ");
                        final EditText editText = new EditText(mContext);
                        editText.setMinLines(10);
                        AlertDialog.Builder inputDialog =
                                new AlertDialog.Builder(mContext);
                        inputDialog.setTitle("请输入内容").setView(editText);
                        inputDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String detail = editText.getText().toString();
                                if(!TextUtils.isEmpty(detail)){
                                    EventBus.getDefault().post(new EventMessage<>(MyEvent.EVENT_SAVE_FUNCTION,detail));
                                }
                            }
                        }).show();
                    }
                });
            }
        }

        public void bind(FunctionBean functionBean){
            if(mBinding.getViewModel() == null){
                mBinding.setViewModel(new FunctionItemViewModel());
            }
            mBinding.getViewModel().bind(functionBean);
            mBinding.executePendingBindings();
        }

    }
}
