package com.vera.sample.wanandroid.ui.fragment.official;

import android.os.Bundle;

import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.app.Constants;
import com.vera.sample.wanandroid.base.BaseFragment;
import com.vera.sample.wanandroid.mvp.BasePresenter;

/**
 * File descripition: 公众号fragment
 *
 * @author: Vera
 * @date: 2019/2/13
 */

public class OfficialAccountsFragment extends BaseFragment {

    public static OfficialAccountsFragment getInstance(String param1, String param2) {
        OfficialAccountsFragment fragment = new OfficialAccountsFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_official;
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }
}
