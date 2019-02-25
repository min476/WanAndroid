package com.vera.sample.wanandroid.ui.fragment.public_account;

import android.os.Bundle;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.flyco.tablayout.SlidingTabLayout;
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.header.StoreHouseHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.impl.RefreshHeaderWrapper;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.adapter.PublicPageAdapter;
import com.vera.sample.wanandroid.app.Constants;
import com.vera.sample.wanandroid.app.MyApplication;
import com.vera.sample.wanandroid.base.BaseFragment;
import com.vera.sample.wanandroid.bean.PublicAcccountBean;
import com.vera.sample.wanandroid.ui.fragment.public_account.classfy.PublicClassfyFragment;
import com.vera.sample.wanandroid.utils.CommonUtils;
import com.vera.sample.wanandroid.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
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
     * @param publicAccountTitles
     */
    @Override
    public void setPublicAccountTab(List<String> publicAccountTitles) {
        for (String title : publicAccountTitles) {
            mFragments.add(PublicClassfyFragment.getInstance(title));
        }

        publicPageAdapter = new PublicPageAdapter(getFragmentManager(),mFragments,publicAccountTitles);
        publicAccountVp.setAdapter(publicPageAdapter);

        // 设置 ViewPager
        slidingTabLayout.setViewPager(publicAccountVp);

    }



}
