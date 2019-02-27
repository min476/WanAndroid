package com.vera.sample.wanandroid.ui.fragment.project.classify;

import android.app.Activity;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.adapter.public_account.PublicAccountClassifyAdapter;
import com.vera.sample.wanandroid.app.DataManager;
import com.vera.sample.wanandroid.bean.publicaccount_bean.PublicAccountListBean;
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

/**
 * File descripition: 项目子分类
 *
 * @author: Vera
 * @date: 2019/2/27
 */

public class ProjectClassifyPresenter extends BasePresenter<ProjectClassifyView> implements BaseQuickAdapter.OnItemClickListener {

    private DataManager mDataManager;

    private PublicAccountClassifyAdapter publicAccountClassifyAdapter;
    private List<PublicAccountListBean.DatasBean> publicAccountListBeans = new ArrayList<>();
    private HttpDialog httpDialog;
    private List<String> publicAcccountCacheList = new ArrayList<>();



    public ProjectClassifyPresenter(ProjectClassifyView baseView, Activity activity) {
        super(baseView, activity);
        httpDialog = new HttpDialog(mActivity);

    }

    public void initAdapter(RecyclerView recyclerView) {
        publicAccountClassifyAdapter = new PublicAccountClassifyAdapter(R.layout.item_public_account_classfy, publicAccountListBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(publicAccountClassifyAdapter);
        // 设置点击事件
        publicAccountClassifyAdapter.setOnItemClickListener(this);

    }

    /**
     *  获取公众号子类列表
     */
    public void getPublicClassfyList(int id,int page) {
        httpDialog.show();
        addSubscribe(apiServer.getPublicAccountListData(id,page)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObservers<PublicAccountListBean>(baseView) {
                    @Override
                    public void onNext(PublicAccountListBean publicAcccountBeans) {
                        if(publicAcccountBeans!=null && publicAcccountBeans.getDatas().size()>0){
                            publicAccountListBeans.addAll(publicAcccountBeans.getDatas());
                            // 更新适配器
                            publicAccountClassifyAdapter.notifyDataSetChanged();
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
        WebLinkActivity.load(mActivity, publicAccountClassifyAdapter.getItem(position).getTitle(), publicAccountClassifyAdapter.getItem(position).getLink());
//        Toast.makeText(mActivity, publicAccountClassifyAdapter.getItem(position).getAuthor(), Toast.LENGTH_LONG).show();
    }

}
