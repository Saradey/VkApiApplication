package com.studio.saradey.aplicationvk.rest.model.request;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.annotations.SerializedName;
import com.studio.saradey.aplicationvk.consts.ApiConstants;

import org.json.JSONObject;

import java.util.Map;

/**
 * @author jtgn on 15.08.2018
 */

//модель запроса регистрации устройства на сервере ВК как получателя push-сообщени
public class AccountRegisterDeviceRequest extends BaseRequestModel {
    @SerializedName(ApiConstants.TOKEN)
    String token;

    @SerializedName(ApiConstants.SYSTEM_VERSION)
    int systemVersion = 22;

    @SerializedName(ApiConstants.DEVICE_MODEL)
    String deviceModel = "android";

    @SerializedName(ApiConstants.DEVICE_ID)
    String deviceId;

    @SerializedName(ApiConstants.SETTINGS)
    JSONObject settings = ApiConstants.getDefaultPushSettings();

    public AccountRegisterDeviceRequest(String deviceId) {
        this.deviceId = deviceId;
        this.token = FirebaseInstanceId.getInstance().getToken();
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(int systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public JSONObject getSettings() {
        return settings;
    }

    public void setSettings(JSONObject settings) {
        this.settings = settings;
    }

    @Override
    public void OnMapCreate(Map<String, String> map) {
        if (getToken() != null) {
            map.put(ApiConstants.TOKEN, getToken());
        }
        map.put(ApiConstants.SYSTEM_VERSION, String.valueOf(getSystemVersion()));
        map.put(ApiConstants.DEVICE_MODEL, getDeviceModel());
        map.put(ApiConstants.DEVICE_ID, getDeviceId());
        map.put(ApiConstants.SETTINGS, getSettings().toString());
    }
}
