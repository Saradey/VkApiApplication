package com.studio.saradey.aplicationvk.rest.api;

import com.studio.saradey.aplicationvk.rest.model.response.Full;
import com.studio.saradey.aplicationvk.rest.model.response.VideosResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author jtgn on 14.08.2018
 */


public interface VideoApi {
    @GET(ApiMethods.VIDEO_GET)
    Observable<Full<VideosResponse>> get(@QueryMap Map<String, String> map);
}
