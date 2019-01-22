package com.studio.saradey.aplicationvk.model.view;

import android.view.View;
import android.widget.TextView;

import com.studio.saradey.aplicationvk.MyAplication;
import com.studio.saradey.aplicationvk.R;
import com.studio.saradey.aplicationvk.common.manager.MyFragmentManager;
import com.studio.saradey.aplicationvk.model.Place;
import com.studio.saradey.aplicationvk.model.Topic;
import com.studio.saradey.aplicationvk.ui.activity.BaseActivity;
import com.studio.saradey.aplicationvk.ui.fragment.TopicCommentsFragment;
import com.studio.saradey.aplicationvk.ui.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author jtgn on 13.08.2018
 */


// списке обсуждений открываются сообщения в этом топике,
// каждое сообщение можно раскрыть в отдельном окне.
public class TopicViewModel extends BaseViewModel {
    private int mId;
    private int mGroupId;
    private String mTitle;

    private String mCommentsCount;

    public TopicViewModel() {

    }

    public TopicViewModel(Topic topic) {
        this.mId = topic.getId();
        this.mGroupId = topic.getGroupId();

        this.mTitle = topic.getTitle();

        this.mCommentsCount = topic.getComments() + " сообщений";
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.Topic;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new TopicViewHolder(view);
    }

    public int getId() {
        return mId;
    }

    public int getGroupId() {
        return mGroupId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getCommentsCount() {
        return mCommentsCount;
    }


    public static class TopicViewHolder extends BaseViewHolder<TopicViewModel> {

        @Inject
        MyFragmentManager mFragmentManager;

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_comments_count)
        TextView tvCommentsCount;


        public TopicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            MyAplication.getApplicationComponent().inject(this);
        }

        @Override
        public void bindViewHolder(TopicViewModel topicViewModel) {
            tvTitle.setText(topicViewModel.getTitle());
            tvCommentsCount.setText(topicViewModel.getCommentsCount());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFragmentManager.addFragment((BaseActivity) view.getContext(),
                            TopicCommentsFragment.newInstance(new Place(String.valueOf(topicViewModel.getGroupId()), String.valueOf(topicViewModel.getId()))),
                            R.id.main_wrapper);
                }
            });
        }

        @Override
        public void unbindViewHolder() {
            tvTitle.setText(null);
            tvCommentsCount.setText(null);
        }
    }
}
