package com.studio.saradey.aplicationvk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.studio.saradey.aplicationvk.model.attachment.ApiAttachment;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author jtgn on 15.08.2018
 */

public class CommentItem extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("from_id")
    @Expose
    public int senderId;

    @SerializedName("place")
    @Expose
    public Place place;


    public String senderName;

    public String senderPhoto;

    @SerializedName("date")
    @Expose
    public int date;
    @SerializedName("text")
    @Expose
    public String text;

    @SerializedName("attachments")
    @Expose
    public RealmList<ApiAttachment> attachments = new RealmList<>();

    public String attachmentsString;

    @SerializedName("likes")
    @Expose
    public Likes likes;


    @SerializedName("reposts")
    @Expose
    public Reposts reposts;

    public boolean isFromTopic = false;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getFromId() {
        return senderId;
    }


    public Integer getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public String getDisplayText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public RealmList<ApiAttachment> getAttachments() {
        return attachments;
    }

    public Reposts getReposts() {
        return reposts;
    }

    public void setReposts(Reposts reposts) {
        this.reposts = reposts;
    }


    public String getDisplayAttachmentsString() {
        return attachmentsString;
    }


    public void setAttachmentsString(String attachmentsString) {
        this.attachmentsString = attachmentsString;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhoto() {
        return senderPhoto;
    }

    public void setSenderPhoto(String senderPhoto) {
        this.senderPhoto = senderPhoto;
    }


    public String getPhoto() {
        return senderPhoto;
    }


    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }


    public void setIsFromTopic(boolean isTopic) {
        this.isFromTopic = isTopic;
    }
}
