package com.fred_w.demo.codercommunity.mvp.model.entity;

import com.fred_w.demo.codercommunity.mvp.model.api.Api;

import java.io.Serializable;


/**
 * 如果你服务器返回的数据固定为这种方式(字段名可根据服务器更改)
 * 替换范型即可重用BaseJson
 * Created by jess on 26/09/2016 15:19
 * Contact with jess.yan.effort@gmail.com
 */

public class BaseJson<T> implements Serializable{
    private T data;
    private int code;
    private String info;
    private String url;
    private int wait;

    public T getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public String getUrl() {
        return url;
    }

    public int getWait() {
        return wait;
    }

    /**
     * 请求是否成功
     * @return
     */
    public boolean isSuccess() {
        if (code == Api.RequestSuccess) {
            return true;
        } else {
            return false;
        }
    }
}
