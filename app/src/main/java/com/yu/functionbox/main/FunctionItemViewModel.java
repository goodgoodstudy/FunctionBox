package com.yu.functionbox.main;

import android.databinding.ObservableField;

import com.yu.functionbox.R;
import com.yu.functionbox.data.FunctionBean;
import com.yu.functionbox.databinding.BaseViewModel;
import com.yu.functionbox.databinding.BindingView;

/**
 * Created by yuw on 2017-12-29.
 * description
 */
@BindingView(R.layout.item_function)
public class FunctionItemViewModel extends BaseViewModel{
    private FunctionBean mFunctionBean;
    public ObservableField<String> mDetail = new ObservableField<>();

    public void bind(FunctionBean functionBean) {
        mFunctionBean = functionBean;
        mDetail.set(functionBean.getDetail());
    }
}
