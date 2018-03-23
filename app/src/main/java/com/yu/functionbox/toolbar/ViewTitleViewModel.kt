package com.yu.functionbox.toolbar
import android.app.Activity
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.graphics.drawable.Drawable
import android.view.View
import com.yu.functionbox.R
import com.yu.functionbox.databinding.BindingView
import com.yu.functionbox.utils.ResourceUtil


/**
 * Created by shigaoyang on 2017/3/23 0023.
 */
@BindingView(R.layout.view_title)
class ViewTitleViewModel(private val mActivity: Activity) {
    val title = ObservableField<String>()
    val rightText = ObservableField<String>()
    val rightTextColor = ObservableField<Int>(ResourceUtil.getColor(R.color.white))
    var rightDrawable = ObservableField<Drawable?>()
    var leftVisible = ObservableBoolean(true)
    var onRightClickListener: View.OnClickListener? = null

    fun onBackClick(view: View) {
        mActivity.finish()
    }
}
