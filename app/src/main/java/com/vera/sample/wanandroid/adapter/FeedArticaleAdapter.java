package com.vera.sample.wanandroid.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.bean.FeedArticleBean;
import com.vera.sample.wanandroid.bean.publicaccount_bean.PublicAccountListBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/*
 * Description: 首页列表适配器
 *
 * Author: Vera
 * Date: 2019/2/27
 */
public class FeedArticaleAdapter extends BaseQuickAdapter<FeedArticleBean, BaseViewHolder> {
    List<FeedArticleBean> listData = new ArrayList<>();

    public FeedArticaleAdapter(int layoutResId, @Nullable List<FeedArticleBean> data) {
        super(layoutResId, data);
        this.listData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, FeedArticleBean item) {
        // 公众号作者
        helper.setText(R.id.item_public_account_classify_title, item.getChapterName());
        // 描述
        helper.setText(R.id.item_public_account_classify_content, item.getTitle());
        // 日期
        helper.setText(R.id.item_public_account_classify_time, "发布日期：" + item.getNiceDate());
        // 不显示背景
        helper.setVisible(R.id.item_public_account_bg, false);

        // 判断是否是最新的数据 最新数据则显示 NEW
        if (item.getNiceDate().contains(mContext.getString(R.string.minute))
                || item.getNiceDate().contains(mContext.getString(R.string.hour))
                || item.getNiceDate().contains(mContext.getString(R.string.one_day))) {
            helper.setVisible(R.id.item_public_account_classify_new, true);
        } else {
            helper.setVisible(R.id.item_public_account_classify_new, false);
        }
    }
}
