package com.fred_w.demo.codercommunity.mvp.contract;

import android.os.Bundle;

import com.fred_w.demo.codercommunity.mvp.model.entity.AccessToken;
import com.fred_w.demo.codercommunity.mvp.model.entity.BaseJson;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import io.reactivex.Observable;


public interface WebviewContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void lanuchActivityByARoute(String path, Bundle bundle);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        /**
         * 获取 AccessToken
         * @param client_id OAuth2客户ID
         * @param client_secret OAuth2密钥
         * @param grant_type 授权方式：authorization_code或者refresh_token
         * @param redirect_uri 回调地址
         * @param code 调用 /action/oauth2/authorize 接口返回的授权码(grant_type为authorization_code时必选)
         * @param dataType 返回数据类型['json'|'jsonp'|'xml']
         * @return
         */
        Observable<AccessToken> getAccessToken(String client_id, String client_secret,
                                                         String grant_type, String redirect_uri,
                                                         String code, String dataType);
    }
}
