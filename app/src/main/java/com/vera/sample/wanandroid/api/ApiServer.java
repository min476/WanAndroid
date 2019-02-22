package com.vera.sample.wanandroid.api;

import com.vera.sample.wanandroid.bean.BannerBean;
import com.vera.sample.wanandroid.bean.PublicAcccountBean;
import com.vera.sample.wanandroid.bean.publicaccount_bean.PublicAccountListBean;
import com.vera.sample.wanandroid.mvp.BaseModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiServer {
    //示例    多种类型请求方式

//    @POST("api/Activity/get_activities?")
//    Observable<BaseModel<List<>>> getApi1(@Query("time") String requestType);

//    @GET("api/Activity/get_activities?")
//    Observable<BaseModel<List<>>> getApi1(@Query("time") String requestType);

//    @FormUrlEncoded
//    @POST("api/Activity/get_activities?")
//    Observable<BaseModel<List<>>> getApi1(@Field("time") String requestType);

//    @FormUrlEncoded
//    @POST("api/Activity/get_activities?")
//    Observable<BaseModel<List<>>> getApi1(@FieldMap HashMap<String, String> params);

//    @Multipart
//    @POST("api/Activity/get_activities?")
//    Observable<BaseModel<List<>>> getApi1(@PartMap Map<String, RequestBody> map);

    // 获取历史列表
//    @GET("article/list/0/json")
//    Observable<BaseModel<List<HomeBeans.DatasBean>>> getList();


    /**
     *  获取首页banner
     *  http://wanandroid.com/banner/json
     * @return
     */
    @GET("banner/json")
    Observable<BaseModel<List<BannerBean>>> getBannerList();

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
    Observable<BaseModel<List<PublicAccountListBean>>> getPublicAccountListData();








}
