package com.studio.saradey.aplicationvk.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.studio.saradey.aplicationvk.model.Profile;
import com.studio.saradey.aplicationvk.ui.fragment.BaseFragment;

/**
 * @author jtgn on 28.07.2018
 */


public interface MainView extends MvpView {

    //авторизуемся
    void startSignIn();

    //уже авторизовались
    void signedId();

    //для получения пользователя
    void showCurrentUser(Profile profile);


    void showFragmentFromDrawer(BaseFragment baseFragment);


    void startActivityFromDrawer(Class<?> act);
}
