package com.fred_w.demo.codercommunity.mvp.model.api;

/**
 * 定义 Api 统一接口 URL
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2017-12-31
 * @update
 */
public interface Api {

    String APP_DOMAIN = "https://www.oschina.net";
    int RequestSuccess = 0;

    String AUTH_URL = "https://www.oschina.net/action/oauth2/authorize?response_type=code&client_id=%s&redirect_uri=%s";
    String APP_ID = "i9CDeNgWRJB71H4hVndG";
    String APP_SECRET = "57Gpy0bCaOEeXPhYq8zvvIVlE2zX2PZf";
    String API_REDIRECT_URI = "http://www.baidu.com/";
}
