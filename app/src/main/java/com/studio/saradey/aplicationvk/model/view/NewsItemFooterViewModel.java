package com.studio.saradey.aplicationvk.model.view;

import android.view.View;

import com.studio.saradey.aplicationvk.model.WallItem;
import com.studio.saradey.aplicationvk.model.view.couter.CommentCounterViewModel;
import com.studio.saradey.aplicationvk.model.view.couter.LikeCounterViewModel;
import com.studio.saradey.aplicationvk.model.view.couter.RepostCounterViewModel;
import com.studio.saradey.aplicationvk.ui.holder.BaseViewHolder;
import com.studio.saradey.aplicationvk.ui.holder.NewsItemFooterHolder;

/**
 * @author jtgn on 11.08.2018
 */

public class NewsItemFooterViewModel extends BaseViewModel {

    //для индетефикаторов и счетчиков
    private int mId;
    private int ownerId;
    private long mDateLong;

    private LikeCounterViewModel mLikes;
    private CommentCounterViewModel mComments;
    private RepostCounterViewModel mreposts;

    public NewsItemFooterViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();
        this.ownerId = wallItem.getOwnerId();
        this.mDateLong = wallItem.getDate();
        this.mLikes = new LikeCounterViewModel(wallItem.getLikes());
        this.mComments = new CommentCounterViewModel(wallItem.getComments());
        this.mreposts = new RepostCounterViewModel(wallItem.getReposts());
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemFooter;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemFooterHolder(view);
    }

    //переопределяем потому что это реальная модель
    @Override
    public boolean isItemDecorator() {
        return true;
    }

    public long getDateLong() {
        return mDateLong;
    }

    public void setDateLong(long mDateLong) {
        this.mDateLong = mDateLong;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public LikeCounterViewModel getLikes() {
        return mLikes;
    }

    public void setLikes(LikeCounterViewModel mLikes) {
        this.mLikes = mLikes;
    }

    public CommentCounterViewModel getComments() {
        return mComments;
    }

    public void setComments(CommentCounterViewModel mComments) {
        this.mComments = mComments;
    }

    public RepostCounterViewModel getReposts() {
        return mreposts;
    }

    public void setMreposts(RepostCounterViewModel mreposts) {
        this.mreposts = mreposts;
    }
}
