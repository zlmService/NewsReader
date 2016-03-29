package zlm.Thread;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import zlm.SQLite.UserMata;
import zlm.user.Stories;
import zlm.user.User;

/**
 * Created by zhao on 2016/3/29.
 */
public class JsonThread extends Thread {
    String url = "http://news-at.zhihu.com/api/4/stories/before/20160327";
    Context context;
    public JsonThread(Context context){
        this.context=context;
    }
    @Override
    public void run() {
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            System.out.println("conn====" + conn);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setReadTimeout(50000);
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader sr = new BufferedReader(new InputStreamReader(is));

            String line;
            StringBuffer sb = new StringBuffer();
            while ((line = sr.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb.toString());
            Gson gson=new Gson();
            User user = gson.fromJson(sb.toString(), User.class);
            System.out.println(user.toString());


            context.getContentResolver().delete(Uri.parse("content://com.zlm.news.ContentProvider/stories"),null,null);
            List<Stories> stories=user.getStories();
            for (int i = 0; i < stories.size(); i++) {
                Stories stories1 = stories.get(i); //获取这个Item 对 应的 图片和title
                String[] images = stories1.getImages();
                //添加到数据库中
                ContentValues values=new ContentValues();
                values.put(UserMata.Stories.IMAGE,images[0]);
                System.out.println(images[0].toString());
                values.put(UserMata.Stories.TITLE, stories1.getTitle());
                System.out.println(stories1.getTitle());
                context.getContentResolver().insert(Uri.parse("content://com.zlm.news.ContentProvider/stories"), values);

                Log.i("info",stories1.toString());
            }
            System.out.println(sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
