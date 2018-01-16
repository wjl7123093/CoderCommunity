package com.fred_w.demo.codercommunity.mvp.model;

import android.app.Application;

import com.fred_w.demo.codercommunity.mvp.model.api.service.ActiveListService;
import com.fred_w.demo.codercommunity.mvp.model.entity.ActiveList;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.fred_w.demo.codercommunity.mvp.contract.ActiveListContract;

import io.reactivex.Observable;


@ActivityScope
public class ActiveListModel extends BaseModel implements ActiveListContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public ActiveListModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<ActiveList> getActiveList(String access_token, String dataType, int catalog, int user, int pageIndex, int pageSize) {
        return mRepositoryManager.obtainRetrofitService(ActiveListService.class)
                .getActiveList(access_token, dataType, catalog, user, pageIndex, pageSize);
    }
}