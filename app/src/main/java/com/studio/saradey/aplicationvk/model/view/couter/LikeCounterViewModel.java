package com.studio.saradey.aplicationvk.model.view.couter;

import com.studio.saradey.aplicationvk.model.Likes;

/**
 * @author jtgn on 08.08.2018
 */

public class LikeCounterViewModel extends CounterViewModel {

    private Likes mLikes;

    public LikeCounterViewModel(Likes likes) {
        super(likes.getCount());

        this.mLikes = likes;

        if (mLikes.getUserLikes() == 1) {
            setAccentColor();
        }
    }

    public Likes getLikes() {
        return mLikes;
    }
}