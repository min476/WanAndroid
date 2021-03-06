package com.vera.sample.wanandroid.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.app.MyApplication;
import com.vera.sample.wanandroid.coustom.PromptDialog;
import com.vera.sample.wanandroid.custom.HttpDialog;
import com.vera.sample.wanandroid.mvp.BaseModel;
import com.vera.sample.wanandroid.mvp.BasePresenter;
import com.vera.sample.wanandroid.mvp.BaseView;
import com.vera.sample.wanandroid.utils.AppManager;
import com.vera.sample.wanandroid.utils.CommonUtils;
import com.vera.sample.wanandroid.utils.NetUtils;
import com.vera.sample.wanandroid.utils.StatusBarUtil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;

import static com.vera.sample.wanandroid.mvp.BaseObserver.NETWORK_ERROR;


/**
 * File descripition: activity基类
 * <p>
 *
 * @author lp
 * @date 2018/5/16
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity
        implements BaseView {
    protected final String TAG = this.getClass().getSimpleName();
    public Context mContext;
    protected P mPresenter;
    public AppManager appManager;

    protected abstract P createPresenter();

    //错误提示框  警告框  成功提示框 加载进度框 （只是提供个案例 可自定义）
    private PromptDialog promptDialog;
    private HttpDialog httpDialog;

    // 列表以及刷新控件
    protected RecyclerView recyclerView;
    protected SmartRefreshLayout smartRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
        mPresenter = createPresenter();
        // 判断当前的Activity是堆栈中是否存在
        appManager = AppManager.getAppManager();
        appManager.addActivity(this);

//        setStatusBar();
        //绑定activity
        ButterKnife.bind(this);
        this.initToolbar(savedInstanceState);
        /**
         * 自适应状态栏高度
         */
        _autoAdaptiveTopBar();
        /**
         * 自动转接back的事件
         */
        _backProce();
        // 设置状态栏颜色
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.color_topBarSteep), 1f);

        this.initData();
        // 初始化刷新
        initRefresh(smartRefreshLayout);

    }


    /**
     * 返回一个topbar的id让base处理状态栏的高度
     * 通过view的高度方式解决
     *
     * @return
     */
    protected View _topBarAdaptiveByViewVH() {
        View result = null;
        int cache = _topBarAdaptiveVH() == -1 ? R.id.comm_topBarSteep : _topBarAdaptiveVH();
        if (cache > 0) {
            result = findViewById(cache);
        }
        return result;
    }

    /**
     * 返回一个topbar的id让base处理状态栏的高度
     * 通过view的高度方式解决
     *
     * @return
     */
    protected int _topBarAdaptiveVH() {
        return -1;
    }


    /**
     * 自适应状态栏高度
     */
    private void _autoAdaptiveTopBar() {
        /**
         * 自适应状态栏高度
         * Padding模式
         */
        View tempTopBar = _topBarAdaptiveByViewP();
        if (tempTopBar != null) {
//            AppUtils.AutoSteepProssByPadding(tempTopBar);
        }
        /**
         * 自适应状态栏高度
         * ViewHight模式
         */
        View tempTopBarVH = _topBarAdaptiveByViewVH();
        if (tempTopBarVH != null) {
//            AppUtils.AutoSteepProssByHeight(tempTopBarVH);
        }
    }

    /**
     * 返回一个topbar的id让base处理状态栏的高度
     * 通过padding方式解决
     *
     * @return
     */
    protected View _topBarAdaptiveByViewP() {
        View result = null;
        int cache = _topBarAdaptiveP();
        if (cache > 0) {
            result = findViewById(cache);
        }
        return result;
    }


    /**
     * 返回一个topbar的id让base处理状态栏的高度
     * 通过padding方式解决
     *
     * @return
     */
    protected int _topBarAdaptiveP() {
        return -1;
    }

    /**
     * 自动转接back的事件
     */
    protected void _backProce() {
        View temp = findViewById(R.id.tv_back);
        if (temp != null) {
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    back();
                    try {
                        //隐藏键盘
                        ((InputMethodManager) MyApplication.getContext().getSystemService(
                                Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    protected void back() {

        appManager.finishActivity();
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
            smartRefreshLayout.setRefreshHeader(new BezierCircleHeader(mContext));
//        smartRefreshLayout.setRefreshHeader(new StoreHouseHeader(getActivity()));
            // 设置主题色
            smartRefreshLayout.setPrimaryColors(getResources().getColor(R.color.colorMain));
            //设置 Footer 为 球脉冲 样式
            smartRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale).setAnimatingColor(getResources().getColor(R.color.colorMain)));

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

    /**
     * 此处设置沉浸式地方
     */
    protected void setStatusBar() {
//        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
    }

    /**
     * 封装toast方法（自行去实现）
     *
     * @param str
     */
    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError() {
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        showToast(errorMsg);
    }


    public void showLongToast(String str) {
    }

    /**
     * 返回所有状态  除去指定的值  可设置所有（根据需求）
     *
     * @param model
     */
    @Override
    public void onErrorCode(BaseModel model) {
        if (model.getErrcode() == NETWORK_ERROR) {

        }
    }

    //显示加载进度框回调
    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    //隐藏进度框回调
    @Override
    public void hideLoading() {
        closeLoadingDialog();
    }

    /**
     * 加载框消失
     */
    public void closeLoadingDialog() {
        if (promptDialog != null) {
            promptDialog.dismiss();
        }
    }

    /**
     * 加载中...
     */
    public void showLoadingDialog() {
//        if (promptDialog == null) {
//            promptDialog = new PromptDialog(this);
//        }
//        promptDialog.showLoading("加载中...",false);
        if (httpDialog == null) {
            httpDialog = new HttpDialog(this);
        }
        httpDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
