package com.vera.sample.wanandroid.ui.fragment.knowledge;

import android.os.Bundle;

import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.app.Constants;
import com.vera.sample.wanandroid.base.BaseFragment;
import com.vera.sample.wanandroid.mvp.BasePresenter;

/**
 * File descripition: 知识体系fragment
 *
 * @author: Vera
 * @date: 2019/2/13
 */

public class KnowledgeHistoryFragment extends BaseFragment {

    public static KnowledgeHistoryFragment getInstance(String param1, String param2) {
        KnowledgeHistoryFragment fragment = new KnowledgeHistoryFragment();
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
        return  R.layout.fragment_knowledeg;
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }
}
