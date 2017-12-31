package com.yu.functionbox;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.yu.functionbox.db.dao.DaoMaster;
import com.yu.functionbox.db.dao.DaoSession;

/**
 * Created by yuw on 2017-12-29.
 * description
 */

public class FunctionBoxApplication extends Application {
    private static FunctionBoxApplication mInstance;
    private static DaoSession mDaoSession;
    private static final String DB_NAME = "function.db";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initDb();
    }

    private void initDb() {
        //创建数据库
        DaoMaster.DevOpenHelper helper= new DaoMaster.DevOpenHelper(this,DB_NAME,null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取dao对象管理者
        mDaoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession(){
        return mDaoSession;
    }


}
