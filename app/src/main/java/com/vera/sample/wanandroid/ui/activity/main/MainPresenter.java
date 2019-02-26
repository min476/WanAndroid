package com.vera.sample.wanandroid.ui.activity.main;

import android.app.Activity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.adapter.PublicAccountListAdapter;
import com.vera.sample.wanandroid.app.DataManager;
import com.vera.sample.wanandroid.bean.publicaccount_bean.PublicAcccountBean;
import com.vera.sample.wanandroid.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vera
 * @descripition:
 * @date 2019/1/30 15:23
 */

public class MainPresenter extends BasePresenter<MainView> implements BaseQuickAdapter.OnItemClickListener {

    private DataManager mDataManager;

    private PublicAccountListAdapter publicAccountListAdapter;
    private List<PublicAcccountBean>   publicAcccountBeanList = new ArrayList<>();
    private List<PublicAcccountBean>   publicAcccountCacheList = new ArrayList<>();


    public MainPresenter(MainView baseView, Activity activity) {
        super(baseView,activity);
    }


    public void initAdapter(RecyclerView recyclerView) {
        publicAccountListAdapter = new PublicAccountListAdapter(R.layout.item_public_account,publicAcccountBeanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(publicAccountListAdapter);
        // 设置点击事件
        publicAccountListAdapter.setOnItemClickListener(this);

    }


    /**
     *  获取公众号列表数据
     */
//    public void getList() {
//        addDisposable(apiServer.getPublicAccountList(), new BaseObserver(baseView) {
//            @Override
//            public void onSuccess(Object o) {
//                baseView.onMainSuccess((BaseModel<List<PublicAcccountBean>>) o);
//
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
     *  列表点击事件
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Toast.makeText(mActivity,publicAccountListAdapter.getItem(position).getName(),Toast.LENGTH_LONG).show();
    }

}
