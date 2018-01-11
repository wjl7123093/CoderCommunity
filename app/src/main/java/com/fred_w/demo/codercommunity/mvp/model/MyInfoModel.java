package com.fred_w.demo.codercommunity.mvp.model;

import android.app.Application;

import com.fred_w.demo.codercommunity.mvp.model.api.service.MyInfoService;
import com.fred_w.demo.codercommunity.mvp.model.entity.MyInfo;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.fred_w.demo.codercommunity.mvp.contract.MyInfoContract;

import io.reactivex.Observable;


@ActivityScope
public class MyInfoModel extends BaseModel implements MyInfoContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public MyInfoModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<MyInfo> getMyInformation(String access_token, String dataType) {
        return mRepositoryManager.obtainRetrofitService(MyInfoService.class)
                .getMyInformation(access_token, dataType);
    }
}