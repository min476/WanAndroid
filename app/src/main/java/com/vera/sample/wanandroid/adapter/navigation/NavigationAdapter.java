package com.vera.sample.wanandroid.adapter.navigation;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.bean.FeedArticleBean;
import com.vera.sample.wanandroid.bean.navigation.NavigationListBean;
import com.vera.sample.wanandroid.ui.activity.webview.WebLinkActivity;
import com.vera.sample.wanandroid.utils.CommonUtils;
import com.vera.sample.wanandroid.viewholder.NavigationViewHolder;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import androidx.annotation.Nullable;


/**
 * @author quchao
 * @date 2018/3/2
 */

public class NavigationAdapter extends BaseQuickAdapter<NavigationListBean, NavigationViewHolder> {

    public NavigationAdapter(int layoutResId, @Nullable List<NavigationListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(NavigationViewHolder helper, NavigationListBean item) {
        if (!TextUtils.isEmpty(item.getName())) {
            helper.setText(R.id.item_navigation_tv, item.getName());
        }
        TagFlowLayout mTagFlowLayout = helper.getView(R.id.item_navigation_flow_layout);
        List<FeedArticleBean> mArticles = item.getArticles();
        mTagFlowLayout.setAdapter(new TagAdapter<FeedArticleBean>(mArticles) {
            @Override
            public View getView(FlowLayout parent, int position, FeedArticleBean feedArticleData) {
                TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.flow_layout_tv,
                        mTagFlowLayout, false);
                if (feedArticleData == null) {
                    return null;
                }
                String name = feedArticleData.getTitle();
                tv.setPadding(CommonUtils.dp2px(10), CommonUtils.dp2px(10),
                        CommonUtils.dp2px(10), CommonUtils.dp2px(10));
                tv.setText(name);
                tv.setTextColor(CommonUtils.randomColor());
                mTagFlowLayout.setOnTagClickListener((view, position1, parent1) -> {
                    startNavigationPager(view, position1, parent, mArticles);
                    return true;
                });
                return tv;
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void startNavigationPager(View view, int position1, FlowLayout parent2, List<FeedArticleBean> mArticles) {
//        ActivityOptions options = ActivityOptions.makeScaleUpAnimation(view,
//                view.getWidth() / 2,
//                view.getHeight() / 2,
//                0 ,
//                0);
//        JudgeUtils.startArticleDetailActivity(parent2.getContext(),
//                options,
//                mArticles.get(position1).getId(),
//                mArticles.get(position1).getTitle(),
//                mArticles.get(position1).getLink(),
//                mArticles.get(position1).isCollect(),
//                false,
//                false);
        WebLinkActivity.load(mContext, mArticles.get(position1).getTitle(), mArticles.get(position1).getLink());
    }

}
