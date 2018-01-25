package com.yu.functionbox.databinding;

import android.databinding.BindingAdapter;
import android.text.InputType;
import android.widget.EditText;

/**
 * Created by shigaoyang on 2017/3/22 0022.
 */

public class ViewAdapter {

    @BindingAdapter("android:enableEdit")
    public static void enableEdit(EditText view,boolean enable){
        view.setFocusable(enable);
        view.setFocusableInTouchMode(enable);
        view.requestFocus();
        view.setSelection(view.getText().length());
    }
}
