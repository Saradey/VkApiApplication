package com.studio.saradey.aplicationvk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.studio.saradey.aplicationvk.MyAplication;
import com.studio.saradey.aplicationvk.R;
import com.studio.saradey.aplicationvk.model.view.NewsItemFooterViewModel;
import com.studio.saradey.aplicationvk.mvp.presenter.BaseFeedPresenter;
import com.studio.saradey.aplicationvk.mvp.presenter.OpenedPostPresenter;
import com.studio.saradey.aplicationvk.mvp.view.OpenedPostView;
import com.studio.saradey.aplicationvk.ui.holder.NewsItemFooterHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author jtgn on 14.08.2018
 */

//открываем видео
public class OpenedPostFragment extends BaseFeedFragment implements OpenedPostView {


    @BindView(R.id.rv_footer)
    View mFooter;

    @InjectPresenter
    OpenedPostPresenter mPresenter;

    int id;


    public static OpenedPostFragment newInstance(int id) {
        Bundle args = new Bundle();

        args.putInt("id", id);
        OpenedPostFragment fragment = new OpenedPostFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyAplication.getApplicationComponent().inject(this);
        isWithEndlessList = false;
        if (getArguments() != null) {
            this.id = getArguments().getInt("id");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_opened_wall_item;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_opened_post;
    }


    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        mPresenter.setId(id);
        return mPresenter;
    }

    @Override
    public void setFooter(NewsItemFooterViewModel viewModel) {
        mFooter.setVisibility(View.VISIBLE);
        new NewsItemFooterHolder(mFooter).bindViewHolder(viewModel);
    }

}
