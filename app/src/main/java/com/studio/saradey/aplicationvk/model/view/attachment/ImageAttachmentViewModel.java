package com.studio.saradey.aplicationvk.model.view.attachment;

import android.view.View;

import com.studio.saradey.aplicationvk.model.attachment.Photo;
import com.studio.saradey.aplicationvk.model.view.BaseViewModel;
import com.studio.saradey.aplicationvk.ui.holder.attachment.ImageAttachmentHolder;

/**
 * @author jtgn on 14.08.2018
 */

public class ImageAttachmentViewModel extends BaseViewModel {

    public boolean needClick = true;
    private String mPhotoUrl;

    public ImageAttachmentViewModel(String url) {
        mPhotoUrl = url;

        needClick = false;
    }

    public ImageAttachmentViewModel(Photo photo) {
        mPhotoUrl = photo.getPhoto604();
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentImage;
    }

    @Override
    public ImageAttachmentHolder onCreateViewHolder(View view) {
        return new ImageAttachmentHolder(view);
    }


    public String getPhotoUrl() {
        return mPhotoUrl;
    }
}
