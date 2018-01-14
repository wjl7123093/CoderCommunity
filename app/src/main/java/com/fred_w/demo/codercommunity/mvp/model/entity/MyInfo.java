package com.fred_w.demo.codercommunity.mvp.model.entity;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 登录用户个人信息 Entity
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2017-1-11
 * @update 2017-1-14
 */
public class MyInfo implements Serializable {

    private long uid;                   // 被查询用户id
    private String name;                // 用户名称
    private int gender;                 // 性别：1-男，2-女
    private String province;            // 省份
    private String city;                // 城市
    private String[] platforms;         // 开发平台
    private String[] expertise;         // 专长领域
    private String joinTime;            // 加入时间
    private String lastLoginTime;       // 最近登录时间
    private String portrait;            // 头像
    private String fansCount;           // 粉丝数
    private String favoriteCount;       // 收藏数
    private String followersCount;      // 关注数
    private Notice notice;              // 通知信息

    @Override
    public String toString() {
        return "MyInfo{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", platforms=" + Arrays.toString(platforms) +
                ", expertise=" + Arrays.toString(expertise) +
                ", joinTime='" + joinTime + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", portrait='" + portrait + '\'' +
                ", fansCount='" + fansCount + '\'' +
                ", favoriteCount='" + favoriteCount + '\'' +
                ", followersCount='" + followersCount + '\'' +
                ", notice=" + notice +
                '}';
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String[] getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String[] platforms) {
        this.platforms = platforms;
    }

    public String[] getExpertise() {
        return expertise;
    }

    public void setExpertise(String[] expertise) {
        this.expertise = expertise;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getFansCount() {
        return fansCount;
    }

    public void setFansCount(String fansCount) {
        this.fansCount = fansCount;
    }

    public String getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(String favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public String getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(String followersCount) {
        this.followersCount = followersCount;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    class Notice implements Serializable {
        int replyCount;     // 未读评论数
        int msgCount;       // 未读私信数
        int fansCount;      // 新增粉丝数
        int referCount;     // 未读@我数
    }

}
