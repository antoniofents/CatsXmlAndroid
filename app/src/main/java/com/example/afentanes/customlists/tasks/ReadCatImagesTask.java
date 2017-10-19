package com.example.afentanes.customlists.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.afentanes.customlists.CatInfo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;



public class ReadCatImagesTask extends AsyncTask<CatInfo, Void, Bitmap> {

    private ImageView imageView;
    public ReadCatImagesTask(ImageView imageView){
        this.imageView= imageView;
    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected Bitmap doInBackground(CatInfo... catInfos) {
        try {
           return BitmapFactory.decodeStream((InputStream)new URL(catInfos[0].url).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
