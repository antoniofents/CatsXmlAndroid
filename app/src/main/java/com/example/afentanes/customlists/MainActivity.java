package com.example.afentanes.customlists;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private final static String CATS_KEY = "cats";
    private ArrayList <CatInfo> cats = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //check if we have previous information stored
        if (savedInstanceState != null) {
            cats=savedInstanceState.getParcelableArrayList(CATS_KEY);
        }
        setContentView(R.layout.activity_main);

        //check if we have portrait layout
        LinearLayout mainLayout= this.findViewById(R.id.main_layout);

        //if portrait layout we will instantiate custom List dinamically,
        //in landscape mode the CustomList view is added in the template
        if(mainLayout!=null){
            CustomLists customList = new CustomLists(this,getString(R.string.title));
            customList.setId(R.id.customListId);
            customList.setWeightSum(70);
            mainLayout.addView(customList,0);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){

        //check which layout is available, portrait or landscape
        LinearLayout mainLayout= this.findViewById(R.id.main_layout);
        if(mainLayout==null){
              mainLayout = (LinearLayout) this.findViewById(R.id.main_land_layout);
        }

        LinearLayout linearLayout = (LinearLayout) mainLayout.findViewById(R.id.customListId);
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
