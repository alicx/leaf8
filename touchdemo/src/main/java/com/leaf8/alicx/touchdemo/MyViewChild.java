package com.leaf8.alicx.touchdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.leaf8.alicx.touchdemo.log.MyViewObserve;

/**
 * Created by alicx on 3/4/15.
 *
 * 子节点-也是叶子j节点，事件流监听
 * 子节点没有 onInterceptTouchEvent事件
 **/
public class MyViewChild extends TextView {
    private MyViewObserve.onLogListener mLog;
    private boolean mIsIntercept = false;

    public MyViewChild(Context context) {
        super(context);
    }

    public MyViewChild(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewChild(Context context, AttributeSet attrs, int defStyle) {
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
    public boolean onTouchEvent(MotionEvent event) {
        boolean o = super.onTouchEvent(event);
        doLogon(">>> onTouchEvent", o);
        return mIsIntercept;
    }

    public void setLogListener(MyViewObserve.onLogListener log) {
        mLog = log;
    }

    public void isIntercept(boolean canIntercept) {
        mIsIntercept = canIntercept;
    }

    private void doLogon(String msg, boolean flag) {
        StringBuilder sb = new StringBuilder("MyViewChild(leaf view) : ");
        sb.append(msg).append(" : ").append(flag ? "true" : "false").append("\n");
        mLog.log(sb.toString());
    }
}
