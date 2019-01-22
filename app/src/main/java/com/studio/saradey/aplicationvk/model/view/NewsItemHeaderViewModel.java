package com.studio.saradey.aplicationvk.model.view;

import android.view.View;

import com.studio.saradey.aplicationvk.model.WallItem;
import com.studio.saradey.aplicationvk.ui.holder.BaseViewHolder;
import com.studio.saradey.aplicationvk.ui.holder.NewsItemHeaderHolder;

/**
 * @author jtgn on 08.08.2018
 */


public class NewsItemHeaderViewModel extends BaseViewModel {

    //для индитификатора
    private int mId;

    //для фото
    private String mProfilePhoto;

    //имя отправителя
    private String mProfileName;

    //является запись репостом, если да то будем менять способ отображения
    private boolean mIsRepost;

    //автор репоста
    private String mRepostProfileName;


    //получаем значения
    public NewsItemHeaderViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();

        this.mProfilePhoto = wallItem.getSenderPhoto();
        this.mProfileName = wallItem.getSenderName();

        this.mIsRepost = wallItem.haveSharedRepost();

        //пресваиваем имя автора репоста
        if (mIsRepost) {
            this.mRepostProfileName = wallItem.getSharedRepost().getSenderName();
        }

    }

    //получаем тип лейаута
    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemHeader;
    }


    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemHeaderHolder(view);
    }

    public int getId() {
        return mId;
    }

    public String getProfilePhoto() {
        return mProfilePhoto;
    }

    public String getProfileName() {
        return mProfileName;
    }

    public boolean isRepost() {
        return mIsRepost;
    }

    public String getRepostProfileName() {
        return mRepostProfileName;
    }
}
