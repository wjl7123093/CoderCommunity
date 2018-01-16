package com.fred_w.demo.codercommunity.mvp.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 动态列表信息 Entity
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2018-1-15
 * @update
 */
public class ActiveList implements Serializable {

    private List<Active> activelist;
    private Notice notice;

    @Override
    public String toString() {
        return "ActiveList{" +
                "activelist=" + activelist +
                ", notice=" + notice +
                '}';
    }

    public List<Active> getActivelist() {
        return activelist;
    }

    public void setActivelist(List<Active> activelist) {
        this.activelist = activelist;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }
}
