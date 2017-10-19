package com.example.afentanes.customlists;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private final static String CATS_KEY = "cats";
    private ArrayList <CatInfo> cats = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            cats=savedInstanceState.getParcelableArrayList(CATS_KEY);
        }
        setContentView(R.layout.activity_main);
        LinearLayout mainLayout= this.findViewById(R.id.main_layout);

        if(mainLayout!=null){
            mainLayout.addView(new CustomLists(this, "title from class"));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        LinearLayout mainLayout= this.findViewById(R.id.main_layout);
        if(mainLayout==null){
              mainLayout = (LinearLayout) this.findViewById(R.id.main_land_layout);
        }

        LinearLayout linearLayout = (LinearLayout) mainLayout.getChildAt(0);
        if(cats.isEmpty()){
            ListView cats = linearLayout.findViewById(R.id.list_rows);
            cats.getItemAtPosition(0);

            ArrayList catList= new ArrayList<CatInfo>();
            for(int i=0; i< cats.getCount(); i++){
                catList.add( cats.getItemAtPosition(i));
            }
            outState.putParcelableArrayList(CATS_KEY, catList);
        }else{
            outState.putParcelableArrayList(CATS_KEY, cats);
        }

    }

    protected ArrayList <CatInfo> getCatInfo(){
        return cats;
    }


}
