package com.fred_w.demo.codercommunity.app.base;

import android.content.Context;
import android.content.SharedPreferences;

import com.fred_w.demo.codercommunity.app.utils.CrashHandler;
import com.fred_w.demo.codercommunity.app.utils.sonic.SonicRuntimeImpl;
import com.jess.arms.base.BaseApplication;
import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;

/**
 * 全局 Application
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2017-12-31
 * @update 2018-1-1    删除了 SP
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

    }

}
