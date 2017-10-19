package com.example.afentanes.customlists;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.afentanes.customlists.tasks.ReadCatImagesTask;


public class CatsAdapter extends ArrayAdapter <CatInfo> {
    CatsAdapter(@NonNull Context context, @NonNull CatInfo[] objects) {
        super(context, R.layout.row_definition, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(getContext());
        View customRowView = inflater.inflate(R.layout.row_definition, parent, false);
        TextView nameRow=  customRowView.findViewById(R.id.row_name) ;
        TextView descRow=  customRowView.findViewById(R.id.row_description) ;
        CatInfo cat = this.getItem(position);
        nameRow.setText(cat.id);
        descRow.setText(cat.source);

        //get, parse and render the  cat image in a separated thread to avoid stopping the main thread
        new ReadCatImagesTask(customRowView.findViewById(R.id.imageRow)).execute(cat);


        return customRowView;
    }


}
