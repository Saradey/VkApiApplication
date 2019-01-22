package com.studio.saradey.aplicationvk.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.studio.saradey.aplicationvk.consts.ApiConstants;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

/**
 * @author jtgn on 31.07.2018
 */


//класс для конкрентного запроса а точнее запроса не стену
public class WallGetRequestModel extends BaseRequestModel {

    @SerializedName(VKApiConst.OWNER_ID)
    int ownerId; //переменные для запроса

    @SerializedName(VKApiConst.COUNT)
    int count = ApiConstants.DEFAULT_COUNT;

    @SerializedName(VKApiConst.OFFSET)
    int offset;

    @SerializedName(VKApiConst.EXTENDED)
    int extended = 1; //всегда равна единице для возможности получения массивов из профалс и группс


    public WallGetRequestModel(int ownerid) {
        this.ownerId = ownerid;
    }

    public WallGetRequestModel(int ownerid, int count, int offset) {
        this.ownerId = ownerid;
        this.count = count;
        this.offset = offset;
    }

    //для создания модели запроса
    @Override
    public void OnMapCreate(Map<String, String> map) {
        map.put(VKApiConst.OWNER_ID, String.valueOf(getOwnerId()));
        map.put(VKApiConst.COUNT, String.valueOf(getCount()));
        map.put(VKApiConst.OFFSET, String.valueOf(getOffset()));
        map.put(VKApiConst.EXTENDED, String.valueOf(getExtended()));
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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

    public int getExtended() {
        return extended;
    }

    public void setExtended(int extended) {
        this.extended = extended;
    }
}
