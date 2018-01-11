package com.fred_w.demo.codercommunity.mvp.model.api.service;

import com.fred_w.demo.codercommunity.mvp.model.entity.MyInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 我的信息接口
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2017-1-11
 * @update
 */
public interface MyInfoService {

    /**
     * 获取个人主页详情
     * @param access_token oauth2_token获取的access_token
     * @param dataType 返回数据类型['json'|'jsonp'|'xml']
     * @return
     */
    @FormUrlEncoded
    @POST("/action/openapi/my_information")
    Observable<MyInfo> getMyInformation(@Field("access_token") String access_token,
                                        @Field("dataType") String dataType);

}
