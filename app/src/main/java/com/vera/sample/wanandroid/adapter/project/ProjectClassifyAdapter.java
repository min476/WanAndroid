package com.vera.sample.wanandroid.adapter.project;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.bean.FeedArticleBean;
import com.vera.sample.wanandroid.bean.project.ProjectClassifyBean;
import com.vera.sample.wanandroid.bean.publicaccount_bean.PublicAccountListBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/*
 * Description: 项目分类列表适配器
 *
 * Author: Vera
 * Date: 2019/2/27
 */
public class ProjectClassifyAdapter extends BaseQuickAdapter<ProjectClassifyBean, BaseViewHolder> {
    List<ProjectClassifyBean> listData = new ArrayList<>();

    public ProjectClassifyAdapter(int layoutResId, @Nullable List<ProjectClassifyBean> data) {
        super(layoutResId, data);
        this.listData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectClassifyBean item) {
//        // 公众号作者
//        helper.setText(R.id.item_public_account_classfy_title,item.getChapterName());
//        // 描述
//        helper.setText(R.id.item_public_account_classfy_content,item.getTitle());
//        // 日期
//        helper.setText(R.id.item_public_account_classfy_time,"发布日期："+item.getNiceDate());
//
//        // 判断是否是最新的数据 最新数据则显示 NEW
//        if (item.getNiceDate().contains(mContext.getString(R.string.minute))
//                || item.getNiceDate().contains(mContext.getString(R.string.hour))
//                || item.getNiceDate().contains(mContext.getString(R.string.one_day))) {
//            helper.setVisible(R.id.item_public_account_classfy_new,true);
//        }else {
//            helper.setVisible(R.id.item_public_account_classfy_new,false);
//        }
    }
}
