package com.studio.saradey.aplicationvk.mvp.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.MvpPresenter;
import com.studio.saradey.aplicationvk.common.manager.NetworkManager;
import com.studio.saradey.aplicationvk.model.view.BaseViewModel;
import com.studio.saradey.aplicationvk.mvp.view.BaseFeedView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;

/**
 * @author jtgn on 11.08.2018
 */


public abstract class BaseFeedPresenter<V extends BaseFeedView> extends MvpPresenter<V> {

    public static final int START_PAGE_SIZE = 15;
    public static final int NEXT_PAGE_SIZE = 5;

    private boolean mIsInLoading;


    @Inject
    NetworkManager mNetworkManager;


    //который будет инициировать загрузку данных с помощью метода
    @SuppressLint("CheckResult")
    public void loadData(ProgressType progressType, int offset, int count) {
        if (mIsInLoading) {
            return;
        }
        mIsInLoading = true;


        //Теперь обсервабл создает класс NetworkManager. Этот Observable возвращает булевую переменную:
        // true — устройство имеет доступ к vk api, false — нет. В методе loadData(…), с помощью
        // оператора flatMap() мы трансформируем Observable<Boolean> в Observable<BaseViewModel>,
        // который в зависимости от доступа к апи излучает либо данные из сети, либо данные из бд.
        mNetworkManager.getNetworkObservable()
                .flatMap(aBoolean -> {
                    if (!aBoolean && offset > 0) {
                        return Observable.empty();
                    }
                    return aBoolean
                            ? onCreateLoadDataObservable(count, offset)
                            : onCreateRestoreDataObservable();
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    onLoadingStart(progressType);
                })
                .doFinally(() -> {
                    onLoadingFinish(progressType);
                })
                .subscribe(repositories -> {
                    onLoadingSuccess(progressType, repositories);
                }, error -> {
                    error.printStackTrace();
                    onLoadingFailed(error);
                });
    }


    //это абстрактный метод для создания Observable, который излучает данные, взятые из сети.
    public abstract Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset);


    // методы, которые вызывают loadData(…) с различными параметрами, зависящими от типа загрузки
    // первая загрузка, загрузка новых элементов при прокрутке, обновление списка).
    public void loadStart() {
        loadData(ProgressType.ListProgress, 0, START_PAGE_SIZE);
    }

    // методы, которые вызывают loadData(…) с различными параметрами, зависящими от типа загрузки
    // первая загрузка, загрузка новых элементов при прокрутке, обновление списка).
    public void loadNext(int offset) {
        loadData(ProgressType.Paging, offset, NEXT_PAGE_SIZE);
    }

    // методы, которые вызывают loadData(…) с различными параметрами, зависящими от типа загрузки
    // первая загрузка, загрузка новых элементов при прокрутке, обновление списка).
    public void loadRefresh() {
        loadData(ProgressType.Refreshing, 0, START_PAGE_SIZE);
    }


    public void onLoadingStart(ProgressType progressType) {
        showProgress(progressType);
    }

    public void onLoadingFinish(ProgressType progressType) {
        mIsInLoading = false;
        hideProgress(progressType);
    }

    public void onLoadingFailed(Throwable throwable) {
        getViewState().showError(throwable.getMessage());
    }


    public void onLoadingSuccess(ProgressType progressType, List<BaseViewModel> items) {
        if (progressType == ProgressType.Paging) {
            getViewState().addItems(items);
        } else {
            getViewState().setItems(items);
        }
    }

    //методы, которые в зависимости от типа загрузки показывают или скрывают различные прогрессбары.
    public void showProgress(ProgressType progressType) {
        switch (progressType) {
            case Refreshing:
                getViewState().showRefreshing();
                break;
            case ListProgress:
                getViewState().showListProgress();
                break;
        }
    }

    //методы, которые в зависимости от типа загрузки показывают или скрывают различные прогрессбары.
    public void hideProgress(ProgressType progressType) {
        switch (progressType) {
            case Refreshing:
                getViewState().hideRefreshing();
                break;
            case ListProgress:
                getViewState().hideListProgress();
                break;
        }
    }


    //Мы будем вызывать этот метод тогда, когда захотим сохранить данные.
    public void saveToDb(RealmObject item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(item));
        //realm.close();    //TODO
    }

    //загружаем данные не из сети
    public abstract Observable<BaseViewModel> onCreateRestoreDataObservable();


    //виды загрузки
    public enum ProgressType {
        Refreshing, ListProgress, Paging
    }
}


