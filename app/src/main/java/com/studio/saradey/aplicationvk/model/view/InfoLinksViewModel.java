package com.studio.saradey.aplicationvk.model.view;

import android.view.View;
import android.widget.RelativeLayout;

import com.studio.saradey.aplicationvk.R;
import com.studio.saradey.aplicationvk.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author jtgn on 14.08.2018
 */

public class InfoLinksViewModel extends BaseViewModel {

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoLinks;
    }

    @Override
    public InfoLinksViewHolder onCreateViewHolder(View view) {
        return new InfoLinksViewHolder(view);
    }


    static class InfoLinksViewHolder extends BaseViewHolder<InfoLinksViewModel> {


        @BindView(R.id.rv_links)
        RelativeLayout rvLinks;

        public InfoLinksViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(InfoLinksViewModel infoLinksViewModel) {

        }

        @Override
        public void unbindViewHolder() {

        }
    }
}
