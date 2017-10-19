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
        super(context, null, 0);
        init(context, title);
    }

    public CustomLists(Context context, AttributeSet attrs) {
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


    private void init(Context context, String titleText) {
        try {

            setOrientation(LinearLayout.VERTICAL);
            setGravity(Gravity.CENTER_VERTICAL);

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.custom_list_view, this, true);


            ListView listView =  findViewById(R.id.list_rows);


            List <CatInfo> cats = ((MainActivity )getContext()).getCatInfo();
            if(!cats.isEmpty()){
                listView.setAdapter(new CatsAdapter(getContext(), cats.toArray(new CatInfo[cats.size()])));
            }else{
                new ReadCatsTask() {

                    @Override
                    protected void onPostExecute(List<CatInfo> cats) {
                        try {
                            listView.setAdapter(new CatsAdapter(getContext(), cats.toArray(new CatInfo[cats.size()])));
                        } catch (Exception e) {
                            Log.i("Main", "error renderando adapter");
                        }

                    }
                }.execute();
            }

            TextView title = findViewById(R.id.list_title);
            title.setText(titleText);

        } catch (Exception e) {

            Log.i("error", "error parse");
            Log.i("error", e.toString());
        }


    }


}
