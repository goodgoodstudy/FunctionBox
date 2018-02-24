package com.yu.functionbox.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ObservableList
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView

import com.yu.functionbox.R
import com.yu.functionbox.adapter.FunctionAdapter
import com.yu.functionbox.adapter.ScenesAdapter
import com.yu.functionbox.adapter.SortAdapter
import com.yu.functionbox.data.FunctionBean
import com.yu.functionbox.data.SceneBean
import com.yu.functionbox.data.SortBean
import com.yu.functionbox.databinding.ActivityMainBinding
import com.yu.functionbox.event.EventMessage
import com.yu.functionbox.event.MyEvent
import com.yu.functionbox.function.FunctionActivity
import com.yu.functionbox.utils.EventBusUtils

import org.greenrobot.eventbus.Subscribe

class MainActivity : Activity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mContext: Context
    private lateinit var mViewModel: MainViewModel
    private lateinit var sortAdapter: SortAdapter
    private lateinit var scenesAdapter: ScenesAdapter
    private lateinit var functionAdapter: FunctionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        EventBusUtils.register(this)
        mViewModel = MainViewModel()
        mBinding.viewModel = mViewModel
        initData()
        initAdapter()
    }

    private fun initData() {
        mViewModel.initData()
    }

    private fun initAdapter() {
        mBinding.rvSort.layoutManager = LinearLayoutManager(mContext)
        mBinding.rvScenes.layoutManager = LinearLayoutManager(mContext)
        mBinding.rvFunction.layoutManager = LinearLayoutManager(mContext)
        sortAdapter = SortAdapter(mContext)
        val sortHeadView = LayoutInflater.from(this).inflate(R.layout.item_header, mBinding.rvSort, false)
        (sortHeadView.findViewById<View>(R.id.header_text) as TextView).setText(R.string.sort)
        sortAdapter.headerView = sortHeadView
        sortAdapter.footerView = LayoutInflater.from(this).inflate(R.layout.item_footer, mBinding.rvSort, false)
        sortAdapter.setOnItemClickListener({ sortAdapter.setSelectPos(it) })

        scenesAdapter = ScenesAdapter(mContext)
        val scenesHeadView = LayoutInflater.from(this).inflate(R.layout.item_header, mBinding.rvSort, false)
        (scenesHeadView.findViewById<View>(R.id.header_text) as TextView).setText(R.string.scenes)
        scenesAdapter.headerView = scenesHeadView
        scenesAdapter.footerView = LayoutInflater.from(this).inflate(R.layout.item_footer, mBinding.rvSort, false)
        scenesAdapter.setOnItemClickListener { selectPos: Int -> scenesAdapter.setSelectPos(selectPos) }

        functionAdapter = FunctionAdapter(mContext)
        val functionHeadView = LayoutInflater.from(this).inflate(R.layout.item_header, mBinding.rvSort, false)
        (functionHeadView.findViewById<View>(R.id.header_text) as TextView).setText(R.string.function)
        functionAdapter.headerView = functionHeadView
        functionAdapter.footerView = LayoutInflater.from(this).inflate(R.layout.item_footer, mBinding.rvSort, false)
        mBinding.rvSort.adapter = sortAdapter
        mBinding.rvScenes.adapter = scenesAdapter
        mBinding.rvFunction.adapter = functionAdapter
        mViewModel.mSortBeans.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableList<SortBean>>() {
            override fun onChanged(sender: ObservableList<SortBean>) {

            }

            override fun onItemRangeChanged(sender: ObservableList<SortBean>, positionStart: Int, itemCount: Int) {

            }

            override fun onItemRangeInserted(sender: ObservableList<SortBean>, positionStart: Int, itemCount: Int) {
                sortAdapter.setDatas(sender)
            }

            override fun onItemRangeMoved(sender: ObservableList<SortBean>, fromPosition: Int, toPosition: Int, itemCount: Int) {

            }

            override fun onItemRangeRemoved(sender: ObservableList<SortBean>, positionStart: Int, itemCount: Int) {

            }
        })

        mViewModel.mSceneBeans.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableList<SceneBean>>() {
            override fun onChanged(sender: ObservableList<SceneBean>) {}

            override fun onItemRangeChanged(sender: ObservableList<SceneBean>, positionStart: Int, itemCount: Int) {}

            override fun onItemRangeInserted(sender: ObservableList<SceneBean>, positionStart: Int, itemCount: Int) {
                scenesAdapter.setDatas(sender)
            }

            override fun onItemRangeMoved(sender: ObservableList<SceneBean>, fromPosition: Int, toPosition: Int, itemCount: Int) {}

            override fun onItemRangeRemoved(sender: ObservableList<SceneBean>, positionStart: Int, itemCount: Int) {
                scenesAdapter.setDatas(sender)
            }
        })

        mViewModel.mFunctionBeans.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableList<FunctionBean>>() {
            override fun onChanged(sender: ObservableList<FunctionBean>) {

            }

            override fun onItemRangeChanged(sender: ObservableList<FunctionBean>, positionStart: Int, itemCount: Int) {

            }

            override fun onItemRangeInserted(sender: ObservableList<FunctionBean>, positionStart: Int, itemCount: Int) {
                functionAdapter.setDatas(sender)
            }

            override fun onItemRangeMoved(sender: ObservableList<FunctionBean>, fromPosition: Int, toPosition: Int, itemCount: Int) {

            }

            override fun onItemRangeRemoved(sender: ObservableList<FunctionBean>, positionStart: Int, itemCount: Int) {
                functionAdapter.setDatas(sender)
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        EventBusUtils.unRegister(this)
        mViewModel.destroy()

    }

    @Subscribe
    fun onEventUpdate(event: EventMessage<*>) {
        when (event.mCode) {
            MyEvent.EVENT_SELECT_LAST_SORT -> sortAdapter.setSelectPos(event.mObj as Int)
            MyEvent.EVENT_SELECT_LAST_SCENE -> scenesAdapter.setSelectPos(event.mObj as Int)
            MyEvent.EVENT_SELECT_FIRST_SORT -> sortAdapter.setSelectPos(1)
            MyEvent.EVENT_SELECT_FIRST_SCENE -> scenesAdapter.setSelectPos(1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FunctionActivity.REQUEST_FUNCTION && resultCode == FunctionActivity.RESULT_FUNCTION) {
            mViewModel.updateFunction()
        }
    }
}
