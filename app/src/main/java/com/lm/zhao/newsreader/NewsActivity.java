package com.lm.zhao.newsreader;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import zlm.SQLite.UserContentProvider;
import zlm.SQLite.UserMata;
import zlm.Thread.JsonThread;

public class NewsActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity_layout);


        JsonThread thread = new JsonThread(this);
        thread.start();
        listView= (ListView) findViewById(R.id.listView_News);
        Uri uri=Uri.parse("content://com.zlm.news.ContentProvider/stories");
        Cursor cursor=getContentResolver().query(uri,null,null,null,null);
        NewsItemAdapter adapter=new NewsItemAdapter(this,cursor);
        listView.setAdapter(adapter);
    }


}




