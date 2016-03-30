package com.lm.zhao.newsreader;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import zlm.Thread.JsonThread;

public class NewsActivity extends AppCompatActivity {
    private ListView listView;
    NewsItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity_layout);


        JsonThread thread = new JsonThread(this);
        thread.start();
        listView= (ListView) findViewById(R.id.listView_News);
//        Uri uri=Uri.parse("content://com.zlm.news.ContentProvider/stories");
//        Cursor cursor=getContentResolver().query(uri,null,null,null,null);
       adapter=new NewsItemAdapter(this,null);
        listView.setAdapter(adapter);

        getSupportLoaderManager().initLoader(0, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                Uri uri = Uri.parse("content://com.zlm.news.ContentProvider/stories");

                CursorLoader cursorLoader = new CursorLoader(NewsActivity.this, uri, null, null, null, null);
                return cursorLoader;
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                if (data!=null){
                    adapter.swapCursor(data);
                }
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {

            }
        });
    }


}




