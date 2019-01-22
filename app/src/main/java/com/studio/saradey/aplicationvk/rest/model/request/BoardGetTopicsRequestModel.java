package com.studio.saradey.aplicationvk.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.studio.saradey.aplicationvk.consts.ApiConstants;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

/**
 * @author jtgn on 13.08.2018
 */

public class BoardGetTopicsRequestModel extends BaseRequestModel {
    @SerializedName(VKApiConst.GROUP_ID)
    int groupId;

    @SerializedName(VKApiConst.COUNT)
    int count = ApiConstants.DEFAULT_COUNT;

    @SerializedName(VKApiConst.OFFSET)
    int offset = 0;

    public BoardGetTopicsRequestModel(int groupId) {
        this.groupId = Math.abs(groupId);
    }

    public BoardGetTopicsRequestModel(int groupId, int count, int offset) {
        this.groupId = Math.abs(groupId);
        this.count = count;
        this.offset = offset;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = Math.abs(groupId);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public void OnMapCreate(Map<String, String> map) {
        map.put(VKApiConst.GROUP_ID, String.valueOf(getGroupId()));
        map.put(VKApiConst.COUNT, String.valueOf(getCount()));
        map.put(VKApiConst.OFFSET, String.valueOf(getOffset()));
    }
}
