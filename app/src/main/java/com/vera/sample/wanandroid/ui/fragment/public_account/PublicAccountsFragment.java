package com.vera.sample.wanandroid.ui.fragment.public_account;

import android.os.Bundle;

import com.flyco.tablayout.SlidingTabLayout;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.adapter.PublicPageAdapter;
import com.vera.sample.wanandroid.app.Constants;
import com.vera.sample.wanandroid.base.BaseFragment;
import com.vera.sample.wanandroid.bean.publicaccount_bean.PublicAcccountBean;
import com.vera.sample.wanandroid.ui.fragment.public_account.classfy.PublicClassifyFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * File descripition: 公众号fragment
 *
 * @author: Vera
 * @date: 2019/2/13
 */

public class PublicAccountsFragment extends BaseFragment<PublicAccountsPresenter> implements PublicAccountsView {

//    @BindView(R.id.frag_public_accout_rv)
//    RecyclerView recyclerView;
//    @BindView(R.id.refreshLayout)
//    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.public_account_tl)
    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.public_account_vp)
    ViewPager publicAccountVp;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private PublicPageAdapter publicPageAdapter ;
    private List<String> publicAcccountTitleCacheList = new ArrayList<>();;



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
        return new PublicAccountsPresenter(this, getActivity());
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
//        mPresenter.initAdapter(recyclerView);
        mPresenter.getPublicList();

    }

    /**
     *  设置公众号分类tab
     * @param publicAccountData
     */
    @Override
    public void setPublicAccountTab(List<PublicAcccountBean> publicAccountData) {

        // 传递id 与公众号名称到子类frg
        for (int i = 0; i <publicAccountData.size() ; i++) {
            mFragments.add(PublicClassifyFragment.getInstance(publicAccountData.get(i).getName(),publicAccountData.get(i).getId()));
//            break;
        }

        // 公众号的分类tab
        for (int i = 0; i < publicAccountData.size(); i++) {
            publicAcccountTitleCacheList.add(publicAccountData.get(i).getName());
        }
        publicPageAdapter = new PublicPageAdapter(getFragmentManager(),mFragments,publicAcccountTitleCacheList);
        publicAccountVp.setAdapter(publicPageAdapter);

        // 设置 ViewPager
        slidingTabLayout.setViewPager(publicAccountVp);

    }



}
