package com.example.afentanes.customlists;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.afentanes.customlists.tasks.ReadCatsTask;

import java.util.List;


public class CustomLists extends LinearLayout {


    public CustomLists(Context context, String title) {
        //this constructor is used when the component is instantiated from a java class
        super(context, null, 0);
        init(context, title);
    }

    public CustomLists(Context context, AttributeSet attrs) {
        //this constructor is called when the custom list is  created by android from a template
        super(context, attrs, 0);

        try {
            TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.CustomLists, 0, 0);
            String titleText = a.getString(R.styleable.CustomLists_labelTitle);
            a.recycle();
            init(context, titleText);
        } catch (Exception e) {
            Log.d("error", "error");

        }


    }


    //receiving context and title (title is a string that could be indicated from a class o from  a template)
    private void init(Context context, String titleText) {
        try {

            setOrientation(LinearLayout.VERTICAL);
            setGravity(Gravity.CENTER_VERTICAL);

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.custom_list_view, this, true);


            ListView listView =  findViewById(R.id.list_rows);

            //check if we have a cats list previously initialized
            List <CatInfo> cats = ((MainActivity )getContext()).getCatInfo();
            if(!cats.isEmpty()){
                //load it from our parent restored instance
                listView.setAdapter(new CatsAdapter(getContext(), cats.toArray(new CatInfo[cats.size()])));
            }else{
                //call the xml parser to get some cats
                new ReadCatsTask() {

                    @Override
                    protected void onPostExecute(List<CatInfo> cats) {
                        //as soon as its  executed, fill the listView with the results
                        try {
                            listView.setAdapter(new CatsAdapter(getContext(), cats.toArray(new CatInfo[cats.size()])));
                        } catch (Exception e) {
                            Log.i("Main", "error renderando adapter");
                        }

                    }
                }.execute();
            }
            //add the text value received
            TextView title = findViewById(R.id.list_title);
            title.setText(titleText);

        } catch (Exception e) {

            Log.i("error", "error parse");
            Log.i("error", e.toString());
        }


    }


}
