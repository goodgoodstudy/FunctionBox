package com.yu.functionbox.main;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.yu.functionbox.base.BaseBizResult;
import com.yu.functionbox.base.BizCallback;
import com.yu.functionbox.base.BizResult;
import com.yu.functionbox.data.FunctionBean;
import com.yu.functionbox.data.SceneBean;
import com.yu.functionbox.data.SortBean;
import com.yu.functionbox.db.DbService;
import com.yu.functionbox.event.EventMessage;
import com.yu.functionbox.event.MyEvent;
import com.yu.functionbox.utils.EventBusUtils;
import com.yu.functionbox.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by yuw on 2017-12-29.
 * description
 */

public class MainViewModel {
    private final static String TAG = "MainViewModel";
    public ObservableList<SortBean> mSortBeans = new ObservableArrayList<>();
    public ObservableList<SceneBean> mSceneBeans = new ObservableArrayList<>();
    public ObservableList<FunctionBean> mFunctionBeans = new ObservableArrayList<>();
    private long mSortId;
    private long mSceneId;

    public MainViewModel() {
        EventBusUtils.register(this);
    }

    public void initData() {
        DbService.get().getSorts(new BizCallback<ArrayList<SortBean>>() {
            @Override
            public void onSucceed(ArrayList<SortBean> response, @NonNull BizResult bizResult) {
                mSortBeans.clear();
                mSortBeans.addAll(response);
                if (response.size() > 0) {
                    EventBus.getDefault().post(new EventMessage<>(MyEvent.EVENT_SELECT_FIRST_SORT));
                    long id = response.get(0).getId();
                    mSortId = id;
                    getScenesBySortId(id);
                }
            }

            @Override
            public void onFailed(String errorMsg, @NonNull BizResult bizResult) {
                Log.i(TAG, "getSorts onFailed: ");
            }

        });

    }

    private void getScenesBySortId(long id) {
        DbService.get().getScenes(id, new BizCallback<ArrayList<SceneBean>>() {
            @Override
            public void onSucceed(ArrayList<SceneBean> response, @NonNull BizResult bizResult) {
                mSceneBeans.clear();
                mSceneBeans.addAll(response);
                if (response.size() > 0) {
                    EventBus.getDefault().post(new EventMessage<>(MyEvent.EVENT_SELECT_FIRST_SCENE));
                    mSceneId = response.get(0).getId();
                    getFunctionBySceneId(mSceneId);
                } else {
                    mFunctionBeans.clear();
                }

            }

            @Override
            public void onFailed(String errorMsg, @NonNull BizResult bizResult) {
                Log.i(TAG, "getScenesBySortId onFailed: ");
            }

        });
    }

    private void getFunctionBySceneId(long sceneId) {
        DbService.get().getFunctions(sceneId, new BizCallback<ArrayList<FunctionBean>>() {
            @Override
            public void onSucceed(ArrayList<FunctionBean> response, @NonNull BizResult bizResult) {
                mFunctionBeans.clear();
                mFunctionBeans.addAll(response);
            }

            @Override
            public void onFailed(String errorMsg, @NonNull BizResult bizResult) {
                Log.i(TAG, "getFunctionBySceneId onFailed: ");
            }

        });
    }


