package com.studio.saradey.aplicationvk.fcm;

import android.os.Bundle;
import android.util.Log;

import com.studio.saradey.aplicationvk.model.Place;

import java.util.Map;

/**
 * @author jtgn on 15.08.2018
 */


//класс уведомления о новой записи
public class NewPostFcmMessage extends FcmMessage {
    public static final String POST_ID = "post_id";

    public static final String TEXT = "text";

    private String postId;


    public NewPostFcmMessage(Map<String, String> source) {
        this.type = source.get(TYPE);
        this.postId = source.get(POST_ID);
        this.fromId = source.get(FROM_ID);
        this.text = source.get(TEXT);

        Log.d("newpostfcmmessage", "source from id: " + source.get(FROM_ID));
        Log.d("newpostfcmmessage", "from id: " + fromId);


    }


    public NewPostFcmMessage(Bundle source) {
        this.type = source.getString(TYPE);
        this.postId = source.getString(POST_ID);
        this.fromId = source.getString(FROM_ID);
        this.text = source.getString(TEXT);
    }


    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public PushModel toPushModel() {
        return new PushModel(this.type, null, this.text, 0, getPlace());
    }

    public Place getPlace() {
        return new Place(this.fromId, this.postId);
    }


}
