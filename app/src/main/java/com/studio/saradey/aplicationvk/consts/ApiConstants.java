package com.studio.saradey.aplicationvk.consts;

import android.view.View;

import com.studio.saradey.aplicationvk.R;
import com.vk.sdk.VKScope;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author jtgn on 28.07.2018
 */


public class ApiConstants {

    public static final String[] DEFAULT_LOGIN_SCOPE = {VKScope.AUDIO, VKScope.DIRECT, VKScope.VIDEO,
            VKScope.WALL, VKScope.MESSAGES, VKScope.PHOTOS, VKScope.PAGES, VKScope.GROUPS, VKScope.EMAIL,
            VKScope.STATS, VKScope.DOCS}; //константы с разрешениями


    public static final Double DEFAULT_VERSION = 5.67; //дефолтная версия
    public static final int DEFAULT_COUNT = 10;         //получить 10 записей
    public static final String DEFAULT_USER_FIELDS = "photo_100";   //аватарка пользователя
    public static final String DEFAULT_MEMBER_FIELDS = "name,photo_100";
    public static final int MY_GROUP_ID = -38509395;
    public static final String DEFAULT_GROUP_FIELDS = "status,description,site,links,contacts";
    public static final String VIDEOS = "videos";
    public static final String POSTS = "posts";
    public static final String EXTENDED = "extended";
    public static final String OWNER_ID = "owner_id";
    public static final String POST_ID = "post_id";
    public static final String COUNT = "count";
    public static final String OFFSET = "offset";
    public static final String NEED_LIKES = "need_likes";
    public static final String GROUP_ID = "group_id";
    public static final String TOPIC_ID = "topic_id";

    public static final String TOKEN = "token";
    public static final String SYSTEM_VERSION = "system_version";
    public static final String DEVICE_MODEL = "device_model";
    public static final String DEVICE_ID = "device_id";
    public static final String SETTINGS = "settings";

    public static JSONObject getDefaultPushSettings() {
        try {
            return new JSONObject("{\"comment\":\"on\", \"reply\":\"on\", \"new_post\":\"on\"}");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
