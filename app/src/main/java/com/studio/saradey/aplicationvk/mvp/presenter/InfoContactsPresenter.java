package com.studio.saradey.aplicationvk.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.studio.saradey.aplicationvk.MyAplication;
import com.studio.saradey.aplicationvk.model.Profile;
import com.studio.saradey.aplicationvk.model.view.BaseViewModel;
import com.studio.saradey.aplicationvk.model.view.MemberViewModel;
import com.studio.saradey.aplicationvk.mvp.view.BaseFeedView;
import com.studio.saradey.aplicationvk.rest.api.GroupsApi;
import com.studio.saradey.aplicationvk.rest.api.UsersApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * @author jtgn on 16.08.2018
 */

@InjectViewState
public class InfoContactsPresenter extends BaseFeedPresenter<BaseFeedView> {

    @Inject
    GroupsApi mGroupApi;

    @Inject
    UsersApi mUserApi;

    public InfoContactsPresenter() {
        MyAplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return null;
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(profile -> Observable.fromIterable(parsePojoModel(profile)));

    }

    private List<BaseViewModel> parsePojoModel(Profile profile) {
        List<BaseViewModel> items = new ArrayList<>();
        items.add(new MemberViewModel(profile));

        return items;
    }

    private List<BaseViewModel> parsePojoModel(List<Profile> profileList) {
        List<BaseViewModel> items = new ArrayList<>();
        for (Profile profile : profileList) {
            items.addAll(parsePojoModel(profile));
        }

        return items;
    }

    public Callable<List<Profile>> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            List<Profile> result = realm.where(Profile.class)
                    .equalTo("isContact", true)
                    .findAll();
            return realm.copyFromRealm(result);
        };
    }
}
