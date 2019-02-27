package com.vera.sample.wanandroid.ui.fragment.project;

import android.os.Bundle;

import com.flyco.tablayout.SlidingTabLayout;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.adapter.PublicPageAdapter;
import com.vera.sample.wanandroid.app.Constants;
import com.vera.sample.wanandroid.base.BaseFragment;
import com.vera.sample.wanandroid.bean.project.ProjectClassifyBean;
import com.vera.sample.wanandroid.bean.publicaccount_bean.PublicAcccountBean;
import com.vera.sample.wanandroid.ui.fragment.project.classify.ProjectClassifyFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * File descripition: 公众号fragment
 *
 * @author: Vera
 * @date: 2019/2/13
 */

public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectView {

    @BindView(R.id.project_tab_layout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.project_vp)
    ViewPager projectVp;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private PublicPageAdapter publicPageAdapter ;
    private List<String> publicAcccountTitleCacheList = new ArrayList<>();;



    List<PublicAcccountBean> publicAcccountBeanList = new ArrayList<>();

    public static ProjectFragment getInstance(String param1, String param2) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected ProjectPresenter createPresenter() {
        return new ProjectPresenter(this, getActivity());
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
    }

    @Override
    protected void initData() {
//        mPresenter.initAdapter(recyclerView);
        mPresenter.getProjectList();

    }


    /**
     *  设置项目分类tab
     * @param projectClassifyData
     */
    @Override
    public void setProjectTab(List<ProjectClassifyBean> projectClassifyData) {
        // 传递id 与公众号名称到子类frg
        for (int i = 0; i <projectClassifyData.size() ; i++) {
            mFragments.add(ProjectClassifyFragment.getInstance(projectClassifyData.get(i).getName(),projectClassifyData.get(i).getId()));
        }

        // 公众号的分类tab
        for (int i = 0; i < projectClassifyData.size(); i++) {
            publicAcccountTitleCacheList.add(projectClassifyData.get(i).getName());
        }
        publicPageAdapter = new PublicPageAdapter(getFragmentManager(),mFragments,publicAcccountTitleCacheList);
        projectVp.setAdapter(publicPageAdapter);

        // 设置 ViewPager
        slidingTabLayout.setViewPager(projectVp);
    }
}