    @Subscribe
    public void onEventUpdate(EventMessage event) {
        switch (event.mCode) {
            case MyEvent.EVENT_INSERT_SORT:
                String title = (String) event.mObj;
                if (!TextUtils.isEmpty(title)) {
                    DbService.get().insertSort(title, new BizCallback() {
                        @Override
                        public void onSucceed(Object response, @NonNull BaseBizResult bizResult) {
                            ToastUtils.show("添加成功!");
                            updateSort();
                        }

                        @Override
                        public void onFailed(String errorMsg, @NonNull BaseBizResult bizResult) {

                        }
                    });
                }
                break;
            case MyEvent.EVENT_INSERT_SCENE:
                String title2 = (String) event.mObj;
                if (!TextUtils.isEmpty(title2)) {
                    DbService.get().insertScenes(mSortId, title2, new BizCallback() {
                        @Override
                        public void onSucceed(Object response, @NonNull BaseBizResult bizResult) {
                            ToastUtils.show("添加成功!");
                            updateScene();
                        }

                        @Override
                        public void onFailed(String errorMsg, @NonNull BaseBizResult bizResult) {

                        }
                    });
                }
                break;
            case MyEvent.EVENT_INSERT_FUNCTION:
                String detail = (String) event.mObj;
                if (!TextUtils.isEmpty(detail)) {
                    DbService.get().insertFunction(mSceneId, "test", detail, new BizCallback() {
                        @Override
                        public void onSucceed(Object response, @NonNull BaseBizResult bizResult) {
                            ToastUtils.show("添加成功!");
                            updateFunction();
                        }

                        @Override
                        public void onFailed(String errorMsg, @NonNull BaseBizResult bizResult) {

                        }
                    });
                }
                break;
            case MyEvent.EVENT_CLICK_SORT:
                mSortId = (long) event.mObj;
                getScenesBySortId(mSortId);
                break;
            case MyEvent.EVENT_CLICK_SCENE:
                mSceneId = (long) event.mObj;
                getFunctionBySceneId(mSceneId);
                break;
            case MyEvent.EVENT_SAVE_FUNCTION:
                saveFunction((FunctionBean) event.mObj);
                break;
        }
    }

    private void saveFunction(FunctionBean bean) {
        DbService.get().updateFunction(bean.getId(), bean.getDetail(), new BizCallback() {
            @Override
            public void onSucceed(Object response, @NonNull BaseBizResult bizResult) {
                ToastUtils.show("保存成功");
            }

            @Override
            public void onFailed(String errorMsg, @NonNull BaseBizResult bizResult) {
                ToastUtils.show("保存失败");
            }
        });
    }


    private void updateSort() {
        DbService.get().getSorts(new BizCallback<ArrayList<SortBean>>() {
            @Override
            public void onSucceed(ArrayList<SortBean> response, @NonNull BizResult bizResult) {
                mSortBeans.clear();
                mSortBeans.addAll(response);
                if (response.size() > 0) {
                    EventBus.getDefault().post(new EventMessage<>(MyEvent.EVENT_SELECT_LAST_SORT, response.size()));
                    long id = response.get(response.size()).getId();
                    mSortId = id;
                    getScenesBySortId(id);
                }
            }

            @Override
            public void onFailed(String errorMsg, @NonNull BizResult bizResult) {
                Log.i(TAG, "getSorts onFailed: ");
            }

        });
    }

    private void updateScene() {
        DbService.get().getScenes(mSortId, new BizCallback<ArrayList<SceneBean>>() {
            @Override
            public void onSucceed(ArrayList<SceneBean> response, @NonNull BizResult bizResult) {
                mSceneBeans.clear();
                mSceneBeans.addAll(response);
                EventBus.getDefault().post(new EventMessage<>(MyEvent.EVENT_SELECT_LAST_SCENE, response.size()));
                if (response.size() > 0) {
                    mSceneId = response.get(response.size() - 1).getId();
                    getFunctionBySceneId(mSceneId);
                }
            }

            @Override
            public void onFailed(String errorMsg, @NonNull BizResult bizResult) {
                Log.i(TAG, "getScenesBySortId onFailed: ");
            }

        });
    }

    public void updateFunction() {
        DbService.get().getFunctions(mSceneId, new BizCallback<ArrayList<FunctionBean>>() {
            @Override
            public void onSucceed(ArrayList<FunctionBean> response, @NonNull BizResult bizResult) {
                mFunctionBeans.clear();
                mFunctionBeans.addAll(response);
            }

            @Override
            public void onFailed(String errorMsg, @NonNull BizResult bizResult) {
                Log.i(TAG, "getFunctionBySceneId onFailed: ");
            }

        });
    }

    public void destroy() {
        EventBusUtils.unRegister(this);
    }
}
