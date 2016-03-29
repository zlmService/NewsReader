package zlm.Thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhao on 2016/3/29.
 */

public class ImageThread extends Thread {
    String url;
    Bitmap bitmap = null;
    Handler handler;
    public ImageThread(String url,Handler handler) {
        this.url = url;
        this.handler=handler;
    }

    @Override
    public void run() {
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            System.out.println("run===" + bitmap);
//            imageView.setImageBitmap(bitmap);
            Message msg=new Message();
            msg.what=1;
            msg.obj=bitmap;
            handler.sendMessage(msg);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Bitmap getBitMap() {
        return bitmap;
    }
}
