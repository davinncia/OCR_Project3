package com.davincia.lucasmahe.entrevoisins_pj3.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_NUMBER = 2;

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {

        //TODO: is this good practice ? Shouldn't we check if fragment already created ?
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
