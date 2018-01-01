package com.fred_w.demo.codercommunity.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Param;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fred_w.demo.codercommunity.app.ARoutePath;
import com.fred_w.demo.codercommunity.app.SharepreferenceKey;
import com.fred_w.demo.codercommunity.app.base.CCApplication;
import com.fred_w.demo.codercommunity.mvp.model.api.Api;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.fred_w.demo.codercommunity.di.component.DaggerMainComponent;
import com.fred_w.demo.codercommunity.di.module.MainModule;
import com.fred_w.demo.codercommunity.mvp.contract.MainContract;
import com.fred_w.demo.codercommunity.mvp.presenter.MainPresenter;

import com.fred_w.demo.codercommunity.R;
import com.jess.arms.utils.DataHelper;


import static android.R.attr.path;
import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 主界面
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2017-12-31
 * @update
 */
@Route(path = ARoutePath.PATH_MAIN)
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @Autowired
    String access_token;


    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (TextUtils.isEmpty(DataHelper.getStringSF(MainActivity.this, SharepreferenceKey.KEY_ACCESS_TOKEN)))
            DataHelper.setStringSF(MainActivity.this, SharepreferenceKey.KEY_ACCESS_TOKEN, access_token);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


}
