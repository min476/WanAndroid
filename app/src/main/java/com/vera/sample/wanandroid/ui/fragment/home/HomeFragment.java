package com.vera.sample.wanandroid.ui.fragment.home;

import android.os.Bundle;

import com.lwj.widget.bannerview.BannerPagerAdapter;
//import com.lzj.gallery.library.views.BannerViewPager;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.app.Constants;
import com.vera.sample.wanandroid.base.BaseFragment;
import com.vera.sample.wanandroid.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * File descripition: 首页fragment
 *
 * @author: Vera
 * @date: 2019/2/13
 */

public class HomeFragment extends BaseFragment {

//    @BindView(R.id.banner_viewpager)
//    BannerViewPager mViewpager;

//    private List<String> urlList= new ArrayList<>();


    public static HomeFragment getInstance(boolean param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putBoolean(Constants.ARG_PARAM1, param1);
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
        return R.layout.fragment_home;
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

//        urlList.add("http://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png");
//        urlList.add("http://www.wanandroid.com/blogimgs/ab17e8f9-6b79-450b-8079-0f2287eb6f0f.png");
//        urlList.add("http://www.wanandroid.com/blogimgs/fb0ea461-e00a-482b-814f-4faca5761427.png");
//        urlList.add("http://www.wanandroid.com/blogimgs/00f83f1d-3c50-439f-b705-54a49fc3d90d.jpg");
//        mViewpager.initBanner(urlList, true)
//                .addPageMargin(10, 60)
//                .addPoint(6)
//                .addPointBottom(7)
//                .addStartTimer(5)
//                .addRoundCorners(13)
//                .finishConfig()
//                .addBannerListener(new BannerViewPager.OnClickBannerListener() {
//                    @Override
//                    public void onBannerClick(int i) {
//                        //点击回调
//                    }
//                });


    }
}
