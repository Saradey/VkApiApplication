package com.studio.saradey.aplicationvk.model.view.couter;

import com.studio.saradey.aplicationvk.model.Reposts;

/**
 * @author jtgn on 08.08.2018
 */


public class RepostCounterViewModel extends CounterViewModel {

    private Reposts mReposts;

    public RepostCounterViewModel(Reposts reposts) {
        super(reposts.getCount());

        this.mReposts = reposts;
        if (mReposts.getUserReposted() == 1) {
            setAccentColor();
        }
    }

    public Reposts getReposts() {
        return mReposts;
    }
}
