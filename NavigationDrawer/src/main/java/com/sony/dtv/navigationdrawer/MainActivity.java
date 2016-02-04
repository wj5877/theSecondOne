package com.sony.dtv.navigationdrawer;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mLvLeftMenu;
    WebView webView;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String externalStorageDirectory = Environment.getExternalStorageDirectory().toString();
        Log.i("navigigi",externalStorageDirectory);
        final String  APK_FILE_PATH="/data/data/com.sony.dtv.mysony/files/autoupdate";
        final String  APK_FILE_NAME="MySony.apk";
        setContentView(R.layout.content_main);

     /*   Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        webView = (WebView) findViewById(R.id.webview);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout);
        mLvLeftMenu = (ListView) findViewById(R.id.id_lv_left_menu);

        toolbar.setTitle("");
        toolbar.setSubtitle("ddd");
        toolbar.setLogo(R.drawable.ic_drawer);
        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        toolbar.setFocusable(false);
        String s4 = getFilesDir().toString();
        Log.i("navigaga",s4);
        webView.setFocusable(true);
        webView.requestFocus();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setNeedInitialFocus(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.loadUrl("http://192.168.1.100:8080/test.html");*/
        String dataDirectory = Environment.getDataDirectory().toString();
        String s = Environment.getDownloadCacheDirectory().toString();
        String s2 = Environment.getRootDirectory().toString();
        String s3 = getCacheDir().toString();
        String s1 = getExternalCacheDir().toString();

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(APK_FILE_PATH, APK_FILE_NAME)),
                        "application/vnd.android.package-archive");
                startActivity(intent);
            }
        });

        Log.i("directory invesigate",dataDirectory+":::"+s+":::"+s2+":::"+s3+":::"+s1);
        //  toolbar.setLogo(R.drawable.head);
        // setSupportActionBar(toolbar);
        //toolbar.setNavigationIcon(R.drawable.head);

        //final ActionBar ab = getSupportActionBar();

        // ab.setDisplayHomeAsUpEnabled(true);
        //ab.setDisplayShowHomeEnabled(false);
        // ab.setHomeButtonEnabled(false);
        // ab.setHomeAsUpIndicator(R.drawable.ic_drawer);

        //setupDrawerContent(mNavigationView);
       // setUpDrawer();
    }

    @Override
    protected void onResume() {
        View currentFocus = getCurrentFocus();
        Log.i("navigaga", "currentFocus===onResume=====" + currentFocus);
        super.onResume();
    }

    private void setUpDrawer() {
        LayoutInflater inflater = LayoutInflater.from(this);
        mLvLeftMenu.addHeaderView(inflater.inflate(R.layout.header_just_username, mLvLeftMenu, false));
        mLvLeftMenu.setAdapter(new MenuItemAdapter(this));
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


  /*  @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                mDrawerLayout.openDrawer(GravityCompat.START);
                mLvLeftMenu.requestFocus();
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
*/
   /* @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            View currentFocus = getCurrentFocus();
            Log.i("navigaga", "currentFocus========" + currentFocus);
            return true;
        } else if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            View currentFocus = getCurrentFocus();
            Log.i("navigaga", "currentFocus========" + currentFocus);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }*/

    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
           Log.i("navigaga","errorCode::"+errorCode);
        }

        /*@Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            Log.i("navigaga",event.getKeyCode()+"");
            return super.shouldOverrideKeyEvent(view, event);
        }*/
    }
}
