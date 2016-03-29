package com.lm.zhao.newsreader;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import zlm.SQLite.UserMata;
import zlm.Thread.ImageThread;
import zlm.Thread.JsonThread;

/**
 * Created by zhao on 2016/3/29.
 */
public class NewsItemAdapter extends CursorAdapter {




   public NewsItemAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    public NewsItemAdapter(Context context, Cursor c) {
        super(context, c, FLAG_AUTO_REQUERY);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item_layout, parent, false);
        new ViewHolder(view);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        final ViewHolder holder = (ViewHolder) view.getTag();
        String imageUrl=cursor.getString(cursor.getColumnIndex(UserMata.Stories.IMAGE));
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what==1){
                   Bitmap bitmap = (Bitmap) msg.obj;
                    holder.iv_images.setImageBitmap(bitmap);// 最后把 bitmap传进来.
                }
            }
        };
        ImageThread image=new ImageThread(imageUrl,handler); //得到数据库image的url  然后请求
        image.start();

//        Bitmap bitMap = image.getBitMap();
//        System.out.println(bitMap);

        holder.tv_title.setText(cursor.getString(cursor.getColumnIndex(UserMata.Stories.TITLE)));
    }

    public class ViewHolder {
        ImageView iv_images;
        TextView tv_title;
        //        TextView tv_id;
        //        TextView tv_type;
    //     TextView ga_prefix;
        ViewHolder(View v) {
            iv_images = (ImageView) v.findViewById(R.id.iv_images);
            tv_title = (TextView) v.findViewById(R.id.tv_title);
//            tv_id= (TextView) v.findViewById(R.id.tv_id);
//            tv_type= (TextView) v.findViewById(R.id.tv_type);
//            ga_prefix= (TextView) v.findViewById(R.id.tv_ga_prefix);
            v.setTag(this);
        }

    }
}
