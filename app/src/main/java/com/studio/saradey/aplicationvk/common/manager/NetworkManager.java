package com.studio.saradey.aplicationvk.common.manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.studio.saradey.aplicationvk.MyAplication;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.internal.Util;

/**
 * @author jtgn on 11.08.2018
 */


public class NetworkManager {

    //С помощью этого класса будем проверять, имеет ли
    // устройство доступ в интернет и доступен ли сервер vk api.

    private static final String TAG = "NetworkManager";

    @Inject
    Context mContext;

    public NetworkManager() {
        MyAplication.getApplicationComponent().inject(this);
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return ((networkInfo != null && networkInfo.isConnected()) || Util.isEmulator());
    }


    public Callable<Boolean> isVkReachableCallable() {
        return () -> {
            try {
                if (!isOnline()) {
                    return false;
                }

                URL url = new URL("https://api.vk.com");
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setConnectTimeout(2000);
                urlc.connect();

                return true;

            } catch (Exception e) {
                return false;
            }
        };
    }


    public Observable<Boolean> getNetworkObservable() {
        return Observable.fromCallable(isVkReachableCallable());
    }

}
