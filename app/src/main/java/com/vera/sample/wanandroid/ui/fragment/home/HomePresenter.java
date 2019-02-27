package com.vera.sample.wanandroid.ui.fragment.home;

import android.app.Activity;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.adapter.FeedArticaleAdapter;
import com.vera.sample.wanandroid.bean.BannerBean;
import com.vera.sample.wanandroid.bean.FeedArticleBean;
import com.vera.sample.wanandroid.bean.FeedArticleListBean;
import com.vera.sample.wanandroid.custom.HttpDialog;
import com.vera.sample.wanandroid.mvp.BaseObservers;
import com.vera.sample.wanandroid.mvp.BasePresenter;
import com.vera.sample.wanandroid.rx.RxUtils;
import com.vera.sample.wanandroid.ui.activity.webview.WebLinkActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * File descripition:
 *
 * @author: Vera
 * @date: 2019/2/20
 */

public class HomePresenter extends BasePresenter<HomeView> implements BaseQuickAdapter.OnItemClickListener {
    private List<String> bannerPathList = new ArrayList<>();
    private FeedArticaleAdapter feedArticaleAdapter;
    private List<FeedArticleBean> publicAccountBeans = new ArrayList<>();
    private HttpDialog httpDialog;

    public HomePresenter(HomeView homeView, Activity activity) {
        super(homeView, activity);
        httpDialog = new HttpDialog(mActivity);
    }

    public void initAdapter(RecyclerView recyclerView) {
        feedArticaleAdapter = new FeedArticaleAdapter(R.layout.item_public_account_classfy, publicAccountBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(feedArticaleAdapter);
        // 设置点击事件
        feedArticaleAdapter.setOnItemClickListener(this);

    }

    /**
     * 获取banner
     */
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
                        baseView.setBannerList(bannerPathList, bannerBeans);
                    }
                }));
    }

    /**
     * 获取首页数据列表
     *
     * @param num
     */
    public void getFeedArticleList(int num) {
        addSubscribe(apiServer.getFeedArticleList(num)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObservers<FeedArticleListBean>(baseView) {
                    @Override
                    public void onNext(FeedArticleListBean feedArticleListBean) {
                        if (feedArticleListBean != null) {
                            if (feedArticleListBean.getDatas().size() > 0) {
                                publicAccountBeans .addAll( feedArticleListBean.getDatas());
                                feedArticaleAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                }));
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        // 跳转到对应的h5界面
        WebLinkActivity.load(mActivity, feedArticaleAdapter.getItem(position).getTitle(), feedArticaleAdapter.getItem(position).getLink());
    }
}
