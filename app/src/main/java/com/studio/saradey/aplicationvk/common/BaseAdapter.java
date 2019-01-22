package com.studio.saradey.aplicationvk.common;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.ViewGroup;

import com.studio.saradey.aplicationvk.model.view.BaseViewModel;
import com.studio.saradey.aplicationvk.ui.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jtgn on 01.08.2018
 */


public class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder<BaseViewModel>> {


    private List<BaseViewModel> list = new ArrayList<>();
    private ArrayMap<Integer, BaseViewModel> mTypeInstances = new ArrayMap<>();


    //для добавления элементов, создоваемого сообщения
    public void insertItem(BaseViewModel newItem) {
        registerTypeInstance(newItem);

        list.add(newItem);
        notifyItemInserted(getItemCount() - 1);
    }



    //создаем нужный нам вью холдер
    @NonNull
    @Override
    public BaseViewHolder<BaseViewModel> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return mTypeInstances.get(viewType).createViewHolder(parent);
    }


    //биндим наш холдер
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<BaseViewModel> holder, int position) {
        holder.bindViewHolder(getItem(position));
    }


    //я так понимаю, для того что бы не нагружать holder
    @Override
    public void onViewRecycled(@NonNull BaseViewHolder<BaseViewModel> holder) {
        super.onViewRecycled(holder);
        holder.unbindViewHolder();
    }


    //возвращаем тип item
    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType().getValue();
    }


    //возвращаем размер списка
    @Override
    public int getItemCount() {
        return list.size();
    }


    //берем наши айтамы из списка
    private BaseViewModel getItem(int position) {
        return list.get(position);
    }


    //метод добавления типа, при каждом добавление нам нужно регестрировать тип лейаута
    public void registerTypeInstance(BaseViewModel item) {
        if (!mTypeInstances.containsKey(item.getType().getValue()))
            mTypeInstances.put(item.getType().getValue(), item);
    }


    //метол добавления элементов в список
    //? extends BaseViewModel для того что бы работало, то есть мы здесь указали, что мы можем передавать также
    //классы наследники BaseViewModel
    public void addItems(List<? extends BaseViewModel> newItems) {
        for (BaseViewModel it : newItems) {
            registerTypeInstance(it);
        }
        list.addAll(newItems);

        notifyDataSetChanged();
    }


    //Он будет перебирать все элементы списка,
    // проверять является ли элемент реальным и возвращать реальное количество элементов.
    public int getRealItemCount() {
        int count = 0;
        for (int i = 0; i < getItemCount(); i++) {
            if (!getItem(i).isItemDecorator()) {
                count += 1;
            }
        }
        return count;
    }


    //метод для замены элементов в списке
    public void setItems(List<BaseViewModel> items) {
        cleanList();
        addItems(items);
    }


    private void cleanList() {
        list.clear();
    }


}
