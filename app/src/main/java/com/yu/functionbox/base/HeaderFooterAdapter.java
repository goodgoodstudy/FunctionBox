package com.yu.functionbox.base;

import android.content.Context;
import android.view.View;

/**
 * Created by yuw on 2017-12-29.
 * description
 */

public abstract class HeaderFooterAdapter<T> extends BaseAdapter<T>{
    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的

    public View mFooterView;
    public View mHeaderView;

    public HeaderFooterAdapter(Context mContext) {
        super(mContext);
    }


    public View getFooterView() {
        return mFooterView;
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount()-1);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_HEADER;
        }else if (position == getItemCount()-1){
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    //返回View中Item的个数，这个时候，总的个数应该是ListView中Item的个数加上HeaderView和FooterView
    @Override
    public int getItemCount() {
        if(mFooterView == null && mHeaderView ==null){
            return mList.size();
        }else if(mFooterView != null && mHeaderView !=null){
            return mList.size() + 2;
        }else {
            return mList.size() + 1;
        }
    }



}
