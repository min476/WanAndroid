package com.vera.sample.wanandroid.ui.fragment.public_account;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.adapter.PublicAccountListAdapter;
import com.vera.sample.wanandroid.app.DataManager;
import com.vera.sample.wanandroid.bean.PublicAcccountBean;
import com.vera.sample.wanandroid.mvp.BaseModel;
import com.vera.sample.wanandroid.mvp.BaseObserver;
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

public class PublicAccountsPresenter extends BasePresenter<PublicAccountsView> implements BaseQuickAdapter.OnItemClickListener {

    private DataManager mDataManager;

    private PublicAccountListAdapter publicAccountListAdapter;
    private List<PublicAcccountBean> publicAcccountBeanList = new ArrayList<>();
    private List<PublicAcccountBean> publicAcccountCacheList = new ArrayList<>();


    public PublicAccountsPresenter(PublicAccountsView baseView, Activity activity) {
        super(baseView, activity);
    }

    public void initAdapter(RecyclerView recyclerView) {
        publicAccountListAdapter = new PublicAccountListAdapter(R.layout.item_public_account, publicAcccountBeanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(publicAccountListAdapter);
        // 设置点击事件
        publicAccountListAdapter.setOnItemClickListener(this);

    }

    public void getList() {
        addSubscribe(apiServer.getPublicAccountList()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObservers<List<PublicAcccountBean>>(baseView) {
                    @Override
                    public void onNext(List<PublicAcccountBean> publicAcccountBeans) {
                        publicAcccountBeanList.addAll(publicAcccountBeans);
                        // 更新适配器
                        publicAccountListAdapter.notifyDataSetChanged();
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
        Toast.makeText(mActivity, publicAccountListAdapter.getItem(position).getName(), Toast.LENGTH_LONG).show();
    }

}
