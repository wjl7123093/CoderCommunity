package com.fred_w.demo.codercommunity.mvp.model.api.service;

import com.fred_w.demo.codercommunity.mvp.model.entity.LoginUser;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 个人中心接口
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2017-1-2
 * @update
 */
public interface FourService {

    /**
     * 获取当前登录用户的账户信息
     * @param access_token oauth2_token获取的access_token
     * @param dataType 返回数据类型['json'|'jsonp'|'xml']
     * @return
     */
    @FormUrlEncoded
    @POST("/action/openapi/user")
    Observable<LoginUser> getLoginUser(@Field("access_token") String access_token,
                                       @Field("dataType") String dataType);

}
