package com.fred_w.demo.codercommunity.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.fred_w.demo.codercommunity.mvp.contract.WebviewContract;
import com.fred_w.demo.codercommunity.mvp.model.WebviewModel;


@Module
public class WebviewModule {
    private WebviewContract.View view;

    /**
     * 构建WebviewModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public WebviewModule(WebviewContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    WebviewContract.View provideWebviewView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    WebviewContract.Model provideWebviewModel(WebviewModel model) {
        return model;
    }
}