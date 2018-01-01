package com.fred_w.demo.codercommunity.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.fred_w.demo.codercommunity.mvp.contract.FourContract;
import com.fred_w.demo.codercommunity.mvp.model.FourModel;


@Module
public class FourModule {
    private FourContract.View view;

    /**
     * 构建FourModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public FourModule(FourContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    FourContract.View provideFourView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    FourContract.Model provideFourModel(FourModel model) {
        return model;
    }
}