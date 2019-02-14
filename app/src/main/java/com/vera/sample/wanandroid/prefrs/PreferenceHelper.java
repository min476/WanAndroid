package com.vera.sample.wanandroid.prefrs;

/**
 * @author quchao
 * @date 2017/11/27
 */

public interface PreferenceHelper {

    /**
     * Set current page
     *
     * @param position Position
     */
    void setCurrentPage(int position);

    /**
     * Get current page
     *
     * @return current page
     */
    int getCurrentPage();

    /**
     * Set project current page
     *
     * @param position Position
     */
    void setProjectCurrentPage(int position);

    /**
     * Get project current page
     *
     * @return current page
     */
    int getProjectCurrentPage();


}
