package com.studio.saradey.aplicationvk.model.view.couter;

import com.studio.saradey.aplicationvk.model.Comments;

/**
 * @author jtgn on 08.08.2018
 */


public class CommentCounterViewModel extends CounterViewModel {

    private Comments mComments;

    public CommentCounterViewModel(Comments comments) {
        super(comments.getCount());

        this.mComments = comments;
    }

    public Comments getComments() {
        return mComments;
    }
}
