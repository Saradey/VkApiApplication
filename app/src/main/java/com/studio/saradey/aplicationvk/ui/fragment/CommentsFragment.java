package com.studio.saradey.aplicationvk.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.studio.saradey.aplicationvk.MyAplication;
import com.studio.saradey.aplicationvk.R;
import com.studio.saradey.aplicationvk.model.Place;
import com.studio.saradey.aplicationvk.mvp.presenter.BaseFeedPresenter;
import com.studio.saradey.aplicationvk.mvp.presenter.CommentsPresenter;
import com.studio.saradey.aplicationvk.ui.activity.CreatePostActivity;

import butterknife.ButterKnife;

/**
 * @author jtgn on 15.08.2018
 */

public class CommentsFragment extends BaseFeedFragment {

    @InjectPresenter
    CommentsPresenter mPresenter;
    Place mPlace;

    public static CommentsFragment newInstance(Place place) {

        Bundle args = new Bundle();
        args.putAll(place.toBundle());

        CommentsFragment fragment = new CommentsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyAplication.getApplicationComponent().inject(this);

        mPlace = new Place(getArguments());
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        mPresenter.setPlace(mPlace);
        return mPresenter;
    }


    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_comments;
    }


    //Реализуем слушатели для FAB и переопределяем метод needFab в CommentsFragment
    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseActivity(), CreatePostActivity.class);
                intent.putExtra("type", "comment");
                intent.putExtra("owner_id", Integer.parseInt(mPlace.getOwnerId()));
                intent.putExtra("id", Integer.parseInt(mPlace.getPostId()));
                startActivityForResult(intent, 0);
            }
        });
    }

    //Реализуем слушатели для FAB и переопределяем метод needFab в CommentsFragment
    @Override
    public boolean needFab() {
        return true;
    }
}
