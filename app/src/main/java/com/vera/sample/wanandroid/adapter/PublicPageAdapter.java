package com.vera.sample.wanandroid.adapter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * File descripition: 公众号页的适配器
 *
 * @author: Vera
 * @date: 2019/2/25
 */

public class PublicPageAdapter extends FragmentPagerAdapter {
    private List<String> mTitles = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();

    public PublicPageAdapter(FragmentManager fm,List<Fragment> mFragments,List<String> mTitles) {
        super(fm);
        this.mTitles = mTitles;
        this.mFragments = mFragments;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}
