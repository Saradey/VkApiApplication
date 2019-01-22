package com.studio.saradey.aplicationvk.ui.holder.attachment;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.studio.saradey.aplicationvk.MyAplication;
import com.studio.saradey.aplicationvk.R;
import com.studio.saradey.aplicationvk.common.manager.MyFragmentManager;
import com.studio.saradey.aplicationvk.model.view.attachment.ImageAttachmentViewModel;
import com.studio.saradey.aplicationvk.ui.activity.BaseActivity;
import com.studio.saradey.aplicationvk.ui.fragment.ImageFragment;
import com.studio.saradey.aplicationvk.ui.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author jtgn on 14.08.2018
 */


public class ImageAttachmentHolder extends BaseViewHolder<ImageAttachmentViewModel> {


    @BindView(R.id.iv_attachment_image)
    public ImageView image;

    @Inject
    MyFragmentManager mFragmentManager;


    public ImageAttachmentHolder(View itemView) {
        super(itemView);

        MyAplication.getApplicationComponent().inject(this);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(ImageAttachmentViewModel imageAttachmentViewModel) {

        if (imageAttachmentViewModel.needClick) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFragmentManager.addFragment((BaseActivity) itemView.getContext(),
                            ImageFragment.newInstance(imageAttachmentViewModel.getPhotoUrl()), R.id.main_wrapper);
                }
            });
        }

        Glide.with(itemView.getContext()).load(imageAttachmentViewModel.getPhotoUrl()).into(image);


    }

    @Override
    public void unbindViewHolder() {

        itemView.setOnClickListener(null);
        image.setImageBitmap(null);
    }

}
