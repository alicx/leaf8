package com.leaf8.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * demo主入口
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.touchDemo:
                startActivity(new Intent(MainActivity.this, TouchActivity.class));
                break;
        }
    }
}
