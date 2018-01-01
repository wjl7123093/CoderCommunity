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
 * @update
 */
public class LvMineFunctionBean implements Parcelable {

    private String name;
    private String packageName;
    private String activityName;
    private String displayName;
    private String icon;

    public LvMineFunctionBean() {

    }

    protected LvMineFunctionBean(Parcel in) {
        name = in.readString();
        packageName = in.readString();
        activityName = in.readString();
        displayName = in.readString();
        icon = in.readString();
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

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(packageName);
        dest.writeString(activityName);
        dest.writeString(displayName);
        dest.writeString(icon);
    }
}
