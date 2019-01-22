package com.studio.saradey.aplicationvk.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.studio.saradey.aplicationvk.R;
import com.studio.saradey.aplicationvk.model.view.NewsItemHeaderViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author jtgn on 08.08.2018
 */


public class NewsItemHeaderHolder extends BaseViewHolder<NewsItemHeaderViewModel> {

    //переменные для аватара
    @BindView(R.id.civ_profile_image)
    CircleImageView civProfileImage;

    //отправителя репоста
    @BindView(R.id.tv_profile_name)
    TextView tvName;

    //для аватара
    @BindView(R.id.iv_reposted_icon)
    ImageView ivRepostedIcon;

    //имени пользователя
    @BindView(R.id.tv_reposted_profile_name)
    TextView tvRepostedProfileName;



    public NewsItemHeaderHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    @Override
    public void bindViewHolder(NewsItemHeaderViewModel item) {
        //у вью холдера оказывается свой контекст
        Context context = itemView.getContext();

        //подгружаем аватар
        Glide.with(context)
                .load(item.getProfilePhoto())
                .into(civProfileImage);
        tvName.setText(item.getProfileName());

        //прописываем имя отправителя
        if (item.isRepost()) {
            ivRepostedIcon.setVisibility(View.VISIBLE);
            tvRepostedProfileName.setText(item.getRepostProfileName());
        } else {
            //иначе скрываем иконку очищаем текст
            ivRepostedIcon.setVisibility(View.GONE);
            tvRepostedProfileName.setText(null);
        }

    }


    //очищаем все
    @Override
    public void unbindViewHolder() {
        civProfileImage.setImageBitmap(null);
        tvName.setText(null);
        tvRepostedProfileName.setText(null);

    }
}
