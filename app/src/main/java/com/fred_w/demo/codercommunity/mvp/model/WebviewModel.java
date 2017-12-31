package com.fred_w.demo.codercommunity.mvp.model;

import android.app.Application;

import com.fred_w.demo.codercommunity.mvp.model.api.service.WebviewService;
import com.fred_w.demo.codercommunity.mvp.model.entity.AccessToken;
import com.fred_w.demo.codercommunity.mvp.model.entity.BaseJson;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.fred_w.demo.codercommunity.mvp.contract.WebviewContract;

import io.reactivex.Observable;


@ActivityScope
public class WebviewModel extends BaseModel implements WebviewContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public WebviewModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<AccessToken> getAccessToken(String client_id, String client_secret, String grant_type, String redirect_uri, String code, String dataType) {
        return mRepositoryManager.obtainRetrofitService(WebviewService.class).getAccessToken(
                client_id, client_secret, grant_type, redirect_uri, code, dataType);
    }
}