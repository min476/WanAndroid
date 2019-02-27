package com.vera.sample.wanandroid.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.mvp.BaseModel;
import com.vera.sample.wanandroid.mvp.BasePresenter;
import com.vera.sample.wanandroid.mvp.BaseView;
import com.vera.sample.wanandroid.utils.CommonUtils;
import com.vera.sample.wanandroid.utils.NetUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;


/**
 * File descripition:   ftagment 基类
 *
 * @author lp
 * @date 2018/6/19
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    public View view;

    public Context mContext;
    protected P mPresenter;

    // 列表以及刷新控件
    protected RecyclerView recyclerView;
    protected SmartRefreshLayout smartRefreshLayout;

    protected abstract P createPresenter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);

        mContext = getActivity();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        mPresenter = createPresenter();

        this.initToolbar(savedInstanceState);
        //绑定activity
        ButterKnife.bind(this, view);

        this.initData();
        // 初始化刷新
        initRefresh(smartRefreshLayout);

        return view;
    }


    /**
     * 初始化刷新
     *
     * @param smartRefreshLayout
     */
    public void initRefresh(SmartRefreshLayout smartRefreshLayout) {
        if (smartRefreshLayout != null) {
            this.smartRefreshLayout = smartRefreshLayout;

            //设置 Header 为 贝塞尔雷达 样式
//        smartRefreshLayout.setRefreshHeader(new BezierRadarHeader(getActivity()).setEnableHorizontalDrag(true));
            //设置 Header 为 贝塞尔球体 样式
            smartRefreshLayout.setRefreshHeader(new BezierCircleHeader(getActivity()));
//        smartRefreshLayout.setRefreshHeader(new StoreHouseHeader(getActivity()));
            // 设置主题色
            smartRefreshLayout.setPrimaryColors(getResources().getColor(R.color.colorMain));
            //设置 Footer 为 球脉冲 样式
            smartRefreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale).setAnimatingColor(getResources().getColor(R.color.colorMain)));


            // 设置刷新事件
            smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    if (NetUtils.isNetworkAvailable(mContext)) {
                        refreshData();
                        refreshLayout.finishRefresh(/*1500,false*/);//传入数字表示延迟，false表示加载失败
                        CommonUtils.showMessage(mContext, "数据刷新成功");
                    } else {
                        CommonUtils.showMessage(mContext, "啊哦，数据刷新失败了T-T");
                        refreshLayout.finishRefresh(false);
                    }

                }
            });
            // 设置加载事件
            smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    if (NetUtils.isNetworkAvailable(mContext)) {
                        loadMoreData();
                        refreshLayout.finishLoadMore(/*1500,false*/);//传入数字表示延迟，false表示加载失败
                        CommonUtils.showMessage(mContext, "数据加载成功");
                    } else {
                        refreshLayout.finishLoadMore(false);//传入false表示加载失败
                        CommonUtils.showMessage(mContext, "啊哦，数据加载失败了T-T");
                    }
                }
            });
        }
    }


    /**
     * 刷新调用
     */
    protected void refreshData() {
    }

    /**
     * 加载更多调用
     */
    protected void loadMoreData() {
    }


    /**
     * 获取布局ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 处理顶部title
     *
     * @param savedInstanceState
     */
    protected abstract void initToolbar(Bundle savedInstanceState);


    /**
     * 数据初始化操作
     */
    protected abstract void initData();

    public void showToast(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String str) {
    }

    @Override
    public void showError() {

    }

    /**
     * 显示错误信息
     *
     * @param msg
     */
    @Override
    public void showErrorMsg(String msg) {
        showToast(msg);
    }

    @Override
    public void onErrorCode(BaseModel model) {
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

//    @Override
//    public void reload() {
//    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.view = null;
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter = null;
        }
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }


    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
}
