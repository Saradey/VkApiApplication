package com.studio.saradey.aplicationvk.ui.holder.attachment;

import android.view.View;
import android.widget.TextView;

import com.studio.saradey.aplicationvk.R;
import com.studio.saradey.aplicationvk.model.view.attachment.AudioAttachmentViewModel;
import com.studio.saradey.aplicationvk.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author jtgn on 14.08.2018
 */

public class AudioAttachmentHolder extends BaseViewHolder<AudioAttachmentViewModel> {

    @BindView(R.id.tv_audio_name)
    public TextView name;

    @BindView(R.id.tv_audio_artist)
    public TextView artist;

    @BindView(R.id.tv_audio_duration)
    public TextView duration;


    public AudioAttachmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(AudioAttachmentViewModel audioAttachmentViewModel) {
        name.setText(audioAttachmentViewModel.getTitle());
        artist.setText(audioAttachmentViewModel.getArtist());

        duration.setText(audioAttachmentViewModel.getDuration());
    }

    @Override
    public void unbindViewHolder() {
        name.setText(null);
        artist.setText(null);

        duration.setText(null);
    }


}
