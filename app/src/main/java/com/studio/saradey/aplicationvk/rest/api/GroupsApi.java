package com.studio.saradey.aplicationvk.rest.api;

import com.studio.saradey.aplicationvk.model.Group;
import com.studio.saradey.aplicationvk.model.Member;
import com.studio.saradey.aplicationvk.rest.model.response.BaseItemResponse;
import com.studio.saradey.aplicationvk.rest.model.response.Full;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author jtgn on 13.08.2018
 */


public interface GroupsApi {

    @GET(ApiMethods.GROUPS_GET_MEMBERS)
    Observable<Full<BaseItemResponse<Member>>> getMembers(@QueryMap Map<String, String> map);


    // метод для получения группы по id
    @GET(ApiMethods.GROUPS_GET_BY_ID)
    Observable<Full<List<Group>>> getById(@QueryMap Map<String, String> map);
}


