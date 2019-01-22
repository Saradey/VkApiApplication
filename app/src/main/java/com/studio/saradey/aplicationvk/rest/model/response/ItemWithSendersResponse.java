package com.studio.saradey.aplicationvk.rest.model.response;

import com.studio.saradey.aplicationvk.model.Group;
import com.studio.saradey.aplicationvk.model.Owner;
import com.studio.saradey.aplicationvk.model.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jtgn on 08.08.2018
 */


//он нужен для того что бы парсить не только item но такие поля как profile и group
public class ItemWithSendersResponse<T> extends BaseItemResponse<T> {

    //списки профелей групп
    private List<Profile> profiles = new ArrayList<>();
    private List<Group> groups = new ArrayList<>();

    private List<Profile> getProfiles() {
        return profiles;
    }

    private List<Group> getGroups() {
        return groups;
    }


    //ДЛЯ ПОЛУЧЕНИЕ ССПИСКА ОТПРАВИТЕЛЕЙ
    private List<Owner> getAllSenders() {
        List<Owner> all = new ArrayList<>();
        all.addAll(getProfiles());
        all.addAll(getGroups());
        return all;
    }


    //получение конкретного отправителя
    public Owner getSender(int id) {
        for (Owner owner : getAllSenders()) {
            if (owner.getId() == Math.abs(id)) {
                return owner;
            }
        }
        return null;
    }


}
