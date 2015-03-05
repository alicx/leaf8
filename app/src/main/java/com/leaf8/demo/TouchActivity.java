package com.leaf8.demo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.leaf8.alicx.touchdemo.MyViewChild;
import com.leaf8.alicx.touchdemo.MyViewGroup;
import com.leaf8.alicx.touchdemo.log.MyViewObserve;

/**
 * touch传递的实例
 * activity，viewGroup，view之间的传递关系
 * touch事件：dispatchTouchEvent、onInterceptTouchEvent、onTouchEvent、onTouch、onTouchListener
 */
public class TouchActivity extends ActionBarActivity {
    private boolean mCanOnTouch = false;

    private MyViewGroup mRootView = null;
    private MyViewChild mChildView = null;
    private TextView mLogView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        mRootView = (MyViewGroup) findViewById(R.id.rootView);
        mChildView = (MyViewChild) findViewById(R.id.childView);
        mLogView = (TextView) findViewById(R.id.logView);

        mRootView.setLogListener(mListener);
        mRootView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                StringBuilder sb = new StringBuilder("MyViewGroup(root view) : ");
                sb.append("<< ontouchListener >>\n");
                mLogView.append(sb.toString());
                return true;
            }
        });

        mChildView.setLogListener(mListener);
        mChildView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                StringBuilder sb = new StringBuilder("MyViewChild(leaf view) : ");
                sb.append("<< ontouchListener >>\n");
                mLogView.append(sb.toString());
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_touch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_none:
                mCanOnTouch = false;
                mRootView.isIntercept(false);
                mLogView.setText(R.string.logTitle);
                return true;
            case R.id.action_activity:
                mCanOnTouch = true;
                mRootView.isIntercept(false);
                mLogView.setText(R.string.logTitle);
                return true;
            case R.id.action_root:
                mCanOnTouch = false;
                mRootView.isIntercept(true);
                mLogView.setText(R.string.logTitle);
                return true;
            case R.id.action_child:
                mCanOnTouch = false;
                mRootView.isIntercept(true);
                mLogView.setText(R.string.logTitle);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        doLogon("-> onKeyDown", mCanOnTouch);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        doLogon("in Dispatch", true);
        boolean d = super.dispatchTouchEvent(ev);
        doLogon("out dispatch", d);
        return d;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        doLogon("-> onKeyUp", mCanOnTouch);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean o = super.onTouchEvent(event);
        doLogon(">>> onTouchEvent", o);
        return o;
    }

    private MyViewObserve.onLogListener mListener = new MyViewObserve.onLogListener() {

        @Override
        public void log(String msg) {
            mLogView.append(msg);
        }
    };

    private void doLogon(String msg, boolean flag) {
        StringBuilder sb = new StringBuilder("Activity : ");
        sb.append(msg).append(" : ").append(flag ? "true" : "false").append("\n");
        mLogView.append(sb.toString());
    }
}
