package com.studio.saradey.aplicationvk.model.view.attachment;

import android.view.View;

import com.studio.saradey.aplicationvk.common.utils.Utils;
import com.studio.saradey.aplicationvk.model.attachment.doc.Doc;
import com.studio.saradey.aplicationvk.model.attachment.doc.Size;
import com.studio.saradey.aplicationvk.model.view.BaseViewModel;
import com.studio.saradey.aplicationvk.ui.holder.attachment.DocImageAttachmentHolder;

import java.util.List;

/**
 * @author jtgn on 14.08.2018
 */


public class DocImageAttachmentViewModel extends BaseViewModel {


    private String mTitle;
    private String mSize;
    private String mExt;

    private String mImage;

    private String mUrl;


    public DocImageAttachmentViewModel(Doc doc) {
        if (doc.getTitle().equals("")) {
            mTitle = "Document";
        } else {
            mTitle = Utils.removeExtFromText(doc.getTitle());
        }

        mUrl = doc.getUrl();

        mSize = Utils.formatSize(doc.getSize());

        mExt = "." + doc.getExt();

        List<Size> sizes = doc.getPreview().getPhoto().getSizes();
        mImage = sizes.get(sizes.size() - 1).getSrc();
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentDocImage;
    }

    public DocImageAttachmentHolder onCreateViewHolder(View view) {
        return new DocImageAttachmentHolder(view);
    }


    public String getmUrl() {
        return mUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSize() {
        return mSize;
    }

    public String getExt() {
        return mExt;
    }

    public String getImage() {
        return mImage;
    }
}
