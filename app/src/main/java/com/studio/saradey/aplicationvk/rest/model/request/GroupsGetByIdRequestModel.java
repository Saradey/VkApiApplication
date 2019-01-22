package com.studio.saradey.aplicationvk.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.studio.saradey.aplicationvk.consts.ApiConstants;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

/**
 * @author jtgn on 14.08.2018
 */


public class GroupsGetByIdRequestModel extends BaseRequestModel {
    @SerializedName(VKApiConst.GROUP_ID)
    int groupId;

    @SerializedName(VKApiConst.FIELDS)
    String fields = ApiConstants.DEFAULT_GROUP_FIELDS;


    public GroupsGetByIdRequestModel(int groupId) {
        this.groupId = Math.abs(groupId);
    }


    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = Math.abs(groupId);
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }


    @Override
    public void OnMapCreate(Map<String, String> map) {
        map.put(VKApiConst.GROUP_ID, String.valueOf(getGroupId()));
        map.put(VKApiConst.FIELDS, String.valueOf(getFields()));
    }
}
