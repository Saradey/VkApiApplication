package com.studio.saradey.aplicationvk.ui.holder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.studio.saradey.aplicationvk.MyAplication;
import com.studio.saradey.aplicationvk.R;
import com.studio.saradey.aplicationvk.common.manager.MyFragmentManager;
import com.studio.saradey.aplicationvk.common.utils.Utils;
import com.studio.saradey.aplicationvk.common.utils.VkListHelper;
import com.studio.saradey.aplicationvk.model.Likes;
import com.studio.saradey.aplicationvk.model.Place;
import com.studio.saradey.aplicationvk.model.WallItem;
import com.studio.saradey.aplicationvk.model.view.NewsItemFooterViewModel;
import com.studio.saradey.aplicationvk.model.view.couter.CommentCounterViewModel;
import com.studio.saradey.aplicationvk.model.view.couter.LikeCounterViewModel;
import com.studio.saradey.aplicationvk.model.view.couter.RepostCounterViewModel;
import com.studio.saradey.aplicationvk.rest.api.LikeEventOnSubscribe;
import com.studio.saradey.aplicationvk.rest.api.WallApi;
import com.studio.saradey.aplicationvk.rest.model.request.WallGetByIdRequestModel;
import com.studio.saradey.aplicationvk.ui.activity.BaseActivity;
import com.studio.saradey.aplicationvk.ui.fragment.CommentsFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;

/**
 * @author jtgn on 11.08.2018
 */
public class NewsItemFooterHolder extends BaseViewHolder<NewsItemFooterViewModel> {

    @Inject
    Typeface mGoogleFontTypeface;

    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_likes_count)
    TextView tvLikesCount;
    @BindView(R.id.tv_likes_icon)
    TextView tvLikesIcon;
    @BindView(R.id.tv_comments_icon)
    TextView tvCommentIcon;
    @BindView(R.id.tv_comments_count)
    TextView tvCommentsCount;
    @BindView(R.id.tv_reposts_icon)
    TextView tvRepostIcon;
    @BindView(R.id.tv_reposts_count)
    TextView tvRepostsCount;
    public static final String POST = "post";
    @BindView(R.id.rl_comments)
    View rlComments;
    @BindView(R.id.rl_likes)
    View rlLikes;

    @Inject
    MyFragmentManager mFragmentManager;
    @Inject
    WallApi mWallApi;

    private Resources mResources;
    private Context mContext;


    public NewsItemFooterHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        MyAplication.getApplicationComponent().inject(this);

        mContext = itemView.getContext();
        mResources = mContext.getResources();

        tvLikesIcon.setTypeface(mGoogleFontTypeface);
        tvCommentIcon.setTypeface(mGoogleFontTypeface);
        tvRepostIcon.setTypeface(mGoogleFontTypeface);

    }


    @Override
    public void bindViewHolder(NewsItemFooterViewModel item) {
        tvDate.setText(Utils.parseDate(item.getDateLong(), mContext));  //установка даты

        bindLikes(item.getLikes());
        bindComments(item.getComments());
        bindReposts(item.getReposts());

        rlComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentManager.addFragment((BaseActivity) view.getContext(),
                        CommentsFragment.newInstance(new Place(String.valueOf(item.getOwnerId()), String.valueOf(item.getId()))),
                        R.id.main_wrapper);
            }
        });

        rlLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                like(item);
            }
        });
    }

    //установка лайков
    private void bindLikes(LikeCounterViewModel likes) {
        tvLikesCount.setText(String.valueOf(likes.getCount()));
        tvLikesCount.setTextColor(mResources.getColor(likes.getTextColor()));
        tvLikesIcon.setTextColor(mResources.getColor(likes.getIconColor()));

    }


    //установка комментариев
    private void bindComments(CommentCounterViewModel comments) {
        tvCommentsCount.setText(String.valueOf(comments.getCount()));
        tvCommentsCount.setTextColor(mResources.getColor(comments.getTextColor()));
        tvCommentIcon.setTextColor(mResources.getColor(comments.getIconColor()));
    }


    //установка репостов
    private void bindReposts(RepostCounterViewModel reposts) {
        tvRepostsCount.setText(String.valueOf(reposts.getCount()));
        tvRepostsCount.setTextColor(mResources.getColor(reposts.getTextColor()));
        tvRepostIcon.setTextColor(mResources.getColor(reposts.getIconColor()));

    }


    @Override
    public void unbindViewHolder() {
        tvDate.setText(null);
        tvLikesCount.setText(null);
        tvCommentsCount.setText(null);
        tvRepostsCount.setText(null);
    }


    //метод like, который вызывает likeObservable
    // и устанавливает или снимает лайк на основании информации от сервера, и метод getWallItemFromRealm.
    public void saveToDb(RealmObject item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(item));
    }

    public Observable<LikeCounterViewModel> likeObservable(int ownerId, int postId, Likes likes) {
        return Observable.create(new LikeEventOnSubscribe(POST, ownerId, postId, likes))

                .observeOn(Schedulers.io())
                .flatMap(count -> {

                    return mWallApi.getById(new WallGetByIdRequestModel(ownerId, postId).toMap());
                })
                .flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
                .doOnNext(this::saveToDb)
                .map(wallItem -> new LikeCounterViewModel(wallItem.getLikes()));
    }

    @SuppressLint("CheckResult")
    public void like(NewsItemFooterViewModel item) {
        WallItem wallItem = getWallItemFromRealm(item.getId());
        likeObservable(wallItem.getOwnerId(), wallItem.getId(), wallItem.getLikes())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(likes -> {
                    item.setLikes(likes);
                    bindLikes(likes);
                }, Throwable::printStackTrace);
    }


    public WallItem getWallItemFromRealm(int postId) {
        Realm realm = Realm.getDefaultInstance();
        WallItem wallItem = realm.where(WallItem.class)
                .equalTo("id", postId)
                .findFirst();

        return realm.copyFromRealm(wallItem);
    }


}
