package com.studio.saradey.aplicationvk.di.component;

import com.studio.saradey.aplicationvk.common.manager.NetworkManager;
import com.studio.saradey.aplicationvk.di.module.ApplicationModule;
import com.studio.saradey.aplicationvk.di.module.ManagerModule;
import com.studio.saradey.aplicationvk.di.module.RestModule;
import com.studio.saradey.aplicationvk.model.view.CommentBodyViewModel;
import com.studio.saradey.aplicationvk.model.view.CommentFooterViewModel;
import com.studio.saradey.aplicationvk.model.view.TopicViewModel;
import com.studio.saradey.aplicationvk.mvp.presenter.BoardPresenter;
import com.studio.saradey.aplicationvk.mvp.presenter.CommentsPresenter;
import com.studio.saradey.aplicationvk.mvp.presenter.InfoContactsPresenter;
import com.studio.saradey.aplicationvk.mvp.presenter.InfoLinksPresenter;
import com.studio.saradey.aplicationvk.mvp.presenter.InfoPresenter;
import com.studio.saradey.aplicationvk.mvp.presenter.MainPresenter;
import com.studio.saradey.aplicationvk.mvp.presenter.MembersPresenter;
import com.studio.saradey.aplicationvk.mvp.presenter.NewsFeedPresenter;
import com.studio.saradey.aplicationvk.mvp.presenter.OpenedCommentPresenter;
import com.studio.saradey.aplicationvk.mvp.presenter.OpenedPostPresenter;
import com.studio.saradey.aplicationvk.mvp.presenter.TopicCommentsPresenter;
import com.studio.saradey.aplicationvk.ui.activity.BaseActivity;
import com.studio.saradey.aplicationvk.ui.activity.MainActivity;
import com.studio.saradey.aplicationvk.ui.activity.OpenedPostFromPushActivity;
import com.studio.saradey.aplicationvk.ui.fragment.CommentsFragment;
import com.studio.saradey.aplicationvk.ui.fragment.InfoContactsFragment;
import com.studio.saradey.aplicationvk.ui.fragment.InfoLinksFragment;
import com.studio.saradey.aplicationvk.ui.fragment.NewsFeedFragment;
import com.studio.saradey.aplicationvk.ui.fragment.OpenedCommentFragment;
import com.studio.saradey.aplicationvk.ui.fragment.OpenedPostFragment;
import com.studio.saradey.aplicationvk.ui.fragment.TopicCommentsFragment;
import com.studio.saradey.aplicationvk.ui.holder.NewsItemBodyHolder;
import com.studio.saradey.aplicationvk.ui.holder.NewsItemFooterHolder;
import com.studio.saradey.aplicationvk.ui.holder.attachment.ImageAttachmentHolder;
import com.studio.saradey.aplicationvk.ui.holder.attachment.VideoAttachmentHolder;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author jtgn on 30.07.2018
 */

//у нас будет создат только один компонент для всего приложения

@Singleton
@Component(modules = {ApplicationModule.class, ManagerModule.class, RestModule.class})
public interface ApplicationComponent {

    //activity
    void inject(BaseActivity baseActivity);
    void inject(MainActivity mainActivity);

    void inject(OpenedPostFromPushActivity activity);

    //fragment
    void inject(NewsFeedFragment fragment);
    void inject(OpenedCommentFragment fragment);
    void inject(CommentsFragment fragment);
    void inject(OpenedPostFragment fragment);

    void inject(InfoContactsFragment fragment);
    void inject(TopicCommentsFragment fragment);

    void inject(InfoLinksFragment fragment);

    //holders
    void inject(NewsItemBodyHolder holder);
    void inject(NewsItemFooterHolder holder);
    void inject(CommentBodyViewModel.CommentBodyViewHolder holder);
    void inject(CommentFooterViewModel.CommentFooterHolder holder);
    void inject(ImageAttachmentHolder holder);
    void inject(VideoAttachmentHolder holder);

    void inject(TopicViewModel.TopicViewHolder holder);

    //presenter
    void inject(NewsFeedPresenter presenter);
    void inject(MainPresenter presenter);
    void inject(MembersPresenter presenter);
    void inject(BoardPresenter presenter);
    void inject(InfoPresenter presenter);

    void inject(InfoContactsPresenter presenter);
    void inject(TopicCommentsPresenter presenter);
    void inject(OpenedCommentPresenter presenter);
    void inject(CommentsPresenter presenter);
    void inject(OpenedPostPresenter presenter);

    void inject(InfoLinksPresenter presenter);

    //managers
    void inject(NetworkManager manager);

}
