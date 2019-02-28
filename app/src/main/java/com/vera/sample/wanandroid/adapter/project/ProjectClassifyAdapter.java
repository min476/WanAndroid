package com.vera.sample.wanandroid.adapter.project;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
public class ProjectClassifyAdapter extends BaseQuickAdapter<FeedArticleBean, BaseViewHolder> {
    List<FeedArticleBean> listData = new ArrayList<>();

    public ProjectClassifyAdapter(int layoutResId, @Nullable List<FeedArticleBean> data) {
        super(layoutResId, data);
        this.listData = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, FeedArticleBean item) {
        ImageView projectImg =  helper.getView(R.id.item_project_img); ;

        // 项目标题
        helper.setText(R.id.item_project_classify_title,item.getTitle());
        // 项目描述
        helper.setText(R.id.item_project_classify_content,item.getDesc());
        // 项目作者及日期
        helper.setText(R.id.item_project_classify_time,item.getAuthor()+"  "+item.getNiceDate());

        // 加载项目图片
        Glide.with(mContext).load(item.getEnvelopePic()).into(projectImg);

        // 不显示背景
        helper.setVisible(R.id.item_project_bg, false);

    }
}
