package com.yu.functionbox.db;

import android.util.Log;

import com.yu.functionbox.FunctionBoxApplication;
import com.yu.functionbox.base.BizResult;
import com.yu.functionbox.db.dao.SortDao;
import com.yu.functionbox.db.entity.Sort;

/**
 * Created by YU on 2017/12/31.
 */

public class DbService {
    public static final String TAG = "DBService";
    public static DbService mInstance;

    public DbService get() {
        if (mInstance == null) {
            mInstance = new DbService();
        }
        return mInstance;
    }

    public void insertSort(){
        new DBTaskRx(){
            @Override
            protected BizResult doInBackground() {
                try{
                    SortDao sortDao = FunctionBoxApplication.getDaoSession().getSortDao();
                    Sort sort = new Sort();
                    sort.setName("test");
                    sortDao.insert(sort);
                }catch (Exception e){
                    Log.e(TAG, "insertSort() e = " + e.getMessage());
                }
                return null;
            }
        }.execute();
    }
}
