package com.studio.saradey.aplicationvk.rest.api;


import com.studio.saradey.aplicationvk.model.CommentItem;
import com.studio.saradey.aplicationvk.rest.model.response.Full;
import com.studio.saradey.aplicationvk.rest.model.response.GetWallByIdResponse;
import com.studio.saradey.aplicationvk.rest.model.response.GetWallResponse;
import com.studio.saradey.aplicationvk.rest.model.response.ItemWithSendersResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author jtgn on 30.07.2018
 */

//формат запроса
public interface WallApi {


    @GET(ApiMethods.WALL_GET)
    Observable<GetWallResponse> get(@QueryMap Map<String, String> map);


    @GET(ApiMethods.WALL_GET_BY_ID)
    Observable<GetWallByIdResponse> getById(@QueryMap Map<String, String> map);

    /*
    @GET(ApiMethods.WALL_GET)
    Call<GetWallResponse> get(@Query("owner_id") String ownerId, //индетефикатор пользователя или группы
                              @Query("access_token") String accessToken, //токен доступа
                              @Query("extended") Integer extended,   //для возвращения доп полей профайл и групп
                              @Query("v") String version);   //версия*/

    @GET(ApiMethods.WALL_GET_COMMENTS)
    Observable<Full<ItemWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);


}