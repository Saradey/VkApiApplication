package com.studio.saradey.aplicationvk.rest.model.response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jtgn on 30.07.2018
 */


public class BaseItemResponse<T> {

    //название переменных будут совпадать с названием ответа сервера
    public Integer count;
    public List<T> items = new ArrayList<>();


    public Integer getCount() {
        return count;
    }

    public List<T> getItems() {
        return items;
    }
}
