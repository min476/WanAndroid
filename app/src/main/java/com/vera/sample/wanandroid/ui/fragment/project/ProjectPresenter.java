package com.vera.sample.wanandroid.ui.fragment.project;

import android.app.Activity;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.adapter.project.ProjectClassifyAdapter;
import com.vera.sample.wanandroid.adapter.public_account.PublicAccountListAdapter;
import com.vera.sample.wanandroid.app.DataManager;
import com.vera.sample.wanandroid.bean.FeedArticleBean;
import com.vera.sample.wanandroid.bean.project.ProjectClassifyBean;
import com.vera.sample.wanandroid.bean.project.ProjectListBean;
import com.vera.sample.wanandroid.custom.HttpDialog;
import com.vera.sample.wanandroid.mvp.BaseObservers;
import com.vera.sample.wanandroid.mvp.BasePresenter;
import com.vera.sample.wanandroid.rx.RxUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * File descripition: 公众号
 *
 * @author: Vera
 * @date: 2019/2/14
 */

public class ProjectPresenter extends BasePresenter<ProjectView> implements BaseQuickAdapter.OnItemClickListener  {

    private DataManager mDataManager;

    private ProjectClassifyAdapter projectClassifyAdapter;
    private List<ProjectClassifyBean> projectClassifyBeans = new ArrayList<>();
    private HttpDialog httpDialog;
    private List<String> publicAcccountCacheList = new ArrayList<>();;



    public ProjectPresenter(ProjectView baseView, Activity activity) {
        super(baseView, activity);
        httpDialog = new HttpDialog(mActivity);

    }

    public void initAdapter(RecyclerView recyclerView) {
        projectClassifyAdapter = new ProjectClassifyAdapter(R.layout.item_public_account, projectClassifyBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(projectClassifyAdapter);
        // 设置点击事件
        projectClassifyAdapter.setOnItemClickListener(this);

    }

    /**
     *  获取项目列表
     */
    public void getProjectList() {
        httpDialog.show();
        addSubscribe(apiServer.getProjectClassifyData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObservers<List<ProjectClassifyBean>>(baseView) {
                    @Override
                    public void onNext(List<ProjectClassifyBean> projectListBeans) {
//                        publicAcccountBeanList.addAll(publicAcccountBeans);
//                        // 更新适配器
//                        publicAccountListAdapter.notifyDataSetChanged();

                        baseView.setProjectTab(projectListBeans);
                        httpDialog.dismiss();

                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        httpDialog.dismiss();
                    }
                })

        );
    }


//    /**
//     * 获取公众号列表数据
//     */
//    public void getPublicList() {
//        addDisposable(apiServer.getPublicAccountList(), new BaseObserver(baseView) {
//            @Override
//            public void onSuccess(Object o) {
//                publicAcccountCacheList = (List<PublicAcccountBean>) ((BaseModel<List<PublicAcccountBean>>) o).getData();
//                publicAcccountBeanList.addAll(publicAcccountCacheList);
//                // 更新适配器
//                publicAccountListAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onError(String msg) {
//                if (baseView != null) {
//                    baseView.showErrorMsg(msg);
//                }
//            }
//        });
//    }

    /**
     * 列表点击事件
     *
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        Toast.makeText(mActivity, publicAccountListAdapter.getItem(position).getName(), Toast.LENGTH_LONG).show();



    }

}
