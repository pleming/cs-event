package com.event.cs.csevent.tab;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
    private OneToOneFragment oneToOneFragment;
    private TwoToOneFragment twoToOneFragment;
    private ThreeToOneFragment threeToOneFragment;
    private int tabCount;

    public SectionsPagerAdapter(FragmentManager fm, int tabCount, Context context) {
        super(fm);

        this.oneToOneFragment = new OneToOneFragment();
        this.twoToOneFragment = new TwoToOneFragment();
        this.threeToOneFragment = new ThreeToOneFragment();
        this.tabCount = tabCount;

        this.oneToOneFragment.setContext(context);
        this.twoToOneFragment.setContext(context);
        this.threeToOneFragment.setContext(context);
    }

    public OneToOneFragment getOneToOneFragment() {
        return oneToOneFragment;
    }

    public TwoToOneFragment getTwoToOneFragment() {
        return twoToOneFragment;
    }

    public ThreeToOneFragment getThreeToOneFragment() {
        return threeToOneFragment;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return oneToOneFragment;
            case 1:
                return twoToOneFragment;
            case 2:
                return threeToOneFragment;
            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "1+1";
            case 1:
                return "2+1";
            case 2:
                return "3+1";
            default:
                return super.getPageTitle(position);
        }
    }
}
