package com.snippet.jseisson.animatedcircularpager;

import android.support.v4.view.PagerAdapter;

import java.util.List;

public abstract class AnimatedCircularPagerAdapter<L extends List> extends PagerAdapter {

    protected L list;

    public AnimatedCircularPagerAdapter(L list) {
        this.list = prepareListForCircularPager(list);
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    // This method duplicate the first item of list and place it to the last position to allow a fake circular
    private L prepareListForCircularPager(L list) {
        if (list != null) {
            list.add(list.get(0));
            return list;
        } else {
            return null;
        }
    }

    public void setList(L list) {
        this.list = prepareListForCircularPager(list);
        notifyDataSetChanged();
    }
}
