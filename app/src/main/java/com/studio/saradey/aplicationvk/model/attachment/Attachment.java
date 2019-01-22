package com.studio.saradey.aplicationvk.model.attachment;

import com.vk.sdk.api.model.Identifiable;

/**
 * @author jtgn on 08.08.2018
 */

//Из-за особенностей структуры ответа, класс Attachment служит контейнером для самих аттачментов.
// В нем будут содержаться поля для каждого типа аттачмента.
public interface Attachment extends Identifiable {

    String getType();

}


