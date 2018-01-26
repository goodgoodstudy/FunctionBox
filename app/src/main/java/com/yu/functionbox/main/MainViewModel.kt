package com.yu.functionbox.main

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.text.TextUtils
import android.util.Log
import com.yu.functionbox.base.BizCallback
import com.yu.functionbox.base.BizResult
import com.yu.functionbox.data.FunctionBean
import com.yu.functionbox.data.SceneBean
import com.yu.functionbox.data.SortBean
import com.yu.functionbox.db.DbService
import com.yu.functionbox.event.EventMessage
import com.yu.functionbox.event.MyEvent
import com.yu.functionbox.utils.EventBusUtils
import com.yu.functionbox.utils.ToastUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*

/**
 * Created by yuw on 2017-12-29.
 * description
 */

class MainViewModel {
    var mSortBeans: ObservableList<SortBean> = ObservableArrayList()
    var mSceneBeans: ObservableList<SceneBean> = ObservableArrayList()
    var mFunctionBeans: ObservableList<FunctionBean> = ObservableArrayList()
    private var mSortId: Long = 0
    private var mSceneId: Long = 0

    init {
        EventBusUtils.register(this)
    }

    fun initData() {
        DbService.getSorts(object : BizCallback<ArrayList<SortBean>> {
            override fun onSucceed(response: ArrayList<SortBean>, bizResult: BizResult) {
                mSortBeans.clear()
                mSortBeans.addAll(response)
                if (response.size > 0) {
                    EventBus.getDefault().post(EventMessage<Any>(MyEvent.EVENT_SELECT_FIRST_SORT))
                    val id = response[0].id
                    mSortId = id
                    getScenesBySortId(id)
                }
            }

            override fun onFailed(errorMsg: String, bizResult: BizResult) {
                Log.i(TAG, "getSorts onFailed: ")
            }

        })

    }

    private fun getScenesBySortId(id: Long) {
        DbService.getScenes(id, object : BizCallback<ArrayList<SceneBean>> {
            override fun onSucceed(response: ArrayList<SceneBean>, bizResult: BizResult) {
                mSceneBeans.clear()
                mSceneBeans.addAll(response)
                if (response.size > 0) {
                    EventBus.getDefault().post(EventMessage<Any>(MyEvent.EVENT_SELECT_FIRST_SCENE))
                    mSceneId = response[0].id
                    getFunctionBySceneId(mSceneId)
                } else {
                    mFunctionBeans.clear()
                }

            }

            override fun onFailed(errorMsg: String, bizResult: BizResult) {
                Log.i(TAG, "getScenesBySortId onFailed: ")
            }

        })
    }

    private fun getFunctionBySceneId(sceneId: Long) {
        DbService.getFunctions(sceneId, object : BizCallback<ArrayList<FunctionBean>> {
            override fun onSucceed(response: ArrayList<FunctionBean>, bizResult: BizResult) {
                mFunctionBeans.clear()
                mFunctionBeans.addAll(response)
            }

            override fun onFailed(errorMsg: String, bizResult: BizResult) {
                Log.i(TAG, "getFunctionBySceneId onFailed: ")
            }

        })
    }


    @Subscribe
    fun onEventUpdate(event: EventMessage<*>) {
        when (event.mCode) {
            MyEvent.EVENT_INSERT_SORT -> {
                val title = event.mObj as String
                if (!TextUtils.isEmpty(title)) {
                    DbService.insertSort(title, object : BizCallback<Any> {
                        override fun onSucceed(response: Any?, bizResult: BizResult) {
                            ToastUtils.show("添加成功!")
                            updateSort()
                        }

                        override fun onFailed(errorMsg: String?, bizResult: BizResult) {

                        }
                    })
                }
            }
            MyEvent.EVENT_INSERT_SCENE -> {
                val title2 = event.mObj as String
                if (!TextUtils.isEmpty(title2)) {
                    DbService.insertScenes(mSortId, title2, object : BizCallback<Any> {
                        override fun onSucceed(response: Any?, bizResult: BizResult) {
                            ToastUtils.show("添加成功!")
                            updateScene()
                        }

                        override fun onFailed(errorMsg: String?, bizResult: BizResult) {

                        }
                    })
                }
            }
            MyEvent.EVENT_INSERT_FUNCTION -> {
                val detail = event.mObj as String
                if (!TextUtils.isEmpty(detail)) {
                    DbService.insertFunction(mSceneId, "test", detail, object : BizCallback<Any> {
                        override fun onSucceed(response: Any?, bizResult: BizResult) {
                            ToastUtils.show("添加成功!")
                            updateFunction()
                        }

                        override fun onFailed(errorMsg: String, bizResult: BizResult) {

                        }
                    })
                }
            }
            MyEvent.EVENT_CLICK_SORT -> {
                mSortId = event.mObj as Long
                getScenesBySortId(mSortId)
            }
            MyEvent.EVENT_CLICK_SCENE -> {
                mSceneId = event.mObj as Long
                getFunctionBySceneId(mSceneId)
            }
            MyEvent.EVENT_SAVE_FUNCTION -> saveFunction(event.mObj as FunctionBean)
        }
    }

    private fun saveFunction(bean: FunctionBean) {
        DbService.updateFunction(bean.id, bean.detail, object : BizCallback<Any> {
            override fun onSucceed(response: Any?, bizResult: BizResult) {
                ToastUtils.show("保存成功")
            }

            override fun onFailed(errorMsg: String, bizResult: BizResult) {
                ToastUtils.show("保存失败")
            }
        })
    }


    private fun updateSort() {
        DbService.getSorts(object : BizCallback<ArrayList<SortBean>> {
            override fun onSucceed(response: ArrayList<SortBean>, bizResult: BizResult) {
                mSortBeans.clear()
                mSortBeans.addAll(response)
                if (response.size > 0) {
                    EventBus.getDefault().post(EventMessage(MyEvent.EVENT_SELECT_LAST_SORT, response.size))
                    val id = response[response.size].id
                    mSortId = id
                    getScenesBySortId(id)
                }
            }

            override fun onFailed(errorMsg: String, bizResult: BizResult) {
                Log.i(TAG, "getSorts onFailed: ")
            }

        })
    }

    private fun updateScene() {
        DbService.getScenes(mSortId, object : BizCallback<ArrayList<SceneBean>> {
            override fun onSucceed(response: ArrayList<SceneBean>, bizResult: BizResult) {
                mSceneBeans.clear()
                mSceneBeans.addAll(response)
                EventBus.getDefault().post(EventMessage(MyEvent.EVENT_SELECT_LAST_SCENE, response.size))
                if (response.size > 0) {
                    mSceneId = response[response.size - 1].id
                    getFunctionBySceneId(mSceneId)
                }
            }

            override fun onFailed(errorMsg: String, bizResult: BizResult) {
                Log.i(TAG, "getScenesBySortId onFailed: ")
            }

        })
    }

    fun updateFunction() {
        DbService.getFunctions(mSceneId, object : BizCallback<ArrayList<FunctionBean>> {
            override fun onSucceed(response: ArrayList<FunctionBean>, bizResult: BizResult) {
                mFunctionBeans.clear()
                mFunctionBeans.addAll(response)
            }

            override fun onFailed(errorMsg: String, bizResult: BizResult) {
                Log.i(TAG, "getFunctionBySceneId onFailed: ")
            }

        })
    }

    fun destroy() {
        EventBusUtils.unRegister(this)
    }

    companion object {
        private val TAG = "MainViewModel"
    }
}
