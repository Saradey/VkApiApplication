package com.studio.saradey.aplicationvk.rest.api;

import com.studio.saradey.aplicationvk.model.Profile;
import com.studio.saradey.aplicationvk.rest.model.response.Full;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author jtgn on 13.08.2018
 */

public interface UsersApi {

    @GET(ApiMethods.USERS_GET)
    Observable<Full<List<Profile>>> get(@QueryMap Map<String, String> map);

}
