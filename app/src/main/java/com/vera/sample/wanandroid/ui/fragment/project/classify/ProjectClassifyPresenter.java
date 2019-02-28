package com.vera.sample.wanandroid.ui.fragment.project.classify;

import android.app.Activity;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.adapter.project.ProjectClassifyAdapter;
import com.vera.sample.wanandroid.app.DataManager;
import com.vera.sample.wanandroid.bean.FeedArticleBean;
import com.vera.sample.wanandroid.bean.project.ProjectListBean;
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
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * File descripition: 项目子分类
 *
 * @author: Vera
 * @date: 2019/2/27
 */

public class ProjectClassifyPresenter extends BasePresenter<ProjectClassifyView> implements BaseQuickAdapter.OnItemClickListener {

    private DataManager mDataManager;

    private ProjectClassifyAdapter projectClassifyAdapter;
    private List<FeedArticleBean> projectListBeans = new ArrayList<>();
    private HttpDialog httpDialog;


    public ProjectClassifyPresenter(ProjectClassifyView baseView, Activity activity) {
        super(baseView, activity);
        httpDialog = new HttpDialog(mActivity);

    }

    public void initAdapter(RecyclerView recyclerView) {
        projectClassifyAdapter = new ProjectClassifyAdapter(R.layout.item_project_classifys, projectListBeans);
//        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(projectClassifyAdapter);
        // 设置点击事件
        projectClassifyAdapter.setOnItemClickListener(this);

    }

    /**
     * 获取公众号子类列表
     */
    public void getPublicClassifyList(int page, int cid) {
        httpDialog.show();
        addSubscribe(apiServer.getProjectListData(page, cid)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObservers<ProjectListBean>(baseView) {
                    @Override
                    public void onNext(ProjectListBean publicAcccountBeans) {
                        if (publicAcccountBeans != null && publicAcccountBeans.getDatas().size() > 0) {
                            projectListBeans.addAll(publicAcccountBeans.getDatas());
                            // 更新适配器
                            projectClassifyAdapter.notifyDataSetChanged();
                            httpDialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        httpDialog.dismiss();
                    }
                })

        );
    }


    /**
     * 列表点击事件
     *
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        // 跳转到对应的h5界面
        WebLinkActivity.load(mActivity, projectClassifyAdapter.getItem(position).getTitle(), projectClassifyAdapter.getItem(position).getLink());
//        Toast.makeText(mActivity, publicAccountClassifyAdapter.getItem(position).getAuthor(), Toast.LENGTH_LONG).show();
    }

}
