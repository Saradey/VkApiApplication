package com.studio.saradey.aplicationvk.model.attachment.video;

import io.realm.RealmObject;

/**
 * @author jtgn on 14.08.2018
 */


public class File extends RealmObject {
    public String external;

    public String getExternal() {
        return external;
    }

    public void setExternal(String external) {
        this.external = external;
    }
}
