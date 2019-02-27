package com.vera.sample.wanandroid.bean.project;

import com.vera.sample.wanandroid.bean.FeedArticleBean;

import java.util.List;


/**
 * File descripition: 项目frg数据实体类
 *
 * @author: Vera
 * @date: 2019/2/27
 */
public class ProjectListBean {

    /**
     * "curPage": 1,
     "datas": [],
     "offset": 0,
     "over": true,
     "pageCount": 1,
     "size": 15,
     "total": 8
     */

    private int curPage;
    private List<FeedArticleBean> datas;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;

    public int getCurPage() {

        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public List<FeedArticleBean> getDatas() {
        return datas;
    }

    public void setDatas(List<FeedArticleBean> datas) {
        this.datas = datas;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
