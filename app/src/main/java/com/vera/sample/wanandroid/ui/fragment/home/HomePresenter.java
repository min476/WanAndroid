package com.vera.sample.wanandroid.ui.fragment.home;

import android.app.Activity;

import com.lzj.gallery.library.views.BannerViewPager;
import com.vera.sample.wanandroid.bean.BannerBean;
import com.vera.sample.wanandroid.mvp.BaseObservers;
import com.vera.sample.wanandroid.mvp.BasePresenter;
import com.vera.sample.wanandroid.rx.RxUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * File descripition:
 *
 * @author: Vera
 * @date: 2019/2/20
 */

public class HomePresenter extends BasePresenter<HomeView> {
    private List<String> bannerPathList = new ArrayList<>();

    public HomePresenter(HomeView homeView, Activity activity) {
        super(homeView, activity);
    }

    public void getBannerList() {
        addSubscribe(apiServer.getBannerList()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObservers<List<BannerBean>>(baseView) {
                    @Override
                    public void onNext(List<BannerBean> bannerBeans) {
                        if (bannerBeans != null && bannerBeans.size() > 0) {
                            for (int i = 0; i < bannerBeans.size(); i++) {
                                bannerPathList.add(bannerBeans.get(i).getImagePath());
                            }

                        }
                        baseView.setBannerList(bannerPathList);
                    }
                }));
    }
}
