package com.example.projectux;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    private int numberTabs;

    public PageAdapter(@NonNull FragmentManager fm, int numberTabs) {
        super(fm);

        this.numberTabs = numberTabs;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new InformationTabFragment();

            case 1:
                return new MapTabFragment();
        }

        return null;

    }

    @Override
    public int getCount() {
        return numberTabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
