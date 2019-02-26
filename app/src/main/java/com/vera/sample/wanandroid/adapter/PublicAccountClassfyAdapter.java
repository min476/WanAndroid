package com.vera.sample.wanandroid.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.bean.publicaccount_bean.PublicAcccountBean;
import com.vera.sample.wanandroid.bean.publicaccount_bean.PublicAccountListBean;
import com.vera.sample.wanandroid.bean.publicaccount_bean.PublicAccountListBeans;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/*
 * Description: 公众号子分类列表适配器
 *
 * Author: Vera
 * Date: 2019/2/26
 */
public class PublicAccountClassfyAdapter extends BaseQuickAdapter<PublicAccountListBean.DatasBean, BaseViewHolder> {
    List<PublicAccountListBean.DatasBean> listData = new ArrayList<>();

    public PublicAccountClassfyAdapter(int layoutResId, @Nullable List<PublicAccountListBean.DatasBean> data) {
        super(layoutResId, data);
        this.listData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, PublicAccountListBean.DatasBean item) {
        // 公众号作者
        helper.setText(R.id.item_public_account_classfy_title,item.getChapterName());
        // 描述
        helper.setText(R.id.item_public_account_classfy_content,item.getTitle());
        // 日期
        helper.setText(R.id.item_public_account_classfy_time,"发布日期："+item.getNiceDate());

        // 判断是否是最新的数据 最新数据则显示 NEW
        if (item.getNiceDate().contains(mContext.getString(R.string.minute))
                || item.getNiceDate().contains(mContext.getString(R.string.hour))
                || item.getNiceDate().contains(mContext.getString(R.string.one_day))) {
            helper.setVisible(R.id.item_public_account_classfy_new,true);
        }else {
            helper.setVisible(R.id.item_public_account_classfy_new,false);
        }
    }
}
