package com.example.albin.forgetmenot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by albin on 13/11/16.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNUmbOfTabsumb) {
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mNUmbOfTabsumb;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            Tab1 tab1 = new Tab1();
            return tab1;
        } else {
            Tab2 tab2 = new Tab2();
            return tab2;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return Titles[position];
    }

    @Override
    public int getCount() {
        return NumbOfTabs;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
