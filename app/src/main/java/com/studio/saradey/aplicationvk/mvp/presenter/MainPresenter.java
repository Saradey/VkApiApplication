package com.studio.saradey.aplicationvk.mvp.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.studio.saradey.aplicationvk.CurrentUser;
import com.studio.saradey.aplicationvk.MyAplication;
import com.studio.saradey.aplicationvk.common.manager.MyFragmentManager;
import com.studio.saradey.aplicationvk.common.manager.NetworkManager;
import com.studio.saradey.aplicationvk.model.Profile;
import com.studio.saradey.aplicationvk.mvp.view.MainView;
import com.studio.saradey.aplicationvk.rest.api.UsersApi;
import com.studio.saradey.aplicationvk.rest.model.request.UsersGetRequestModel;
import com.studio.saradey.aplicationvk.ui.activity.SettingActivity;
import com.studio.saradey.aplicationvk.ui.fragment.BaseFragment;
import com.studio.saradey.aplicationvk.ui.fragment.BoardFragment;
import com.studio.saradey.aplicationvk.ui.fragment.GroupRulesFragment;
import com.studio.saradey.aplicationvk.ui.fragment.InfoFragment;
import com.studio.saradey.aplicationvk.ui.fragment.MembersFragment;
import com.studio.saradey.aplicationvk.ui.fragment.MyPostsFragment;
import com.studio.saradey.aplicationvk.ui.fragment.NewsFeedFragment;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;

/**
 * @author jtgn on 28.07.2018
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    UsersApi mUserApi;

    @Inject
    NetworkManager mNetworkManager;

    @Inject
    MyFragmentManager myFragmentManager;


    public MainPresenter() {
        MyAplication.getApplicationComponent().inject(this);
    }

    //здесь проверяется условия, если пользователь не авторизован
    //вызывается метод мейнВью для старта авторизации
    //в противном случает вызывается другой метод для получения авторизорованного пользователя
    public void chekAuth() {
        if (!CurrentUser.isAuthorized()) {
            getViewState().startSignIn();
        } else {
            getCurrentUser();
            getViewState().signedId();
        }
    }

    // методы, которые будут использоваться для получения,
    // сохранения и восстановления данных пользователя:
    public Observable<Profile> getProfileFromNetwork() {
        return mUserApi.get(new UsersGetRequestModel(CurrentUser.getId()).toMap())
                .flatMap(listFull -> Observable.fromIterable(listFull.response))
                .doOnNext(this::saveToDb);
    }

    private Observable<Profile> getProfileFromDb() {
        return Observable.fromCallable(getListFromRealmCallable());
    }

    public void saveToDb(RealmObject item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(item));
    }

    public Callable<Profile> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            Profile realmResults = realm.where(Profile.class)
                    .equalTo("id", Integer.parseInt(CurrentUser.getId()))
                    .findFirst();
            return realm.copyFromRealm(realmResults);
        };
    }


    //Этот метод мы будем вызывать когда пользователь совершит нажатие на пункт дровера.
    // В свою очередь этот метод будет создавать фрагмент основываясь на id и передавать
    // этот фрагмент в MainView(MainActivity). MainActivity будет открывать этот фрагмент.
    public void drawerItemClick(int id) {
        BaseFragment fragment = null;

        switch (id) {
            case 1:
                fragment = new NewsFeedFragment();
                break;
            case 2:
                fragment = new MyPostsFragment();
                break;
            case 3:
                getViewState().startActivityFromDrawer(SettingActivity.class);
                return;
            case 4:
                fragment = new MembersFragment();
                break;
            case 5:
                fragment = new BoardFragment();
                break;
            case 6:
                fragment = new InfoFragment();
                break;
            case 7:
                fragment = new GroupRulesFragment();
                break;
        }

        if (fragment != null && !myFragmentManager.isAlreadyContains(fragment)) {
            getViewState().showFragmentFromDrawer(fragment);
        }
    }


    //метод, который будем вызывать из активити для заполнения хедера данными пользователя
    // брать данные из сети или из бд.
    @SuppressLint("CheckResult")
    private void getCurrentUser() {
        mNetworkManager.getNetworkObservable()
                .flatMap(aBoolean -> {
                    if (!CurrentUser.isAuthorized()) {
                        getViewState().startSignIn();
                    }

                    return aBoolean
                            ? getProfileFromNetwork()
                            : getProfileFromDb();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(profile -> {
                    getViewState().showCurrentUser(profile);
                }, error -> {
                    error.printStackTrace();
                });
    }

}
