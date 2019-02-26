package com.vera.sample.wanandroid.ui.activity.webview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.base.BaseActivity;
import com.vera.sample.wanandroid.mvp.BasePresenter;

import butterknife.BindView;

/**
 * File descripition: webview通用加载页面
 *
 * @author: Vera
 * @date: 2019/2/26
 */

public class CommonWebActivity extends BaseActivity {

    private String title = "";
    private String url = "";

    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) { }

    @Override
    protected void initData() {
        tvBack.setVisibility(View.VISIBLE);
        tvTitle.setTextSize(12);
        init();
    }

    private void init() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setHorizontalScrollbarOverlay(true);
        mWebView.setWebViewClient(getViewClient());

        initIntentData();
        loadData();
    }

    /**
     * 设置状态栏高度
     * @return
     */
    @Override
    protected int _topBarAdaptiveVH() {
        return R.id.comm_topBarSteep;
    }

    private void initIntentData() {
        Intent intent = getIntent();
        if (null != intent) {
            title = intent.getStringExtra("title");
            setTitle(title);
            url = intent.getStringExtra("url");
        }
    }

    private WebViewClient getViewClient() {
        return new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        };
    }

    private WebChromeClient getChromeClient() {
        return new WebChromeClient(){

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        };
    }

    private void loadData() {
        mWebView.loadUrl(url);
    }


}
