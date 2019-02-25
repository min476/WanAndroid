package com.vera.sample.wanandroid.ui.fragment.public_account.classfy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.base.BaseFragment;
import com.vera.sample.wanandroid.mvp.BasePresenter;
import com.vera.sample.wanandroid.ui.fragment.public_account.PublicAccountsView;
import com.vera.sample.wanandroid.utils.CommonUtils;
import com.vera.sample.wanandroid.utils.NetUtils;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * File descripition: 公众号的子分类列表Fragment
 *
 * @author: Vera
 * @date: 2019/2/25
 */

public class PublicClassfyFragment extends BaseFragment<PublicClassfyPresenter> implements PublicClassfyView {
    private String mTitle;
    private int publicId;

    @BindView(R.id.frag_public_accout_rv)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    public static PublicClassfyFragment getInstance(String title) {//,int id
        PublicClassfyFragment sf = new PublicClassfyFragment();
        sf.mTitle = title;
//        sf.publicId = id;
        return sf;
    }

    @Override
    protected PublicClassfyPresenter createPresenter() {
        return new PublicClassfyPresenter(this,getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_public_classfy;
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        mPresenter.initAdapter(recyclerView);
        mPresenter.getPublicList();
        initRefresh();

    }

    /**
     *  初始化刷新控件
     */
    private void initRefresh() {
        //设置 Header 为 贝塞尔雷达 样式
//        smartRefreshLayout.setRefreshHeader(new BezierRadarHeader(getActivity()).setEnableHorizontalDrag(true));
        //设置 Header 为 贝塞尔球体 样式
        smartRefreshLayout.setRefreshHeader(new BezierCircleHeader(getActivity()));
//        smartRefreshLayout.setRefreshHeader(new StoreHouseHeader(getActivity()));
        // 设置主题色
        smartRefreshLayout.setPrimaryColors(getResources().getColor(R.color.colorMain));
        //设置 Footer 为 球脉冲 样式
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale).setAnimatingColor(getResources().getColor(R.color.colorMain)));


        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (NetUtils.isNetworkAvailable(mContext)) {
                    refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                    mPresenter.getPublicList();
                } else {
                    CommonUtils.showMessage(getActivity(), "报告小主，网络可能被外星人偷走啦~");
                    refreshlayout.finishRefresh(false);//传入false表示刷新失败
                }
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                if (NetUtils.isNetworkAvailable(mContext)) {
                    refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                }else {
                    CommonUtils.showMessage(getActivity(), "报告小主，网络可能被外星人偷走啦~");
                    refreshlayout.finishLoadMore(false);//传入false表示加载失败
                }
            }
        });

    }
}