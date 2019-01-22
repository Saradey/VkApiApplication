package com.studio.saradey.aplicationvk.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.studio.saradey.aplicationvk.CurrentUser;
import com.studio.saradey.aplicationvk.consts.ApiConstants;
import com.vk.sdk.api.VKApiConst;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jtgn on 31.07.2018
 */

//класс для запроса
public abstract class BaseRequestModel {

    //что бы ретрофит понимал название полей
    @SerializedName(VKApiConst.VERSION)
    Double version = ApiConstants.DEFAULT_VERSION;


    @SerializedName(VKApiConst.ACCESS_TOKEN)
    String accessToken = CurrentUser.getAccessToken();


    public Double getVersion() {
        return version;
    }

    public String getAccessToken() {
        return accessToken;
    }


    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();

        map.put(VKApiConst.VERSION, String.valueOf(getVersion()));
        if (accessToken != null) {
            map.put(VKApiConst.ACCESS_TOKEN, getAccessToken());
        }

        OnMapCreate(map);

        return map;
    }

    public abstract void OnMapCreate(Map<String, String> map);

}
