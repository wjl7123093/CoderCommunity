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
 */
public class CCApplication extends BaseApplication {

    /**
     * SharePreferences 全局配置参数存储类
     */
    private SharedPreferences spRTApp;
    private SharedPreferences.Editor spEditor;
    private static String SP_NAME = "POO_Config";

    private static String KEY_TOKEN_ACCESS = "token_access";

    private String token_access;                   // token 用于验证

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

        spRTApp = getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        spEditor = spRTApp.edit();

        this.token_access = spRTApp.getString(KEY_TOKEN_ACCESS, "");

    }

    /** 清空 SharePreference 数据 */
    public void clearSharePreferenceData() {
        spEditor.clear();
        spEditor.commit();
    }

    public String getToken_access() {
        return token_access;
    }

    public void setToken_access(String token_access) {
        this.token_access = token_access;
        spEditor.putString(KEY_TOKEN_ACCESS, token_access);
        spEditor.commit();
    }
}
