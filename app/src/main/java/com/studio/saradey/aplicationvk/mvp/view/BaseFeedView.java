package com.studio.saradey.aplicationvk.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.studio.saradey.aplicationvk.model.view.BaseViewModel;

import java.util.List;

/**
 * @author jtgn on 11.08.2018
 */

//Этот интерфейс будет отвечать за отображение списков: стена, комментарии, обсуждения и тд.
public interface BaseFeedView extends MvpView {
    //показ и скрытие анимации загрузки(сверху), когда мы обновляем список(свайп вниз).
    void showRefreshing();

    //скрытие анимации загрузки
    void hideRefreshing();

    // показ и скрытие анимации загрузки(в центре), когда список появляется на экране(запуск приложения
// с последующим отображением стены, открытие комментариев, переход по вкладкам дровера).
    void showListProgress();

    //скрытие анимации загрузки
    void hideListProgress();


    //отображение ошибки.
    void showError(String message);

    //замена существующего списка новым.
    void setItems(List<BaseViewModel> items);

    // добавление новых элементов списка в конец существующего.
    void addItems(List<BaseViewModel> items);
}
