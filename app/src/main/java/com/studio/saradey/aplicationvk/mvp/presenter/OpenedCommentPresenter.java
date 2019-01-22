package com.studio.saradey.aplicationvk.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.studio.saradey.aplicationvk.MyAplication;
import com.studio.saradey.aplicationvk.common.utils.VkListHelper;
import com.studio.saradey.aplicationvk.model.CommentItem;
import com.studio.saradey.aplicationvk.model.view.BaseViewModel;
import com.studio.saradey.aplicationvk.model.view.CommentFooterViewModel;
import com.studio.saradey.aplicationvk.model.view.OpenedPostHeaderViewModel;
import com.studio.saradey.aplicationvk.mvp.view.BaseFeedView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * @author jtgn on 15.08.2018
 */

@InjectViewState
public class OpenedCommentPresenter extends BaseFeedPresenter<BaseFeedView> {

    int id;

    public OpenedCommentPresenter() {
        MyAplication.getApplicationComponent().inject(this);

    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return createObservable();

    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return createObservable();
    }


    private Observable<BaseViewModel> createObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .retry(2) //Если вы вызываете функцию retry () без аргументов, она будет повторно подписывать бесконечное количество раз для
                .flatMap(commentItem -> {
                    List<BaseViewModel> list = new ArrayList<>();
                    List<BaseViewModel> forwardedList = new ArrayList<>();

                    list.add(new OpenedPostHeaderViewModel(commentItem));

                    list.addAll(VkListHelper.getAttachmentVhItems(commentItem.getAttachments()));

                    list.add(new CommentFooterViewModel(commentItem));

                    return Observable.fromIterable(list).concatWith(Observable.fromIterable(forwardedList));
                });
    }


    public Callable<CommentItem> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            CommentItem commentItem = realm.where(CommentItem.class).equalTo("id", id).findFirst();

            return realm.copyFromRealm(commentItem);
        };
    }

    public void setId(int id) {
        this.id = id;
    }

}
