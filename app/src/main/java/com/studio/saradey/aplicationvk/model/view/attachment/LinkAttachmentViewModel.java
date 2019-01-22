package com.studio.saradey.aplicationvk.model.view.attachment;

import android.view.View;

import com.studio.saradey.aplicationvk.model.attachment.Link;
import com.studio.saradey.aplicationvk.model.view.BaseViewModel;
import com.studio.saradey.aplicationvk.ui.holder.attachment.LinkAttachmentViewHolder;

/**
 * @author jtgn on 14.08.2018
 */


public class LinkAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mUrl;

    public LinkAttachmentViewModel(Link link) {

        if (link.getTitle() == null || link.getTitle().equals("")) {
            if (link.getName() != null) {
                mTitle = link.getName();
            } else {
                mTitle = "Link";
            }
        } else {
            mTitle = link.getTitle();
        }

        mUrl = link.getUrl();
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentLink;
    }

    @Override
    public LinkAttachmentViewHolder onCreateViewHolder(View view) {
        return new LinkAttachmentViewHolder(view);
    }


    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }
}
