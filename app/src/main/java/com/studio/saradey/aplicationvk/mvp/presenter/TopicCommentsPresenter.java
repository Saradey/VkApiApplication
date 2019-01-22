package com.studio.saradey.aplicationvk.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.studio.saradey.aplicationvk.MyAplication;
import com.studio.saradey.aplicationvk.common.utils.VkListHelper;
import com.studio.saradey.aplicationvk.model.CommentItem;
import com.studio.saradey.aplicationvk.model.Place;
import com.studio.saradey.aplicationvk.model.view.BaseViewModel;
import com.studio.saradey.aplicationvk.model.view.CommentBodyViewModel;
import com.studio.saradey.aplicationvk.model.view.CommentFooterViewModel;
import com.studio.saradey.aplicationvk.model.view.CommentHeaderViewModel;
import com.studio.saradey.aplicationvk.mvp.view.BaseFeedView;
import com.studio.saradey.aplicationvk.rest.api.BoardApi;
import com.studio.saradey.aplicationvk.rest.model.request.BoardGetCommentsRequestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * @author jtgn on 15.08.2018
 */


@InjectViewState
public class TopicCommentsPresenter extends BaseFeedPresenter<BaseFeedView> {
    @Inject
    BoardApi mBoardApi;
    private Place mPlace;


    public TopicCommentsPresenter() {
        MyAplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mBoardApi.getComments(new BoardGetCommentsRequestModel(
                Integer.parseInt(mPlace.getOwnerId()), Integer.parseInt(mPlace.getPostId()), offset).toMap())
                .flatMap(full -> Observable.fromIterable(VkListHelper.getCommentsList(full.response, true)))
                .doOnNext(commentItem -> commentItem.setPlace(mPlace))
                .doOnNext(this::saveToDb)
                .flatMap(commentItem -> Observable.fromIterable(parsePojoModel(commentItem)));
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .filter(commentItem -> commentItem.getPlace().equals(this.mPlace) && commentItem.isFromTopic)
                .flatMap(commentItem -> Observable.fromIterable(parsePojoModel(commentItem)));
    }


    private List<BaseViewModel> parsePojoModel(CommentItem commentItem) {
        List<BaseViewModel> baseItems = new ArrayList<>();
        baseItems.add(new CommentHeaderViewModel(commentItem));
        baseItems.add(new CommentBodyViewModel(commentItem));
        baseItems.add(new CommentFooterViewModel(commentItem));
        return baseItems;
    }


    public Callable<List<CommentItem>> getListFromRealmCallable() {
        return () -> {
            String[] sortFields = {"id"};
            Sort[] sortOrder = {Sort.ASCENDING};

            Realm realm = Realm.getDefaultInstance();

            RealmResults<CommentItem> realmResults = realm.where(CommentItem.class)
                    .findAll();
            realmResults.sort(sortFields, sortOrder);

            return realm.copyFromRealm(realmResults);
        };
    }


    public void setPlace(Place place) {
        this.mPlace = place;
    }
}
