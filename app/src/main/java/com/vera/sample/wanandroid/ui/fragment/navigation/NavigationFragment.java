package com.vera.sample.wanandroid.ui.fragment.navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.adapter.navigation.NavigationAdapter;
import com.vera.sample.wanandroid.app.Constants;
import com.vera.sample.wanandroid.base.BaseFragment;
import com.vera.sample.wanandroid.bean.navigation.NavigationListBean;
import com.vera.sample.wanandroid.mvp.BasePresenter;
import com.vera.sample.wanandroid.ui.fragment.project.classify.ProjectClassifyPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * File descripition: 导航fragment
 *
 * @author: Vera
 * @date: 2019/2/13
 */

public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationView,BaseQuickAdapter.OnItemClickListener   {

    @BindView(R.id.navigation_tablayout)
    VerticalTabLayout verticalTabLayout;
    @BindView(R.id.navigation_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.normal_view)
    LinearLayout mNavigationGroup;
    @BindView(R.id.navigation_divider)
    View mDivider;

    private boolean needScroll;
    private int index;
    private boolean isClickTab;
    private NavigationAdapter mNavigationAdapter;
    private LinearLayoutManager mManager;
    private NavigationAdapter navigationAdapter;


    public static NavigationFragment getInstance(String param1, String param2) {
        NavigationFragment fragment = new NavigationFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected NavigationPresenter createPresenter() {
        return new NavigationPresenter(this, getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
//        mPresenter.initAdapter(mRecyclerView);
         initAdapter(mRecyclerView);
        mPresenter.getNavigationList();
    }


    public void initAdapter(RecyclerView recyclerView) {
        List<NavigationListBean> navigationListBeans = new ArrayList<>();
        navigationAdapter = new NavigationAdapter(R.layout.item_navigation, navigationListBeans);
        mManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(navigationAdapter);
        // 设置点击事件
//        navigationAdapter.setOnItemClickListener(this);

    }


    /**
     * 设置导航数据
     *
     * @param navigationData
     */
    @Override
    public void setNavigationTab(List<NavigationListBean> navigationData) {
        verticalTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return navigationData == null ? 0 : navigationData.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new TabView.TabTitle.Builder()
                        .setContent(navigationData.get(position).getName())
                        .setTextColor(ContextCompat.getColor(mContext, R.color.colorMain),
                                ContextCompat.getColor(mContext, R.color.color_ff757575))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        });
        navigationAdapter.replaceData(navigationData);
        leftRightLinkage();
    }

    /**
     * Left tabLayout and right recyclerView linkage
     */
    private void leftRightLinkage() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (needScroll && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                    scrollRecyclerView();
                }
                rightLinkageLeft(newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (needScroll) {
                    scrollRecyclerView();
                }
            }
        });

        verticalTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tabView, int i) {
                isClickTab = true;
                selectTag(i);
            }

            @Override
            public void onTabReselected(TabView tabView, int i) {
            }
        });
    }

    private void scrollRecyclerView() {
        needScroll = false;
        int indexDistance = index - mManager.findFirstVisibleItemPosition();
        if (indexDistance >= 0 && indexDistance < mRecyclerView.getChildCount()) {
            int top = mRecyclerView.getChildAt(indexDistance).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        }
    }

    private void setChildViewVisibility(int visibility) {
        mNavigationGroup.setVisibility(visibility);
        verticalTabLayout.setVisibility(visibility);
        mDivider.setVisibility(visibility);
    }

    /**
     * Right recyclerView linkage left tabLayout
     * SCROLL_STATE_IDLE just call once
     *
     * @param newState RecyclerView new scroll state
     */
    private void rightLinkageLeft(int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (isClickTab) {
                isClickTab = false;
                return;
            }
            int firstPosition = mManager.findFirstVisibleItemPosition();
            if (index != firstPosition) {
                index = firstPosition;
                setChecked(index);
            }
        }
    }

    private void selectTag(int i) {
        index = i;
        mRecyclerView.stopScroll();
        smoothScrollToPosition(i);
    }

    /**
     * Smooth right to select the position of the left tab
     *
     * @param position checked position
     */
    private void setChecked(int position) {
        if (isClickTab) {
            isClickTab = false;
        } else {
            if (verticalTabLayout == null) {
                return;
            }
            verticalTabLayout.setTabSelected(index);
        }
        index = position;
    }

    private void smoothScrollToPosition(int currentPosition) {
        int firstPosition = mManager.findFirstVisibleItemPosition();
        int lastPosition = mManager.findLastVisibleItemPosition();
        if (currentPosition <= firstPosition) {
            mRecyclerView.smoothScrollToPosition(currentPosition);
        } else if (currentPosition <= lastPosition) {
            int top = mRecyclerView.getChildAt(currentPosition - firstPosition).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        } else {
            mRecyclerView.smoothScrollToPosition(currentPosition);
            needScroll = true;
        }
    }

    public void jumpToTheTop() {
        if (verticalTabLayout != null) {
            verticalTabLayout.setTabSelected(0);
        }
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
