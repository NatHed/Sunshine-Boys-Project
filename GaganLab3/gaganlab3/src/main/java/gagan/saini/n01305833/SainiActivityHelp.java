package gagan.saini.n01305833;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class SainiActivityHelp extends AppCompatActivity {


    private WebView web;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saini_help);



        web = findViewById(R.id.gaganwebviewid);

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SainiActivityHelp.this, R.string.helptoast, Toast.LENGTH_SHORT).show();
            }
        });


        web.setWebViewClient(new WebViewClient());
        web.loadUrl(getString(R.string.websitelink));

    }

    @Override
    public void onBackPressed() {
        if(web.canGoBack())
        {
            web.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }
}



