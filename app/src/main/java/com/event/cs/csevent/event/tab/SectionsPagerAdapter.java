package com.event.cs.csevent.event.tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private int tabCount;

    public SectionsPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                OneToOneFragment oneToOneFragment = new OneToOneFragment();
                return oneToOneFragment;
            case 1:
                TwoToOneFragment twoToOneFragment = new TwoToOneFragment();
                return twoToOneFragment;
            case 2:
                ThreeToOneFragment threeToOneFragment = new ThreeToOneFragment();
                return threeToOneFragment;
            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
