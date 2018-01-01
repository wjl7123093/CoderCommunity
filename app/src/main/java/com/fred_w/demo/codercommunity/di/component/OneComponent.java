package com.fred_w.demo.codercommunity.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.fred_w.demo.codercommunity.di.module.OneModule;

import com.fred_w.demo.codercommunity.mvp.ui.fragment.OneFragment;

@ActivityScope
@Component(modules = OneModule.class, dependencies = AppComponent.class)
public interface OneComponent {
    void inject(OneFragment fragment);
}