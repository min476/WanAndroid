package com.vera.sample.wanandroid.bean.navigation;

import com.vera.sample.wanandroid.bean.FeedArticleBean;

import java.io.Serializable;
import java.util.List;


/**
 * File descripition: 导航数据实体
 *
 * @author: Vera
 * @date: 2019/3/1
 */

public class NavigationListBean implements Serializable {

    /**
     * "articles": [],
     * "cid": 272,
     * "name": "常用网站"
     */

    private List<FeedArticleBean> articles;
    private int cid;
    private String name;

    public List<FeedArticleBean> getArticles() {
        return articles;
    }

    public void setArticles(List<FeedArticleBean> articles) {
        this.articles = articles;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
