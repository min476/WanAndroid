package com.vera.sample.wanandroid.ui.fragment.public_account;

import android.os.Bundle;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
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
import com.vera.sample.wanandroid.app.Constants;
import com.vera.sample.wanandroid.app.MyApplication;
import com.vera.sample.wanandroid.base.BaseFragment;
import com.vera.sample.wanandroid.bean.PublicAcccountBean;
import com.vera.sample.wanandroid.utils.CommonUtils;
import com.vera.sample.wanandroid.utils.NetUtils;

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
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;


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
        mPresenter.initAdapter(recyclerView);
//        mPresenter.getPublicList();
        mPresenter.getList();
        //设置 Header 为 贝塞尔雷达 样式
//        smartRefreshLayout.setRefreshHeader(new BezierRadarHeader(getActivity()).setEnableHorizontalDrag(true));
        //设置 Header 为 贝塞尔球体 样式
        smartRefreshLayout.setRefreshHeader(new BezierCircleHeader(getActivity()));
//        smartRefreshLayout.setRefreshHeader(new StoreHouseHeader(getActivity()));
        // 设置主题色
        smartRefreshLayout.setPrimaryColors(getResources().getColor(R.color.colorMain));
       //设置 Footer 为 球脉冲 样式
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale).setAnimatingColor(getResources().getColor(R.color.colorMain)));


        if (NetUtils.isNetworkAvailable(MyApplication.getContext())) {
            smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                    mPresenter.getList();
                }
            });
            smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshlayout) {
                    refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                }
            });
        }else {
            CommonUtils.showMessage(getActivity(),"报告小主，网络可能被外星人偷走啦~");
        }



    }
}
