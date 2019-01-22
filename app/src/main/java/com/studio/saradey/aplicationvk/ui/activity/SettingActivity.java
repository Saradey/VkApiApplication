package com.studio.saradey.aplicationvk.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.studio.saradey.aplicationvk.R;
import com.studio.saradey.aplicationvk.ui.fragment.MyPreferencesFragment;

/**
 * @author jtgn on 15.08.2018
 */

public class SettingActivity extends BaseActivity {

    @Override
    protected int getMainContentLayout() {
        return R.layout.activity_setting;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(R.id.main_wrapper, new MyPreferencesFragment())
                .commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("Настройки");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFab.hide();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
