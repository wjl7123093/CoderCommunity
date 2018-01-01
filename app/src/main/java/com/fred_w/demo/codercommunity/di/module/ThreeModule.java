package com.fred_w.demo.codercommunity.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.fred_w.demo.codercommunity.mvp.contract.ThreeContract;
import com.fred_w.demo.codercommunity.mvp.model.ThreeModel;


@Module
public class ThreeModule {
    private ThreeContract.View view;

    /**
     * 构建ThreeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ThreeModule(ThreeContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ThreeContract.View provideThreeView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ThreeContract.Model provideThreeModel(ThreeModel model) {
        return model;
    }
}