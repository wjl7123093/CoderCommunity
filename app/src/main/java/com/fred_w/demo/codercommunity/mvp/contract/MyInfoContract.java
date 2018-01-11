package com.fred_w.demo.codercommunity.mvp.contract;

import com.fred_w.demo.codercommunity.mvp.model.entity.MyInfo;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import io.reactivex.Observable;


public interface MyInfoContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void showMyInfo(MyInfo myInfo);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        /**
         * 获取个人主页详情
         * @param access_token oauth2_token获取的access_token
         * @param dataType 返回数据类型['json'|'jsonp'|'xml']
         * @return
         */
        Observable<MyInfo> getMyInformation(String access_token, String dataType);
    }
}
