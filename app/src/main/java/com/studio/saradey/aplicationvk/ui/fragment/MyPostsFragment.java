package com.studio.saradey.aplicationvk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.studio.saradey.aplicationvk.R;


/**
 * @author jtgn on 13.08.2018
 */

//отображение записей текущего пользователя
public class MyPostsFragment extends NewsFeedFragment {
    public MyPostsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.setEnableIdFiltering(true);
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_my_posts;
    }
}
