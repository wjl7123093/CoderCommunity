package com.fred_w.demo.codercommunity.mvp.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.fred_w.demo.codercommunity.R;
import com.fred_w.demo.codercommunity.app.ARoutePath;
import com.fred_w.demo.codercommunity.app.base.CCApplication;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

/**
 * 欢迎界面
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2017-12-31
 * @update
 */
@Route(path = ARoutePath.PATH_START)
public class StartActivity extends BaseActivity {

    private CCApplication mApplication;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_start;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (null == mApplication)
            mApplication = (CCApplication) getApplication();

        new Handler().postDelayed(() -> {
            if (TextUtils.isEmpty(mApplication.getToken_access())) {
                ARouter.getInstance().build(ARoutePath.PATH_WEBVIEW).navigation();
                StartActivity.this.finish();
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("access_token", mApplication.getToken_access());
                ARouter.getInstance().build(ARoutePath.PATH_MAIN).with(bundle).navigation();
                StartActivity.this.finish();
            }
        }, 2000);
    }
}
