package com.studio.saradey.aplicationvk.model.view.couter;

import com.studio.saradey.aplicationvk.R;

/**
 * @author jtgn on 08.08.2018
 */


//этот общий класс будет содержать в себе цвета и количество для заполнения счетчика-view.
// Для каждого конкретного счетчика(лайк, комментарий, репост)
public class CounterViewModel {

    protected int mCount;
    protected int mIconColor = R.color.colorIconDis;
    protected int mTextColor = R.color.colorIconDis;

    public CounterViewModel(int count) {
        this.mCount = count;
        if (mCount > 0) {
            setDefaultColor();
        } else {
            setDisabledColor();
        }
    }

    private void setDisabledColor() {
        mIconColor = R.color.colorIconDis;
        mTextColor = R.color.colorIconDis;
    }

    private void setDefaultColor() {
        mIconColor = R.color.colorIcon;
        mTextColor = R.color.colorIcon;
    }

    protected void setAccentColor() {
        mIconColor = R.color.colorAccent;
        mTextColor = R.color.colorAccent;
    }

    public int getCount() {
        return mCount;
    }

    public int getIconColor() {
        return mIconColor;
    }

    public int getTextColor() {
        return mTextColor;
    }

}
