package com.studio.saradey.aplicationvk.di.module;

import android.content.Context;

import com.studio.saradey.aplicationvk.rest.RestClient;
import com.studio.saradey.aplicationvk.rest.api.AccountApi;
import com.studio.saradey.aplicationvk.rest.api.BoardApi;
import com.studio.saradey.aplicationvk.rest.api.GroupsApi;
import com.studio.saradey.aplicationvk.rest.api.UsersApi;
import com.studio.saradey.aplicationvk.rest.api.VideoApi;
import com.studio.saradey.aplicationvk.rest.api.WallApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author jtgn on 30.07.2018
 */

@Module
public class RestModule {

    private RestClient restClient;
    private Context context;


    public RestModule() {
        restClient = new RestClient();
    }


    @Singleton
    @Provides
    RestClient getRestClient() {
        return restClient;
    }


    //для инилизации нашего вызова
    @Singleton
    @Provides
    public WallApi provideWallApi() {
        return restClient.createService(WallApi.class);
    }



    @Provides
    @Singleton
    public UsersApi provideUsersApi() {
        return restClient.createService(UsersApi.class);
    }


    @Provides
    @Singleton
    public GroupsApi provideGroupsApi() {
        return restClient.createService(GroupsApi.class);
    }


    @Provides
    @Singleton
    public BoardApi provideBoardApi() {
        return restClient.createService(BoardApi.class);
    }


    @Provides
    @Singleton
    public VideoApi provideVideoApi() {
        return restClient.createService(VideoApi.class);
    }


    @Provides
    @Singleton
    public AccountApi provideAccountApi() {
        return restClient.createService(AccountApi.class);
    }
}
