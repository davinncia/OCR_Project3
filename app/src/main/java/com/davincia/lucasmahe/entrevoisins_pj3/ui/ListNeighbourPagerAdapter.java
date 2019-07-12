package com.davincia.lucasmahe.entrevoisins_pj3.ui;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ListNeighbourPagerAdapter extends FragmentStatePagerAdapter {

    private final int PAGE_NUMBER = 2;

    public ListNeighbourPagerAdapter(FragmentManager fm) {

        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {

        if(position == 0) {
            return NeighbourFragment.newInstance();
        } else {
            return FavoritesFragment.newInstance();
        }
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }
}
