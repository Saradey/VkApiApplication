package com.studio.saradey.aplicationvk.model.attachment.doc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.studio.saradey.aplicationvk.model.attachment.video.Video;

import io.realm.RealmObject;

/**
 * @author jtgn on 14.08.2018
 */


public class Preview extends RealmObject {
    @SerializedName("photo")
    @Expose
    public PhotoPreview photo;
    @SerializedName("video")
    @Expose
    public Video video;


    public PhotoPreview getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoPreview photo) {
        this.photo = photo;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }


}
