package com.studio.saradey.aplicationvk.model.view.attachment;

import android.view.View;

import com.studio.saradey.aplicationvk.model.attachment.Page;
import com.studio.saradey.aplicationvk.model.view.BaseViewModel;
import com.studio.saradey.aplicationvk.ui.holder.attachment.PageAttachmentHolder;

/**
 * @author jtgn on 14.08.2018
 */


public class PageAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mUrl;

    public PageAttachmentViewModel(Page page) {
        mUrl = page.getUrl();
        mTitle = page.getTitle();
    }

    public String getTitle() {
        return mTitle;
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentPage;
    }

    @Override
    public PageAttachmentHolder onCreateViewHolder(View view) {
        return new PageAttachmentHolder(view);
    }


    public String getmUrl() {
        return mUrl;
    }
}
