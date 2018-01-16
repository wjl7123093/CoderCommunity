package com.fred_w.demo.codercommunity.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 个人中心功能列表 Entity
 *
 * @author Fred_W
 * @version v1.0.0
 *
 * @crdate 2018-1-1
 * @update 2018-1-16
 */
public class LvMineFunctionBean implements Parcelable {

    private String name;
    private String displayName;
    private String icon;
    private String path;    // ARoute Path

    public LvMineFunctionBean() {

    }

    protected LvMineFunctionBean(Parcel in) {
        name = in.readString();
        displayName = in.readString();
        icon = in.readString();
        path = in.readString();
    }

    public static final Creator<LvMineFunctionBean> CREATOR = new Creator<LvMineFunctionBean>() {
        @Override
        public LvMineFunctionBean createFromParcel(Parcel in) {
            return new LvMineFunctionBean(in);
        }

        @Override
        public LvMineFunctionBean[] newArray(int size) {
            return new LvMineFunctionBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(displayName);
        dest.writeString(icon);
        dest.writeString(path);
    }
}
