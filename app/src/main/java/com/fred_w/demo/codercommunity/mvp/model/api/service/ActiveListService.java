package com.fred_w.demo.codercommunity.mvp.model.api.service;

import com.fred_w.demo.codercommunity.mvp.model.entity.ActiveList;
import com.fred_w.demo.codercommunity.mvp.model.entity.LoginUser;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 动态列表接口
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2018-1-15
 * @update
 */
public interface ActiveListService {

    /**
     * 获取动态列表
     * @param access_token oauth2_token获取的access_token
     * @param dataType 返回数据类型['json'|'jsonp'|'xml']
     * @param catalog 类别ID [ 0、1所有动态,2提到我的,3评论,4我自己 ] 默认0
     * @param user 用户ID
     * @param pageIndex 页数
     * @param pageSize 每页条数
     * @return
     */
    @FormUrlEncoded
    @POST("/action/openapi/active_list")
    Observable<ActiveList> getActiveList(@Field("access_token") String access_token,
                                         @Field("dataType") String dataType,
                                         @Field("catalog") int catalog,
                                         @Field("user") int user,
                                         @Field("pageIndex") int pageIndex,
                                         @Field("pageSize") int pageSize);

}
