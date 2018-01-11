package com.fred_w.demo.codercommunity.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.fred_w.demo.codercommunity.mvp.contract.MyInfoContract;
import com.fred_w.demo.codercommunity.mvp.model.MyInfoModel;


@Module
public class MyInfoModule {
    private MyInfoContract.View view;

    /**
     * 构建MyInfoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MyInfoModule(MyInfoContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MyInfoContract.View provideMyInfoView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MyInfoContract.Model provideMyInfoModel(MyInfoModel model) {
        return model;
    }
}