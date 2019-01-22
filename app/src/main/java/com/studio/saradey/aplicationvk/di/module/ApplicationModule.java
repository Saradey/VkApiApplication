package com.studio.saradey.aplicationvk.di.module;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author jtgn on 30.07.2018
 */


@Module
public class ApplicationModule {

    private Application mApplication;


    public ApplicationModule(Application application) {
        this.mApplication = application;
    }


    @Singleton  //объект в одном экземпляре
    @Provides   //для внедрение нужной зависимости
    public Context provideContext() {
        return mApplication;
    }


    //добавляем метод для внедрения шрифта TypeFace нужен для того что бы TextView понимал какой стиль шрифта ему использовать
    //теперь наш текст будет преобразован в иконки
    @Singleton
    @Provides
    Typeface provideGoogleTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "MaterialIcons-Regular.ttf");
    }

}
