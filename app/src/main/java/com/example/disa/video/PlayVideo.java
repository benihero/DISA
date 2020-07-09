package com.example.disa.video;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.disa.R;


public class PlayVideo extends Activity {

    String videourl;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);


        setContentView(R.layout.activity_play_video);


        final ProgressBar Pbar;

        Pbar = (ProgressBar) findViewById(R.id.pB4);
        WebView wv = (WebView) findViewById(R.id.webVie1);
//wv.setWebViewClient(new Callback());
        WebSettings webSettings = wv.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
//wv.setBackgroundColor(0x919191);
        final String mimeType = "text/html";
        final String encoding = "UTF-8";
        String html = getHTML();
        wv.loadDataWithBaseURL("", html, mimeType, encoding, "");


        final Activity activity = this;

        wv.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%

                activity.setProgress(progress * 100);
                {
                    if (progress < 100 && Pbar.getVisibility() == ProgressBar.GONE) {
                        Pbar.setVisibility(ProgressBar.VISIBLE);

                    }
                    Pbar.setProgress(progress);
                    if (progress == 100) {
                        Pbar.setVisibility(ProgressBar.GONE);

                    }
                }
            }
        });
        wv.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_LONG).show();
            }
        });
        wv.setDownloadListener(new DownloadListener() {

            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                finish();
            }


        });


    }

    private String getHTML() {


         videourl = getIntent().getStringExtra("EXTRA_SESSION_ID");
        // TODO Auto-generated method stub
        String html1 = "<iframe class=\"youtube-player\" style=\"border: 0; width: 100%; height: 95%; padding:0px; margin:0px\" id=\"ytplayer\" type=\"text/html\" src=\"http://www.youtube.com/embed/"
                + videourl
                + "?fs=0\" frameborder=\"0\">\n"
                + "</iframe>\n";
        return html1;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        onPause();
    }

    public void onPause()
    {
        super.onPause();
        System.exit(0);
    }


}

