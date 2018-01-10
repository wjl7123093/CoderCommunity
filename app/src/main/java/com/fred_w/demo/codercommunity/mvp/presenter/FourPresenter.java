package com.fred_w.demo.codercommunity.mvp.presenter;

import android.app.Activity;
import android.app.Application;

import com.fred_w.demo.codercommunity.mvp.model.entity.LoginUser;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

import javax.inject.Inject;

import com.fred_w.demo.codercommunity.mvp.contract.FourContract;
import com.jess.arms.utils.DataHelper;
import com.jess.arms.utils.PermissionUtil;
import com.jess.arms.utils.RxLifecycleUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;


@ActivityScope
public class FourPresenter extends BasePresenter<FourContract.Model, FourContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public FourPresenter(FourContract.Model model, FourContract.View rootView
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
     * 执行 获取当前登录用户的账户信息 方法
     * @param access_token
     * @param activity 为了实例化 RxPermission 传递的 activity对象
     */
    public void callMethodOfGetLoginUser(String access_token, Activity activity) {
        mModel.getLoginUser(access_token, "json")
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .doOnSubscribe(disposable -> {
                    // Action onSubscriber
                    mRootView.showLoading();
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {
                    // Action onFinally
                    mRootView.hideLoading();
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView)) //使用Rxlifecycle,使Disposable和Activity一起销毁
                .subscribe(new ErrorHandleSubscriber<LoginUser>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull LoginUser loginUser) {

                        // 请求存储权限
                        PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
                            @Override
                            public void onRequestPermissionSuccess() {
                                // 获取存储权限后再显示头像
                                mRootView.showHeaderImage(loginUser.getAvatar());//请求权限成功后做一些操作
                            }

                            @Override
                            public void onRequestPermissionFailure() {
                                mRootView.showMessage("存储权限请求失败，可能会影响图片显示效果");
                            }

                        }, new RxPermissions(activity), mErrorHandler);

                        mRootView.saveLoginUser(loginUser);
                        mRootView.showName(loginUser.getName());

                    }
                });

    }

    /**
     * 展示SP数据到 UI
     * @param loginUser 当前登录用户信息
     */
    public void showDataToUI(LoginUser loginUser) {
        mRootView.showHeaderImage(loginUser.getAvatar());
        mRootView.showName(loginUser.getName());
    }
}
