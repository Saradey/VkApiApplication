package com.studio.saradey.aplicationvk.ui.activity;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.studio.saradey.aplicationvk.MyAplication;
import com.studio.saradey.aplicationvk.R;
import com.studio.saradey.aplicationvk.consts.ApiConstants;
import com.studio.saradey.aplicationvk.model.Profile;
import com.studio.saradey.aplicationvk.mvp.presenter.MainPresenter;
import com.studio.saradey.aplicationvk.mvp.view.MainView;
import com.studio.saradey.aplicationvk.rest.api.AccountApi;
import com.studio.saradey.aplicationvk.rest.model.request.AccountRegisterDeviceRequest;
import com.studio.saradey.aplicationvk.ui.fragment.BaseFragment;
import com.studio.saradey.aplicationvk.ui.fragment.NewsFeedFragment;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements MainView {


    @InjectPresenter
    MainPresenter presenter;

    private Drawer mDrawer;

    private AccountHeader mAccountHeader;

    @Inject
    AccountApi mAccountApi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyAplication.getApplicationComponent().inject(this);

        presenter.chekAuth();
    }


    //создание Drawer
    public void setUpDrawer() {
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1)
                .withName(R.string.screen_name_news)
                .withIcon(GoogleMaterial.Icon.gmd_home);

        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2)
                .withName(R.string.screen_name_my_posts)
                .withIcon(GoogleMaterial.Icon.gmd_list);

        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3)
                .withName(R.string.screen_name_settings)
                .withIcon(GoogleMaterial.Icon.gmd_settings);

        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4)
                .withName(R.string.screen_name_members)
                .withIcon(GoogleMaterial.Icon.gmd_people);

        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5)
                .withName(R.string.screen_name_topics)
                .withIcon(GoogleMaterial.Icon.gmd_record_voice_over);

        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6)
                .withName(R.string.screen_name_info)
                .withIcon(GoogleMaterial.Icon.gmd_info);

        PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(7)
                .withName(R.string.screen_name_rules)
                .withIcon(GoogleMaterial.Icon.gmd_assignment);

        mAccountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .build();

        mDrawer = new DrawerBuilder()
                .withActivity(this) //указываем активити.
                .withToolbar(toolbar) // указываем тулбар.
                .withTranslucentStatusBar(true) //если true — статусбар становится прозрачным при открытии дровера.
                .withActionBarDrawerToggle(true) //добавляет для дровера иконку в тулбаре (в нашем случае — гамбургер)
                .withAccountHeader(mAccountHeader)
                .addDrawerItems(item1, item2, item3,
                        new SectionDrawerItem().withName("Группа"),
                        item4, item5, item6, item7)
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    presenter.drawerItemClick((int) drawerItem.getIdentifier());
                    return false;
                })
                .build();
    }


    //получаем макет этой активити
    @Override
    protected int getMainContentLayout() {
        return R.layout.activity_main;
    }


    //если мы еще не авторизованы
    @Override
    public void startSignIn() {
        VKSdk.login(this, ApiConstants.DEFAULT_LOGIN_SCOPE); //код для авторизации

        setUpDrawer();
        setContent(new NewsFeedFragment());
    }


    //если мы уже автризованы
    @Override
    public void signedId() {
        setUpDrawer();
        setContent(new NewsFeedFragment());
        //регистрируем устройство на сервере ВК как получатель push-сообщений
        mAccountApi.registerDevice(new AccountRegisterDeviceRequest(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID)).toMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }


    //показываем в хедаре конкретного юзера
    @Override
    public void showCurrentUser(Profile profile) {
        List<IProfile> profileDrawerItems = new ArrayList<>();
        profileDrawerItems.add(new ProfileDrawerItem().withName(profile.getFullName()).withEmail(VKAccessToken.currentToken().email)
                .withIcon(profile.getDisplayProfilePhoto()));
        profileDrawerItems.add(new ProfileSettingDrawerItem().withName(getString(R.string.screen_name_exit))
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    mAccountHeader.removeProfile(0);
                    mAccountHeader.removeProfile(0);
                    VKSdk.logout();
                    return false;
                }));
        mAccountHeader.setProfiles(profileDrawerItems);
    }


    //Создадим приложение, которое будет вызывать два разных Activity и получать от них результат. Как мы помним,
    // результат приходит в метод onActivityResult. И requestCode используется, чтобы отличать друг от друга
    // пришедшие результаты. А resultCode – позволяет определить успешно прошел вызов или нет.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data,
                new VKCallback<VKAccessToken>() {
                    @Override
                    public void onResult(VKAccessToken res) {
                        // Пользователь успешно авторизовался
                        presenter.chekAuth();

                    }

                    @Override
                    public void onError(VKError error) {
                        // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
                    }
                })) {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    //отображаем наш фрагмент
    @Override
    public void showFragmentFromDrawer(BaseFragment baseFragment) {
        setContent(baseFragment);
    }


    @Override
    public void startActivityFromDrawer(Class<?> act) {
        startActivity(new Intent(MainActivity.this, act));
    }


}
