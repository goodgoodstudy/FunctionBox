package com.yu.functionbox.db;

import android.util.Log;

import com.yu.functionbox.FunctionBoxApplication;
import com.yu.functionbox.base.BizCallback;
import com.yu.functionbox.base.BizResult;
import com.yu.functionbox.base.BusinessId;
import com.yu.functionbox.data.FunctionBean;
import com.yu.functionbox.data.SceneBean;
import com.yu.functionbox.data.SortBean;
import com.yu.functionbox.db.dao.FunctionDao;
import com.yu.functionbox.db.dao.ScenesDao;
import com.yu.functionbox.db.dao.SortDao;
import com.yu.functionbox.db.entity.Function;
import com.yu.functionbox.db.entity.Scenes;
import com.yu.functionbox.db.entity.Sort;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YU on 2017/12/31.
 */

public class DbService {
    public static final String TAG = "DBService";
    public static DbService mInstance;

    public static DbService get() {
        if (mInstance == null) {
            mInstance = new DbService();
        }
        return mInstance;
    }

    public void insertSort(String name,BizCallback callback){
        new DBTaskRx(callback){
            @Override
            protected BizResult doInBackground() {
                BizResult bizResult = new BizResult(BusinessId.INSERT_SORT_INFO_TO_DB);
                bizResult.setSucceed(false);
                try{
                    SortDao sortDao = FunctionBoxApplication.getDaoSession().getSortDao();
                    Sort sort = new Sort();
                    sort.setId(null);
                    sort.setName(name);
                    sortDao.insert(sort);
                    bizResult.setSucceed(true);
                }catch (Exception e){
                    Log.e(TAG, "insertSort() e = " + e.getMessage());
                }
                return bizResult;
            }
        }.execute();
    }

    public void insertScenes(long sortId,String title,BizCallback callback){
        new DBTaskRx(callback){
            @Override
            protected BizResult doInBackground() {
                BizResult bizResult = new BizResult(BusinessId.INSERT_SCENES_INFO_TO_DB);
                bizResult.setSucceed(false);
                try{
                    ScenesDao scenesDao = FunctionBoxApplication.getDaoSession().getScenesDao();
                    Scenes scenes = new Scenes();
                    scenes.setSortId(sortId);
                    scenes.setName(title);
                    scenesDao.insert(scenes);
                    bizResult.setSucceed(true);
                }catch (Exception e){
                    Log.e(TAG, "insertScenes() e = " + e.getMessage());
                }
                return bizResult;
            }
        }.execute();
    }

    public void insertFunction(long sceneId, String name,String detail,BizCallback callback){
        new DBTaskRx(callback){
            @Override
            protected BizResult doInBackground() {
                BizResult bizResult = new BizResult(BusinessId.INSERT_FUNCTION_INFO_TO_DB);
                bizResult.setSucceed(false);
                try{
                    FunctionDao functionDao = FunctionBoxApplication.getDaoSession().getFunctionDao();
                    Function function = new Function();
                    function.setSceneId(sceneId);
                    function.setName(name);
                    function.setDetail(detail);
                    functionDao.insert(function);
                    bizResult.setSucceed(true);
                }catch (Exception e){
                    Log.e(TAG, "insertScenes() e = " + e.getMessage());
                }
                return bizResult;
            }
        }.execute();
    }

    public void getSorts(BizCallback callback) {
        new DBTaskRx(callback) {
            @Override
            protected BizResult doInBackground() {
                BizResult bizResult = new BizResult(BusinessId.GET_ALL_SORT_FROM_DB);
                bizResult.setSucceed(false);
                List <SortBean> sortBeanList = new ArrayList<>();
                try {
                    SortDao sortDao = FunctionBoxApplication.getDaoSession().getSortDao();
                    Query<Sort> query = sortDao.queryBuilder().orderAsc(SortDao.Properties.Id).build();
                    List<Sort> sorts = query.list();
                    for(Sort sort:sorts){
                        SortBean bean  = new SortBean();
                        bean.setId(sort.getId());
                        bean.setName(sort.getName());
                        sortBeanList.add(bean);
                    }
                    bizResult.setSucceed(true);
                    bizResult.setData(sortBeanList);
                } catch (Exception e) {
                    Log.e(TAG, "getSorts() e = " + e.getMessage());
                }
                return bizResult;
            }
        }.execute();
    }

    public void getScenes(long sortId, BizCallback callback) {
        new DBTaskRx(callback) {
            @Override
            protected BizResult doInBackground() {
                BizResult bizResult = new BizResult(BusinessId.GET_ALL_SCENES_FROM_DB);
                bizResult.setSucceed(false);
                List <SceneBean> sceneBeanList = new ArrayList<>();
                try {
                    ScenesDao scenesDao = FunctionBoxApplication.getDaoSession().getScenesDao();
                    Query<Scenes> query = scenesDao.queryBuilder().where(ScenesDao.Properties.SortId.eq(sortId)).orderAsc(ScenesDao.Properties.Id).build();
                    List<Scenes> scenesList = query.list();
                    for(Scenes scenes:scenesList){
                        SceneBean bean  = new SceneBean();
                        bean.setId(scenes.getId());
                        bean.setName(scenes.getName());
                        sceneBeanList.add(bean);
                    }
                    bizResult.setSucceed(true);
                    bizResult.setData(sceneBeanList);
                } catch (Exception e) {
                    Log.e(TAG, "getScenes() e = " + e.getMessage());
                }
                return bizResult;
            }
        }.execute();
    }

    public void getFunctions(long sceneId, BizCallback callback) {
        new DBTaskRx(callback) {
            @Override
            protected BizResult doInBackground() {
                BizResult bizResult = new BizResult(BusinessId.GET_ALL_FUNCTION_FROM_DB);
                bizResult.setSucceed(false);
                List <FunctionBean> functionBeanList = new ArrayList<>();
                try {
                    FunctionDao functionDao = FunctionBoxApplication.getDaoSession().getFunctionDao();
                    Query<Function> query = functionDao.queryBuilder().where(FunctionDao.Properties.SceneId.eq(sceneId)).orderAsc(FunctionDao.Properties.Id).build();
                    List<Function> functionList = query.list();
                    for(Function function:functionList){
                        FunctionBean bean  = new FunctionBean();
                        bean.setId(function.getId());
                        bean.setName(function.getName());
                        bean.setDetail(function.getDetail());
                        functionBeanList.add(bean);
                    }
                    bizResult.setSucceed(true);
                    bizResult.setData(functionBeanList);
                } catch (Exception e) {
                    Log.e(TAG, "getFuncitons() e = " + e.getMessage());
                }
                return bizResult;
            }
        }.execute();
    }
}
