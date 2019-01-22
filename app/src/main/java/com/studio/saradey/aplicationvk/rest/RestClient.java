package com.studio.saradey.aplicationvk.rest;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author jtgn on 30.07.2018
 */


public class RestClient {

    //константа для юрл запросов к нашму апи
    private static final String VK_BASE_URL = "https://api.vk.com/method/";


    private Retrofit retrofit;



    public RestClient() {
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(VK_BASE_URL)
                .build();
    }


    //переменные иницилизации для запросов к апи
    public <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }


}
