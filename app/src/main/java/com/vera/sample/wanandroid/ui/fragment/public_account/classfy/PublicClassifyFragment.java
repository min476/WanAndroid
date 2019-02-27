package com.vera.sample.wanandroid.ui.fragment.public_account.classfy;

import android.os.Bundle;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.base.BaseFragment;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * File descripition: 公众号的子分类列表Fragment
 *
 * @author: Vera
 * @date: 2019/2/25
 */

public class PublicClassifyFragment extends BaseFragment<PublicClassifyPresenter> implements PublicClassifyView {
    private String mTitle;
    private int publicId;
    private int page= 1;

    @BindView(R.id.frag_public_accout_rv)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout smartRefreshLayout;


    public static PublicClassifyFragment getInstance(String title, int id) {//,int id
        PublicClassifyFragment sf = new PublicClassifyFragment();
        sf.mTitle = title;
        sf.publicId = id;
        return sf;
    }

    @Override
    protected PublicClassifyPresenter createPresenter() {
        return new PublicClassifyPresenter(this,getActivity());
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

        // 初始化刷新
        initRefresh(smartRefreshLayout);
        mPresenter.initAdapter(recyclerView);
        mPresenter.getPublicClassfyList(publicId,page);

    }

    /**
     *  刷新数据
     */
    @Override
    protected void refreshData() {
        page = 1;
        mPresenter.getPublicClassfyList(publicId,page);
    }

    /**
     *  加载数据
     */
    @Override
    protected void loadMoreData() {
        page++;
        mPresenter.getPublicClassfyList(publicId,page);
    }
}