package com.vera.sample.wanandroid.ui.fragment.navigation;

import android.app.Activity;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.adapter.navigation.NavigationAdapter;
import com.vera.sample.wanandroid.adapter.project.ProjectListAdapter;
import com.vera.sample.wanandroid.app.DataManager;
import com.vera.sample.wanandroid.bean.navigation.NavigationListBean;
import com.vera.sample.wanandroid.bean.project.ProjectClassifyBean;
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
 * File descripition: 导航逻辑
 *
 * @author: Vera
 * @date: 2019/3/1
 */

public class NavigationPresenter extends BasePresenter<NavigationView> implements BaseQuickAdapter.OnItemClickListener  {

    private DataManager mDataManager;

    private NavigationAdapter navigationAdapter;
    private List<NavigationListBean> navigationListBeans = new ArrayList<>();
    private HttpDialog httpDialog;
    private List<String> publicAcccountCacheList = new ArrayList<>();;



    public NavigationPresenter(NavigationView baseView, Activity activity) {
        super(baseView, activity);
        httpDialog = new HttpDialog(mActivity);

    }

    public void initAdapter(RecyclerView recyclerView) {
        navigationAdapter = new NavigationAdapter(R.layout.item_navigation, navigationListBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(navigationAdapter);
        // 设置点击事件
        navigationAdapter.setOnItemClickListener(this);

    }

    /**
     *  获取导航列表
     */
    public void getNavigationList() {
        httpDialog.show();
        addSubscribe(apiServer.getNavigationListData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObservers<List<NavigationListBean>>(baseView) {
                    @Override
                    public void onNext(List<NavigationListBean> projectListBeans) {
                        baseView.setNavigationTab(projectListBeans);
//                        navigationListBeans.addAll(projectListBeans);
//                        navigationAdapter.notifyDataSetChanged();
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
