package com.studio.saradey.aplicationvk.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.studio.saradey.aplicationvk.MyAplication;
import com.studio.saradey.aplicationvk.common.utils.VkListHelper;
import com.studio.saradey.aplicationvk.consts.ApiConstants;
import com.studio.saradey.aplicationvk.model.WallItem;
import com.studio.saradey.aplicationvk.model.view.BaseViewModel;
import com.studio.saradey.aplicationvk.model.view.NewsItemFooterViewModel;
import com.studio.saradey.aplicationvk.model.view.OpenedPostHeaderViewModel;
import com.studio.saradey.aplicationvk.model.view.OpenedPostRepostHeaderViewModel;
import com.studio.saradey.aplicationvk.mvp.view.OpenedPostView;
import com.studio.saradey.aplicationvk.rest.api.WallApi;
import com.studio.saradey.aplicationvk.rest.model.request.WallGetByIdRequestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * @author jtgn on 14.08.2018
 */

@InjectViewState
public class OpenedPostPresenter extends BaseFeedPresenter<OpenedPostView> {

    @Inject
    WallApi mWallApi;
    private int id;

    public OpenedPostPresenter() {
        MyAplication.getApplicationComponent().inject(this);
    }


    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mWallApi.getById(new WallGetByIdRequestModel(ApiConstants.MY_GROUP_ID, id).toMap())
                .flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(wallItem -> {
                    NewsItemFooterViewModel newsItemFooterViewModel = new NewsItemFooterViewModel(wallItem);

                    getViewState().setFooter(newsItemFooterViewModel);
                })
                .observeOn(Schedulers.io())
                .doOnNext(this::saveToDb)
                .flatMap(wallItem -> {
                    List<BaseViewModel> list = new ArrayList<>();
                    List<BaseViewModel> forwardedList = new ArrayList<>();

                    list.add(new OpenedPostHeaderViewModel(wallItem));

                    list.addAll(VkListHelper.getAttachmentVhItems(wallItem.getAttachments()));
                    if (wallItem.haveSharedRepost()) {

                        forwardedList.add(new OpenedPostRepostHeaderViewModel(wallItem.getSharedRepost()));
                        forwardedList.addAll(VkListHelper.getAttachmentVhItems(wallItem.getSharedRepost().getAttachments()));
                    }
                    return Observable.fromIterable(list).concatWith(Observable.fromIterable(forwardedList));
                });

    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(wallItem -> {
                    NewsItemFooterViewModel newsItemFooterViewModel = new NewsItemFooterViewModel(wallItem);

                    getViewState().setFooter(newsItemFooterViewModel);
                })
                .observeOn(Schedulers.io())
                .flatMap(wallItem -> {
                    List<BaseViewModel> list = new ArrayList<>();
                    List<BaseViewModel> forwardedList = new ArrayList<>();

                    list.add(new OpenedPostHeaderViewModel(wallItem));

                    list.addAll(VkListHelper.getAttachmentVhItems(wallItem.getAttachments()));
                    if (wallItem.haveSharedRepost()) {

                        forwardedList.add(new OpenedPostRepostHeaderViewModel(wallItem.getSharedRepost()));
                        forwardedList.addAll(VkListHelper.getAttachmentVhItems(wallItem.getSharedRepost().getAttachments()));
                    }
                    return Observable.fromIterable(list).concatWith(Observable.fromIterable(forwardedList));
                });
    }


    private Callable<WallItem> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            WallItem wallItem = realm.where(WallItem.class).equalTo("id", id).findFirst();

            return realm.copyFromRealm(wallItem);
        };
    }

    public void setId(int id) {
        this.id = id;
    }
}