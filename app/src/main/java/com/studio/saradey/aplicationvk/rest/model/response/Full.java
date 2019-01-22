package com.studio.saradey.aplicationvk.rest.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author jtgn on 30.07.2018
 */

//корневой уровень пришедшего ответа
public class Full<T> {

    //класс отвечает за парсинг ответа
    @SerializedName("response")
    @Expose
    public T response;

}
