package com.studio.saradey.aplicationvk.ui.holder;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.studio.saradey.aplicationvk.MyAplication;
import com.studio.saradey.aplicationvk.R;
import com.studio.saradey.aplicationvk.common.manager.MyFragmentManager;
import com.studio.saradey.aplicationvk.common.utils.UiHelper;
import com.studio.saradey.aplicationvk.model.view.NewsItemBodyViewModel;
import com.studio.saradey.aplicationvk.ui.activity.BaseActivity;
import com.studio.saradey.aplicationvk.ui.fragment.OpenedPostFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author jtgn on 01.08.2018
 */

//это холдер в котором мы проводим иницилизацию вьюшек, он наследуется от базового холдера
//а тот в свою очередь имеет дженерик, дженерик это
public class NewsItemBodyHolder extends BaseViewHolder<NewsItemBodyViewModel> {

    //для того что бы сделать из R.string иконки
    @Inject
    protected Typeface mFontGoogle;

    @Inject
    MyFragmentManager myFragmentManager;

    @BindView(R.id.tv_text)
    TextView tvText;

    @BindView(R.id.tv_attachments)
    TextView tvAttachments;


    public NewsItemBodyHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        MyAplication.getApplicationComponent().inject(this);


        if (tvAttachments != null) {
            tvAttachments.setTypeface(mFontGoogle);
        }
    }

    @Override
    public void bindViewHolder(NewsItemBodyViewModel item) {
        tvText.setText(item.getText());
        tvAttachments.setText(item.getmAttachmentString());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFragmentManager.addFragment((BaseActivity) view.getContext(),
                        OpenedPostFragment.newInstance(item.getId()),
                        R.id.main_wrapper);

            }
        });
        UiHelper.getInstance().setUpTextViewWithVisibility(tvText, item.getText());
        UiHelper.getInstance().setUpTextViewWithVisibility(tvAttachments, item.getmAttachmentString());
    }

    @Override
    public void unbindViewHolder() {
        tvText.setText(null);
        tvAttachments.setText(null);
        itemView.setOnClickListener(null);
    }
}
