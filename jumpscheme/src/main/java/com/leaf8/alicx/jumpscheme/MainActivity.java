package com.leaf8.alicx.jumpscheme;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


/**
 * 执行跳转
 */
public class MainActivity extends Activity {
    private EditText mJumpScheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJumpScheme = (EditText) findViewById(R.id.scheme_content);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        if (clipboard.hasPrimaryClip() && 0 < clipboard.getPrimaryClip().getItemCount()) {
            ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
            mJumpScheme.setText(item.coerceToText(this));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear:
                mJumpScheme.setText("");
                return true;
            case R.id.action_go:
                try {
                    String jumpScheme = mJumpScheme.getText().toString();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(jumpScheme));
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(this, "找不到应用，请确认链接", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
