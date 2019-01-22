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

public class InfoContactsViewModel extends BaseViewModel {
    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoContacts;
    }

    @Override
    public InfoContactsViewHolder onCreateViewHolder(View view) {
        return new InfoContactsViewHolder(view);
    }


    static class InfoContactsViewHolder extends BaseViewHolder<InfoContactsViewModel> {
        @BindView(R.id.rv_contacts)
        RelativeLayout rvContacts;

        public InfoContactsViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(InfoContactsViewModel infoContactsViewModel) {
        }

        @Override
        public void unbindViewHolder() {
        }
    }
}
