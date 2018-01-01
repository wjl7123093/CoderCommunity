package com.fred_w.demo.codercommunity.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.fred_w.demo.codercommunity.di.module.FourModule;

import com.fred_w.demo.codercommunity.mvp.ui.fragment.FourFragment;

@ActivityScope
@Component(modules = FourModule.class, dependencies = AppComponent.class)
public interface FourComponent {
    void inject(FourFragment fragment);
}