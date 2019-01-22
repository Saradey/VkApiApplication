package com.studio.saradey.aplicationvk.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.studio.saradey.aplicationvk.model.WallItem;
import com.studio.saradey.aplicationvk.model.view.couter.LikeCounterViewModel;

/**
 * @author jtgn on 15.08.2018
 */


public interface PostFooterView extends MvpView {
    void like(LikeCounterViewModel likes);

    void openComments(WallItem wallItem);
}
