package com.studio.saradey.aplicationvk.mvp.view;

import com.studio.saradey.aplicationvk.model.view.NewsItemFooterViewModel;

/**
 * @author jtgn on 14.08.2018
 */


public interface OpenedPostView extends BaseFeedView {
    void setFooter(NewsItemFooterViewModel viewModel);
}