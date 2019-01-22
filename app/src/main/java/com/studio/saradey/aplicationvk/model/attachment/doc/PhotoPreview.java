package com.studio.saradey.aplicationvk.model.attachment.doc;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * @author jtgn on 14.08.2018
 */


public class PhotoPreview extends RealmObject {
    RealmList<Size> sizes;

    public RealmList<Size> getSizes() {
        return sizes;
    }

    public void setSizes(RealmList<Size> sizes) {
        this.sizes = sizes;
    }
}
