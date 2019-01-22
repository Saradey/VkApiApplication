package com.studio.saradey.aplicationvk.rest.api;

import com.studio.saradey.aplicationvk.model.CommentItem;
import com.studio.saradey.aplicationvk.model.Topic;
import com.studio.saradey.aplicationvk.rest.model.response.BaseItemResponse;
import com.studio.saradey.aplicationvk.rest.model.response.Full;
import com.studio.saradey.aplicationvk.rest.model.response.ItemWithSendersResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author jtgn on 13.08.2018
 */

public interface BoardApi {
    @GET(ApiMethods.BOARD_GET_TOPICS)
    Observable<Full<BaseItemResponse<Topic>>> getTopics(@QueryMap Map<String, String> map);

    @GET(ApiMethods.BOARD_GET_COMMENTS)
    Observable<Full<ItemWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);
}
