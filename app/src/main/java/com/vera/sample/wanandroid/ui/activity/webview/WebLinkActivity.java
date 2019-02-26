package com.vera.sample.wanandroid.ui.activity.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
public class WebLinkActivity extends BaseActivity {

    @BindView(R.id.webView)
     WebView webView;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static void load(Context context, String title, String linkUrl) {
        Intent intent = new Intent(context, WebLinkActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("linkUrl", linkUrl);
        context.startActivity(intent);

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        tvBack.setVisibility(View.VISIBLE);
        tvTitle.setTextSize(14);
        String productName = getIntent().getStringExtra("title");
        String productLink = getIntent().getStringExtra("linkUrl");
        setTitle(productName);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        tvTitle.setText(productName);

        webView.loadUrl(productLink);
    }


    @Override
    protected void back() {
        goBack();
    }


    @Override
    public void onBackPressed() {
        goBack();
    }

    private void goBack() {
        if (webView == null || !webView.canGoBack()) {
            finish();
        } else {
            webView.goBack();
        }
    }
}
