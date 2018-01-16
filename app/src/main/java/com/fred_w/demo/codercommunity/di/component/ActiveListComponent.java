package com.fred_w.demo.codercommunity.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.fred_w.demo.codercommunity.di.module.ActiveListModule;

import com.fred_w.demo.codercommunity.mvp.ui.activity.ActiveListActivity;

@ActivityScope
@Component(modules = ActiveListModule.class, dependencies = AppComponent.class)
public interface ActiveListComponent {
    void inject(ActiveListActivity activity);
}