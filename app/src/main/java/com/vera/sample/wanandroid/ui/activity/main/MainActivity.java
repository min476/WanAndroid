package com.vera.sample.wanandroid.ui.activity.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.app.Constants;
import com.vera.sample.wanandroid.base.BaseActivity;
import com.vera.sample.wanandroid.base.BaseFragment;
import com.vera.sample.wanandroid.bean.publicaccount_bean.PublicAcccountBean;
import com.vera.sample.wanandroid.mvp.BaseModel;
import com.vera.sample.wanandroid.ui.fragment.home.HomeFragment;
import com.vera.sample.wanandroid.ui.fragment.knowledge.KnowledgeHistoryFragment;
import com.vera.sample.wanandroid.ui.fragment.navigation.NavigationFragment;
import com.vera.sample.wanandroid.ui.fragment.project.ProjectFragment;
import com.vera.sample.wanandroid.ui.fragment.public_account.PublicAccountsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @BindView(R.id.act_public_accout_rv)
    RecyclerView rvPublicAccout;

    @BindView(R.id.contentContainer)
    FrameLayout fragmentContent;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView navigationBar;
    @BindView(R.id.tv_back)
    TextView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    List<PublicAcccountBean> publicAcccountBeanList = new ArrayList<>();

    private ArrayList<BaseFragment> mFragments;
    private HomeFragment homeFragment;
    private KnowledgeHistoryFragment knowledgeHistoryFragment; //知识体系
    private NavigationFragment navigationFragment; // 导航
    private PublicAccountsFragment publicAccountsFragment; // 公众号
    private ProjectFragment projectFragment;// 项目

    private int mLastFgIndex;


    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this,MainActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragments = new ArrayList<>();

        if (savedInstanceState == null) {
            initPager(false, Constants.TYPE_MAIN_PAGER);
        } else {
            navigationBar.setSelectedItemId(R.id.tab_main_pager);
            initPager(true, Constants.TYPE_SETTING);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        ivBack.setVisibility(View.GONE);
        tvTitle.setText(getString(R.string.home_pager));
    }

    @Override
    protected void initData() {
        mPresenter.initAdapter(rvPublicAccout);
    }

    private void initPager(boolean isRecreate, int position) {
        homeFragment = HomeFragment.getInstance(isRecreate, null);
        mFragments.add(homeFragment);
        initFragments();
        init();
        switchFragment(position);
    }

    private void init() {
        initBottomNavigationView();
    }

    private void initFragments() {
        knowledgeHistoryFragment = KnowledgeHistoryFragment.getInstance(null, null);
        publicAccountsFragment = PublicAccountsFragment.getInstance(null, null);
        navigationFragment = NavigationFragment.getInstance(null, null);
        projectFragment = ProjectFragment.getInstance(null, null);

        mFragments.add(knowledgeHistoryFragment);
        mFragments.add(publicAccountsFragment);
        mFragments.add(navigationFragment);
        mFragments.add(projectFragment);
    }


    /**
     * 切换fragment
     *
     * @param position 要显示的fragment的下标
     */
    private void switchFragment(int position) {
//        if (position >= Constants.TYPE_COLLECT) {
//            mFloatingActionButton.setVisibility(View.INVISIBLE);
//            mBottomNavigationView.setVisibility(View.INVISIBLE);
//        } else {
//            mFloatingActionButton.setVisibility(View.VISIBLE);
//            mBottomNavigationView.setVisibility(View.VISIBLE);
//        }
        if (position >= mFragments.size()) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment targetFg = mFragments.get(position);
        Fragment lastFg = mFragments.get(mLastFgIndex);
        mLastFgIndex = position;
        ft.hide(lastFg);
        if (!targetFg.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(targetFg).commitAllowingStateLoss();
            ft.add(R.id.contentContainer, targetFg);
        }
        ft.show(targetFg);
        ft.commitAllowingStateLoss();
    }

    /**
     *  初始化导航view
     */
    private void initBottomNavigationView() {
        navigationBar.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
//        BottomNavigationViewHelper.removeShiftMode(navigationBar);

        navigationBar.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tab_main_pager:
                    loadPager(getString(R.string.home_pager), 0,
                            homeFragment, Constants.TYPE_MAIN_PAGER);
                    break;
                case R.id.tab_knowledge_hierarchy:
                    loadPager(getString(R.string.knowledge_hierarchy), 1,
                            knowledgeHistoryFragment, Constants.TYPE_KNOWLEDGE);
                    break;
                case R.id.tab_wx_article:
                    loadPager(getString(R.string.wx_article), 2,
                            publicAccountsFragment, Constants.TYPE_WX_ARTICLE);
                    break;
                case R.id.tab_navigation:
                    loadPager(getString(R.string.navigation), 3,
                            navigationFragment, Constants.TYPE_NAVIGATION);
                    break;
                case R.id.tab_project:
                    loadPager(getString(R.string.project), 4,
                            projectFragment, Constants.TYPE_PROJECT);
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    private void loadPager(String title, int position, BaseFragment mFragment, int pagerType) {
        tvTitle.setText(title);
        switchFragment(position);
//        mFragment.reload();
//        mPresenter.setCurrentPage(pagerType);
    }



    @Override
    public void onMainSuccess(BaseModel<List<PublicAcccountBean>> list) { //BaseModel<List<HomeBeans.DatasBean>>
        publicAcccountBeanList = list.getData();
    }

}
