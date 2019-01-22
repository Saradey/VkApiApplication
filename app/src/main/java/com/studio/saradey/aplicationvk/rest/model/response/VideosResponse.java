package com.studio.saradey.aplicationvk.rest.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.studio.saradey.aplicationvk.model.attachment.video.Video;

import java.util.List;

/**
 * @author jtgn on 14.08.2018
 */

public class VideosResponse {
    public int count;
    @SerializedName("items")
    @Expose
    public List<Video> items;
}
