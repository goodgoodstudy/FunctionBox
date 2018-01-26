package com.yu.functionbox

import android.app.Application
import com.yu.functionbox.db.dao.DaoMaster
import com.yu.functionbox.db.dao.DaoSession

/**
 * Created by yuw on 2017-12-29.
 * description
 */

class FunctionBoxApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
        initDb()
    }

    private fun initDb() {
        //创建数据库
        val helper = DaoMaster.DevOpenHelper(this, DB_NAME, null)
        //获取可写数据库
        val db = helper.writableDatabase
        //获取数据库对象
        val daoMaster = DaoMaster(db)
        //获取dao对象管理者
        daoSession = daoMaster.newSession()
    }

    companion object {
        var application: FunctionBoxApplication? = null
            private set
        var daoSession: DaoSession? = null
            private set
        private val DB_NAME = "function.db"
    }


}
