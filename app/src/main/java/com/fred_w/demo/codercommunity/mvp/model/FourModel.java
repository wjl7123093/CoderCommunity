package com.fred_w.demo.codercommunity.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.fred_w.demo.codercommunity.mvp.contract.FourContract;


@ActivityScope
public class FourModel extends BaseModel implements FourContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public FourModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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

}