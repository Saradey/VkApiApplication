package com.studio.saradey.aplicationvk.model.view;

import android.view.View;

import com.studio.saradey.aplicationvk.model.WallItem;
import com.studio.saradey.aplicationvk.ui.holder.NewsItemBodyHolder;


/**
 * @author jtgn on 01.08.2018
 */


//эта сущность для отображения аттачментов
public class NewsItemBodyViewModel extends BaseViewModel {

    private int mId;

    private String mText;

    private String mAttachmentString;

    private boolean mIsRepost;


    public NewsItemBodyViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();
        this.mIsRepost = wallItem.haveSharedRepost();

        if (mIsRepost) {
            this.mText = wallItem.getSharedRepost().getText();
            this.mAttachmentString = wallItem.getSharedRepost().getAttachmentsString();
        } else {
            this.mText = wallItem.getText();
            this.mAttachmentString = wallItem.getAttachmentsString();
        }
    }

    //переопределяем потому что это реальная модель
    @Override
    public boolean isItemDecorator() {
        return true;
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemBody;
    }

    @Override
    public NewsItemBodyHolder onCreateViewHolder(View view) {
        return new NewsItemBodyHolder(view);
    }


    public String getText() {
        return mText;
    }

    public int getId() {
        return mId;
    }

    public String getmAttachmentString() {
        return mAttachmentString;
    }
}
