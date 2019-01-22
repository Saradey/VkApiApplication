package com.studio.saradey.aplicationvk.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.studio.saradey.aplicationvk.ui.activity.BaseActivity;

/**
 * @author jtgn on 29.07.2018
 */
public abstract class BaseFragment extends MvpAppCompatFragment {

    //возвращаем id макета +
    @LayoutRes
    protected abstract int getMainContentLayout();

    //
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getMainContentLayout(), container, false);
    }


    //для отображения заголовка тулбара +
    public String createToolbarTitle(Context context) {
        return context.getString(onCreateToolbarTitle());
    }

    //-
    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    //метод который будет запрашивать заголовок тулбара +
    @StringRes
    public abstract int onCreateToolbarTitle();

    //мы будем переопределять метод в тех фрагментах в которых нужна кнопка fab -
    public boolean needFab() {
        return false;
    }
}
