package com.studio.saradey.aplicationvk.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * @author jtgn on 14.08.2018
 */

public class Contact extends RealmObject {
    public static final String USER_ID = "user_id";
    public static final String DESC = "desc";
    public static final String PHOTO = "photo_100";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";

    @SerializedName(USER_ID)
    public int userId;

    @SerializedName(DESC)
    public String desc;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
