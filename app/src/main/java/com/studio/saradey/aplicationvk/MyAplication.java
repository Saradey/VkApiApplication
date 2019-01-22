package com.studio.saradey.aplicationvk;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.studio.saradey.aplicationvk.di.component.ApplicationComponent;
import com.studio.saradey.aplicationvk.di.component.DaggerApplicationComponent;
import com.studio.saradey.aplicationvk.di.module.ApplicationModule;
import com.studio.saradey.aplicationvk.fcm.MyPreferencesManager;
import com.vk.sdk.VKSdk;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * @author jtgn on 28.07.2018
 */


public class MyAplication extends Application {

    //
    //очень удобно здесь хранить, так как многим молдулям нужен контект, а он уже здесь проиницилизирован
    private static ApplicationComponent sApplicationComponent;


    public static ApplicationComponent getApplicationComponent() {
        return sApplicationComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        //иницилизация вк сервиса
        VKSdk.initialize(this);
        initApplicationComponent(); //иницилизация даггер компонетнта


        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);


        ///инициализацию DrawerImageLoader
        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder, String tag) {
                Glide.with(imageView.getContext()).load(uri).into(imageView);
            }
        }); //Теперь дровер умеет загружать изображения из сети.


        MyPreferencesManager.getInstance().init(this);
    }


    //иницилизация даггер компонетнта
    private void initApplicationComponent() {
        sApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }


}
