package com.studio.saradey.aplicationvk.model;

import com.vk.sdk.api.model.Identifiable;

/**
 * @author jtgn on 08.08.2018
 */


//для получение полного имени и фото
public interface Owner extends Identifiable {

    String getFullName();

    String getPhoto();

}
