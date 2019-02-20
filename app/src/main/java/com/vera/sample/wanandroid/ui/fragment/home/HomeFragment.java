package com.vera.sample.wanandroid.ui.fragment.home;

import android.os.Bundle;

import com.lwj.widget.bannerview.BannerPagerAdapter;
import com.lzj.gallery.library.views.BannerViewPager;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.app.Constants;
import com.vera.sample.wanandroid.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * File descripition: 首页fragment
 *
 * @author: Vera
 * @date: 2019/2/13
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView {

    @BindView(R.id.banner_viewpager)
    BannerViewPager mViewpager;

    private List<String> urlList= new ArrayList<>();


    public static HomeFragment getInstance(boolean param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putBoolean(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this,getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        mPresenter.getBannerList();
    }

    /**
     *  设置banner
     * @param bannerList
     */
    @Override
    public void setBannerList(List<String> bannerList) {
        mViewpager.initBanner(bannerList, true)
                .addPageMargin(10, 60)
                .addPoint(6)
                .addPointBottom(7)
                .addStartTimer(5)
                .addRoundCorners(13)
                .finishConfig()
                .addBannerListener(new BannerViewPager.OnClickBannerListener() {
                    @Override
                    public void onBannerClick(int i) {
                        //点击回调
                    }
                });
    }
}
