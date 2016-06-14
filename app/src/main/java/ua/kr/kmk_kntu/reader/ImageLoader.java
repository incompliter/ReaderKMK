package ua.kr.kmk_kntu.reader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by cooper on 14.06.16.
 */
public class ImageLoader extends AsyncTask<String,Void,String> {
    private String imgName,urlImage;
    private ImageView view;
    private Bitmap bitmap;
    public ImageLoader(String urlImage,ImageView view,String imgName){
        this.urlImage= urlImage;
        this.view= view;
        this.imgName= imgName;
    }

    @Override
    protected String doInBackground(String... params) {

            try {
                URL url = new URL(urlImage);
                URLConnection connection = url.openConnection();
                bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
       view.setImageBitmap(bitmap);
    }
}
