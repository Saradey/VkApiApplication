package com.studio.saradey.aplicationvk.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.studio.saradey.aplicationvk.model.view.BaseViewModel;

/**
 * @author jtgn on 01.08.2018
 */


//для других вью холдеров
//Item extends BaseViewModel это нужно для безопасности типа
//теперь BaseViewHolder сможет работать только с BaseViewModel
public abstract class BaseViewHolder<Item extends BaseViewModel> extends RecyclerView.ViewHolder {


    public BaseViewHolder(View itemView) {
        super(itemView);
    }


    //для заполнения макета, данными
    public abstract void bindViewHolder(Item item);

    //разгружать макет когда он не используется
    public abstract void unbindViewHolder();

}
