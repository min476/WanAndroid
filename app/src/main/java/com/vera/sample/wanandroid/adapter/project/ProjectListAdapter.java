package com.vera.sample.wanandroid.adapter.project;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.bean.project.ProjectClassifyBean;
import com.vera.sample.wanandroid.bean.publicaccount_bean.PublicAcccountBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/*
 * Description: 项目分类适配器
 *
 * Author: Vera
 * Date: 2019/2/27
 */
public class ProjectListAdapter extends BaseQuickAdapter<ProjectClassifyBean, BaseViewHolder> {
    List<ProjectClassifyBean> listData = new ArrayList<>();

    public ProjectListAdapter(int layoutResId, @Nullable List<ProjectClassifyBean> data) {
        super(layoutResId, data);
        this.listData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectClassifyBean item) {
        helper.setText(R.id.item_public_account_title,item.getName());
        helper.setText(R.id.item_public_account_visible,String.valueOf(item.getVisible()));

    }
}
