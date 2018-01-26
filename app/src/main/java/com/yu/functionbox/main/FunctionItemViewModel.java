package com.yu.functionbox.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;

import com.yu.functionbox.R;
import com.yu.functionbox.data.FunctionBean;
import com.yu.functionbox.databinding.BaseViewModel;
import com.yu.functionbox.databinding.BindingView;
import com.yu.functionbox.function.FunctionActivity;

/**
 * Created by yuw on 2017-12-29.
 * description
 */
@BindingView(R.layout.item_function)
public class FunctionItemViewModel extends BaseViewModel{
    private FunctionBean mFunctionBean;
    public ObservableField<String> mDetail = new ObservableField<>();
    private Context mContext;

    public FunctionItemViewModel(Context context) {
        mContext = context;
    }

    public void bind(FunctionBean functionBean) {
        mFunctionBean = functionBean;
        mDetail.set(functionBean.getDetail());
    }

    public void clickFunction(){
//        EventBus.getDefault().post(new EventMessage<>(MyEvent.EVENT_CLICK_FUNCTION,mFunctionBean.getId()));
        Intent intent = new Intent(mContext, FunctionActivity.class);
        intent.putExtra(FunctionActivity.KEY_TYPE,FunctionActivity.FLAG_CHECK);
        intent.putExtra(FunctionActivity.KEY_ID,mFunctionBean.getId());
        intent.putExtra(FunctionActivity.KEY_CONTENT,mFunctionBean.getDetail());
        ((Activity)mContext).startActivityForResult(intent,FunctionActivity.REQUEST_FUNCTION);
    }
}
