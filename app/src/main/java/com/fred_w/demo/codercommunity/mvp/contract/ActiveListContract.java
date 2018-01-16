package com.fred_w.demo.codercommunity.mvp.contract;

import com.fred_w.demo.codercommunity.mvp.model.entity.Active;
import com.fred_w.demo.codercommunity.mvp.model.entity.ActiveList;
import com.fred_w.demo.codercommunity.mvp.model.entity.LoginUser;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.List;

import io.reactivex.Observable;


public interface ActiveListContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void showActiveList(List<Active> activeList);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
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
        Observable<ActiveList> getActiveList(String access_token, String dataType,
                                             int catalog, int user, int pageIndex, int pageSize);
    }
}
