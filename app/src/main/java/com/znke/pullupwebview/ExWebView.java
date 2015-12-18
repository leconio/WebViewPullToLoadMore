package com.znke.pullupwebview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * Created by liucl on 2015/12/18.
 */
public class ExWebView extends WebView {

    private static final float OFFSET = 1.0f;

    /**
     * 为了防止被回调多次
     */
    private boolean flag = true;

    /**
     * 从调用者那里返回，是否还有更多
     */
    private boolean isMore = true;

    public ExWebView(Context context) {
        super(context);
    }

    public ExWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ExWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (((getTotalHeight() - getCurrentHeight()) <= OFFSET)
                && mScrollerToBottomListener != null
                && flag
                && isMore) {
            isMore = mScrollerToBottomListener.onReach();
            flag = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            //手指按下时候重置操作
            flag = true;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 总高度
     */
    public float getTotalHeight(){
        return getContentHeight() * getScale();
    }

    /**
     * 现在的高度
     * @return
     */
    public float getCurrentHeight(){
        return getHeight() + getScrollY();
    }

    private ScrollerToBottomListener mScrollerToBottomListener;

    public void setOnScrollToBottomListener(ScrollerToBottomListener mScrollerToBottomListener) {
        this.mScrollerToBottomListener = mScrollerToBottomListener;
    }

    public interface ScrollerToBottomListener{
        /**
         * 上拉加载更多时候回调，
         * @return 是否有更多数据
         */
        boolean onReach();
    }

}
