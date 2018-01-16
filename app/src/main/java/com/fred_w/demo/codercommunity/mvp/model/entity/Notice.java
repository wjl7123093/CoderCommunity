package com.fred_w.demo.codercommunity.mvp.model.entity;

import java.io.Serializable;

/**
 * 通知信息 Entity
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2018-1-15
 * @update
 */
public class Notice implements Serializable {

    int replyCount;     // 未读评论数
    int msgCount;       // 未读私信数
    int fansCount;      // 新增粉丝数
    int referCount;     // 未读@我数

    @Override
    public String toString() {
        return "Notice{" +
                "replyCount=" + replyCount +
                ", msgCount=" + msgCount +
                ", fansCount=" + fansCount +
                ", referCount=" + referCount +
                '}';
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getReferCount() {
        return referCount;
    }

    public void setReferCount(int referCount) {
        this.referCount = referCount;
    }
}
