package com.studio.saradey.aplicationvk.common.manager;


import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;

import com.studio.saradey.aplicationvk.ui.activity.BaseActivity;
import com.studio.saradey.aplicationvk.ui.fragment.BaseFragment;

import java.util.Stack;

/**
 * @author jtgn on 29.07.2018
 */

//костомный менеджер фрагментов
public class MyFragmentManager {

    //у нас в стеки всегда должен оставаться один фрагмент
    private static final int EMPTY_FRAGMENT_STACK_SIZE = 1;


    //стек для фрагментов
    private Stack<BaseFragment> mFragmentStack = new Stack<>();

    //текущий фрагмент
    private BaseFragment mCurrentFragment;


    //метод установки корневого фрагмента, в методе будет отображаться содержимое окна
    //будет меняться видимость тул бара и кнопки флотинг экшен баттон
    //@IdRes This will accept resource id
    public void setFragment(BaseActivity activity, BaseFragment baseFragment, @IdRes int containerId) {
        if (activity != null && !activity.isFinishing() && !isAlreadyContains(baseFragment)) {
            FragmentTransaction transaction = createFragmentTransaction(activity, baseFragment, false);
            transaction.replace(containerId, baseFragment);
            commitAddTransaction(activity, baseFragment, transaction, false);
        }
    }


    //метод который будет добавлять фрагменты по верх корневого
    //в них у нас будут открываться окна из меню навигации
    public void addFragment(BaseActivity activity, BaseFragment fragment, @IdRes int containerId) {
        if (activity != null && !activity.isFinishing() && !isAlreadyContains(fragment)) {
            FragmentTransaction fragmentTransaction = createFragmentTransaction(activity, fragment, true);
            fragmentTransaction.add(containerId, fragment);
            commitAddTransaction(activity, fragment, fragmentTransaction, true);
        }
    }


    //создаем FragmentTransaction
    private FragmentTransaction createFragmentTransaction(BaseActivity baseActivity,
                                                          BaseFragment baseFragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = baseActivity.getSupportFragmentManager().beginTransaction();

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(baseFragment.getTag());
        }

        return fragmentTransaction;
    }



    private void commitAddTransaction(BaseActivity baseActivity,
                                      BaseFragment baseFragment,
                                      FragmentTransaction fragmentTransaction,
                                      boolean addToBackStack) {

        if (fragmentTransaction != null) {
            mCurrentFragment = baseFragment;

            if (!addToBackStack)
                mFragmentStack = new Stack<>();

            mFragmentStack.add(baseFragment);
            commitTransaction(baseActivity, fragmentTransaction);
        }
    }


    //метод удаления текущего фрагмента
    public boolean removeCurrentFragment(BaseActivity activity) {
        return removeFragment(activity, mCurrentFragment);
    }


    //мето для удаления фрагмента
    public boolean removeFragment(BaseActivity activity, BaseFragment fragment) {
        //что бы мы случайно не удалили корневой фрагмент
        boolean canRemove = fragment != null && mFragmentStack.size() > EMPTY_FRAGMENT_STACK_SIZE;

        if (canRemove) {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

            mFragmentStack.pop();
            mCurrentFragment = mFragmentStack.lastElement();

            transaction.remove(fragment);
            commitTransaction(activity, transaction);  //коммитим транзакцию
        }

        return canRemove;
    }


    //коммитит в любом случае
    private void commitTransaction(BaseActivity baseActivity, FragmentTransaction transaction) {
        transaction.commit();

        baseActivity.fragmentOnScreen(mCurrentFragment);
    }


    //проверим, существует ли в секи, текущий фрагмент, проверка по имени класса
    public boolean isAlreadyContains(BaseFragment baseFragment) {
        if (baseFragment == null) {
            return false;
        }

        return mCurrentFragment != null
                && mCurrentFragment.getClass().getName().equals(
                baseFragment.getClass().getName());
    }


}
