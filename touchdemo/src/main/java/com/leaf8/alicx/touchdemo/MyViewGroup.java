package com.leaf8.alicx.touchdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.leaf8.alicx.touchdemo.log.MyViewObserve;

/**
 * VIewGroup 主要用来监听touch的事件流
 */
public class MyViewGroup extends LinearLayout {
    private MyViewObserve.onLogListener mLog;
    private boolean mIsIntercept = false;

    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        doLogon("in Dispatch", true);
        boolean d = super.dispatchTouchEvent(ev);
        doLogon("out dispatch", d);
        return d;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        doLogon("onInterceptTouchEvent", mIsIntercept);
        return mIsIntercept;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean o = super.onTouchEvent(event);
        doLogon(">>> onTouchEvent", o);
        return mIsIntercept;
    }

    public void isIntercept(boolean canIntercept) {
        mIsIntercept = canIntercept;
    }

    public void setLogListener(MyViewObserve.onLogListener log) {
        mLog = log;
    }

    private void doLogon(String msg, boolean flag) {
        StringBuilder sb = new StringBuilder("MyViewGroup(root view) : ");
        sb.append(msg).append(" : ").append(flag ? "true" : "false").append("\n");
        mLog.log(sb.toString());
    }
}
