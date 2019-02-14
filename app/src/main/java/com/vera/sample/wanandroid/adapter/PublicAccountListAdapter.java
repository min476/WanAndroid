package com.vera.sample.wanandroid.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.bean.PublicAcccountBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/*
 * Description: 公众号列表适配器
 *
 * Author: Vera
 * Date: 2019/2/11
 */
public class PublicAccountListAdapter extends BaseQuickAdapter<PublicAcccountBean, BaseViewHolder> {
    List<PublicAcccountBean> listData = new ArrayList<>();

    public PublicAccountListAdapter(int layoutResId, @Nullable List<PublicAcccountBean> data) {
        super(layoutResId, data);
        this.listData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, PublicAcccountBean item) {
        helper.setText(R.id.item_public_account_title,item.getName());
        helper.setText(R.id.item_public_account_visible,String.valueOf(item.getVisible()));

    }
}
