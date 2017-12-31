package com.fred_w.demo.codercommunity.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.fred_w.demo.codercommunity.di.module.WebviewModule;

import com.fred_w.demo.codercommunity.mvp.ui.activity.WebviewActivity;

@ActivityScope
@Component(modules = WebviewModule.class, dependencies = AppComponent.class)
public interface WebviewComponent {
    void inject(WebviewActivity activity);
}