package com.studio.saradey.aplicationvk.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.studio.saradey.aplicationvk.consts.ApiConstants;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

/**
 * @author jtgn on 13.08.2018
 */


//Это модель, которая вместе с UsersApi.get(…) будет использоваться
// для получения данных пользователя из сети.
public class UsersGetRequestModel extends BaseRequestModel {

    @SerializedName(VKApiConst.USER_IDS)
    String userId;

    @SerializedName(VKApiConst.FIELDS)
    String fields = ApiConstants.DEFAULT_USER_FIELDS;


    public UsersGetRequestModel(String userId) {
        this.userId = userId;
    }


    public String getUserIds() {
        return userId;
    }

    public void setUserIds(String userIds) {
        this.userId = userIds;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }


    @Override
    public void OnMapCreate(Map<String, String> map) {
        map.put(VKApiConst.USER_ID, getUserIds());
        map.put(VKApiConst.FIELDS, getFields());
    }


}