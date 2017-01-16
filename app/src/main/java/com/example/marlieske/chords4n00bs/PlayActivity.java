package com.example.marlieske.chords4n00bs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.webkit.WebView;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {
Song song;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Intent playSong = getIntent();
        Bundle data = playSong.getExtras();
        song = data.getParcelable("content");
        setInfo();
    }

    public void setInfo(){

        // get our html content
        String htmlAsString = song.content;
        Spanned htmlAsApanned = Html.fromHtml(htmlAsString);
    //    Spanned htmlAsSpanned = Html(htmlAsString); // used by TextView

        // set the html content on a TextView
//        TextView textView = (TextView) findViewById(R.id.textView4);
//        textView.setText(htmlAsApanned);

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.loadDataWithBaseURL(null, htmlAsString, "text/html", "utf-8", null);


    }
}
