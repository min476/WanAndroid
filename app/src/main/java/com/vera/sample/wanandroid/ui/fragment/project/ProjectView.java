package com.vera.sample.wanandroid.ui.fragment.project;



import com.vera.sample.wanandroid.bean.FeedArticleBean;
import com.vera.sample.wanandroid.bean.project.ProjectClassifyBean;
import com.vera.sample.wanandroid.bean.project.ProjectListBean;
import com.vera.sample.wanandroid.bean.publicaccount_bean.PublicAcccountBean;
import com.vera.sample.wanandroid.mvp.BaseView;

import java.util.List;

/**
 * File descripition: 公众号
 *
 * @author: Vera
 * @date: 2019/2/14
 */

public interface ProjectView extends BaseView {
//    void onMainSuccess(BaseModel<List<HomeBeans.DatasBean>> list);

    void setProjectTab(List<ProjectClassifyBean> projectTab);
}
