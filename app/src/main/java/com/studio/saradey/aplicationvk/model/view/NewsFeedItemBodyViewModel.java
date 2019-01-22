package com.studio.saradey.aplicationvk.model.view;

import android.view.View;

import com.studio.saradey.aplicationvk.model.WallItem;
import com.studio.saradey.aplicationvk.ui.holder.BaseViewHolder;
import com.studio.saradey.aplicationvk.ui.holder.NewsItemBodyHolder;


/**
 * @author jtgn on 01.08.2018
 */


//эта сущность для отображения в холдере NewsItemBodyHolder
public class NewsFeedItemBodyViewModel extends BaseViewModel {

    private int mId;
    private String mText;


    //так удобнее, что бы не раздувать код
    public NewsFeedItemBodyViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();
        this.mText = wallItem.getText();
    }


    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemBodyHolder(view);
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemBody;
    }


    public int getId() {
        return mId;
    }


    public String getText() {
        return mText;
    }


}
