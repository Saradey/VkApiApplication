package com.studio.saradey.aplicationvk.ui.fragment;

/**
 * @author jtgn on 15.08.2018
 */

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.studio.saradey.aplicationvk.R;


public class MyPreferencesFragment extends PreferenceFragment {


    public MyPreferencesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

    }


}
