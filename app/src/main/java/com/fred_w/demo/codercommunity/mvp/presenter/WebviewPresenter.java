package com.fred_w.demo.codercommunity.mvp.presenter;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.fred_w.demo.codercommunity.app.ARoutePath;
import com.fred_w.demo.codercommunity.app.SharepreferenceKey;
import com.fred_w.demo.codercommunity.app.base.CCApplication;
import com.fred_w.demo.codercommunity.mvp.model.api.Api;
import com.fred_w.demo.codercommunity.mvp.model.entity.AccessToken;
import com.fred_w.demo.codercommunity.mvp.model.entity.BaseJson;
import com.fred_w.demo.codercommunity.mvp.ui.activity.MainActivity;
import com.jess.arms.base.App;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

import javax.inject.Inject;

import com.fred_w.demo.codercommunity.mvp.contract.WebviewContract;
import com.jess.arms.utils.RxLifecycleUtils;


@ActivityScope
public class WebviewPresenter extends BasePresenter<WebviewContract.Model, WebviewContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    private String accessToken;

    @Inject
    public WebviewPresenter(WebviewContract.Model model, WebviewContract.View rootView
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
     * 执行 获取 AccessToken 方法
     * @param code
     */
    public void callMethodOfGetAccessToken(String code) {
        // RxJava 链式事物流执行 [登录] 操作
        mModel.getAccessToken(Api.APP_ID, Api.APP_SECRET, "authorization_code", Api.API_REDIRECT_URI, code, "json")
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))    // 重试机制，第一个参数重试次数，第二个重试间隔
                .doOnSubscribe(disposable -> {
                    // Action onSubscriber
                    mRootView.showLoading();
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {
                    // Action onFinally
                    mRootView.hideLoading();
//                    mRootView.launchActivity(new Intent(mApplication.getApplicationContext(), MainActivity.class));
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView)) //使用Rxlifecycle,使Disposable和Activity一起销毁
                .subscribe(new ErrorHandleSubscriber<AccessToken>(mErrorHandler) {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.onSubscribe(d);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
//                        mRootView.launchActivity(new Intent(mApplication.getApplicationContext(), MainActivity.class));
                        Bundle bundle = new Bundle();
                        bundle.putString(SharepreferenceKey.KEY_ACCESS_TOKEN, accessToken);
                        mRootView.lanuchActivityByARoute(ARoutePath.PATH_MAIN, bundle);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                        mRootView.showMessage("登陆失败");
                    }

                    @Override
                    public void onNext(@NonNull AccessToken userBaseJson) {
                        if (userBaseJson.getCode() == 0) {
                            accessToken = userBaseJson.getAccess_token();
                            mRootView.showMessage("登陆成功");
                        } else {
                            mRootView.showMessage("登陆失败");
                        }
                    }
                });
    }

}
