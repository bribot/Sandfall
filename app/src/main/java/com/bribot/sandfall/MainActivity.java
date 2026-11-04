package com.bribot.sandfall;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Insets;
import android.os.Build;
import android.os.Bundle;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;

/**
 * Hosts the Sandfall game (assets/index.html) in a WebView that covers the whole screen,
 * edge to edge and behind the camera cutout, with the system bars hidden.
 */
public class MainActivity extends Activity {

    private WebView web;
    private boolean pageLoaded;
    /** Safe insets (camera cutout etc.) in CSS px: top, right, bottom, left. */
    private final float[] insets = new float[4];

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        drawEdgeToEdge(win);

        web = new WebView(this);
        web.setBackgroundColor(0xFF080B14);
        web.setOverScrollMode(View.OVER_SCROLL_NEVER);
        web.setVerticalScrollBarEnabled(false);
        web.setHorizontalScrollBarEnabled(false);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);          // saved worlds, solved puzzles, settings
        s.setSupportZoom(false);
        s.setAllowFileAccess(false);
        s.setAllowContentAccess(false);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                pageLoaded = true;
                sendInsets();
            }
        });
        web.addJavascriptInterface(new Bridge(), "Android");
        web.setOnApplyWindowInsetsListener((v, in) -> {
            readInsets(in);
            sendInsets();
            return in;
        });
        setContentView(web);

        if (savedInstanceState != null) web.restoreState(savedInstanceState);
        else web.loadUrl("file:///android_asset/index.html");
        hideSystemBars();

        if (Build.VERSION.SDK_INT >= 33) registerBack();
    }

    @SuppressWarnings("deprecation")
    private void drawEdgeToEdge(Window win) {
        if (Build.VERSION.SDK_INT >= 30) {
            win.setDecorFitsSystemWindows(false);
        }
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.layoutInDisplayCutoutMode = Build.VERSION.SDK_INT >= 30
                    ? WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
                    : WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            win.setAttributes(lp);
        }
        win.setStatusBarColor(0x00000000);
        win.setNavigationBarColor(0x00000000);
    }

    @SuppressWarnings("deprecation")
    private void hideSystemBars() {
        if (Build.VERSION.SDK_INT >= 30) {
            WindowInsetsController c = getWindow().getInsetsController();
            if (c != null) {
                c.hide(WindowInsets.Type.systemBars());
                c.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
            }
        } else {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    /** Keeps the buttons clear of the camera cutout (and of the bars if the user swipes them in). */
    @SuppressWarnings("deprecation")
    private void readInsets(WindowInsets in) {
        int t, r, b, l;
        if (Build.VERSION.SDK_INT >= 30) {
            Insets i = in.getInsets(WindowInsets.Type.displayCutout() | WindowInsets.Type.systemBars());
            t = i.top; r = i.right; b = i.bottom; l = i.left;
        } else {
            t = in.getSystemWindowInsetTop(); r = in.getSystemWindowInsetRight();
            b = in.getSystemWindowInsetBottom(); l = in.getSystemWindowInsetLeft();
            if (Build.VERSION.SDK_INT >= 28) {
                DisplayCutout dc = in.getDisplayCutout();
                if (dc != null) {
                    t = Math.max(t, dc.getSafeInsetTop()); r = Math.max(r, dc.getSafeInsetRight());
                    b = Math.max(b, dc.getSafeInsetBottom()); l = Math.max(l, dc.getSafeInsetLeft());
                }
            }
        }
        float d = getResources().getDisplayMetrics().density;
        insets[0] = t / d; insets[1] = r / d; insets[2] = b / d; insets[3] = l / d;
    }

    private void sendInsets() {
        if (!pageLoaded) return;
        web.evaluateJavascript(String.format(java.util.Locale.US,
                "window.setInsets&&setInsets(%.1f,%.1f,%.1f,%.1f)",
                insets[0], insets[1], insets[2], insets[3]), null);
    }

    /** Back closes an open menu first; otherwise the app closes. */
    private void handleBack() {
        web.evaluateJavascript("window.onAndroidBack?onAndroidBack():false", result -> {
            if (!"true".equals(result)) finish();
        });
    }

    @TargetApi(33)
    private void registerBack() {
        OnBackInvokedCallback cb = this::handleBack;
        getOnBackInvokedDispatcher().registerOnBackInvokedCallback(OnBackInvokedDispatcher.PRIORITY_DEFAULT, cb);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBackPressed() {   // Android 12 and older
        handleBack();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) hideSystemBars();
    }

    @Override
    protected void onPause() {
        web.evaluateJavascript("window.autosave&&autosave()", null);   // keep the sandbox if Android closes the app later
        web.onPause();
        web.pauseTimers();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        web.resumeTimers();
        web.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        web.saveState(outState);
    }

    /** Called from JavaScript as window.Android.* */
    private class Bridge {
        /** The Exit button in the options menu. */
        @JavascriptInterface
        public void exit() {
            runOnUiThread(MainActivity.this::finishAndRemoveTask);
        }
    }

    @Override
    protected void onDestroy() {
        web.destroy();
        super.onDestroy();
    }
}
