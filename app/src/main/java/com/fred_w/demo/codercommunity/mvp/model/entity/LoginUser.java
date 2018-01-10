package com.fred_w.demo.codercommunity.mvp.model.entity;

import java.io.Serializable;

/**
 * 登录用户 Entity
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2017-1-2
 * @update
 */
public class LoginUser implements Serializable {

    private String id;
    private String email;
    private String name;
    private String gender;
    private String avatar;
    private String location;
    private String url;

    @Override
    public String toString() {
        return "LoginUser{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", avatar='" + avatar + '\'' +
                ", location='" + location + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
