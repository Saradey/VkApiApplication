package com.studio.saradey.aplicationvk.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.studio.saradey.aplicationvk.MyAplication;
import com.studio.saradey.aplicationvk.R;
import com.studio.saradey.aplicationvk.mvp.presenter.BaseFeedPresenter;
import com.studio.saradey.aplicationvk.mvp.presenter.NewsFeedPresenter;
import com.studio.saradey.aplicationvk.rest.api.WallApi;
import com.studio.saradey.aplicationvk.ui.activity.CreatePostActivity;

import javax.inject.Inject;


public class NewsFeedFragment extends BaseFeedFragment {

    //Для отображения записей текущего пользователя будем
    // использовать фрагмент, наследованный от NewsFeedFragment.

    @Inject
    WallApi mWallApi;

    @InjectPresenter
    NewsFeedPresenter mPresenter;


    public NewsFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyAplication.getApplicationComponent().inject(this);
    }


    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return mPresenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }

    //Реализуем слушатели для FAB и переопределяем метод needFab в CommentsFragment
    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), CreatePostActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public boolean needFab() {
        return true;
    }

}

