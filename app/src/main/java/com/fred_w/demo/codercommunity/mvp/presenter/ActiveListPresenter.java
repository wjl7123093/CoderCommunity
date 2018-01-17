package com.fred_w.demo.codercommunity.mvp.presenter;

import android.app.Application;

import com.fred_w.demo.codercommunity.mvp.model.entity.ActiveList;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

import javax.inject.Inject;

import com.fred_w.demo.codercommunity.mvp.contract.ActiveListContract;
import com.jess.arms.utils.RxLifecycleUtils;

/**
 * ActiveListPresenter 动态列表页面 P 层(MVP)
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2018-1-16
 * @update 2017-1-17 新增 数据加载错误提示
 */
@ActivityScope
public class ActiveListPresenter extends BasePresenter<ActiveListContract.Model, ActiveListContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public ActiveListPresenter(ActiveListContract.Model model, ActiveListContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    /**
     * 执行 获取动态列表 方法
     * @param access_token
     * @param catalog
     * @param user
     * @param pageIndex
     * @param pageSize
     */
    public void callMethodOfGetActiveList(String access_token, int catalog, int user,
                                          int pageIndex, int pageSize) {
        mModel.getActiveList(access_token, "json", catalog, user, pageIndex, pageSize)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {
                    mRootView.hideLoading();
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView)) //使用Rxlifecycle,使Disposable和Activity一起销毁
                .subscribe(new ErrorHandleSubscriber<ActiveList>(mErrorHandler) {
                    @Override
                    public void onError(@NonNull Throwable e) {
                        mRootView.showMessage("数据加载错误：" + e.getMessage());
                        mRootView.finishRefreshing();
                    }

                    @Override
                    public void onNext(@NonNull ActiveList activeList) {
                        mRootView.showActiveList(activeList.getActivelist());
                    }
                });
    }

}
