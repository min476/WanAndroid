package com.vera.sample.wanandroid.custom;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.vera.sample.wanandroid.R;


public class HttpDialog extends Dialog {

	private String message = "";

	private TextView mTextView;

	public HttpDialog(Context context) {
		this(context, R.style.DialogStyle_1);
		setCanceledOnTouchOutside(false);
	}

	public HttpDialog(Context context, int theme) {
		super(context, theme);
		setContentView(R.layout.dialog_loading);
		initialViews();
	}

	private void initialViews() {
//		mTextView = (TextView) findViewById(R.id.dialog_text);
//		mTextView.setText("加载中...");
//		GifView gif = (GifView) findViewById(R.id.gif);
//		gif.setMovieResource(R.mipmap.hgh);
	}

	public void setMessage(String message) {
		this.message = message;
		mTextView.setText(message);
	}

	public void setColor() {
		mTextView.setTextColor(Color.RED);
	}

	public String getMessage() {
		return message;
	}

}
