package com.studio.saradey.aplicationvk.model.view;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studio.saradey.aplicationvk.R;
import com.studio.saradey.aplicationvk.ui.holder.BaseViewHolder;

/**
 * @author jtgn on 01.08.2018
 */


//мы получаем ответ с сервера и парсим его в поджо модели, на уровне макета нам нужны не все данные с этой модел
//а некоторые данные нуждаются в обработке, для того что бы не тратить ресурсы зря и не перегружать
//onBindView обработкой и преобразованием данных, будем использовать вью модель
public abstract class BaseViewModel {


    public BaseViewHolder createViewHolder(ViewGroup parent) {
        return onCreateViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(getType().getValue(), parent, false));
    }


    //нужен для того что бы переложить ответсвенность за создания конкретного BaseViewHolder на дочерние классы
    protected abstract BaseViewHolder onCreateViewHolder(View view);


    //мето для того что бы отличать модели разного типа, а также для инфлейта необходимого макета
    public abstract LayoutTypes getType();


    //который по умолчанию будет возвращать false. Теперь нам остается просто
    // переопределить этот метод в тех моделях, которые мы не будем считать реальными.
    public boolean isItemDecorator() {
        return false;
    }

    //содердит в себу тип лейаута и ссылки на него
    public enum LayoutTypes {
        NewsFeedItemHeader(R.layout.items_news_header),
        NewsFeedItemBody(R.layout.item_news_body),
        NewsFeedItemFooter(R.layout.item_news_footer),
        Member(R.layout.item_member),
        Topic(R.layout.item_topic),
        InfoStatus(R.layout.item_info_status),
        InfoContacts(R.layout.item_info_contacts),
        InfoLinks(R.layout.item_info_links),
        AttachmentAudio(R.layout.item_attachment_audio),
        AttachmentDoc(R.layout.item_attachment_doc),
        AttachmentDocImage(R.layout.item_attachment_doc_image),
        AttachmentImage(R.layout.item_attachment_image),
        AttachmentLink(R.layout.item_attachment_link),
        AttachmentLinkExternal(R.layout.item_attachment_link_external),
        AttachmentPage(R.layout.item_attachment_page),
        AttachmentVideo(R.layout.item_attachment_video),
        OpenedPostHeader(R.layout.item_opened_post_header),
        OpenedPostRepostHeader(R.layout.item_opened_post_repost_header),
        CommentHeader(R.layout.item_comment_header),
        CommentBody(R.layout.item_comment_body),
        CommentFooter(R.layout.item_comment_footer),
        CreatePostText(R.layout.item_create_post_text);

        private final int id;

        LayoutTypes(int id) {
            this.id = id;
        }

        //возвращаем айди ресурса лейаута
        @LayoutRes
        public int getValue() {
            return id;
        }

    }


}
