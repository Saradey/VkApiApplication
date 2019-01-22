package com.studio.saradey.aplicationvk;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;

/**
 * @author jtgn on 28.07.2018
 */


public class CurrentUser {

    //возвращаем токен пользователя
    public static String getAccessToken() {
        if (VKAccessToken.currentToken() == null) {
            return null;
        }
        return VKAccessToken.currentToken().accessToken;
    }

    //метод вовзращаем индетефикатор текущего пользователя, если токен получен
    public static String getId() {
        if (VKAccessToken.currentToken() == null) {
            return null;
        }
        return VKAccessToken.currentToken().userId;
    }

    //будет возвращать фолс если хтя бы одно из условий не верно, если пользователь авторизован
    //если пользовательский токен не равен нулу и если он не устарел
    public static boolean isAuthorized() {
        return VKSdk.isLoggedIn()
                && VKAccessToken.currentToken() != null
                && !VKAccessToken.currentToken().isExpired();
    }

}
