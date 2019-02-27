package com.vera.sample.wanandroid.bean.project;

/**
 * File descripition: 项目frg数据实体类
 *
 * @author: Vera
 * @date: 2019/2/27
 */

public class ProjectClassifyBean {

    /**
     *   "children": [ ],
     "courseId": 13,
     "id": 294,
     "name": "完整项目",
     "order": 145000,
     "parentChapterId": 293,
     "visible": 0
     */

    private int courseId;
    private int id;
    private String name;
    private int order;
    private int parentChapterId;
    private int visible;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }
}
