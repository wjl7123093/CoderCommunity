package com.fred_w.demo.codercommunity.mvp.model.api.service;

import com.fred_w.demo.codercommunity.mvp.model.entity.AccessToken;
import com.fred_w.demo.codercommunity.mvp.model.entity.BaseJson;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 登录认证接口
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2017-12-31
 * @update
 */
public interface WebviewService {

    /**
     * 获取 AccessToken
     */
    @FormUrlEncoded
    @POST("/action/openapi/token")
    Observable<AccessToken> getAccessToken(@Field("client_id") String client_id,
                                                     @Field("client_secret") String client_secret,
                                                     @Field("grant_type") String grant_type,
                                                     @Field("redirect_uri") String redirect_uri,
                                                     @Field("code") String code,
                                                     @Field("dataType") String dataType);

}
