package com.znke.pullupwebview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ExWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (ExWebView) findViewById(R.id.webview);
        mWebView.loadUrl("http://192.168.3.137:8080/ImgExplorer/index.jsp");
        webViewScrool();
    }

    //核心代码
    private void webViewScrool() {
        mWebView.setScrollerToBottomListener(new ExWebView.ScrollerToBottomListener() {
            @Override
            public void onReach() {
                Log.e(TAG, "onReach:  到了到了到了到了到了到了到了");
            }
        });
    }

}
