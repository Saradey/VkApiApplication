package com.studio.saradey.aplicationvk.common.manager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * @author jtgn on 11.08.2018
 */

//ПАГИНАЦИЯ
//Этот класс имеет доступ к вью списка и будет проверять, находится ли список
// в той позиции, в которой нам нужно подгружать следующие элементы.
//для того что бы подгружать нужное количество элементов, нам нужно знать
//сколько элементов находиться в адапторе
//так как мы делим элементы на три группы, количество будет отличаться от реального
public class MyLinearLayoutManager extends LinearLayoutManager {
    public MyLinearLayoutManager(Context context) {
        super(context);
    }

    public MyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public MyLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public boolean isOnNextPagePosition() {
        int visibleItemCount = getChildCount();
        int totalItemCount = getItemCount();
        int pastVisibleItems = findFirstVisibleItemPosition();

        return (visibleItemCount + pastVisibleItems) >= totalItemCount / 2;
    }
}
