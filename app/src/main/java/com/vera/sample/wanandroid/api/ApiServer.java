package com.vera.sample.wanandroid.api;

import com.vera.sample.wanandroid.bean.BannerBean;
import com.vera.sample.wanandroid.bean.FeedArticleListBean;
import com.vera.sample.wanandroid.bean.project.ProjectClassifyBean;
import com.vera.sample.wanandroid.bean.project.ProjectListBean;
import com.vera.sample.wanandroid.bean.publicaccount_bean.PublicAcccountBean;
import com.vera.sample.wanandroid.bean.publicaccount_bean.PublicAccountListBean;
import com.vera.sample.wanandroid.mvp.BaseModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServer {

    /**
     *  获取首页banner
     *  http://wanandroid.com/banner/json
     * @return
     */
    @GET("banner/json")
    Observable<BaseModel<List<BannerBean>>> getBannerList();


    /**
     * 获取feed文章列表
     * http://wanandroid.com/article/list/20/json
     *
     * @param num 页数
     * @return feed文章列表数据
     */
    @GET("article/list/{num}/json")
    Observable<BaseModel<FeedArticleListBean>> getFeedArticleList(@Path("num") int num);


    /**
     *  获取公众号列表
     *  http://wanandroid.com/wxarticle/chapters/json
     *
     */
    @GET("wxarticle/chapters/json")
    Observable<BaseModel<List<PublicAcccountBean>>> getPublicAccountList();

    /**
     * 获取当前公众号某页的数据
     * http://wanandroid.com/wxarticle/list/408/1/json
     * @return
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseModel<PublicAccountListBean>> getPublicAccountListData(@Path("id") int id, @Path("page") int page);



    /**
     * 项目分类
     * http://www.wanandroid.com/project/tree/json
     *
     * @return 项目分类数据
     */
    @GET("project/tree/json")
    Observable<BaseModel<List<ProjectClassifyBean>>> getProjectClassifyData();

    /**
     * 项目类别数据
     * http://www.wanandroid.com/project/list/1/json?cid=294
     *
     * @param page page num
     * @param cid second page id
     * @return 项目类别数据
     */
    @GET("project/list/{page}/json")
    Observable<BaseModel<ProjectListBean>> getProjectListData(@Path("page") int page, @Query("cid") int cid);








}
