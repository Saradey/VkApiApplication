package com.studio.saradey.aplicationvk.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.studio.saradey.aplicationvk.MyAplication;
import com.studio.saradey.aplicationvk.consts.ApiConstants;
import com.studio.saradey.aplicationvk.model.Member;
import com.studio.saradey.aplicationvk.model.view.BaseViewModel;
import com.studio.saradey.aplicationvk.model.view.MemberViewModel;
import com.studio.saradey.aplicationvk.mvp.view.BaseFeedView;
import com.studio.saradey.aplicationvk.rest.api.GroupsApi;
import com.studio.saradey.aplicationvk.rest.model.request.GroupsGetMembersRequestModel;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * @author jtgn on 13.08.2018
 */


@InjectViewState
public class MembersPresenter extends BaseFeedPresenter<BaseFeedView> {
    @Inject
    GroupsApi mGroupApi;

    public MembersPresenter() {
        MyAplication.getApplicationComponent().inject(this);
    }


    //загрузка данных из сети
    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mGroupApi.getMembers(new GroupsGetMembersRequestModel(
                ApiConstants.MY_GROUP_ID, count, offset).toMap())
                .flatMap(baseItemResponseFull -> {
                    return Observable.fromIterable(baseItemResponseFull.response.getItems());
                })
                .doOnNext(this::saveToDb)
                .map(MemberViewModel::new);
    }


    //загрузка данных из бд
    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .map(MemberViewModel::new);
    }


    public Callable<List<Member>> getListFromRealmCallable() {
        return () -> {
            String[] sortFields = {Member.ID};
            Sort[] sortOrder = {Sort.ASCENDING};

            Realm realm = Realm.getDefaultInstance();
            RealmResults<Member> results = realm.where(Member.class)
                    .findAll();
            results.sort(sortFields, sortOrder);
            return realm.copyFromRealm(results);
        };
    }


}
