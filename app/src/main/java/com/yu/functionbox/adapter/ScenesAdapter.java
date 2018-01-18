package com.yu.functionbox.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.yu.functionbox.R;
import com.yu.functionbox.base.BaseAdapterWithFooter;
import com.yu.functionbox.data.SceneBean;
import com.yu.functionbox.databinding.ItemSceneBinding;
import com.yu.functionbox.event.EventMessage;
import com.yu.functionbox.event.MyEvent;
import com.yu.functionbox.main.SceneItemViewModel;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yuw on 2017-12-29.
 * description
 */

public class ScenesAdapter extends BaseAdapterWithFooter<SceneBean> {
    private final static String TAG = "ScenesAdapter";
    private int mSelectPos = 0;

    public ScenesAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mFooterView != null && viewType == TYPE_FOOTER) {
            return new ScenesAdapter.ScenesViewHolder(mFooterView);
        }
        return new ScenesViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_scene, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType() != TYPE_FOOTER) {
            ((ScenesViewHolder) holder).bind(position, mList.get(position));
        }
    }

    class ScenesViewHolder extends RecyclerView.ViewHolder {
        ItemSceneBinding mBinding;

        public ScenesViewHolder(ItemSceneBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
        public ScenesViewHolder(View itemView) {
            super(itemView);
            if (itemView == mFooterView){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final EditText editText = new EditText(mContext);
                        AlertDialog.Builder inputDialog = new AlertDialog.Builder(mContext);
                        inputDialog.setTitle("请输入名称").setView(editText);
                        inputDialog.setPositiveButton("确定", (dialogInterface, i) -> {
                            String title = editText.getText().toString();
                            if(!TextUtils.isEmpty(title)){
                                EventBus.getDefault().post(new EventMessage<>(MyEvent.EVENT_SAVE_SCENE,title));
                            }
                        }).show();
                    }
                });
            }
        }

        public void bind(final int pos, SceneBean sceneBean){
            if(mBinding.getViewModel() == null){
                mBinding.setViewModel(new SceneItemViewModel());
            }
            mBinding.getViewModel().bind(sceneBean, pos == mSelectPos);
            mBinding.getRoot().setOnClickListener(view -> {
                mSceneItemClickListener.onItemClick(pos);
                mBinding.getViewModel().clickScene();
                notifyDataSetChanged();

            });
            mBinding.executePendingBindings();
        }

    }

    private ScenesAdapter.SceneItemClickListener mSceneItemClickListener;

    public interface SceneItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ScenesAdapter.SceneItemClickListener listener){
        mSceneItemClickListener = listener;
    }

    public void setSelectPos(int selectPos) {
        mSelectPos = selectPos;
    }
}
