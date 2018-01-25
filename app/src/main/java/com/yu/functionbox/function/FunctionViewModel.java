package com.yu.functionbox.function;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.yu.functionbox.R;
import com.yu.functionbox.data.FunctionBean;
import com.yu.functionbox.databinding.BindingView;
import com.yu.functionbox.event.EventMessage;
import com.yu.functionbox.event.MyEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yuw on 2018-01-25.
 * description
 */
@BindingView(R.layout.activity_function)
public class FunctionViewModel {
    private Context mContext;
    public ObservableField<String> mContent = new ObservableField<>();
    public ObservableBoolean enable = new ObservableBoolean();
    public boolean mIsCheck;
    public long mId;

    public FunctionViewModel(Context context) {
        mContext = context;
    }

    public void setContent(long id,String content) {
        mId = id;
        mContent.set(content);
        mIsCheck =true;
    }

    public void enableEdit(boolean isEnable) {
        enable.set(isEnable);
    }
    public void action(){
        if(enable.get()){
            completeEdit();
            enableEdit(false);
        }else{
            enableEdit(true);

        }
    }
    public void completeEdit(){
        if(mIsCheck){
            FunctionBean bean = new FunctionBean();
            bean.setId(mId);
            bean.setDetail(mContent.get());
            EventBus.getDefault().post(new EventMessage<>(MyEvent.EVENT_SAVE_FUNCTION,bean));
        }else{
            EventBus.getDefault().post(new EventMessage<>(MyEvent.EVENT_INSERT_FUNCTION,mContent.get()));
        }

    }
}
