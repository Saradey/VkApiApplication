package com.studio.saradey.aplicationvk.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.studio.saradey.aplicationvk.MyAplication;
import com.studio.saradey.aplicationvk.R;
import com.studio.saradey.aplicationvk.common.manager.MyFragmentManager;
import com.studio.saradey.aplicationvk.ui.fragment.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author jtgn on 29.07.2018
 */
public abstract class BaseActivity extends MvpAppCompatActivity {

    @Inject
    MyFragmentManager myFragmentManager;    //менеджер для фрагментов

    @BindView(R.id.progress)
    ProgressBar mProgressBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.main_wrapper)
    FrameLayout parent;

    @BindView(R.id.fab)
    public FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);

        MyAplication.getApplicationComponent().inject(this);

        setSupportActionBar(toolbar);

        getLayoutInflater().inflate(getMainContentLayout(), parent); //надуваем через inflate наш контент
    }



    @LayoutRes //анотация LayoutRes предпологает что метод будет возвращать ссылку на этот лейаут
    protected abstract int getMainContentLayout();


    // вызываем его в методе fragmentOnScreen
    //для управления видимости кнопкой
    public void setupFabVisibility(boolean needFab) {
        if (mFab == null) return;

        if (needFab) {
            mFab.show();
        } else {
            mFab.hide();
        }
    }


    //устанавливаем анотацию фрагмента в тулбар и видимость фаба
    public void fragmentOnScreen(BaseFragment baseFragment) {
        setToolbarTitle(baseFragment.createToolbarTitle(this));
        setupFabVisibility(baseFragment.needFab());
    }



    public ProgressBar getProgressBar() {
        return mProgressBar;
    }

    //метод для установки корневого фрагмета
    public void setContent(BaseFragment fragment) {
        myFragmentManager.setFragment(this, fragment, R.id.main_wrapper);
    }


    //метод для установки дополнительных фрагментов
    public void addContent(BaseFragment fragment) {
        myFragmentManager.addFragment(this, fragment, R.id.main_wrapper);
    }

    //удаляем текущий фрагмент
    public boolean removeCurrentFragment() {
        return myFragmentManager.removeCurrentFragment(this);
    }

    //чистим стек
    public boolean removeFragment(BaseFragment fragment) {
        return myFragmentManager.removeFragment(this, fragment);
    }


    //если нажали кнопку назад
    @Override
    public void onBackPressed() {
        removeCurrentFragment();
    }


    //метод установки заголовка тулбара
    public void setToolbarTitle(String title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }


}
