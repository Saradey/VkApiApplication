package com.studio.saradey.aplicationvk.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.studio.saradey.aplicationvk.CurrentUser;
import com.studio.saradey.aplicationvk.MyAplication;
import com.studio.saradey.aplicationvk.common.utils.VkListHelper;
import com.studio.saradey.aplicationvk.consts.ApiConstants;
import com.studio.saradey.aplicationvk.model.WallItem;
import com.studio.saradey.aplicationvk.model.view.BaseViewModel;
import com.studio.saradey.aplicationvk.model.view.NewsItemBodyViewModel;
import com.studio.saradey.aplicationvk.model.view.NewsItemFooterViewModel;
import com.studio.saradey.aplicationvk.model.view.NewsItemHeaderViewModel;
import com.studio.saradey.aplicationvk.mvp.view.BaseFeedView;
import com.studio.saradey.aplicationvk.rest.api.WallApi;
import com.studio.saradey.aplicationvk.rest.model.request.WallGetRequestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * @author jtgn on 11.08.2018
 */

@InjectViewState
public class NewsFeedPresenter extends BaseFeedPresenter<BaseFeedView> {

    @Inject
    WallApi mWallApi;

    private boolean enableIdFiltering = false;

    public NewsFeedPresenter() {
        MyAplication.getApplicationComponent().inject(this);
    }


    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        //count количество элементов
        //offset смещение
        return mWallApi.get(new WallGetRequestModel(ApiConstants.MY_GROUP_ID, count, offset).toMap())
                .flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
                .compose(applyFilter())
                .doOnNext(this::saveToDb)   //сохраняем обновляем данные
                .flatMap(wallItem -> {
                    List<BaseViewModel> baseItems = new ArrayList<>();
                    baseItems.add(new NewsItemHeaderViewModel(wallItem));
                    baseItems.add(new NewsItemBodyViewModel(wallItem));
                    baseItems.add(new NewsItemFooterViewModel(wallItem));
                    return Observable.fromIterable(baseItems);
                });

        //Методы onCreateLoadDataObservable() и onCreateRestoreDataObservable():
        //
        //с помощью оператора compose(..) трансформируем rx цепочку в Observable,
        // возвращаемый методом applyFilter().

        //имея условие enableIdFiltering && CurrentUser.getId() != null,
        // возвращаем отфильтрованный по id текущего пользователя Observable (если true)
    }


    //метод получения списка из бд
    public Callable<List<WallItem>> getListFromRealmCallable() {
        return () -> {
            String[] sortFields = {"date"};
            Sort[] sortOrder = {Sort.DESCENDING};
            Realm realm = Realm.getDefaultInstance();
            RealmResults<WallItem> realmResults = realm.where(WallItem.class)
                    .findAll();
            realmResults.sort(sortFields, sortOrder);
            return realm.copyFromRealm(realmResults);
        };
    }


    //создаем Observable для подгрузки данных и возвращаем его
    //с помощью оператора compose(..) трансформируем rx цепочку в Observable
    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .compose(applyFilter())
                .flatMap(wallItem -> Observable.fromIterable(parsePojoModel(wallItem)));
    }


    //возвращаем списки постов
    private List<BaseViewModel> parsePojoModel(WallItem wallItem) {
        List<BaseViewModel> baseItems = new ArrayList<>();
        baseItems.add(new NewsItemHeaderViewModel(wallItem));
        baseItems.add(new NewsItemBodyViewModel(wallItem));
        baseItems.add(new NewsItemFooterViewModel(wallItem));
        return baseItems;
    }


    //ObservableTransformer позволяет трансформировать не только данные,
    // излучаемые Observable, как оператор flatMap, но и всю Rx цепочку.
    protected ObservableTransformer<WallItem, WallItem> applyFilter() {
        if (enableIdFiltering && CurrentUser.getId() != null) {
            return baseItemObservable -> baseItemObservable.
                    filter(wallItem -> CurrentUser.getId().equals(String.valueOf(wallItem.getFromId())));
        } else {
            return baseItemObservable -> baseItemObservable;
        }
    }


    public void setEnableIdFiltering(boolean enableIdFiltering) {
        this.enableIdFiltering = enableIdFiltering;
    }
}

