package com.studio.saradey.aplicationvk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.studio.saradey.aplicationvk.MyAplication;
import com.studio.saradey.aplicationvk.R;
import com.studio.saradey.aplicationvk.mvp.presenter.BaseFeedPresenter;
import com.studio.saradey.aplicationvk.mvp.presenter.InfoLinksPresenter;

import butterknife.ButterKnife;

/**
 * @author jtgn on 18.08.2018
 */


public class InfoLinksFragment extends BaseFeedFragment {

    @InjectPresenter
    InfoLinksPresenter mPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setWithEndlessList(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyAplication.getApplicationComponent().inject(this);
        ButterKnife.bind(this, view);
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return mPresenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.title_links;
    }
}
