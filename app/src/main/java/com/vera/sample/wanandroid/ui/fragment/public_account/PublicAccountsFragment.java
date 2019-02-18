package com.vera.sample.wanandroid.ui.fragment.public_account;

import android.os.Bundle;

import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.app.Constants;
import com.vera.sample.wanandroid.base.BaseFragment;
import com.vera.sample.wanandroid.bean.PublicAcccountBean;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * File descripition: 公众号fragment
 *
 * @author: Vera
 * @date: 2019/2/13
 */

public class PublicAccountsFragment extends BaseFragment<PublicAccountsPresenter> implements PublicAccountsView {

    @BindView(R.id.frag_public_accout_rv)
    RecyclerView recyclerView;

    List<PublicAcccountBean> publicAcccountBeanList = new ArrayList<>();

    public static PublicAccountsFragment getInstance(String param1, String param2) {
        PublicAccountsFragment fragment = new PublicAccountsFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected PublicAccountsPresenter createPresenter() {
        return new PublicAccountsPresenter(this,getActivity());
    }

//    @Override
//    protected PublicAccountsPresenter createPresenter() {
//        return new PublicAccountsPresenter();
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_official;
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        mPresenter.initAdapter(recyclerView);
//        mPresenter.getPublicList();
        mPresenter.getList();
    }
}
