package com.vera.sample.wanandroid.app;


import com.vera.sample.wanandroid.prefrs.PreferenceHelper;

import java.util.List;

/**
 * @author quchao
 * @date 2017/11/27
 */

public class DataManager implements PreferenceHelper  {

    private PreferenceHelper mPreferenceHelper;

    public DataManager(){}

    public DataManager(PreferenceHelper preferencesHelper) {
        mPreferenceHelper = preferencesHelper;
    }

    @Override
    public void setCurrentPage(int position) {
        mPreferenceHelper.setCurrentPage(position);
    }

    @Override
    public int getCurrentPage() {
        return mPreferenceHelper.getCurrentPage();
    }

    @Override
    public void setProjectCurrentPage(int position) {
        mPreferenceHelper.setProjectCurrentPage(position);
    }

    @Override
    public int getProjectCurrentPage() {
        return mPreferenceHelper.getProjectCurrentPage();
    }

}
