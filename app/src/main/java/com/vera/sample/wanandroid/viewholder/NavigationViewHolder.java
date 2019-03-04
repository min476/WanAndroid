package com.vera.sample.wanandroid.viewholder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.vera.sample.wanandroid.R;
import com.zhy.view.flowlayout.FlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author quchao
 * @date 2018/3/14
 */

public class NavigationViewHolder extends BaseViewHolder {

    @BindView(R.id.item_navigation_tv)
    TextView mTitle;
    @BindView(R.id.item_navigation_flow_layout)
    FlowLayout mFlowLayout;

    public NavigationViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
