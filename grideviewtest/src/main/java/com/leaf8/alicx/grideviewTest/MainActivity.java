package com.leaf8.alicx.grideviewTest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GrideAdapater imageAdapter = new GrideAdapater(this);
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(imageAdapter);
    }
}
