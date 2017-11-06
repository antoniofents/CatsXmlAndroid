package com.example.afentanes.customlists.tasks;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.afentanes.customlists.CatInfo;
import com.example.afentanes.customlists.CatsAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ReadCatsTask  extends AsyncTask<String, Void, List <CatInfo>> {



    @Override
    protected List <CatInfo> doInBackground(String... strings) {
       return  getCats();
    }



    //instantiate XML pull parser with a stream of cats from getCatsInfoStream
     List<CatInfo> getCats(){
         InputStream catsInfoStream =null;
        try{
         XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
           //get stream
             catsInfoStream = getCatsInfoStream();
            parser.setInput(catsInfoStream, null);
            parser.nextTag();
            return readCatsInfo(parser, catsInfoStream);

            
        }catch (Exception e) {
            Log.i("Main", "error abriendo stream");
        }

        return Collections.emptyList();
    }

    //get cats info stream, method separated to make possible unittests
    InputStream getCatsInfoStream(){
        HttpURLConnection urlConnection =null;
        try {
            URL url = new URL("http://thecatapi.com/api/images/get?format=xml&results_per_page=20");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            return inputStream;
        }catch (Exception e) {
            Log.i("Main", "error llamando url");
        } finally {
            if(urlConnection!=null) urlConnection.disconnect();
        }

        return null;

    }
    //Cat info parsing
    private List<CatInfo> readCatsInfo(XmlPullParser parser , InputStream catsStream) throws XmlPullParserException, IOException {
        List <CatInfo> currentCats= new ArrayList<>();
        try{

                parser.require(XmlPullParser.START_TAG, null, "response");
                while (parser.next() != XmlPullParser.END_DOCUMENT) {

                    if (parser.getEventType() == XmlPullParser.START_TAG ) {
                        CatInfo catInfo= new CatInfo();
                        String name = parser.getName();
                        if(name.equals("image")){
                            parser.nextTag();
                            catInfo.url= parser.nextText();
                            parser.nextTag();;
                            catInfo.id = parser.nextText();
                            parser.nextTag();
                            catInfo.source = parser.nextText();
                            currentCats.add(catInfo);

                        }
                    }
                }
        }catch (Exception e) {
            Log.i("Main", "error al parsear un elemento");
        }
        catsStream.close();

        return currentCats;
    }



}
