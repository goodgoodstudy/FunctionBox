package com.yu.functionbox.main;

import android.databinding.ObservableField;

import com.yu.functionbox.R;
import com.yu.functionbox.databinding.BaseViewModel;
import com.yu.functionbox.databinding.BindingView;

/**
 * Created by yuw on 2017-12-29.
 * description
 */
@BindingView(R.layout.item_simple)
public class SimpleItemViewModel extends BaseViewModel{

    public ObservableField<String> mTitle = new ObservableField<>();

    public void bind(String str) {
        mTitle.set(str);
    }
}
