package com.yu.functionbox.db

import android.util.Log
import com.yu.functionbox.FunctionBoxApplication
import com.yu.functionbox.base.BizCallback
import com.yu.functionbox.base.BizResult
import com.yu.functionbox.base.BusinessId
import com.yu.functionbox.data.FunctionBean
import com.yu.functionbox.data.SceneBean
import com.yu.functionbox.data.SortBean
import com.yu.functionbox.db.dao.FunctionDao
import com.yu.functionbox.db.dao.ScenesDao
import com.yu.functionbox.db.dao.SortDao
import com.yu.functionbox.db.entity.Function
import com.yu.functionbox.db.entity.Scenes
import com.yu.functionbox.db.entity.Sort
import java.util.*

/**
 * Created by YU on 2017/12/31.
 */

object DbService {

    private val TAG: String ="DbService"

    fun insertSort(name: String, callback: BizCallback<*>) {
        object : DBTaskRx(callback) {
            override fun doInBackground(): BizResult {
                val bizResult = BizResult(BusinessId.INSERT_SORT_INFO_TO_DB)
                bizResult.succeed = false
                try {
                    val sortDao = FunctionBoxApplication.daoSession!!.sortDao
                    val sort = Sort()
                    sort.id = null
                    sort.name = name
                    sortDao.insert(sort)
                    bizResult.succeed = true
                    bizResult.data = null
                } catch (e: Exception) {
                    Log.e(TAG, "insertSort() e = " + e.message)
                }

                return bizResult
            }
        }.execute()
    }

    fun insertScenes(sortId: Long, title: String, callback: BizCallback<*>) {
        object : DBTaskRx(callback) {
            override fun doInBackground(): BizResult {
                val bizResult = BizResult(BusinessId.INSERT_SCENES_INFO_TO_DB)
                bizResult.succeed = false
                try {
                    val scenesDao = FunctionBoxApplication.daoSession!!.scenesDao
                    val scenes = Scenes()
                    scenes.sortId = sortId
                    scenes.name = title
                    scenesDao.insert(scenes)
                    bizResult.succeed = true
                } catch (e: Exception) {
                    Log.e(TAG, "insertScenes() e = " + e.message)
                }

                return bizResult
            }
        }.execute()
    }



    fun insertFunction(sceneId: Long, name: String, detail: String, callback: BizCallback<*>) {
        object : DBTaskRx(callback) {
            override fun doInBackground(): BizResult {
                val bizResult = BizResult(BusinessId.INSERT_FUNCTION_INFO_TO_DB)
                bizResult.succeed = false
                try {
                    val functionDao = FunctionBoxApplication.daoSession!!.functionDao
                    val function = Function()
                    function.sceneId = sceneId
                    function.name = name
                    function.detail = detail
                    functionDao.insert(function)
                    bizResult.succeed = true
                } catch (e: Exception) {
                    Log.e(TAG, "insertFunction() e = " + e.message)
                }

                return bizResult
            }
        }.execute()
    }

    fun updateFunction(id: Long, content: String, callback: BizCallback<*>) {
        object : DBTaskRx(callback) {
            override fun doInBackground(): BizResult {
                val bizResult = BizResult(BusinessId.INSERT_FUNCTION_INFO_TO_DB)
                bizResult.succeed = false
                try {
                    val functionDao = FunctionBoxApplication.daoSession!!.functionDao
                    val function = functionDao.queryBuilder().where(FunctionDao.Properties.Id.eq(id)).build().unique()
                    function.detail = content
                    functionDao.update(function)
                    bizResult.succeed = true
                } catch (e: Exception) {
                    Log.e(TAG, "updateFunction() e = " + e.message)
                }

                return bizResult
            }
        }.execute()
    }

    fun getSorts(callback: BizCallback<*>) {
        object : DBTaskRx(callback) {
            override fun doInBackground(): BizResult {
                val bizResult = BizResult(BusinessId.GET_ALL_SORT_FROM_DB)
                bizResult.succeed = false
                val sortBeanList = ArrayList<SortBean>()
                try {
                    val sortDao = FunctionBoxApplication.daoSession!!.sortDao
                    val query = sortDao.queryBuilder().orderAsc(SortDao.Properties.Id).build()
                    val sorts = query.list()
                    for (sort in sorts) {
                        val bean = SortBean()
                        bean.id = sort.id
                        bean.name = sort.name
                        sortBeanList.add(bean)
                    }
                    bizResult.succeed = true
                    bizResult.data = sortBeanList
                } catch (e: Exception) {
                    Log.e(TAG, "getSorts() e = " + e.message)
                }

                return bizResult
            }
        }.execute()
    }

    fun getScenes(sortId: Long, callback: BizCallback<*>) {
        object : DBTaskRx(callback) {
            override fun doInBackground(): BizResult {
                val bizResult = BizResult(BusinessId.GET_ALL_SCENES_FROM_DB)
                bizResult.succeed = false
                val sceneBeanList = ArrayList<SceneBean>()
                try {
                    val scenesDao = FunctionBoxApplication.daoSession!!.scenesDao
                    val query = scenesDao.queryBuilder().where(ScenesDao.Properties.SortId.eq(sortId)).orderAsc(ScenesDao.Properties.Id).build()
                    val scenesList = query.list()
                    for (scenes in scenesList) {
                        val bean = SceneBean()
                        bean.id = scenes.id
                        bean.name = scenes.name
                        sceneBeanList.add(bean)
                    }
                    bizResult.succeed = true
                    bizResult.data = sceneBeanList
                } catch (e: Exception) {
                    Log.e(TAG, "getScenes() e = " + e.message)
                }

                return bizResult
            }
        }.execute()
    }

    fun getFunctions(sceneId: Long, callback: BizCallback<*>) {
        object : DBTaskRx(callback) {
            override fun doInBackground(): BizResult {
                val bizResult = BizResult(BusinessId.GET_ALL_FUNCTION_FROM_DB)
                bizResult.succeed = false
                val functionBeanList = ArrayList<FunctionBean>()
                try {
                    val functionDao = FunctionBoxApplication.daoSession!!.functionDao
                    val query = functionDao.queryBuilder().where(FunctionDao.Properties.SceneId.eq(sceneId)).orderAsc(FunctionDao.Properties.Id).build()
                    val functionList = query.list()
                    for (function in functionList) {
                        val bean = FunctionBean()
                        bean.id = function.id
                        bean.name = function.name
                        bean.detail = function.detail
                        functionBeanList.add(bean)
                    }
                    bizResult.succeed = true
                    bizResult.data = functionBeanList
                } catch (e: Exception) {
                    Log.e(TAG, "getFuncitons() e = " + e.message)
                }

                return bizResult
            }
        }.execute()
    }
}
