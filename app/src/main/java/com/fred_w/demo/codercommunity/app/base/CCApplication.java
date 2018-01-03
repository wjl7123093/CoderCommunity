package com.fred_w.demo.codercommunity.app.base;

import android.content.Context;
import android.content.SharedPreferences;

import com.fred_w.demo.codercommunity.app.utils.CrashHandler;
import com.fred_w.demo.codercommunity.app.utils.sonic.SonicRuntimeImpl;
import com.jess.arms.base.BaseApplication;
import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;

import org.xutils.x;

/**
 * 全局 Application
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2017-12-31
 * @update 2018-1-3    新增了 xutils3 的初始化
 */
public class CCApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        // init sonic engine if necessary, or maybe u can do this when application created
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(new SonicRuntimeImpl(this), new SonicConfig.Builder().build());
        }

        // 全局异常捕捉
        CrashHandler catchHandler = CrashHandler.getInstance();
        catchHandler.init(getApplicationContext());

        // 初始化 xutils3
        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能

    }

}
