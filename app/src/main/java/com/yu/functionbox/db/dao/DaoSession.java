package com.yu.functionbox.db.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.yu.functionbox.db.entity.Function;
import com.yu.functionbox.db.entity.Scenes;
import com.yu.functionbox.db.entity.Sort;

import com.yu.functionbox.db.dao.FunctionDao;
import com.yu.functionbox.db.dao.ScenesDao;
import com.yu.functionbox.db.dao.SortDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig functionDaoConfig;
    private final DaoConfig scenesDaoConfig;
    private final DaoConfig sortDaoConfig;

    private final FunctionDao functionDao;
    private final ScenesDao scenesDao;
    private final SortDao sortDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        functionDaoConfig = daoConfigMap.get(FunctionDao.class).clone();
        functionDaoConfig.initIdentityScope(type);

        scenesDaoConfig = daoConfigMap.get(ScenesDao.class).clone();
        scenesDaoConfig.initIdentityScope(type);

        sortDaoConfig = daoConfigMap.get(SortDao.class).clone();
        sortDaoConfig.initIdentityScope(type);

        functionDao = new FunctionDao(functionDaoConfig, this);
        scenesDao = new ScenesDao(scenesDaoConfig, this);
        sortDao = new SortDao(sortDaoConfig, this);

        registerDao(Function.class, functionDao);
        registerDao(Scenes.class, scenesDao);
        registerDao(Sort.class, sortDao);
    }
    
    public void clear() {
        functionDaoConfig.clearIdentityScope();
        scenesDaoConfig.clearIdentityScope();
        sortDaoConfig.clearIdentityScope();
    }

    public FunctionDao getFunctionDao() {
        return functionDao;
    }

    public ScenesDao getScenesDao() {
        return scenesDao;
    }

    public SortDao getSortDao() {
        return sortDao;
    }

}