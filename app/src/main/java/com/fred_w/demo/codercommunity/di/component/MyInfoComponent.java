package com.fred_w.demo.codercommunity.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.fred_w.demo.codercommunity.di.module.MyInfoModule;

import com.fred_w.demo.codercommunity.mvp.ui.activity.MyInfoActivity;

@ActivityScope
@Component(modules = MyInfoModule.class, dependencies = AppComponent.class)
public interface MyInfoComponent {
    void inject(MyInfoActivity activity);
}