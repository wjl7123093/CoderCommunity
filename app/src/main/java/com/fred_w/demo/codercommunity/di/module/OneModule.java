package com.fred_w.demo.codercommunity.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.fred_w.demo.codercommunity.mvp.contract.OneContract;
import com.fred_w.demo.codercommunity.mvp.model.OneModel;


@Module
public class OneModule {
    private OneContract.View view;

    /**
     * 构建OneModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public OneModule(OneContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    OneContract.View provideOneView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    OneContract.Model provideOneModel(OneModel model) {
        return model;
    }
}