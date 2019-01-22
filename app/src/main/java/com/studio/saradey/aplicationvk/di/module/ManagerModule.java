package com.studio.saradey.aplicationvk.di.module;

import com.studio.saradey.aplicationvk.common.manager.MyFragmentManager;
import com.studio.saradey.aplicationvk.common.manager.NetworkManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author jtgn on 30.07.2018
 */

//он будет предоствавлять менеджеров, к примеру фрагмент менеджера
@Module
public class ManagerModule {


    @Singleton
    @Provides
    MyFragmentManager provideMyFragmentManager() {
        return new MyFragmentManager();
    }


    @Provides
    @Singleton
    NetworkManager provideNetworkManager() {
        return new NetworkManager();
    }


}
