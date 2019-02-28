package com.vera.sample.wanandroid.ui.fragment.project.classify;

import android.os.Bundle;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.base.BaseFragment;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * File descripition: 项目的子分类列表Fragment
 *
 * @author: Vera
 * @date: 2019/2/27
 */

public class ProjectClassifyFragment extends BaseFragment<ProjectClassifyPresenter> implements ProjectClassifyView {
    private String mTitle;
    private int cid;
    private int page= 1;

    @BindView(R.id.frag_public_accout_rv)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;


    public static ProjectClassifyFragment getInstance(String title, int id) {//,int id
        ProjectClassifyFragment sf = new ProjectClassifyFragment();
        sf.mTitle = title;
        sf.cid = id;
        return sf;
    }

    @Override
    protected ProjectClassifyPresenter createPresenter() {
        return new ProjectClassifyPresenter(this,getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_public_classify;
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
    }

    @Override
    protected void initData() {
        // 初始化刷新
        initRefresh(smartRefreshLayout);
        mPresenter.initAdapter(recyclerView);
        mPresenter.getPublicClassifyList(page,cid);

    }

    /**
     *  刷新数据
     */
    @Override
    protected void refreshData() {
        page = 1;
        mPresenter.getPublicClassifyList(page,cid);
    }

    /**
     *  加载数据
     */
    @Override
    protected void loadMoreData() {
        page++;
        mPresenter.getPublicClassifyList(page,cid);
    }
}