package com.yu.functionbox.main;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.yu.functionbox.R;
import com.yu.functionbox.data.SortBean;
import com.yu.functionbox.databinding.BaseViewModel;
import com.yu.functionbox.databinding.BindingView;
import com.yu.functionbox.event.EventMessage;
import com.yu.functionbox.event.MyEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yuw on 2017-12-29.
 * description
 */
@BindingView(R.layout.item_sort)
public class SortItemViewModel extends BaseViewModel{
    private SortBean mSortBean;
    public ObservableBoolean mIsSelect =new ObservableBoolean();
    public ObservableField<String> mTitle = new ObservableField<>();



    public void bind(SortBean sortBean) {
        mSortBean = sortBean;
        mTitle.set(mSortBean.getName());
    }
    public void clickSort(){
        mIsSelect.set(true);
        EventBus.getDefault().post(new EventMessage<>(MyEvent.EVENT_CLICK_SORT,mSortBean.getId()));
    }
}