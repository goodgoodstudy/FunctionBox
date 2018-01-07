package com.yu.functionbox.main;

import android.databinding.ObservableField;

import com.yu.functionbox.R;
import com.yu.functionbox.data.SceneBean;
import com.yu.functionbox.databinding.BaseViewModel;
import com.yu.functionbox.databinding.BindingView;
import com.yu.functionbox.event.EventMessage;
import com.yu.functionbox.event.MyEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yuw on 2017-12-29.
 * description
 */
@BindingView(R.layout.item_scene)
public class SceneItemViewModel extends BaseViewModel{
    private SceneBean mSceneBean;
    public ObservableField<String> mTitle = new ObservableField<>();

    public void bind(SceneBean sceneBean) {
        mSceneBean = sceneBean;
        mTitle.set(sceneBean.getName());

    }

    public void clickScene(){
        EventBus.getDefault().post(new EventMessage<>(MyEvent.EVENT_CLICK_SCENE,mSceneBean.getId()));
    }
}
