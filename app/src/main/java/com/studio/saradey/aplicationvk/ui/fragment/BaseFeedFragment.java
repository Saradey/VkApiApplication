package com.studio.saradey.aplicationvk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.studio.saradey.aplicationvk.R;
import com.studio.saradey.aplicationvk.common.BaseAdapter;
import com.studio.saradey.aplicationvk.common.manager.MyLinearLayoutManager;
import com.studio.saradey.aplicationvk.model.view.BaseViewModel;
import com.studio.saradey.aplicationvk.mvp.presenter.BaseFeedPresenter;
import com.studio.saradey.aplicationvk.mvp.view.BaseFeedView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author jtgn on 11.08.2018
 */


public abstract class BaseFeedFragment extends BaseFragment implements BaseFeedView {

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;

    protected ProgressBar mProgressBar;

    //если она true, присваиваем recyclerView scroll listener,
    // для того чтобы работала пагинация и загружались новые элементы в список.
    //В некоторый списках(инфо, открытая запись, открытый комментарий) мы не будем использовать
    // пагинацию. Для этого в их фрагментах будет присваивать этой переменной значение false.
    protected boolean isWithEndlessList;

    BaseAdapter mAdapter;

    protected BaseFeedPresenter mBaseFeedPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isWithEndlessList = true;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        setUpRecyclerView(view);
        setUpAdapter(mRecyclerView);
        setUpSwipeToRefreshLayout(view);

        mBaseFeedPresenter = onCreateFeedPresenter();
        mBaseFeedPresenter.loadStart();
    }


    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_feed;
    }


    protected abstract BaseFeedPresenter onCreateFeedPresenter();


    //Мы устанавливаем RecyclerView кастомный LayoutManager, который умеет проверять, нужно ли загружать
    // новые элементы и ScrollListener, который «слушает» список и оповещает, когда он скроллится.
    private void setUpRecyclerView(View rootView) {

        MyLinearLayoutManager mLinearLayoutManager = new MyLinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        if (isWithEndlessList) {
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if (mLinearLayoutManager.isOnNextPagePosition()) {
                        mBaseFeedPresenter.loadNext(mAdapter.getRealItemCount());
                    }
                }
            });
        }

        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
    }


    protected void setUpAdapter(RecyclerView rv) {
        mAdapter = new BaseAdapter();
        rv.setAdapter(mAdapter);
    }

    private void setUpSwipeToRefreshLayout(View rootView) {
        //обновлять данные при свайпе вниз
        mSwipeRefreshLayout.setOnRefreshListener(() -> onCreateFeedPresenter().loadRefresh());
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        mProgressBar = getBaseActivity().getProgressBar();
    }


    @Override
    public void showRefreshing() {
    }

    @Override
    public void hideRefreshing() {
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void showListProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideListProgress() {
        mProgressBar.setVisibility(View.GONE);
    }


    @Override
    public void showError(String message) {
        Toast.makeText(getBaseActivity(), message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void setItems(List<BaseViewModel> items) {
        mAdapter.setItems(items);
    }

    @Override
    public void addItems(List<BaseViewModel> items) {
        mAdapter.addItems(items);
    }

    public void setWithEndlessList(boolean withEndlessList) {
        isWithEndlessList = withEndlessList;
    }
}