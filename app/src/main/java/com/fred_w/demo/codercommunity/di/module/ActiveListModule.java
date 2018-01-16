package com.fred_w.demo.codercommunity.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.fred_w.demo.codercommunity.mvp.contract.ActiveListContract;
import com.fred_w.demo.codercommunity.mvp.model.ActiveListModel;


@Module
public class ActiveListModule {
    private ActiveListContract.View view;

    /**
     * 构建ActiveListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ActiveListModule(ActiveListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ActiveListContract.View provideActiveListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ActiveListContract.Model provideActiveListModel(ActiveListModel model) {
        return model;
    }
}